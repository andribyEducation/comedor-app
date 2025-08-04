// Nombre del archivo: AdminDashboardView.java
package com.ucv.views.admin.dashboards;

import com.ucv.components.Button.RoundedButton;
import com.ucv.components.Header.HeaderPanel;
import com.ucv.components.TextInput.TextInput;
import com.ucv.controllers.login.LoginController;
import com.ucv.views.login.LoginView;
import com.ucv.components.UserMenu.UserMenu;
import com.ucv.services.CcbService;

import javax.swing.*;
import org.json.JSONObject;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminDashboardView extends JFrame {
    private HeaderPanel headerPanel;
    private UserMenu menuUsuario;
    private final Map<String, RoundedButton> actionButtons = new HashMap<>();
    private JLabel iconoUsuario;

    public AdminDashboardView() {
        setupUI();
        createHeader();
        createBody();
    }

    private void setupUI() {
        setTitle("Dashboard de Administrador");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
    }

    private void createHeader() {
        headerPanel = new HeaderPanel(false, "Dashboard de Administrador", "Juan", true);
        add(headerPanel, BorderLayout.NORTH);

        iconoUsuario = headerPanel.getIconoUsuario();
        menuUsuario = new UserMenu(this, "Administrador: Nombre");

        headerPanel.getBackButtonLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginView view = new LoginView();
                new LoginController(view);
                view.setVisible(true);
                dispose();
            }
        });
    }

    private void createBody() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(Box.createVerticalStrut(60));

        // Botones
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 100, 0));
        buttonsPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 50, 20, 50);

        String[] options = {"Gestionar Menú", "CCB", "Gestionar Reservas"};
        String[] buttonTexts = {"Gestionar", "Calcular", "Ver Reservas"};

        for (int i = 0; i < options.length; i++) {
            gbc.gridx = i;
            buttonsPanel.add(createActionButtonPanel(options[i], buttonTexts[i]), gbc);
        }
        mainPanel.add(buttonsPanel);

        // Listener para el botón CCB
        RoundedButton ccbButton = actionButtons.get("CCB");
        if (ccbButton != null) {
            ccbButton.addActionListener(e -> new VentanaCCB().setVisible(true));
        }

        // Listener para el botón Gestionar Reservas
        RoundedButton reservasButton = actionButtons.get("Gestionar Reservas");
        if (reservasButton != null) {
            reservasButton.addActionListener(e -> {
                com.ucv.views.admin.reservas.GestionarReservasView view = new com.ucv.views.admin.reservas.GestionarReservasView();
                new com.ucv.controllers.admin.reservas.GestionarReservasController(view);
                view.setVisible(true);
            });
        }
    }


    private JPanel createActionButtonPanel(String option, String buttonText) {
        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setOpaque(false);

        JLabel lblOption = new JLabel(option, SwingConstants.CENTER);
        lblOption.setFont(new Font("Inter", Font.BOLD, 30));
        panel.add(lblOption, BorderLayout.CENTER);

        RoundedButton actionButton = new RoundedButton(buttonText);
        actionButton.setFont(new Font("Inter", Font.BOLD, 22));
        actionButton.setPreferredSize(new Dimension(300, 50));
        actionButtons.put(option, actionButton);
        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonContainer.setOpaque(false);
        buttonContainer.add(actionButton);
        panel.add(buttonContainer, BorderLayout.SOUTH);

        return panel;
    }

    public JLabel getIconoUsuario() {
        return iconoUsuario;
    }

    public UserMenu getMenuUsuario() {
        return menuUsuario;
    }

    public Map<String, RoundedButton> getActionButtons() {
        return actionButtons;
    }

    public void display() {
        setVisible(true);
    }

    // VENTANA CCB INTERNA
    class VentanaCCB extends JFrame {
        private TextInput inputFijos;
        private TextInput inputVariables;
        private TextInput inputNB;
        private TextInput inputMerma;
        private final CcbService ccbService;

        public VentanaCCB() {
            this.ccbService = new CcbService();
            setTitle("Costo Cubierto por Bandeja (CCB)");
            setSize(1000, 850);
            setLocationRelativeTo(AdminDashboardView.this);
            setLayout(new GridBagLayout());
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            getContentPane().setBackground(Color.WHITE);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;

            // Título
            JLabel lblTitle = new JLabel("Costo Cubierto por Bandeja (CCB)");
            lblTitle.setFont(new Font("Inter", Font.BOLD, 24));
            add(lblTitle, gbc);

            // Secciones de Costos
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.WEST;

            inputFijos = new TextInput("Ingrese costo fijo");
            gbc.gridy++;
            add(createCostSection("Costos Fijos", inputFijos), gbc);

            inputVariables = new TextInput("Ingrese costo variable");
            gbc.gridy++;
            add(createCostSection("Costos Variables", inputVariables), gbc);

            inputNB = new TextInput("Ingrese número de bandejas del periodo");
            gbc.gridy++;
            add(createCostSection("Número de Bandejas (NB)", inputNB), gbc);

            inputMerma = new TextInput("Ingrese porcentaje de merma");
            gbc.gridy++;
            add(createCostSection("% Merma", inputMerma), gbc);


            // Botón Calcular
            gbc.gridy++;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            RoundedButton btnCalcular = new RoundedButton("Calcular");
            btnCalcular.setPreferredSize(new Dimension(150, 45));
            add(btnCalcular, gbc);

            // Cargar datos existentes
            loadCcb();

            // Listener para el botón de calcular
            btnCalcular.addActionListener(e -> calcularYGuardarCCB());
        }

        private void calcularYGuardarCCB() {
            try {
                double costoFijo = Double.parseDouble(inputFijos.getText());
                double costoVariable = Double.parseDouble(inputVariables.getText());
                int nb = Integer.parseInt(inputNB.getText());
                double merma = Double.parseDouble(inputMerma.getText());

                if (nb <= 0) {
                    JOptionPane.showMessageDialog(this, "El número de bandejas debe ser mayor a cero.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (merma < 0) {
                    JOptionPane.showMessageDialog(this, "El porcentaje de merma no puede ser negativo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double ccb = ((costoFijo + costoVariable) / nb) * (1 + merma / 100.0);

                JSONObject datos = new JSONObject();
                datos.put("costoFijo", costoFijo);
                datos.put("costoVariable", costoVariable);
                datos.put("nb", nb);
                datos.put("merma", merma);
                datos.put("ccb", ccb);

                ccbService.guardarCcb(datos);

                String mensaje = String.format("El CCB calculado es: %.2f\nLos valores han sido guardados.", ccb);
                JOptionPane.showMessageDialog(this, mensaje, "Cálculo Exitoso", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar los datos de CCB: " + ex.getMessage(), "Error de Guardado", JOptionPane.ERROR_MESSAGE);
            }
        }

        private JPanel createCostSection(String title, TextInput input) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setOpaque(false);

            JLabel label = new JLabel(title);
            label.setFont(new Font("Inter", Font.BOLD, 20));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(label);
            panel.add(Box.createVerticalStrut(10));

            input.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(input);

            return panel;
        }

        private void loadCcb() {
            try {
                JSONObject datos = ccbService.leerCcb();
                if (datos.has("costoFijo")) {
                    inputFijos.setText(String.valueOf(datos.getDouble("costoFijo")));
                }
                if (datos.has("costoVariable")) {
                    inputVariables.setText(String.valueOf(datos.getDouble("costoVariable")));
                }
                if (datos.has("nb")) {
                    inputNB.setText(String.valueOf(datos.getInt("nb")));
                }
                if (datos.has("merma")) {
                    inputMerma.setText(String.valueOf(datos.getDouble("merma")));
                }
            } catch (IOException ex) {
                System.err.println("No se pudo cargar ccb.json: " + ex.getMessage());
            }
        }
    }
}

