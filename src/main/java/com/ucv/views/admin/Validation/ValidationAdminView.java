package com.ucv.views.admin.Validation;

import com.ucv.components.Button.RoundedButton;
import com.ucv.components.TextInput.TextInput;

import javax.swing.*;
import java.awt.*;

public class ValidationAdminView extends JFrame {

    private TextInput credencialesInput;
    private RoundedButton validarButton;

    public ValidationAdminView() {
        setTitle("Validación de Administrador");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Usar un layout que permita centrar los componentes verticalmente
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
       

        // Espaciador superior para empujar el contenido hacia el centro
        add(Box.createVerticalGlue());

        // 1. Título "Validación"
        JLabel titulo = new JLabel("Validación");
        titulo.setFont(new Font("Inter", Font.BOLD, 38));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titulo);

        add(Box.createVerticalStrut(50)); // Espacio vertical

        // 2. TextInput para las credenciales
        credencialesInput = new TextInput("Ingrese Credenciales");
        credencialesInput.setMaximumSize(new Dimension(400, 70)); // Ajustar tamaño máximo
        credencialesInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(credencialesInput);

        add(Box.createVerticalStrut(30)); // Espacio vertical

        // 3. Botón de validación
        validarButton = new RoundedButton("Validar");
        validarButton.setFont(new Font("Inter", Font.BOLD, 20));
        validarButton.setPreferredSize(new Dimension(400, 50));
        validarButton.setMaximumSize(new Dimension(400, 50));
        validarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(validarButton);

        // Espaciador inferior para empujar el contenido hacia el centro
        add(Box.createVerticalGlue());
    }

    // --- Getters para acceder a los componentes desde un controlador ---

    public TextInput getCredencialesInput() {
        return credencialesInput;
    }

    public RoundedButton getValidarButton() {
        return validarButton;
    }
}
