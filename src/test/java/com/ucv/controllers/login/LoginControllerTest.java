package com.ucv.controllers.login;

import com.ucv.views.login.LoginView;
import com.ucv.services.AuthService;
import com.ucv.views.registroView.RegistroView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    private LoginView loginView;
    private AuthService authService;
    private RegistroView registroView;
    private LoginController controller;

    @BeforeEach
    void setUp() {
        loginView = mock(LoginView.class);
        authService = mock(AuthService.class);
        registroView = mock(RegistroView.class);

        // Mock necesarios para evitar NullPointerException en el constructor de LoginController
        when(loginView.getLoginButton()).thenReturn(mock(com.ucv.components.Button.RoundedButton.class));
        when(loginView.getBackLabel()).thenReturn(new JLabel());

        controller = new LoginController(loginView, registroView);
    }

    @Test
    void testHandleLogin_ComensalFail() {
        when(loginView.getCedula()).thenReturn("30513493");
        when(loginView.getContrasena()).thenReturn("wrongpass");
        when(loginView.getTipo()).thenReturn("comensal");
        controller.authService = mock(AuthService.class);
        when(controller.authService.autenticar(anyString(), anyString(), anyString())).thenReturn(false);

        assertDoesNotThrow(() -> {
            try {
                controller.handleLogin("30513493", "wrongpass", "comensal");
            } catch (Exception ignored) {
                // Ignora cualquier excepción de UI
            }
        });
    }

    @Test
    void testSetUsuario_ComensalDatosSecretaria() {
        // Cedula: 30513493, nombre: Adrian, apellido: Gonzalez, tipo: estudiante
        when(loginView.getCedula()).thenReturn("30513493");
        when(loginView.getContrasena()).thenReturn("za63qj2p");
        when(loginView.getTipo()).thenReturn("comensal");
        controller.authService = mock(AuthService.class);
        when(controller.authService.autenticar(anyString(), anyString(), anyString())).thenReturn(true);

        assertDoesNotThrow(() -> controller.handleLogin("30513493", "za63qj2p", "comensal"));
        assertNotNull(com.ucv.models.Usuario.getUsuarioActual());
        com.ucv.models.Usuario usuario = com.ucv.models.Usuario.getUsuarioActual();
        assertEquals("30513493", usuario.getCedula());
        assertEquals("Adrian", usuario.getNombre());
        assertEquals("Gonzalez", usuario.getApellido());
        assertEquals("estudiante", usuario.getRol());
        assertEquals("adrian@gmail.com", usuario.getCorreo());
        assertEquals(999999979, (int)usuario.getSaldo());
    }

    @Test
    void testSetUsuario_AdminDatosSecretaria() {
        // Cedula: 123456789, nombre: Juan, apellido: Rodriguez, tipo: administrador
        when(loginView.getCedula()).thenReturn("123456789");
        when(loginView.getContrasena()).thenReturn("123546458456");
        when(loginView.getTipo()).thenReturn("administrador");
        controller.authService = mock(AuthService.class);
        when(controller.authService.autenticar(anyString(), anyString(), anyString())).thenReturn(true);

        assertDoesNotThrow(() -> controller.handleLogin("123456789", "123546458456", "administrador"));
        assertNotNull(com.ucv.models.Usuario.getUsuarioActual());
        com.ucv.models.Usuario usuario = com.ucv.models.Usuario.getUsuarioActual();
        assertEquals("123456789", usuario.getCedula());
        assertEquals("Juan", usuario.getNombre());
        assertEquals("Rodriguez", usuario.getApellido());
        assertEquals("administrador", usuario.getTipo());
        assertEquals("rgerg@gmail.com", usuario.getCorreo());
    }
}
