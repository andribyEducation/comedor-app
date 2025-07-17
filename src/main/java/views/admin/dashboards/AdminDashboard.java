package views.admin.dashboards;

import components.Backgrounds.DarkBackground;
import components.Button.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Dashboard Administrador");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Usar DarkBackground como fondo
        setContentPane(new DarkBackground());
        setLayout(new BorderLayout());

        // Panel superior con título y logo
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Panel principal con botones
        JPanel mainPanel = createMainPanel();
        add(mainPanel, BorderLayout.CENTER);

    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false); // Hacer transparente para que se vea el fondo
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Título
        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 38));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Logo
        try {
            ImageIcon logo = new ImageIcon(getClass().getResource("/assets/logos/Logo_Universidad_Central_de_Venezuela.svg.png"));
            Image scaledLogo = logo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
            headerPanel.add(logoLabel, BorderLayout.EAST);
        } catch (Exception e) {
            System.out.println("Logo no encontrado");
        }

        return headerPanel;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false); // Hacer transparente
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] buttonTexts = {
            "Gestionar Insumos",
            "Registrar Consumo",
            "Gestionar Menú",
            "Gestionar Disponibilidad",
            "Cambiar Contraseña",
            "Reportar Problema",
            "Cerrar Sesión"
        };

        int row = 0;
        int col = 0;
        for (String text : buttonTexts) {
            RoundedButton button = new RoundedButton(text, true); // Usar RoundedButton
            button.setFont(new Font("Inter", Font.BOLD, 16));
            gbc.gridx = col;
            gbc.gridy = row;
            mainPanel.add(button, gbc);

            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }

        return mainPanel;
    }

}