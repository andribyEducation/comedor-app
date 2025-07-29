package com.ucv.views.home;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class HomeTest {

    private Home home;

    @BeforeEach
    void setUp() {
        home = new Home();
    }

    @Test
    void testBtnIngresarInitialized() {
        JButton btnIngresar = home.getBtnIngresar();
        assertNotNull(btnIngresar, "El botón 'Ingresar' debe estar inicializado");
        assertEquals("Ingresar", btnIngresar.getText());
        assertTrue(btnIngresar.isVisible());
    }

    @Test
    void testBtnRegistrarseInitialized() {
        JButton btnRegistrarse = home.getBtnRegistrarse();
        assertNotNull(btnRegistrarse, "El botón 'Registrarse' debe estar inicializado");
        assertEquals("Registrarse", btnRegistrarse.getText());
        assertTrue(btnRegistrarse.isVisible());
    }

    @Test
    void testOlvideContraseñaInitialized() {
        JLabel olvideContraseña = home.getOlvideContraseña();
        assertNotNull(olvideContraseña, "El JLabel 'Olvidaste tu contraseña' debe estar inicializado");
        assertEquals("¿Olvidaste tu contraseña?", olvideContraseña.getText());
        assertTrue(olvideContraseña.isVisible());
    }

    @Test
    void testTitle() {
        assertEquals("Comedor", home.getTitle());
    }

    @Test
    void testPanelLayoutIsBoxLayout() {
        JPanel panel = (JPanel) home.getContentPane().getComponent(0);
        assertTrue(panel.getLayout() instanceof BoxLayout, "El layout del panel debe ser BoxLayout");
    }
}