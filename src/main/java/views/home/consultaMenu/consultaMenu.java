package views.home.consultaMenu;

import components.Button.RoundedButton;
// import components.CheckBox.CheckBox;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class consultaMenu extends JFrame {

    public consultaMenu() {

        setTitle("Turnos disponibles");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/assets/logos/logoucv.png"));
        Image imagenEscalada = iconoLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(imagenEscalada));
        logoLabel.setBounds(800, 20, 40, 40);
        add(logoLabel);

        JLabel iconoUsuario = new JLabel();
        iconoUsuario.setBounds(850, 20, 40, 40);
        iconoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/assets/logos/usericon.png"));
        Image iconoEscalado = iconoOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        iconoUsuario.setIcon(new ImageIcon(iconoEscalado));
        add(iconoUsuario);

        JPopupMenu menuUsuario = new JPopupMenu() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(30, 30, 30));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        menuUsuario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuUsuario.setOpaque(false);

        JLabel nombreUsuario = new JLabel("Usuario: Juan Pérez");
        nombreUsuario.setFont(new Font("Inter", Font.BOLD, 15));
        nombreUsuario.setForeground(Color.WHITE);
        nombreUsuario.setOpaque(false);
        menuUsuario.add(nombreUsuario);
        menuUsuario.add(Box.createVerticalStrut(8));

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

        JLabel titulo = new JLabel("Turnos disponibles");
        titulo.setFont(new Font("Inter", Font.BOLD, 35));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(80, 30, 400, 40);
        add(titulo);

        JPanel linea = new JPanel();
        linea.setBackground(new Color(255, 204, 0));
        linea.setBounds(80, 75, 330, 2);
        add(linea);

        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        int[][] posiciones = {
            {250, 120},
            {550, 120},
            {250, 220},
            {550, 220},
            {400, 320}
        };

        for (int i = 0; i < dias.length; i++) {
            agregarDia(dias[i], posiciones[i][0], posiciones[i][1]);
        }

        // Botón "Saldo Disponible" con menú emergente
        JButton btnSaldo = new JButton("Saldo Disponible");
        btnSaldo.setBounds(700, 430, 200, 30);
        btnSaldo.setForeground(Color.BLACK);
        btnSaldo.setFont(new Font("Inter", Font.BOLD, 20));
        btnSaldo.setFocusPainted(false);
        btnSaldo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSaldo.setBackground(new Color(255, 204, 0));
        add(btnSaldo);

        // Menú emergente de saldo
        JPopupMenu popupSaldo = new JPopupMenu() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(30, 30, 30));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        popupSaldo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        popupSaldo.setOpaque(false);

        // Contenido del menú de saldo
        JLabel saldoLabel = new JLabel("250 Bs");
        saldoLabel.setFont(new Font("Inter", Font.BOLD, 20));
        saldoLabel.setForeground(Color.WHITE);
        saldoLabel.setOpaque(false);
        popupSaldo.add(saldoLabel);

        // Acción al hacer clic en el botón
        btnSaldo.addActionListener(e -> {
            int x = 0; // Puedes ajustar si quieres desplazarlo a la izquierda o derecha
            int y = btnSaldo.getHeight();
            popupSaldo.show(btnSaldo, x, y);
        });
    }

    private void personalizarItem(JMenuItem item) {
        item.setBackground(new Color(50, 50, 50));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Inter", Font.BOLD, 15));
        item.setOpaque(true);
        item.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
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
        int botonAncho = 160;
        int etiquetaAncho = 200;

        JLabel lblDia = new JLabel(dia, SwingConstants.CENTER);
        lblDia.setForeground(Color.WHITE);
        lblDia.setFont(new Font("Inter", Font.BOLD, 25));
        lblDia.setBounds(x - (etiquetaAncho - botonAncho) / 2, y, etiquetaAncho, 30);
        add(lblDia);

        RoundedButton verMenu = new RoundedButton("Consultar", true);
        verMenu.setBounds(x, y + 35, 160, 30);
        verMenu.setFont(new Font("Inter", Font.BOLD, 18));
        verMenu.setForeground(Color.BLACK);  // ← Color del texto
        verMenu.setBackground(new Color(255, 204, 0));
        verMenu.setFocusPainted(false);
        verMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        verMenu.addActionListener(e -> {
        JOptionPane.showMessageDialog(this, "Mostrar menú del día: " + dia);
        });
        add(verMenu);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new consultaMenu().setVisible(true);
        });
    }
}
