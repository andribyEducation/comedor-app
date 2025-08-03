package com.ucv.services;
import org.json.JSONObject;

import java.io.*;

public class CostosService {
        private static final String RUTA_ARCHIVO = "data/costos.json";

    public JSONObject leerCostos() throws IOException {
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

    public void guardarCostos(double costoFijo, double costoVariable) throws IOException {
        ConexionService conexion = new ConexionService();
        conexion.crearDirectorioSiNoExiste("data");

        JSONObject json = new JSONObject();
        json.put("costoFijo", costoFijo);
        json.put("costoVariable", costoVariable);

        FileWriter writer = new FileWriter(RUTA_ARCHIVO);
        writer.write(json.toString(4));
        writer.close();
    }
}

