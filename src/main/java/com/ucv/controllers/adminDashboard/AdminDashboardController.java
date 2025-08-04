// Nombre del archivo: AdminDashboardController.java
package com.ucv.controllers.adminDashboard;

import com.ucv.views.admin.dashboards.AdminDashboardView;
import com.ucv.views.admin.menu.GestionarMenuView;
import com.ucv.controllers.admin.menu.GestionarMenuController;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * El Controlador maneja las interacciones del usuario.
 * Conecta la Vista (UI) con la lógica de la aplicación.
 */
public class AdminDashboardController {

    private final AdminDashboardView view;

    public AdminDashboardController(AdminDashboardView view) {
        this.view = view;
        initController();
    }

    /**
     * Inicializa todos los listeners para los componentes de la vista.
     */
    private void initController() {
        // Listener para mostrar el menú desplegable al hacer clic en el icono de usuario
        view.getIconoUsuario().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showUserMenu();
            }
        });

        // Listeners para los botones de acción principales
        view.getActionButtons().forEach((option, button) -> {
            button.addActionListener(e -> showDashboardOption(option));
        });
    }

    // --- MÉTODOS DE LÓGICA DE ACCIONES ---

    private void showUserMenu() {
        JPopupMenu menu = view.getMenuUsuario();
        JLabel icon = view.getIconoUsuario();
        // Calcula la posición para que el menú aparezca alineado a la derecha del icono
        int x = -menu.getPreferredSize().width + icon.getWidth();
        int y = icon.getHeight();
        menu.show(icon, x, y);
    }

    private void showDashboardOption(String option) {
        if ("Gestionar Menú".equals(option)) {
            GestionarMenuView menuView = new GestionarMenuView();
            new GestionarMenuController (menuView);
            menuView.display();
        }
    }
}