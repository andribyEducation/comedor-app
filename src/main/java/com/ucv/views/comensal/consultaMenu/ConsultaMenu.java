package com.ucv.views.comensal.consultaMenu;

import com.ucv.components.Button.RoundedButton;
import com.ucv.components.UserMenu.UserMenu;
import com.ucv.controllers.saldo.SaldoController;
import com.ucv.views.comensal.menuDelDia.menuDelDia;
import com.ucv.views.comensal.saldo.SaldoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConsultaMenu extends JFrame {

    private Image backgroundImage;

    public ConsultaMenu() {
        setTitle("Turnos disponibles");
        setSize(1366, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Fondo con imagen
        backgroundImage = new ImageIcon(getClass().getResource("/assets/Background/Gradient Background #2.png"))
        .getImage()
        .getScaledInstance(1366, 768, Image.SCALE_SMOOTH);

        JPanel fondoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        fondoPanel.setLayout(null);
        setContentPane(fondoPanel);

        // Logo UCV (más grande)
        ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/assets/logos/logoucv.png"));
        Image imagenEscalada = iconoLogo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(imagenEscalada));
        logoLabel.setBounds(1160, 30, 80, 80); // Ajustado para 1366x768
        getContentPane().add(logoLabel);

        // Ícono de usuario (más grande)
        JLabel iconoUsuario = new JLabel();
        iconoUsuario.setBounds(1260, 30, 80, 80); // Ajustado para 1366x768
        iconoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/assets/logos/usericon.png"));
        Image iconoEscalado = iconoOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        iconoUsuario.setIcon(new ImageIcon(iconoEscalado));
        getContentPane().add(iconoUsuario);

        // Menú de usuario reutilizable
        UserMenu menuUsuario = new UserMenu(this, "Usuario: Nombre");
        iconoUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = -menuUsuario.getPreferredSize().width + iconoUsuario.getWidth();
                int y = iconoUsuario.getHeight();
                menuUsuario.show(iconoUsuario, x, y);
            }
        });

        // Título
        JLabel titulo = new JLabel("Turnos disponibles");
        titulo.setFont(new Font("Inter", Font.BOLD, 40)); // Ajustado tamaño de fuente
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(60, 30, 500, 50); // Ajustado para 1366x768
        getContentPane().add(titulo);

        JPanel linea = new JPanel();
        linea.setBackground(new Color(255, 204, 0));
        linea.setBounds(60, 90, 400, 4); // Ajustado para 1366x768
        getContentPane().add(linea);

        // Días y posiciones (ajustados para 1366x768)
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        int[][] posiciones = {
            {120, 180},    // Lunes
            {520, 180},    // Martes
            {920, 180},    // Miércoles
            {320, 400},    // Jueves
            {720, 400}     // Viernes
        };

        for (int i = 0; i < dias.length; i++) {
            agregarDia(dias[i], posiciones[i][0], posiciones[i][1]);
        }

        // Botón Saldo Disponible (RoundedButton y mensaje)
        RoundedButton btnSaldo = new RoundedButton("Saldo Disponible", true);
        btnSaldo.setBounds(1060, 650, 250, 40); // Ajustado para 1366x768
        btnSaldo.setFont(new Font("Inter", Font.BOLD, 18)); // Ajustado tamaño de fuente
        btnSaldo.setBackground(new Color(255, 204, 0));
        btnSaldo.setFocusPainted(false);
        btnSaldo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSaldo.addActionListener(e -> {
            SaldoView saldoView = new SaldoView();
            saldoView.setVisible(true);
            new SaldoController(saldoView);
            this.dispose();
        });
        getContentPane().add(btnSaldo);
    }

    private void agregarDia(String dia, int x, int y) {
        int botonAncho = 220; // Ajustado para 1366x768
        int etiquetaAncho = 220; // Ajustado para 1366x768

        JLabel lblDia = new JLabel(dia, SwingConstants.CENTER);
        lblDia.setForeground(Color.WHITE);
        lblDia.setFont(new Font("Inter", Font.BOLD, 22)); // Ajustado tamaño de fuente
        lblDia.setBounds(x, y, etiquetaAncho, 30); // Ajustado para 1366x768
        getContentPane().add(lblDia);

        RoundedButton verMenu = new RoundedButton("Consultar", true);
        verMenu.setBounds(x, y + 40, botonAncho, 35); // Ajustado para 1366x768
        verMenu.setFont(new Font("Inter", Font.BOLD, 16)); // Ajustado tamaño de fuente
        verMenu.setBackground(new Color(255, 204, 0));
        verMenu.setFocusPainted(false);
        verMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        verMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new menuDelDia(dia).setVisible(true);
            }
        });
        getContentPane().add(verMenu);
    }
}
