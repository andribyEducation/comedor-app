// Nombre del archivo: AdminDashboardController.java
package com.ucv.controllers.adminDashboard;

import com.ucv.views.admin.dashboards.AdminDashboardView;
import com.ucv.views.admin.menu.GestionarMenuView;
import com.ucv.controllers.admin.menu.GestionarMenuController;

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
        // Listeners para los botones de acción principales
        view.getActionButtons().forEach((option, button) -> {
            button.addActionListener(e -> showDashboardOption(option));
        });
    }

    private void showDashboardOption(String option) {
        if ("Gestionar Menú".equals(option)) {
            GestionarMenuView menuView = new GestionarMenuView();
            new GestionarMenuController (menuView);
            menuView.display();
        }
    }
}