// Nombre del archivo: AdminDashboardController.java
package controllers;

import views.admin.dashboards.AdminDashboardView;
import javax.swing.*;
import java.awt.Color;
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

        // Listeners para los items del menú
        view.getCambiarContrasenaItem().addActionListener(e -> changePassword());
        view.getReportarProblemaItem().addActionListener(e -> reportProblem());
        
        // Asigna el efecto hover a los items del menú
        addHoverEffect(view.getCambiarContrasenaItem());
        addHoverEffect(view.getReportarProblemaItem());

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

    private void changePassword() {
        JOptionPane.showMessageDialog(view, "Lógica para cambiar contraseña.");
    }

    private void reportProblem() {
        JOptionPane.showMessageDialog(view, "Aquí va la lógica para reportar un problema.");
    }

    private void showDashboardOption(String option) {
        JOptionPane.showMessageDialog(view, "Mostrar dashboard: " + option);
    }
    
    /**
     * Añade un efecto visual de hover a un JMenuItem.
     * Aunque es un cambio visual, se controla aquí porque es una respuesta a un evento.
     */
    private void addHoverEffect(JMenuItem item) {
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                item.setBackground(new Color(70, 70, 70));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                item.setBackground(new Color(50, 50, 50));
            }
        });
    }

   
}