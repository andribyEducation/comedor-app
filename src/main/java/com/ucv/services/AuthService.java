
package com.ucv.services;

<<<<<<< HEAD
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AuthService {
    private static final String ADMINS_DATA_PATH = "data/admins.json";
    private static final String COMENSALES_DATA_PATH = "data/comensales.json";

    public String autenticarYObtenerTipo(String cedula, String contrasena) {
        if (autenticar(ADMINS_DATA_PATH, cedula, contrasena)) {
            return "administrador";
=======
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
            
>>>>>>> 7b6030e (Conexion service)
        }

        if (autenticar(COMENSALES_DATA_PATH, cedula, contrasena)) {
            return "comensal";
        }

        return null;
    }

    private boolean autenticar(String dataPath, String cedula, String contrasena) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(dataPath)));
            JSONArray users = new JSONArray(content);

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                String userCedula = user.getString("ID");
                String userContrasena = user.getString("contrasena");

                if (userCedula.equals(cedula) && userContrasena.equals(contrasena)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
