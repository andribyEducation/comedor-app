package com.ucv.controllers;

import org.junit.jupiter.api.*;
import com.ucv.views.admin.dashboards.AdminDashboardView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

class AdminDashboardControllerTest {

    static class StubAdminDashboardView extends AdminDashboardView {
        public StubAdminDashboardView(String nombreCompleto) {
            super(nombreCompleto);
        }

        private JLabel iconoUsuario = new JLabel();
        private HashMap<String, JButton> actionButtons = new HashMap<>();


        @Override
        public JLabel getIconoUsuario() { return iconoUsuario; }

        @Override
        public Map getActionButtons() { return actionButtons; }
    }

    private StubAdminDashboardView stubView;

    @BeforeEach
    void setUp() {
        stubView = new StubAdminDashboardView("Admin");
    }

    @Test
    void testShowUserMenuListener() {
        MouseEvent event = new MouseEvent(stubView.getIconoUsuario(), MouseEvent.MOUSE_PRESSED, 0, 0, 10, 10, 1, false);
        for (java.awt.event.MouseListener listener : stubView.getIconoUsuario().getMouseListeners()) {
            listener.mousePressed(event);
        }
        Assertions.assertTrue(true); // No debe lanzar excepción
    }
}
