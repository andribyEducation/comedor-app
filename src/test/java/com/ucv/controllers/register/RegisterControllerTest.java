package com.ucv.controllers.register;

import com.ucv.views.registroView.RegistroView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterControllerTest {

    private RegistroView registroView;
    private RegisterController controller;

    @BeforeEach
    void setUp() {
        registroView = mock(RegistroView.class);
        // Mock necesarios para evitar NullPointerException en el constructor de RegisterController
        when(registroView.getRegisterButton()).thenReturn(mock(com.ucv.components.Button.RoundedButton.class));
        when(registroView.getBackLabel()).thenReturn(new JLabel());
        controller = new RegisterController(registroView);
    }

    @Test
    void testValidarCorreoValido() {
        assertTrue(controller.validarCorreo("test@email.com"));
    }

    @Test
    void testValidarCorreoInvalido() {
        assertFalse(controller.validarCorreo("testemail.com"));
    }

    @Test
    void testValidarContrasena() {
        assertTrue(controller.validarContrasena("123456"));
        assertFalse(controller.validarContrasena("123"));
    }

    @Test
    void testValidarTipoRegistro_AdminPuedeAmbos() {
        // Un administrador puede registrarse como comensal o administrador
        assertTrue(controller.validarTipoRegistro("administrador", "comensal"));
        assertTrue(controller.validarTipoRegistro("administrador", "administrador"));
    }

    @Test
    void testValidarTipoRegistro_EstudianteSoloComensal() {
        assertTrue(controller.validarTipoRegistro("estudiante", "comensal"));
        assertFalse(controller.validarTipoRegistro("estudiante", "administrador"));
    }

    @Test
    void testValidarTipoRegistro_ProfesorSoloComensal() {
        assertTrue(controller.validarTipoRegistro("profesor", "comensal"));
        assertFalse(controller.validarTipoRegistro("profesor", "administrador"));
    }

    @Test
    void testObtenerTipoPersonaSecretaria() {
        // Cedula de Adrian (estudiante)
        String tipo = controller.obtenerTipoPersonaSecretaria("30513493");
        assertEquals("estudiante", tipo);

        // Cedula de Juan (administrador)
        tipo = controller.obtenerTipoPersonaSecretaria("123456789");
        assertEquals("administrador", tipo);

        // Cedula de Luis (profesor)
        tipo = controller.obtenerTipoPersonaSecretaria("987654321");
        assertEquals("profesor", tipo);

        // Cedula inexistente
        tipo = controller.obtenerTipoPersonaSecretaria("00000000");
        assertNull(tipo);
    }
}
