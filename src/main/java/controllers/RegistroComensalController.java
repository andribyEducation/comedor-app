package controllers;

import views.registroComensalView.RegistroComensalView;
import views.registroComensalView.RegistroExitosoView;
import views.home.Home;
import controllers.home.HomeController;
import controllers.registroExitoso.RegistroExitosoController;

import javax.swing.*;
import java.io.*;

public class RegistroComensalController {
    private String dataPath;
    private RegistroComensalView view;

    public RegistroComensalController(RegistroComensalView view) {
        this.view = view;
        this.dataPath = "data/comensales.txt";

        // Listener para botón de registro
        this.view.getRegisterButton().addActionListener(e -> {
            String correo = view.getCorreo();
            String contrasena = view.getContrasena();
            String cedula = view.getCedula();
            String tipo = view.getTipo();

            if (correo.isEmpty()) {
                JOptionPane.showMessageDialog(view, "El campo correo es obligatorio.");
                return;
            }
            if (!validarCorreo(correo)) {
                JOptionPane.showMessageDialog(view, "El correo electrónico no es válido.");
                return;
            }
            if (contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(view, "El campo contraseña es obligatorio.");
                return;
            }
            if (contrasena.length() < 6) {
                JOptionPane.showMessageDialog(view, "La contraseña debe tener al menos 6 caracteres.");
                return;
            }
            if (cedula.isEmpty()) {
                JOptionPane.showMessageDialog(view, "El campo cédula es obligatorio.");
                return;
            }
            if (!cedula.matches("\\d+")) {
                JOptionPane.showMessageDialog(view, "La cédula debe ser numérica.");
                return;
            }
            if (tipo.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Debe seleccionar un rol.");
                return;
            }
            if (existeCorreoOCedula(correo, cedula)) {
                JOptionPane.showMessageDialog(view, "El correo o la cédula ya están registrados.");
                return;
            }

            String saldo = "0";
            String registro = correo + "|" + contrasena + "|" + cedula + "|" + tipo + "|" + saldo + "\n";
            try {
                File dataDir = new File("data");
                if (!dataDir.exists()) {
                    dataDir.mkdirs();
                }
                FileWriter writer = new FileWriter(dataPath, true);
                writer.write(registro);
                writer.close();
                view.setVisible(false);
                RegistroExitosoView registroExitosoView = new RegistroExitosoView();
                new RegistroExitosoController(registroExitosoView);
                registroExitosoView.setVisible(true);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view, "Error al registrar: " + ex.getMessage());
            }
        });

        // Listener para volver (icono de flecha)
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

    // Constructor para test
    public RegistroComensalController(String dataPath) {
        this.dataPath = dataPath;
    }

    public boolean validarCorreo(String correo) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return java.util.regex.Pattern.matches(regex, correo);
    }

    public boolean validarCedula(String cedula) {
        return cedula.matches("\\d+");
    }

    public boolean validarContrasena(String contrasena) {
        return contrasena.length() >= 6;
    }

    public boolean existeCorreoOCedula(String correo, String cedula) {
        File file = new File(dataPath);
        if (!file.exists()) return false;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length >= 3) {
                    if (partes[0].equalsIgnoreCase(correo) || partes[2].equals(cedula)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            // Si hay error leyendo, no bloquea el registro
        }
        return false;
    }

    public void registrarComensal(String correo, String contrasena, String cedula, String tipo) {
        String saldo = "0";
        String registro = correo + "|" + contrasena + "|" + cedula + "|" + tipo + "|" + saldo + "\n";
        try {
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }
            FileWriter writer = new FileWriter(dataPath, true);
            writer.write(registro);
            writer.close();
        } catch (IOException ex) {
            // Manejo simple para test
        }
    }
}
