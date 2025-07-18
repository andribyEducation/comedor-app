package views.comensal.consultaMenu;

import components.Button.RoundedButton;
import views.home.menuDelDia.menuDelDia;
import views.comensal.saldo.SaldoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConsultaMenu extends JFrame {

    private Image backgroundImage;

    public ConsultaMenu() {
        setTitle("Turnos disponibles");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Fondo con imagen
        backgroundImage = new ImageIcon(getClass().getResource("/assets/Background/Gradient Background #2.png"))
                .getImage()
                .getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        setContentPane(new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });
        getContentPane().setLayout(null);

        // Logo UCV (más grande)
        ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/assets/logos/logoucv.png"));
        Image imagenEscalada = iconoLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(imagenEscalada));
        logoLabel.setBounds(1600, 60, 100, 100);
        getContentPane().add(logoLabel);

        // Ícono de usuario (más grande)
        JLabel iconoUsuario = new JLabel();
        iconoUsuario.setBounds(1720, 60, 100, 100);
        iconoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/assets/logos/usericon.png"));
        Image iconoEscalado = iconoOriginal.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        iconoUsuario.setIcon(new ImageIcon(iconoEscalado));
        getContentPane().add(iconoUsuario);

        // Menú emergente del usuario
        JPopupMenu menuUsuario = new JPopupMenu() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(30, 30, 30));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        menuUsuario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        menuUsuario.setOpaque(false);

        JLabel nombreUsuario = new JLabel("Usuario: Nombre");
        nombreUsuario.setFont(new Font("Inter", Font.BOLD, 22));
        nombreUsuario.setForeground(Color.WHITE);
        nombreUsuario.setOpaque(false);
        menuUsuario.add(nombreUsuario);
        menuUsuario.add(Box.createVerticalStrut(10));

        JMenuItem cambiarContrasena = new JMenuItem("Cambiar contraseña");
        personalizarItem(cambiarContrasena);
        cambiarContrasena.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Lógica para cambiar contraseña.");
        });
        menuUsuario.add(cambiarContrasena);

        JMenuItem reportarProblema = new JMenuItem("Reportar problema");
        personalizarItem(reportarProblema);
        reportarProblema.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Aquí va la lógica para reportar un problema.");
        });
        menuUsuario.add(reportarProblema);

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
        titulo.setFont(new Font("Inter", Font.BOLD, 50));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(100, 60, 600, 60);
        getContentPane().add(titulo);

        JPanel linea = new JPanel();
        linea.setBackground(new Color(255, 204, 0));
        linea.setBounds(100, 130, 500, 4);
        getContentPane().add(linea);

        // Días y posiciones (según distribución solicitada)
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        int[][] posiciones = {
            {450, 250},     // Lunes
            {1020, 250},    // Martes
            {450, 480},     // Miércoles
            {1020, 480},    // Jueves
            {735, 700}      // Viernes (centrado)
        };

        for (int i = 0; i < dias.length; i++) {
            agregarDia(dias[i], posiciones[i][0], posiciones[i][1]);
        }

        // Botón Saldo Disponible (RoundedButton y mensaje)
        RoundedButton btnSaldo = new RoundedButton("Saldo Disponible", true);
        btnSaldo.setBounds(1550, 870, 280, 50);
        btnSaldo.setFont(new Font("Inter", Font.BOLD, 22));
        btnSaldo.setForeground(Color.BLACK);
        btnSaldo.setBackground(new Color(255, 204, 0));
        btnSaldo.setFocusPainted(false);
        btnSaldo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSaldo.addActionListener(e -> {
            new SaldoView().setVisible(true);
            this.dispose();
        });
        getContentPane().add(btnSaldo);
    }

    private void personalizarItem(JMenuItem item) {
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
    }

    private void agregarDia(String dia, int x, int y) {
        int botonAncho = 400;
        int etiquetaAncho = 400;

        JLabel lblDia = new JLabel(dia, SwingConstants.CENTER);
        lblDia.setForeground(Color.WHITE);
        lblDia.setFont(new Font("Inter", Font.BOLD, 30));
        lblDia.setBounds(x - (etiquetaAncho - botonAncho) / 2, y, etiquetaAncho, 40);
        getContentPane().add(lblDia);

        RoundedButton verMenu = new RoundedButton("Consultar", true);
        verMenu.setBounds(x, y + 50, botonAncho, 50);
        verMenu.setFont(new Font("Inter", Font.BOLD, 22));
        verMenu.setForeground(Color.BLACK);
        verMenu.setBackground(new Color(255, 204, 0));
        verMenu.setFocusPainted(false);
        verMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        verMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new menuDelDia(dia).setVisible(true);
            }
}       );
        getContentPane().add(verMenu);
    }

   
}
