package com.ucv.services;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;

public class CcbService {
    private static final String RUTA_ARCHIVO = "data/ccb.json";
    private static final String MENUS_PATH = "data/menus.json";

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

        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            writer.write(datos.toString(4));
        }
    }

    public void actualizarPreciosMenu(double ccb) throws IOException {
        File menusFile = new File(MENUS_PATH);
        JSONObject menusData;

        if (menusFile.exists() && menusFile.length() > 0) {
            String content = new String(Files.readAllBytes(Paths.get(MENUS_PATH)));
            menusData = new JSONObject(content);
        } else {
            menusData = new JSONObject();
        }

        JSONObject precios = new JSONObject();
        precios.put("estudiante", ccb * 0.80);
        precios.put("profesor", ccb * 0.30);
        precios.put("administrativo", ccb * 0.10);
        precios.put("base", ccb);

        menusData.put("precios", precios);

        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        for (String dia : dias) {
            if (menusData.has(dia)) {
                JSONObject diaObj = menusData.getJSONObject(dia);
                if (diaObj.has("almuerzo")) {
                    diaObj.getJSONObject("almuerzo").remove("precio");
                }
                if (diaObj.has("desayuno")) {
                    diaObj.getJSONObject("desayuno").remove("precio");
                }
            }
        }

        try (FileWriter writer = new FileWriter(MENUS_PATH)) {
            writer.write(menusData.toString(4));
        }
    }
}