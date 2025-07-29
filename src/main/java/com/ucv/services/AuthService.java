package com.ucv.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class AuthService {
    private static final String DATA_PATH = "data/comensales.txt";

    public String autenticarYObtenerTipo(String cedula, String contrasena) {
        File file = new File(DATA_PATH);
        if (!file.exists()) return null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length >= 4) {
                    String pass = partes[1].trim();
                    String ced = partes[2].trim();
                    String tipo = partes[3].trim();
                    if (ced.equals(cedula) && pass.equals(contrasena)) {
                        return tipo;
                    }
                }
            }
        } catch (Exception e) {
            
        }
        return null;
    }
}
