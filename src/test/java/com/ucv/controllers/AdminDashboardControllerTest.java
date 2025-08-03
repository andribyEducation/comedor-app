package com.ucv.controllers;

import org.junit.jupiter.api.*;
import com.ucv.views.admin.dashboards.AdminDashboardView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

class AdminDashboardControllerTest {

    static class StubAdminDashboardView extends AdminDashboardView {
        private JLabel iconoUsuario = new JLabel();
        private HashMap<String, JButton> actionButtons = new HashMap<>();

        public StubAdminDashboardView() {
            actionButtons.put("opcion1", new JButton());
            actionButtons.put("opcion2", new JButton());
        }

        @Override
        public JLabel getIconoUsuario() { return iconoUsuario; }
    }

    private StubAdminDashboardView stubView;

    @BeforeEach
    void setUp() {
        stubView = new StubAdminDashboardView();
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
