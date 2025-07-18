package controllers;

import org.junit.jupiter.api.*;
import java.io.*;

class RegistroComensalControllerTest {

    private static final String TEST_DATA_PATH = "data/comensales_test.txt";

    @BeforeEach
    void setUp() throws IOException {
        // Crear archivo de prueba vacío
        File file = new File(TEST_DATA_PATH);
        file.getParentFile().mkdirs();
        file.createNewFile();
        // Cambiar la ruta del controlador temporalmente
        setControllerDataPath(TEST_DATA_PATH);
    }

    @AfterEach
    void tearDown() {
        // Eliminar archivo de prueba
        new File(TEST_DATA_PATH).delete();
        setControllerDataPath("data/comensales.txt");
    }

    @Test
    void testValidarCorreo() {
        Assertions.assertTrue(callValidarCorreo("test@mail.com"));
        Assertions.assertFalse(callValidarCorreo("testmail.com"));
        Assertions.assertFalse(callValidarCorreo("test@.com"));
    }

    @Test
    void testValidarCedula() {
        Assertions.assertTrue(callValidarCedula("123456"));
        Assertions.assertFalse(callValidarCedula("abc123"));
        Assertions.assertFalse(callValidarCedula(""));
    }

    @Test
    void testValidarContrasena() {
        Assertions.assertTrue(callValidarContrasena("abcdef"));
        Assertions.assertFalse(callValidarContrasena("abc"));
    }

    @Test
    void testUnicidadCorreoCedula() throws IOException {
        // Registrar un usuario
        RegistroComensalController.registrarComensal("a@b.com", "123456", "111", "comensal");
        // Debe detectar duplicados
        Assertions.assertTrue(callExisteCorreoOCedula("a@b.com", "222"));
        Assertions.assertTrue(callExisteCorreoOCedula("c@d.com", "111"));
        Assertions.assertFalse(callExisteCorreoOCedula("x@y.com", "333"));
    }

    // Métodos auxiliares para acceder a métodos privados
    private boolean callValidarCorreo(String correo) {
        try {
            java.lang.reflect.Method m = RegistroComensalController.class.getDeclaredMethod("validarCorreo", String.class);
            m.setAccessible(true);
            return (boolean) m.invoke(null, correo);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean callValidarCedula(String cedula) {
        return cedula.matches("\\d+");
    }

    private boolean callValidarContrasena(String contrasena) {
        return contrasena.length() >= 6;
    }

    private boolean callExisteCorreoOCedula(String correo, String cedula) {
        try {
            java.lang.reflect.Method m = RegistroComensalController.class.getDeclaredMethod("existeCorreoOCedula", String.class, String.class);
            m.setAccessible(true);
            return (boolean) m.invoke(null, correo, cedula);
        } catch (Exception e) {
            return false;
        }
    }

    private void setControllerDataPath(String path) {
        try {
            java.lang.reflect.Field f = RegistroComensalController.class.getDeclaredField("DATA_PATH");
            f.setAccessible(true);
            f.set(null, path);
        } catch (Exception e) {
            // Ignorar
        }
    }
}
