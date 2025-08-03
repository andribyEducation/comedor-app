package com.ucv.controllers;

import com.ucv.controllers.register.RegisterController;
import com.ucv.views.registroView.RegistroView;
import org.junit.jupiter.api.*;
import com.ucv.components.Button.RoundedButton;
import static org.mockito.Mockito.*;

class RegisterControllerTest {

    private RegisterController controller;
    private RegistroView mockRegistroView;

    @BeforeEach
    void setUp() {
        mockRegistroView = mock(RegistroView.class);
        when(mockRegistroView.getRegisterButton()).thenReturn(mock(RoundedButton.class));
        controller = new RegisterController(mockRegistroView);
    }

    @Test
    void testValidarCorreo() {
        Assertions.assertTrue(controller.validarCorreo("test@mail.com"));
        Assertions.assertFalse(controller.validarCorreo("testmail.com"));
        Assertions.assertFalse(controller.validarCorreo("test@.com"));
    }
<<<<<<< HEAD
=======

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
        Assertions.assertTrue(controller.existeCorreoOCedula("a@b.com", "222", "comensal"));
        Assertions.assertTrue(controller.existeCorreoOCedula("c@d.com", "111", "comensal"));
        Assertions.assertFalse(controller.existeCorreoOCedula("x@y.com", "333", "comensal"));
    }

    @Test
    void testCedulaPuedeSerAdminYComensal() throws IOException {
        // Registrar como comensal
        controller.registrarComensal("comensal@correo.com", "clave123", "555", "comensal");
        // Comprueba si existe el usuario creado
        Assertions.assertTrue(controller.existeCorreoOCedula("comensal@correo.com", "555", "comensal"));

        // Registra como administrador con la misma cédula
        controller.registrarComensal("admin@correo.com", "clave123", "555", "administrador");

        // Comprueba si el administrador también se registró correctamente
        Assertions.assertTrue(controller.existeCorreoOCedula("admin@correo.com", "555", "administrador"));
    }
>>>>>>> 07e98cc (Registro y pruebas de comensales/administradores)
}

