package com.ucv.controllers.admin.Validation;

import com.ucv.views.admin.Validation.ValidationAdminView;
import com.ucv.views.registroView.RegistroView;
import com.ucv.controllers.register.RegisterController;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ValidationAdminController {
    private ValidationAdminView view;
    private RegistroView registroView;
    private RegisterController registerController;

    private static final String ADMIN_CODE_PATH = "data/admin_code.txt";

    public ValidationAdminController(ValidationAdminView view, RegistroView registroView, RegisterController registerController) {
        this.view = view;
        this.registroView = registroView;
        this.registerController = registerController;

        this.view.getValidarButton().addActionListener(e -> validateCode());
    }

    private void validateCode() {
        String enteredCode = view.getCredencialesInput().getText();
        String correctCode = getAdminCode();

        if (enteredCode.equals(correctCode)) {
            JOptionPane.showMessageDialog(view, "Código de administrador válido. Procediendo con el registro.");
            // Assuming RegisterController has a method to complete admin registration
            // This part needs to be handled carefully, as RegisterController's constructor takes a view
            // We might need to pass the registration details back to RegisterController
            // For now, let's just close this view and indicate success.
            view.dispose();
            // Now, we need to trigger the actual registration in RegisterController
            // This will require a new public method in RegisterController to handle this.
            registerController.completeAdminRegistration();

        } else {
            JOptionPane.showMessageDialog(view, "Código de administrador incorrecto.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getAdminCode() {
        try {
            return new String(Files.readAllBytes(Paths.get(ADMIN_CODE_PATH))).trim();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al leer el código de administrador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return ""; // Return empty string on error
        }
    }
}
