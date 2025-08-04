package com.ucv.controllers.home;

import com.ucv.views.home.Home;
import com.ucv.views.login.LoginView;
import com.ucv.views.registroView.RegistroView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    private Home homeView;
    private HomeController controller;

    @BeforeEach
    void setUp() {
        homeView = mock(Home.class);
        when(homeView.getBtnIngresar()).thenReturn(new JButton());
        when(homeView.getBtnRegistrarse()).thenReturn(new JButton());
        when(homeView.getOlvideContraseña()).thenReturn(new JLabel());
        controller = new HomeController(homeView);
    }

    @Test
    void testActionPerformedRedirigirALogin() {
        JButton btnIngresar = homeView.getBtnIngresar();
        ActionEvent event = new ActionEvent(btnIngresar, ActionEvent.ACTION_PERFORMED, "");
        assertDoesNotThrow(() -> controller.actionPerformed(event));
    }

    @Test
    void testActionPerformedRedirigirARegistro() {
        JButton btnRegistrarse = homeView.getBtnRegistrarse();
        ActionEvent event = new ActionEvent(btnRegistrarse, ActionEvent.ACTION_PERFORMED, "");
        assertDoesNotThrow(() -> controller.actionPerformed(event));
    }

    @Test
    void testOlvideContraseñaListener() {
        JLabel olvideContraseña = homeView.getOlvideContraseña();
        // Busca el MouseListener agregado en el constructor
        assertTrue(olvideContraseña.getMouseListeners().length > 0);
    }
}
