package views.Registro_Comensal_view;

import components.Button.RoundedButton;
import components.CheckBox.CheckBox;
import components.TextInput.TextInput;
import controllers.RegistroComensalController;

import javax.swing.*;
import java.awt.*;

public class RegistroComensalView extends JFrame {

    public RegistroComensalView() {
        setTitle("Registro ");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de fondo
        JPanel backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.setBackground(Color.WHITE); // Fondo blanco
        setContentPane(backgroundPanel);

        GridBagConstraints gbc = new GridBagConstraints();

        // Icono de flecha
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/assets/Iconos/FlechaIzquierda.png"));
        Image scaledBackImage = backIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JLabel backLabel = new JLabel(new ImageIcon(scaledBackImage));
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
        JLabel titleLabel = new JLabel("Registro");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 38));
        titleLabel.setForeground(Color.BLACK); // Texto negro
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.gridwidth = 2;
        mainGbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, mainGbc);

        // Configuración para los campos de texto
        mainGbc.gridwidth = 2;
        mainGbc.fill = GridBagConstraints.HORIZONTAL;
        mainGbc.weightx = 1;

        // Campos de texto
        TextInput emailInput = new TextInput("Ingrese su correo electrónico");
        emailInput.setTextPaneOpaque(false);
        mainGbc.gridy++;
        mainPanel.add(emailInput, mainGbc);

        TextInput passwordInput = new TextInput("Ingrese su contraseña");
        passwordInput.setTextPaneOpaque(false);
        mainGbc.gridy++;
        mainPanel.add(passwordInput, mainGbc);

        TextInput cedulaInput = new TextInput("Ingrese su cédula");
        cedulaInput.setTextPaneOpaque(false);
        mainGbc.gridy++;
        mainPanel.add(cedulaInput, mainGbc);

        // Restaurar gbc para otros componentes si es necesario
        mainGbc.fill = GridBagConstraints.NONE;
        mainGbc.weightx = 0;
        mainGbc.gridwidth = 1;

        // Etiqueta de rol
        JLabel rolLabel = new JLabel("Seleccione su rol dentro del comedor");
        rolLabel.setFont(new Font("Inter", Font.BOLD, 18));
        rolLabel.setForeground(Color.BLACK); // Texto negro
        mainGbc.gridy++;
        mainGbc.gridwidth = 2; // La etiqueta puede ocupar dos columnas
        mainPanel.add(rolLabel, mainGbc);

        // Checkboxes
        JPanel checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        checkBoxPanel.setOpaque(false);
        CheckBox comensalCheck = new CheckBox("Comensal", new Color(0, 102, 204));
        comensalCheck.setForeground(Color.BLACK);
        CheckBox adminCheck = new CheckBox("Administrador", new Color(0, 102, 204));
        adminCheck.setForeground(Color.BLACK);

        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(comensalCheck);
        roleGroup.add(adminCheck);

        checkBoxPanel.add(comensalCheck);
        checkBoxPanel.add(adminCheck);
        mainGbc.gridy++;
        mainPanel.add(checkBoxPanel, mainGbc);

        // Botón de registro
        RoundedButton registerButton = new RoundedButton("Registrarse", false); // false para fondo azul
        registerButton.setFont(new Font("Inter", Font.BOLD, 18));
        mainGbc.gridy++;
        mainGbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(registerButton, mainGbc);

        // Acción del botón de registro
        registerButton.addActionListener(e -> {
            String correo = emailInput.getText().trim();
            String contrasena = passwordInput.getText().trim();
            String cedula = cedulaInput.getText().trim();
            String tipo = comensalCheck.isSelected() ? "comensal" : (adminCheck.isSelected() ? "administrador" : "");
            if (correo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo correo es obligatorio.");
                return;
            }
            if (contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo contraseña es obligatorio.");
                return;
            }
            if (cedula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo cédula es obligatorio.");
                return;
            }
            if (tipo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un rol.");
                return;
            }
            RegistroComensalController.registrarComensal(correo, contrasena, cedula, tipo);
        });

        // Añadir el panel principal al panel de fondo
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0; // No dejar que el panel principal se estire verticalmente
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(mainPanel, gbc);

        // Componente fantasma para empujar el formulario hacia arriba
        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        gbc.gridy = 2;
        gbc.weighty = 1; // Darle todo el peso vertical
        backgroundPanel.add(spacer, gbc);
    }
}
