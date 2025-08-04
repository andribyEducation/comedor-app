package com.ucv.components.Header;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.ucv.components.UserMenu.UserMenu;

public class HeaderPanel extends JPanel {
    private JLabel iconoUsuario;
    private UserMenu menuUsuario;
    private JLabel backButtonLabel;

    public HeaderPanel(JFrame parent, boolean bgDark, String titulo, String userName, boolean showBackButton) {
        setLayout(new BorderLayout());
        setOpaque(true);
        if (bgDark) {
            setBackground(new Color(30, 30, 30));
        } else {
            setBackground(new Color(255, 255, 255));
        }
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Panel para el botón de retroceso (izquierda)
        JPanel westPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        westPanel.setOpaque(false);

        if (showBackButton) {
            ImageIcon backIcon = new ImageIcon(getClass().getResource("/assets/Iconos/FlechaIzquierda.png"));
            Image scaledBackImage = backIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            backButtonLabel = new JLabel(new ImageIcon(scaledBackImage));
            backButtonLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            westPanel.add(backButtonLabel);
        }

        // Panel vertical para logo y título (centro)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        // Logo UCV
        ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/assets/logos/logoucv.png"));
        Image imagenEscalada = iconoLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(imagenEscalada));
        logoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(logoLabel);

        // Título
        if (titulo != null && !titulo.isEmpty()) {
            JLabel tituloLabel = new JLabel(titulo);
            tituloLabel.setFont(new Font("Inter", Font.BOLD, 32));
            tituloLabel.setForeground(bgDark ? Color.WHITE : new Color(0, 51, 102));
            tituloLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            centerPanel.add(Box.createVerticalStrut(5));
            centerPanel.add(tituloLabel);
        }
        JPanel linea = new JPanel();
        linea.setBackground(new Color(0, 51, 102));
        linea.setPreferredSize(new Dimension(200, 5));
        centerPanel.add(linea);

        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        // Icono de Usuario (derecha)
        iconoUsuario = new JLabel();
        iconoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/assets/logos/usericon.png"));
        Image iconoEscalado = iconoOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        iconoUsuario.setIcon(new ImageIcon(iconoEscalado));
        add(iconoUsuario, BorderLayout.EAST);

        // Menú de Usuario
        menuUsuario = new UserMenu(parent);
        iconoUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = -menuUsuario.getPreferredSize().width + iconoUsuario.getWidth();
                int y = iconoUsuario.getHeight();
                menuUsuario.show(iconoUsuario, x, y);
            }
        });
    }

    public JLabel getIconoUsuario() {
        return iconoUsuario;
    }

    public UserMenu getMenuUsuario() {
        return menuUsuario;
    }

    public JLabel getBackButtonLabel() {
        return backButtonLabel;
    }
}
