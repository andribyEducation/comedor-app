package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;

public class RegistroComensalController {
    private static final String DATA_PATH = "data/comensales.txt";

    public static void registrarComensal(String correo, String contrasena, String cedula, String tipo) {
        // Validaciones
        if (!validarCorreo(correo)) {
            JOptionPane.showMessageDialog(null, "El correo electrónico no es válido.");
            return;
        }
        if (contrasena.length() < 6) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 6 caracteres.");
            return;
        }
        if (!cedula.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "La cédula debe ser numérica.");
            return;
        }
        if (existeCorreoOCedula(correo, cedula)) {
            JOptionPane.showMessageDialog(null, "El correo o la cédula ya están registrados.");
            return;
        }

        // Saldo inicial
        String saldo = "0";
        // Formato: correo|contrasena|cedula|tipo|saldo
        String registro = correo + "|" + contrasena + "|" + cedula + "|" + tipo + "|" + saldo + "\n";
        try {
            // Crear carpeta data si no existe
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }
            FileWriter writer = new FileWriter(DATA_PATH, true);
            writer.write(registro);
            writer.close();
            JOptionPane.showMessageDialog(null, "Registro exitoso.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar: " + e.getMessage());
        }
    }

    private static boolean validarCorreo(String correo) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, correo);
    }

    private static boolean existeCorreoOCedula(String correo, String cedula) {
        File file = new File(DATA_PATH);
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
}
