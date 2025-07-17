package views.admin.dashboards;

import components.Button.RoundedButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setupUI();
        createHeader();
        createTitle();
        createActionButtons();

    }

    private void setupUI() {
        setTitle("Dashboard de Administrador");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        BackgroundPanel backgroundPanel = new BackgroundPanel("/assets/Background/Gradient Background #2.png");
        setContentPane(backgroundPanel);
        setLayout(new GridBagLayout());
    }

    private void createHeader() {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerPanel.setOpaque(false);

        ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/assets/logos/logoucv.png"));
        Image imagenEscalada = iconoLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(imagenEscalada));
        headerPanel.add(logoLabel);

        JLabel iconoUsuario = new JLabel();
        iconoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/assets/logos/usericon.png"));
        Image iconoEscalado = iconoOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        iconoUsuario.setIcon(new ImageIcon(iconoEscalado));
        headerPanel.add(iconoUsuario);

        JPopupMenu menuUsuario = createUserMenu();

        iconoUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = -menuUsuario.getPreferredSize().width + iconoUsuario.getWidth();
                int y = iconoUsuario.getHeight();
                menuUsuario.show(iconoUsuario, x, y);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(20, 0, 0, 20);
        add(headerPanel, gbc);
    }

    private JPopupMenu createUserMenu() {
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

        return menuUsuario;
    }

    private void createTitle() {
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Dashboard de Administrador");
        titulo.setFont(new Font("Inter", Font.BOLD, 38));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.add(titulo);

        JPanel linea = new JPanel();
        linea.setBackground(new Color(255, 204, 0));
        linea.setMaximumSize(new Dimension(330, 2));
        linea.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.add(linea);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 80, 0, 0);
        add(titlePanel, gbc);
    }

    private void createActionButtons() {
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        String[] options = {"Gestionar insumos", "Registrar consumo", "Gestionar Menú", "Gestionar disponibilidad"};
        
        gbc.insets = new Insets(100, 100, 100, 100);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < options.length; i++) {
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            buttonsPanel.add(createDayPanel(options[i]), gbc);
        }

        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 2;
        gbcPanel.gridwidth = 2;
        gbcPanel.weightx = 1.0;
        gbcPanel.weighty = 1.0;
        gbcPanel.anchor = GridBagConstraints.CENTER;
        add(buttonsPanel, gbcPanel);
    }
    
    private JPanel createDayPanel(String option) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel lblOption = new JLabel(option, SwingConstants.CENTER);
        lblOption.setForeground(Color.WHITE);
        lblOption.setFont(new Font("Inter", Font.BOLD, 25));
        lblOption.setBounds(0, 0, 200, 30);
        lblOption.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblOption);
        
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        RoundedButton verMenu = new RoundedButton("Consultar", true);
        verMenu.setFont(new Font("Inter", Font.BOLD, 18));
        verMenu.setBounds(0, 0, 160, 30);
        verMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        verMenu.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Mostrar dashboard: " + option);
        });
        panel.add(verMenu);
        
        return panel;
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
        // This method is no longer used with GridBagLayout
    }

}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String fileName) {
        try {
            backgroundImage = new ImageIcon(getClass().getResource(fileName)).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}