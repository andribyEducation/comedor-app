
package com.ucv.services;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.ucv.models.Usuario;

public class AuthService {
    private static final String ADMINS_DATA_PATH = "data/admins.json";
    private static final String COMENSALES_DATA_PATH = "data/comensales.json";

    public boolean autenticar(String cedula, String contrasena, String tipo) {
        String dataPath = "administrador".equalsIgnoreCase(tipo) ? ADMINS_DATA_PATH : COMENSALES_DATA_PATH;
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

    public void cerrarSesion() {
        Usuario.setUsuarioActual(null);
    }
}
