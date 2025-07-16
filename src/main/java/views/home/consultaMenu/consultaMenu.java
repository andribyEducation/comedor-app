package views.home.consultaMenu;

import components.Button.RoundedButton;
//import components.CheckBox.CheckBox;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class consultaMenu extends JFrame {

    public consultaMenu() {
        setTitle("Turnos disponibles");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);

        // Icono de usuario en esquina superior derecha
        JLabel iconoUsuario = new JLabel();
        iconoUsuario.setBounds(930, 20, 40, 40);
        iconoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        iconoUsuario.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        add(iconoUsuario);

        // Menú desplegable personalizado
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
        nombreUsuario.setFont(new Font("SansSerif", Font.BOLD, 14));
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

        // Título principal
        JLabel titulo = new JLabel("Turnos disponibles");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);
        titulo.setBounds(80, 30, 400, 40);
        add(titulo);

        // Línea decorativa
        JPanel linea = new JPanel();
        linea.setBackground(new Color(255, 204, 0));
        linea.setBounds(80, 75, 300, 2);
        add(linea);

        // Días de la semana
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

        // Ejemplo Uso CheckBox"
        // CheckBox checkNotificaciones = new CheckBox(" CheckBox");
        // checkNotificaciones.setBounds(80, 430, 300, 30);
        // add(checkNotificaciones);

        // Botón "Saldo Disponible"
        JButton reportar = new JButton("Saldo Disponible");
        reportar.setBounds(700, 430, 200, 20);
        reportar.setForeground(Color.YELLOW);
        reportar.setFont(new Font("SansSerif", Font.PLAIN, 14));
        reportar.setFocusPainted(false);
        reportar.setBorderPainted(false);
        reportar.setContentAreaFilled(false);
        reportar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        reportar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Aquí va la lógica para ver el saldo.");
        });
        add(reportar);
    }

    private void personalizarItem(JMenuItem item) {
        item.setBackground(new Color(50, 50, 50));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("SansSerif", Font.PLAIN, 13));
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

        // Centrado horizontal de la etiqueta
        JLabel lblDia = new JLabel(dia, SwingConstants.CENTER);
        lblDia.setForeground(Color.WHITE);
        lblDia.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblDia.setBounds(x - (etiquetaAncho - botonAncho) / 2, y, etiquetaAncho, 30);
        add(lblDia);

        // Botón centrado respecto a la misma coordenada X
        RoundedButton verMenu = new RoundedButton("Ver Menú", true);
        verMenu.setBounds(x, y + 30, botonAncho, 30);
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
