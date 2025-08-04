package com.ucv.services;
import org.json.JSONObject;

import java.io.*;

public class CcbService {
    private static final String RUTA_ARCHIVO = "data/ccb.json";

    public JSONObject leerCcb() throws IOException {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            return new JSONObject(); 
        }

        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        StringBuilder sb = new StringBuilder();
        String linea;
        while ((linea = reader.readLine()) != null) {
            sb.append(linea);
        }
        reader.close();

        return new JSONObject(sb.toString());
    }

    public void guardarCcb(JSONObject datos) throws IOException {
        ConexionService conexion = new ConexionService();
        conexion.crearDirectorioSiNoExiste("data");

        FileWriter writer = new FileWriter(RUTA_ARCHIVO);
        writer.write(datos.toString(4));
        writer.close();
    }
}
