// Nombre del archivo: AdminDashboardView.java
package com.ucv.views.admin.dashboards;

import com.ucv.components.Button.RoundedButton;
import com.ucv.components.Header.HeaderPanel;
import com.ucv.components.TextInput.TextInput;
import com.ucv.controllers.login.LoginController;
import com.ucv.views.login.LoginView;
import com.ucv.components.UserMenu.UserMenu;
import com.ucv.services.CostosService;

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
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(Box.createVerticalStrut(60));

        // Botones
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 100, 0));
        buttonsPanel.setOpaque(false);

        String[] options = {"Gestionar Menú", "CCB", "Gestionar Reservas"};
        String[] buttonTexts = {"Gestionar", "Calcular", "Ver Reservas"};

        for (int i = 0; i < options.length; i++) {
            buttonsPanel.add(createActionButtonPanel(options[i], buttonTexts[i]));
        }
        mainPanel.add(buttonsPanel);

        // Listener para el botón CCB
        RoundedButton ccbButton = actionButtons.get("CCB");
        if (ccbButton != null) {
            ccbButton.addActionListener(e -> new VentanaCCB());
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
        JPanel panel = new JPanel(new BorderLayout(0, 0));
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
        public VentanaCCB() {
            setTitle("Costo Cubierto por Bandeja (CCB)");
            setSize(700, 600);
            setLocationRelativeTo(AdminDashboardView.this);
            setLayout(new BorderLayout());
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            getContentPane().setBackground(Color.WHITE);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
            mainPanel.setBackground(Color.WHITE);
            add(mainPanel, BorderLayout.CENTER);

            // Título
            JLabel lblTitle = new JLabel("Costo Cubierto por Bandeja (CCB)");
            lblTitle.setFont(new Font("Inter", Font.BOLD, 24));
            lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(lblTitle);
            mainPanel.add(Box.createVerticalStrut(30));

            // Sección de Costos Fijos
            mainPanel.add(createCostSection("Costos Fijos", "Ingrese costo fijo"));
            mainPanel.add(Box.createVerticalStrut(30));

            // Sección de Costos Variables
            mainPanel.add(createCostSection("Costos Variables", "Ingrese costo variable"));
            mainPanel.add(Box.createVerticalStrut(40));

            // Botón Calcular
            RoundedButton btnCalcular = new RoundedButton("Calcular");
            btnCalcular.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCalcular.setPreferredSize(new Dimension(150, 45));
            mainPanel.add(btnCalcular);

            // Cargar datos existentes
            TextInput inputFijos = (TextInput) ((JPanel) mainPanel.getComponent(2)).getComponent(2);
            TextInput inputVariables = (TextInput) ((JPanel) mainPanel.getComponent(4)).getComponent(2);
            loadCostos(inputFijos, inputVariables);

            // Action Listener para el botón
            btnCalcular.addActionListener(e -> {
                try {
                    double costoFijo = Double.parseDouble(inputFijos.getText());
                    double costoVariable = Double.parseDouble(inputVariables.getText());
                    double ccb = costoFijo + costoVariable;

                    CostosService serviceJson = new CostosService();
                    serviceJson.guardarCostos(costoFijo, costoVariable);

                    JOptionPane.showMessageDialog(this, "CCB calculado: " + ccb + "\nCostos guardados en JSON.");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor ingresa valores numéricos válidos.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + ex.getMessage());
                }
            });

            setVisible(true);
        }

        private JPanel createCostSection(String title, String placeholder) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setOpaque(false);

            JLabel label = new JLabel(title);
            label.setFont(new Font("Inter", Font.BOLD, 20));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(label);
            panel.add(Box.createVerticalStrut(10));

            TextInput input = new TextInput(placeholder);
            input.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(input);

            return panel;
        }

        private void loadCostos(TextInput inputFijos, TextInput inputVariables) {
            try {
                CostosService service = new CostosService();
                JSONObject datos = service.leerCostos();
                if (datos.has("costoFijo")) {
                    inputFijos.setText(String.valueOf(datos.getDouble("costoFijo")));
                }
                if (datos.has("costoVariable")) {
                    inputVariables.setText(String.valueOf(datos.getDouble("costoVariable")));
                }
            } catch (IOException ex) {
                System.err.println("No se pudo cargar costos.json: " + ex.getMessage());
            }
        }
    }
}
