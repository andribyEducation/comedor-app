package com.ucv.views.comensal.saldo;

import javax.swing.*;

import com.ucv.components.UserMenu.UserMenu;
import com.ucv.models.Usuario;

import java.awt.*;

public class SaldoView extends JFrame {

    private Image backgroundImage;
    private JLabel iconoUsuario;
    private UserMenu menuUsuario; // Cambia el tipo aquí
    private JLabel saldoLabel;
    private JPanel panelRecarga;
    private JTextField txtMonto;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JButton btnVolver;
    private JButton btnRecargar;

    public SaldoView() {
        setTitle("Saldo");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);

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
        JLabel titulo = new JLabel("Saldo", SwingConstants.LEFT);
        titulo.setFont(new Font("Inter", Font.BOLD, 38));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(80, 30, 150, 40);
        add(titulo);

        // Logo UCV
        ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/assets/logos/logoucv.png"));
        Image imagenEscalada = iconoLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(imagenEscalada));
        logoLabel.setBounds(800, 20, 40, 40);
        add(logoLabel);

        // Icono de usuario (perfil)
        iconoUsuario = new JLabel();
        iconoUsuario.setBounds(850, 20, 40, 40);
        iconoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/assets/logos/usericon.png"));
        Image iconoEscalado = iconoOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        iconoUsuario.setIcon(new ImageIcon(iconoEscalado));
        add(iconoUsuario);

        // Usa el componente UserMenu
        menuUsuario = new UserMenu(this, "Usuario: Juan Pérez");

        // Etiqueta "saldo disponible:"
        JLabel saldoDisponibleLabel = new JLabel("saldo disponible:", SwingConstants.LEFT);
        saldoDisponibleLabel.setFont(new Font("Inter", Font.PLAIN, 22));
        saldoDisponibleLabel.setForeground(Color.WHITE);
        saldoDisponibleLabel.setBounds(250, 38, 180, 30);
        add(saldoDisponibleLabel);

        // Saldo al lado del título, más pequeño
        saldoLabel = new JLabel( Usuario.getUsuarioActual().getSaldo() + " Bs", SwingConstants.LEFT);
        saldoLabel.setFont(new Font("Inter", Font.BOLD, 26));
        saldoLabel.setForeground(new Color(255, 204, 0));
        saldoLabel.setBounds(440, 38, 120, 30);
        add(saldoLabel);

        // Ajuste del ancho de la barra amarilla para cubrir todo el texto superior
        int barraX = 80;
        int barraY = 75;
        int barraAncho = (440 + 120) - barraX; // desde el inicio del título hasta el final del saldo
        JPanel linea = new JPanel();
        linea.setBackground(new Color(255, 204, 0));
        linea.setBounds(barraX, barraY, barraAncho, 2);
        add(linea);

        // Panel/formulario para recargar saldo (campo y botones abajo)
        panelRecarga = new JPanel(null);
        panelRecarga.setBounds(80, 120, 370, 120); // Más cerca del título y más alto
        panelRecarga.setBackground(new Color(40, 40, 40));
        panelRecarga.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 0), 2));
        panelRecarga.setVisible(false);

        JLabel lblMonto = new JLabel("Monto a recargar:");
        lblMonto.setFont(new Font("Inter", Font.PLAIN, 18));
        lblMonto.setForeground(Color.WHITE);
        lblMonto.setBounds(20, 15, 160, 30);
        panelRecarga.add(lblMonto);

        txtMonto = new JTextField();
        txtMonto.setFont(new Font("Inter", Font.PLAIN, 18));
        txtMonto.setBounds(180, 15, 150, 30);
        panelRecarga.add(txtMonto);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(new Font("Inter", Font.BOLD, 18));
        btnConfirmar.setBackground(new Color(255, 204, 0));
        btnConfirmar.setForeground(Color.BLACK);
        btnConfirmar.setBounds(20, 65, 140, 35);
        panelRecarga.add(btnConfirmar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Inter", Font.BOLD, 18));
        btnCancelar.setBackground(Color.GRAY);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBounds(190, 65, 140, 35);
        panelRecarga.add(btnCancelar);

        add(panelRecarga);

        // Botones Volver y Recargar saldo abajo del formulario
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 260, 200, 40);
        btnVolver.setFont(new Font("Inter", Font.BOLD, 20));
        btnVolver.setBackground(new Color(255, 204, 0));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(btnVolver);

        btnRecargar = new JButton("Recargar saldo");
        btnRecargar.setBounds(300, 260, 200, 40);
        btnRecargar.setFont(new Font("Inter", Font.BOLD, 20));
        btnRecargar.setBackground(new Color(255, 204, 0));
        btnRecargar.setForeground(Color.BLACK);
        btnRecargar.setFocusPainted(false);
        btnRecargar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(btnRecargar);
    }

    // Métodos para el controlador
    public JLabel getIconoUsuario() { return iconoUsuario; }
    public UserMenu getMenuUsuario() { return menuUsuario; }
    public JLabel getSaldoLabel() { return saldoLabel; }
    public JPanel getPanelRecarga() { return panelRecarga; }
    public JTextField getTxtMonto() { return txtMonto; }
    public JButton getBtnConfirmar() { return btnConfirmar; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public JButton getBtnVolver() { return btnVolver; }
    public JButton getBtnRecargar() { return btnRecargar; }
}