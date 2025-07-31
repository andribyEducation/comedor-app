package com.ucv.components.Header;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeaderPanel extends JPanel {
    private JLabel iconoUsuario;
    private JPopupMenu menuUsuario;
    private JMenuItem cambiarContrasenaItem;
    private JMenuItem reportarProblemaItem;

    public HeaderPanel(boolean bgDark,String titulo, String userName) {
        setLayout(new BorderLayout());
        setOpaque(true);
        if (bgDark) {
            setBackground(new Color(30, 30, 30));
        } else {
            setBackground(new Color(255, 255, 255));
        }
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Panel vertical para logo y título
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        // Logo UCV
        ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/assets/logos/logoucv.png"));
        Image imagenEscalada = iconoLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(imagenEscalada));
        logoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(logoLabel);

        // Título
        if (titulo != null && !titulo.isEmpty()) {
            JLabel tituloLabel = new JLabel(titulo);
            tituloLabel.setFont(new Font("Inter", Font.BOLD, 32));
            tituloLabel.setForeground(bgDark ? Color.WHITE : new Color(0, 51, 102));
            tituloLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            leftPanel.add(Box.createVerticalStrut(5));
            leftPanel.add(tituloLabel);
        }
        JPanel linea = new JPanel();
        linea.setBackground(new Color(0, 51, 102));
        linea.setPreferredSize(new Dimension(200, 5));
        leftPanel.add(linea);

        add(leftPanel, BorderLayout.WEST);

        // Icono de Usuario
        iconoUsuario = new JLabel();
        iconoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/assets/logos/usericon.png"));
        Image iconoEscalado = iconoOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        iconoUsuario.setIcon(new ImageIcon(iconoEscalado));
        add(iconoUsuario, BorderLayout.EAST);

        // Menú de Usuario
        menuUsuario = createUserMenu(userName);
        iconoUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = -menuUsuario.getPreferredSize().width + iconoUsuario.getWidth();
                int y = iconoUsuario.getHeight();
                menuUsuario.show(iconoUsuario, x, y);
            }
        });
    }

    private JPopupMenu createUserMenu(String userName) {
        JPopupMenu menu = new JPopupMenu() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(30, 30, 30));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        menu.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        menu.setOpaque(false);

        JLabel nombreUsuarioLabel = new JLabel(userName);
        nombreUsuarioLabel.setFont(new Font("Inter", Font.BOLD, 18));
        nombreUsuarioLabel.setForeground(Color.WHITE);
        menu.add(nombreUsuarioLabel);
        menu.add(Box.createVerticalStrut(12));

        cambiarContrasenaItem = new JMenuItem("Cambiar contraseña");
        personalizarItem(cambiarContrasenaItem);
        menu.add(cambiarContrasenaItem);

        reportarProblemaItem = new JMenuItem("Reportar problema");
        personalizarItem(reportarProblemaItem);
        menu.add(reportarProblemaItem);

        return menu;
    }

    private void personalizarItem(JMenuItem item) {
        item.setBackground(new Color(50, 50, 50));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Inter", Font.BOLD, 18));
        item.setOpaque(true);
        item.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public JLabel getIconoUsuario() {
        return iconoUsuario;
    }

    public JMenuItem getCambiarContrasenaItem() {
        return cambiarContrasenaItem;
    }

    public JMenuItem getReportarProblemaItem() {
        return reportarProblemaItem;
    }
}
