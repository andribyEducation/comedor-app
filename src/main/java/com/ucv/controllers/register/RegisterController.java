package com.ucv.controllers.register;

import com.ucv.views.registroView.RegistroView;
import com.ucv.views.admin.Validation.ValidationAdminView;
import com.ucv.views.home.Home;
import com.ucv.controllers.home.HomeController;
import com.ucv.controllers.registroExitoso.RegistroExitosoController;
import com.ucv.views.registroView.RegistroExitosoView;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RegisterController {
    private final String comensalesDataPath = "data/comensales.json";
    private final String adminsDataPath = "data/admins.json";
    private RegistroView view;
    private String currentCorreo;
    private String currentContrasena;
    private String currentCedula;
    private String currentTipo;

    public RegisterController(RegistroView view) {
        this.view = view;

        this.view.getRegisterButton().addActionListener(e -> {
            currentCorreo = view.getCorreo();
            currentContrasena = view.getContrasena();
            currentCedula = view.getCedula();
            currentTipo = view.getTipo();

            if (!validarCampos(currentCorreo, currentContrasena, currentCedula, currentTipo)) {
                return;
            }

            if (existeCorreoOCedula(currentCorreo, currentCedula)) {
                JOptionPane.showMessageDialog(view, "El correo o la cédula ya están registrados.");
                return;
            }

            if (currentTipo.equals("administrador")) {
                ValidationAdminView validationView = new ValidationAdminView();
                new com.ucv.controllers.admin.Validation.ValidationAdminController(validationView, view, this);
                validationView.setVisible(true);
                view.setVisible(false);
            } else {
                registrarUsuario(currentCorreo, currentContrasena, currentCedula, currentTipo);
            }
        });

        this.view.getBackLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Home homeView = new Home();
                new HomeController(homeView);
                view.setVisible(false);
                homeView.setVisible(true);
            }
        });
    }

    private boolean validarCampos(String correo, String contrasena, String cedula, String tipo) {
        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(view, "El campo correo es obligatorio.");
            return false;
        }
        if (!validarCorreo(correo)) {
            JOptionPane.showMessageDialog(view, "El correo electrónico no es válido.");
            return false;
        }
        if (contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(view, "El campo contraseña es obligatorio.");
            return false;
        }
        if (contrasena.length() < 6) {
            JOptionPane.showMessageDialog(view, "La contraseña debe tener al menos 6 caracteres.");
            return false;
        }
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(view, "El campo cédula es obligatorio.");
            return false;
        }
        if (!cedula.matches("\\d+")) {
            JOptionPane.showMessageDialog(view, "La cédula debe ser numérica.");
            return false;
        }
        if (cedula.length() < 6 || cedula.length() > 9) {
            JOptionPane.showMessageDialog(view, "La cédula debe tener entre 6 y 9 dígitos.");
            return false;
        }
        if (tipo.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Debe seleccionar un rol.");
            return false;
        }
        return true;
    }

    private void registrarUsuario(String correo, String contrasena, String cedula, String tipo) {
        String dataPath = tipo.equals("comensal") ? comensalesDataPath : adminsDataPath;
        JSONObject nuevoUsuario = new JSONObject();
        nuevoUsuario.put("ID", cedula);
        nuevoUsuario.put("contrasena", contrasena);
        nuevoUsuario.put("correo", correo);
        if (tipo.equals("comensal")) {
            nuevoUsuario.put("saldo", 0);
        }

        JSONArray usuarios;
        try {
            if (Files.exists(Paths.get(dataPath))) {
                String content = new String(Files.readAllBytes(Paths.get(dataPath)));
                if (!content.trim().isEmpty()) {
                    usuarios = new JSONArray(content);
                } else {
                    usuarios = new JSONArray();
                }
            } else {
                usuarios = new JSONArray();
            }

            usuarios.put(nuevoUsuario);

            try (FileWriter writer = new FileWriter(dataPath)) {
                writer.write(usuarios.toString(4));
            }

            view.setVisible(false);
            RegistroExitosoView registroExitosoView = new RegistroExitosoView();
            new RegistroExitosoController(registroExitosoView, view);
            registroExitosoView.setVisible(true);
        } catch (IOException | org.json.JSONException ex) {
            JOptionPane.showMessageDialog(view, "Error al registrar el usuario: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean validarCorreo(String correo) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return java.util.regex.Pattern.matches(regex, correo);
    }

    private boolean existeCorreoOCedula(String correo, String cedula) {
        return checkExistence(comensalesDataPath, correo, cedula) ||
               checkExistence(adminsDataPath, correo, cedula);
    }

    private boolean checkExistence(String dataPath, String correo, String cedula) {
        try {
            if (!Files.exists(Paths.get(dataPath))) {
                return false;
            }
            String content = new String(Files.readAllBytes(Paths.get(dataPath)));
            if (content.trim().isEmpty()) {
                return false;
            }
            JSONArray users = new JSONArray(content);

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                if (user.has("correo") && user.has("ID") &&
                    (user.getString("correo").equalsIgnoreCase(correo) || user.getString("ID").equals(cedula))) {
                    return true;
                }
            }
        } catch (IOException | org.json.JSONException e) {
            System.err.println("Error al verificar la existencia en " + dataPath + ": " + e.getMessage());
        }
        return false;
    }

    public void completeAdminRegistration() {
        registrarUsuario(currentCorreo, currentContrasena, currentCedula, currentTipo);
    }
}