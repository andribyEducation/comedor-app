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

    private JLabel iconoUsuario;
    private UserMenu menuUsuario; // Cambia el tipo aquí
    // Elimina los JMenuItem individuales
    private final Map<String, RoundedButton> actionButtons = new HashMap<>();

    public AdminDashboardView() {
        setupUI();
        createHeader();
        createBody();

        // Mueve esto después de createBody(), cuando el botón ya existe en el mapa
        RoundedButton ccbButton = actionButtons.get("CCB");
        if (ccbButton != null) {
            ccbButton.addActionListener(e -> {
                new VentanaCCB();
            });
        }
    }

    private void setupUI() {
        setTitle("Dashboard de Administrador");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void createHeader() {
        headerPanel = new HeaderPanel(false, "Dashboard de Administrador", "Juan", true);
        add(headerPanel, BorderLayout.NORTH);

        headerPanel.getIconoUsuario().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = -menuUsuario.getPreferredSize().width + headerPanel.getIconoUsuario().getWidth();
                int y = headerPanel.getIconoUsuario().getHeight();
                menuUsuario.show(headerPanel.getIconoUsuario(), x, y);
            }
        });

        headerPanel.getBackButtonLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginView view=new LoginView();
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
        ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/assets/logos/logoucv.png"));
        Image imagenEscalada = iconoLogo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        headerPanel.add(new JLabel(new ImageIcon(imagenEscalada)));

        iconoUsuario = new JLabel();
        iconoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/assets/logos/usericon.png"));
        Image iconoEscalado = iconoOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        iconoUsuario.setIcon(new ImageIcon(iconoEscalado));
        headerPanel.add(iconoUsuario);

        // Usa el componente UserMenu
        menuUsuario = new UserMenu(this, "Administrador: Nombre");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(20, 0, 0, 20);
        add(headerPanel, BorderLayout.NORTH);
    }

    private void createTitle() {
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Dashboard de Administrador");
        titulo.setFont(new Font("Inter", Font.BOLD, 48));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.add(titulo);

        JPanel linea = new JPanel();
        linea.setBackground(new Color(255, 204, 0));
        linea.setMaximumSize(new Dimension(430, 4));
        linea.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.add(linea);

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 60, 60));
        buttonsPanel.setBackground(Color.WHITE);

        String[] options = {
                "Gestionar Menú",
                "CCB"
        };

        String[] buttonTexts = {
                "Gestionar",
                "Calcular"
        };

        for (int i = 0; i < options.length; i++) {
            buttonsPanel.add(createActionButtonPanel(options[i], buttonTexts[i]));
        }

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 100, 0, 100);
    }

    private JPanel createActionButtonPanel(String option, String buttonText) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);

        JLabel lblOption = new JLabel(option, SwingConstants.CENTER);
        lblOption.setFont(new Font("Inter", Font.BOLD, 30));
        panel.add(lblOption, BorderLayout.CENTER);

        RoundedButton actionButton = new RoundedButton(buttonText);
        actionButton.setFont(new Font("Inter", Font.BOLD, 22));
        actionButton.setPreferredSize(new Dimension(300, 50));

        actionButtons.put(option, actionButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(actionButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    public JMenuItem getCambiarContrasenaItem() {
        return headerPanel.getCambiarContrasenaItem();
    }

    public JMenuItem getReportarProblemaItem() {
        return headerPanel.getReportarProblemaItem();
    }

    public HeaderPanel getHeaderPanel() {
        return headerPanel;
    }


    public JLabel getIconoUsuario() { return iconoUsuario; }
    public UserMenu getMenuUsuario() { return (UserMenu) menuUsuario; }
    public Map<String, RoundedButton> getActionButtons() { return actionButtons; }

    public void display() {
        setVisible(true);
    }

    // VENTANA CCB INTERNA
    class VentanaCCB extends JFrame {
        public VentanaCCB() {
            setTitle("Costo Cubierto por Bandeja (CCB)");
            setSize(700, 600);
            setLocationRelativeTo(null);
            setLayout(new GridBagLayout());
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            getContentPane().setBackground(Color.WHITE);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            mainPanel.setBackground(Color.WHITE);

            JLabel lblFijos = new JLabel("Costos Fijos:");
            lblFijos.setFont(new Font("Inter", Font.BOLD, 20));
            lblFijos.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(lblFijos);

            JLabel descFijos = new JLabel("Aquí se mostrará la información de costos fijos.");
            descFijos.setFont(new Font("Inter", Font.PLAIN, 14));
            descFijos.setForeground(Color.GRAY);
            descFijos.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(descFijos);
            mainPanel.add(Box.createVerticalStrut(10));

            TextInput inputFijos = new TextInput("Ingrese costo fijo");
            mainPanel.add(inputFijos);
            mainPanel.add(Box.createVerticalStrut(30));

            JLabel lblVariables = new JLabel("Costos Variables:");
            lblVariables.setFont(new Font("Inter", Font.BOLD, 20));
            lblVariables.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(lblVariables);

            JLabel descVariables = new JLabel("Aquí se mostrará la información de costos variables.");
            descVariables.setFont(new Font("Inter", Font.PLAIN, 14));
            descVariables.setForeground(Color.GRAY);
            descVariables.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(descVariables);
            mainPanel.add(Box.createVerticalStrut(10));

            TextInput inputVariables = new TextInput("Ingrese costo variable");
            mainPanel.add(inputVariables);
            mainPanel.add(Box.createVerticalStrut(40));

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

            RoundedButton btnCalcular = new RoundedButton("Calcular");
            btnCalcular.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCalcular.setPreferredSize(new Dimension(150, 45));
            mainPanel.add(btnCalcular);

            btnCalcular.addActionListener(e -> {
            try {
                double costoFijo = Double.parseDouble(inputFijos.getText());
                double costoVariable = Double.parseDouble(inputVariables.getText());
                double ccb = costoFijo + costoVariable;

                // Guarda los costos ingresados
                CostosService serviceJson = new CostosService();
                serviceJson.guardarCostos(costoFijo, costoVariable);

            JOptionPane.showMessageDialog(this, "CCB calculado: " + ccb + "\nCostos guardados en JSON.");

            } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa valores numéricos válidos.");
            } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + ex.getMessage());
            }
        });

            add(mainPanel);
            setVisible(true);
        }

    }
}