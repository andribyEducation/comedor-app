package com.ucv.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConexionService {

    public BufferedReader obtenerLectorArchivo(String ruta) throws IOException {
        return new BufferedReader(new FileReader(ruta));
    }

}
