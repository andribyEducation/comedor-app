package controllers.register;

import controllers.register.RegisterController;

import org.junit.jupiter.api.*;
import java.io.*;

class RegisterControllerTest {

    private static final String TEST_DATA_PATH = "data/comensales_test.txt";
    private RegistroComensalController controller;

    @BeforeEach
    void setUp() throws IOException {
        // Crear archivo de prueba vacío
        File file = new File(TEST_DATA_PATH);
        file.getParentFile().mkdirs();
        file.createNewFile();
        // Instanciar el controlador con ruta de test
        controller = new RegisterController(TEST_DATA_PATH);
    }

    @AfterEach
    void tearDown() {
        new File(TEST_DATA_PATH).delete();
    }

    @Test
    void testValidarCorreo() {
        Assertions.assertTrue(controller.validarCorreo("test@mail.com"));
        Assertions.assertFalse(controller.validarCorreo("testmail.com"));
        Assertions.assertFalse(controller.validarCorreo("test@.com"));
    }

    @Test
    void testValidarCedula() {
        Assertions.assertTrue(controller.validarCedula("123456"));
        Assertions.assertFalse(controller.validarCedula("abc123"));
        Assertions.assertFalse(controller.validarCedula(""));
    }

    @Test
    void testValidarContrasena() {
        Assertions.assertTrue(controller.validarContrasena("abcdef"));
        Assertions.assertFalse(controller.validarContrasena("abc"));
    }

    @Test
    void testUnicidadCorreoCedula() throws IOException {
        // Registrar un usuario
        controller.registrarComensal("a@b.com", "123456", "111", "comensal");
        // Debe detectar duplicados
        Assertions.assertTrue(controller.existeCorreoOCedula("a@b.com", "222"));
        Assertions.assertTrue(controller.existeCorreoOCedula("c@d.com", "111"));
        Assertions.assertFalse(controller.existeCorreoOCedula("x@y.com", "333"));
    }
}
