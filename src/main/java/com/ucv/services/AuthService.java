
package com.ucv.services;

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
