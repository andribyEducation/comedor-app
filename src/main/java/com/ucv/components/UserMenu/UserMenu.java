package com.ucv.components.UserMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.ucv.controllers.login.LoginController;
import com.ucv.services.AuthService;
import com.ucv.views.login.LoginView;

public class UserMenu extends JPopupMenu {

    public UserMenu(JFrame parent, String userName) {
        super();
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setOpaque(false);

        // Fondo redondeado
        setUI(new javax.swing.plaf.basic.BasicPopupMenuUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(30, 30, 30));
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 15, 15);
            }
        });

        JLabel nombreUsuario = new JLabel(userName);
        nombreUsuario.setFont(new Font("Inter", Font.BOLD, 22));
        nombreUsuario.setForeground(Color.WHITE);
        nombreUsuario.setOpaque(false);
        add(nombreUsuario);
        add(Box.createVerticalStrut(10));

        JMenuItem cambiarContrasena = crearItem("Cambiar contraseña");
        cambiarContrasena.addActionListener(e -> {
            JOptionPane.showMessageDialog(parent, "Lógica para cambiar contraseña.");
        });
        add(cambiarContrasena);

        JMenuItem reportarProblema = crearItem("Reportar problema");
        reportarProblema.addActionListener(e -> {
            JOptionPane.showMessageDialog(parent, "Aquí va la lógica para reportar un problema.");
        });
        add(reportarProblema);

        JMenuItem cerrarSesion = crearItem("Cerrar sesión");
        cerrarSesion.addActionListener(e -> {
            new AuthService().cerrarSesion();
            LoginView loginView = new LoginView();
            new LoginController(loginView);
            loginView.setVisible(true);
            // Dispose the parent frame to close the current view
            parent.dispose();
        });
        add(cerrarSesion);
    }

    private JMenuItem crearItem(String texto) {
        JMenuItem item = new JMenuItem(texto);
        item.setBackground(new Color(50, 50, 50));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Inter", Font.BOLD, 18));
        item.setOpaque(true);
        item.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
        return item;
    }
}
