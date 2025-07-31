// Nombre del archivo: AdminDashboardView.java
package com.ucv.views.admin.dashboards;

import com.ucv.components.Button.RoundedButton;
import com.ucv.components.Header.HeaderPanel;
import com.ucv.components.TextInput.TextInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class AdminDashboardView extends JFrame {
    private HeaderPanel headerPanel;
    private JPopupMenu menuUsuario;
    private final Map<String, RoundedButton> actionButtons = new HashMap<>();

    public AdminDashboardView() {
        setupUI();
        createHeader();
        createBody();

        // Acción para botón CCB
        actionButtons.get("CCB").addActionListener(e -> {
            new VentanaCCB();
        });
    }

    private void setupUI() {
        setTitle("Dashboard de Administrador");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void createHeader() {
        headerPanel = new HeaderPanel(false, "Dashboard de Administrador", "Juan");
        add(headerPanel, BorderLayout.NORTH);

        headerPanel.getIconoUsuario().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = -menuUsuario.getPreferredSize().width + headerPanel.getIconoUsuario().getWidth();
                int y = headerPanel.getIconoUsuario().getHeight();
                menuUsuario.show(headerPanel.getIconoUsuario(), x, y);
            }
        });
    }

    private void createBody() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 2, 60, 60));
        buttonsPanel.setBackground(Color.WHITE);

        String[] options = {
                "Gestionar insumos",
                "Registrar consumo",
                "Gestionar Menú",
                "Gestionar disponibilidad",
                "CCB"
        };

        String[] buttonTexts = {
                "Consultar",
                "Consultar",
                "Consultar",
                "Consultar",
                "Calcular"
        };

        for (int i = 0; i < options.length; i++) {
            buttonsPanel.add(createActionButtonPanel(options[i], buttonTexts[i]));
        }

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 100, 0, 100);
        mainPanel.add(buttonsPanel, gbc);
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

            RoundedButton btnCalcular = new RoundedButton("Calcular");
            btnCalcular.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCalcular.setPreferredSize(new Dimension(150, 45));
            mainPanel.add(btnCalcular);

            btnCalcular.addActionListener(e -> {
                String fijo = inputFijos.getText();
                String variable = inputVariables.getText();
                JOptionPane.showMessageDialog(this,
                        "Lógica aún no implementada. Fijo: " + fijo + ", Variable: " + variable);
            });

            add(mainPanel);
            setVisible(true);
        }

    }

    public JLabel getIconoUsuario() {
        return headerPanel.getIconoUsuario();

    }

    public JPopupMenu getMenuUsuario() {
        return menuUsuario;
    }
}
