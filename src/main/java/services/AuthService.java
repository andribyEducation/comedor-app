package services;

import models.Usuario;

public class AuthService {

    // Por ahora, usaremos un usuario "quemado" para la prueba.
    // En una aplicación real, esto consultaría una base de datos.
    private final Usuario usuarioRegistrado = new Usuario("12345678", "test@ucv.ve", "password123");

    public boolean autenticar(String correo, String contraseña) {
        if (correo == null || contraseña == null) {
            return false;
        }
        // Comparamos el correo y la contraseña proporcionados con el usuario registrado.
        return correo.equals(usuarioRegistrado.getCorreo()) && contraseña.equals(usuarioRegistrado.getContraseña());
    }
}
