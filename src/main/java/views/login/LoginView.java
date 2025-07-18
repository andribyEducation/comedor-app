package views.login;

import javax.swing.*;
import java.awt.*;
import components.Button.RoundedButton;
import components.TextInput.TextInput;

public class LoginView extends JFrame {

    private RoundedButton loginButton;
    private JLabel backLabel;
    private TextInput cedulaInput;
    private TextInput passwordInput;

    public LoginView() {

        setTitle("Ingreso al Comedor Universitario");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de fondo
        JPanel backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.setBackground(Color.WHITE);
        setContentPane(backgroundPanel);

        GridBagConstraints gbc = new GridBagConstraints();

        // Icono de flecha
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/assets/Iconos/FlechaIzquierda.png"));
        Image scaledBackImage = backIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        backLabel = new JLabel(new ImageIcon(scaledBackImage));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(20, 20, 0, 0);
        backgroundPanel.add(backLabel, gbc);

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/assets/logos/logoucv.png"));
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(20, 0, 0, 20);
        backgroundPanel.add(logoLabel, gbc);

        // Contenedor principal para centrar los componentes
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.insets = new Insets(10, 10, 10, 10);

        // Título
        JLabel titleLabel = new JLabel("Ingresar");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 38));
        titleLabel.setForeground(Color.BLACK);

        

        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.gridwidth = 2;
        mainGbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, mainGbc);

        // Campos de texto
        mainGbc.gridwidth = 2;
        mainGbc.fill = GridBagConstraints.HORIZONTAL;
        mainGbc.weightx = 1;

        cedulaInput = new TextInput("Ingrese su cédula");
        cedulaInput.setTextPaneOpaque(false);
        cedulaInput.setPreferredSize(new Dimension(300, 70));
        mainGbc.gridy++;
        mainPanel.add(cedulaInput, mainGbc);

        passwordInput = new TextInput("Ingrese su contraseña");
        passwordInput.setTextPaneOpaque(false);
        passwordInput.setPreferredSize(new Dimension(300, 70));
        mainGbc.gridy++;
        mainPanel.add(passwordInput, mainGbc);

        // Botón de ingreso
        loginButton = new RoundedButton("Ingreso", false);
        loginButton.setFont(new Font("Inter", Font.BOLD, 18));
        mainGbc.gridy++;
        mainGbc.fill = GridBagConstraints.NONE;
        mainGbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, mainGbc);

        // Centrar el formulario en la pantalla
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1; // ¡IMPORTANTE! Esto permite centrar verticalmente
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        backgroundPanel.add(mainPanel, gbc);

        // Componente fantasma para empujar el formulario hacia arriba
        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        gbc.gridy = 2;
        gbc.weighty = 1; // Darle todo el peso vertical
        backgroundPanel.add(spacer, gbc);
    }

    public String getCedula() {
        return cedulaInput.getText();
    }

    public String getContrasena() {
        return passwordInput.getText();
    }

    public RoundedButton getLoginButton() {
        return loginButton;
    }

    public JLabel getBackLabel() {
        return backLabel;
    }
}
