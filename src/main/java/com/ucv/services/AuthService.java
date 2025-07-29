package com.ucv.services;

import java.io.BufferedReader;

public class AuthService {
    private static final String DATA_PATH = "data/comensales.txt";
    private ConexionService conexionService = new ConexionService();

    public String autenticarYObtenerTipo(String cedula, String contrasena) {
        try (BufferedReader reader = conexionService.obtenerLectorArchivo(DATA_PATH)) {
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
