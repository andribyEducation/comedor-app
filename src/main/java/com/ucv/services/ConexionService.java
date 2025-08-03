package com.ucv.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class ConexionService {

    public BufferedReader obtenerLectorArchivo(String ruta) throws IOException {
        File file = new File(ruta);
        if (!file.exists()) throw new IOException("Archivo no existe: " + ruta);
        return new BufferedReader(new FileReader(file));
    }

    public void escribirLineaArchivo(String ruta, String linea) throws IOException {
        FileWriter writer = new FileWriter(ruta, true);
        writer.write(linea);
        writer.close();
    }

    public void crearDirectorioSiNoExiste(String rutaDirectorio) {
        File dir = new File(rutaDirectorio);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
