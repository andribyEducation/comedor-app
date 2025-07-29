package com.ucv.views.registroView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import com.ucv.components.Button.RoundedButton; // Importar el componente RoundedButton

public class RegistroExitosoView extends JFrame {

    private Font interBold28;
    private Font interRegular18;
    private Font interBold18;
    private RoundedButton ingresarButton; // Exponer el botón "Ingresar"

    public RegistroExitosoView() {
        setTitle("Registro Exitoso");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Load custom fonts
        try {
            interBold28 = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Inter_28pt-Bold.ttf")).deriveFont(36f);
            interRegular18 = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Inter_18pt-Regular.ttf")).deriveFont(20f);
            interBold18 = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Inter_18pt-Bold.ttf")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(interBold28);
            ge.registerFont(interRegular18);
            ge.registerFont(interBold18);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Fallback to default fonts if custom fonts cannot be loaded
            interBold28 = new Font("Inter", Font.BOLD, 36);
            interRegular18 = new Font("Inter", Font.PLAIN, 20);
            interBold18 = new Font("Inter", Font.BOLD, 24);
        }

        // Panel principal con fondo blanco
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Título "Registro Exitoso" en negritas
        JLabel titleLabel = new JLabel("Registro Exitoso");
        titleLabel.setFont(interBold28);
        mainPanel.add(titleLabel, gbc);

        // Icono de check
        gbc.gridy++;
        ImageIcon checkIcon = new ImageIcon("src/main/resources/assets/Iconos/check.png");
        JLabel checkLabel = new JLabel(checkIcon);
        mainPanel.add(checkLabel, gbc);

        // Mensaje "Hemos registrado su usuario exitosamente"
        gbc.gridy++;
        JLabel messageLabel = new JLabel("Hemos registrado su usuario exitosamente");
        messageLabel.setFont(interRegular18);
        mainPanel.add(messageLabel, gbc);

        // Botón "Ingresar"
        gbc.gridy++;
        ingresarButton = new RoundedButton("Ingresar", false); // Usar RoundedButton, false para fondo azul
        ingresarButton.setBackground(new Color(52, 152, 219)); // Azul
        ingresarButton.setForeground(Color.WHITE);
        ingresarButton.setFont(interBold18); // Establecer la fuente
        mainPanel.add(ingresarButton, gbc);

        // Logo UCV en la esquina superior derecha
        ImageIcon logoIcon = new ImageIcon("src/main/resources/assets/logos/logoucv.png");
        Image image = logoIcon.getImage(); // Obtener la imagen
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // Redimensionar a 50x50
        logoIcon = new ImageIcon(newimg);  // Crear un nuevo ImageIcon con la imagen redimensionada
        JLabel logoLabel = new JLabel(logoIcon);
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoPanel.setBackground(Color.WHITE);
        logoPanel.add(logoLabel);
        add(logoPanel, BorderLayout.NORTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    public RoundedButton getIngresarButton() {
        return ingresarButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegistroExitosoView().setVisible(true);
        });
    }
}