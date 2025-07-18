// Nombre del archivo: AdminDashboardView.java
package views.admin.dashboards;

import components.Button.RoundedButton;
import components.TextInput.TextInput;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AdminDashboardView extends JFrame {

    private JMenuItem cambiarContrasenaItem;
    private JMenuItem reportarProblemaItem;
    private JLabel iconoUsuario;
    private JPopupMenu menuUsuario;
    private final Map<String, RoundedButton> actionButtons = new HashMap<>();

    public AdminDashboardView() {
        setupUI();
        createHeader();
        createTitle();
        createActionButtons();

        // Acción para botón CCB
        actionButtons.get("CCB").addActionListener(e -> {
            new VentanaCCB();
        });
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
        Image imagenEscalada = iconoLogo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        headerPanel.add(new JLabel(new ImageIcon(imagenEscalada)));

        iconoUsuario = new JLabel();
        iconoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/assets/logos/usericon.png"));
        Image iconoEscalado = iconoOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        iconoUsuario.setIcon(new ImageIcon(iconoEscalado));
        headerPanel.add(iconoUsuario);

        menuUsuario = createUserMenu();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(20, 0, 0, 20);
        add(headerPanel, gbc);
    }

    private JPopupMenu createUserMenu() {
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

        JLabel nombreUsuario = new JLabel("Administrador: Nombre");
        nombreUsuario.setFont(new Font("Inter", Font.BOLD, 18));
        nombreUsuario.setForeground(Color.WHITE);
        menu.add(nombreUsuario);
        menu.add(Box.createVerticalStrut(12));

        cambiarContrasenaItem = new JMenuItem("Cambiar contraseña");
        personalizarItem(cambiarContrasenaItem);
        menu.add(cambiarContrasenaItem);

        reportarProblemaItem = new JMenuItem("Reportar problema");
        personalizarItem(reportarProblemaItem);
        menu.add(reportarProblemaItem);

        return menu;
    }

    private void createTitle() {
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Dashboard de Administrador");
        titulo.setFont(new Font("Inter", Font.BOLD, 48));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.add(titulo);

        JPanel linea = new JPanel();
        linea.setBackground(new Color(255, 204, 0));
        linea.setMaximumSize(new Dimension(430, 4));
        linea.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.add(linea);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 80, 0, 0);
        add(titlePanel, gbc);
    }

    private void createActionButtons() {
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        String[] options = {
            "Gestionar insumos",
            "Registrar consumo",
            "Gestionar Menú",
            "Gestionar disponibilidad",
            "CCB"
        };

        String[] buttonTexts = {
            "Consultar",
            "Consultar",
            "Consultar",
            "Consultar",
            "Calcular"
        };

        gbc.insets = new Insets(100, 100, 100, 100);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < options.length; i++) {
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            buttonsPanel.add(createDayPanel(options[i], buttonTexts[i]), gbc);
        }

        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.gridx = 0; gbcPanel.gridy = 2; gbcPanel.gridwidth = 2;
        gbcPanel.weightx = 1.0; gbcPanel.weighty = 1.0;
        gbcPanel.anchor = GridBagConstraints.CENTER;
        add(buttonsPanel, gbcPanel);
    }

    private JPanel createDayPanel(String option, String buttonText) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblOption = new JLabel(option, SwingConstants.CENTER);
        lblOption.setForeground(Color.WHITE);
        lblOption.setFont(new Font("Inter", Font.BOLD, 30));
        lblOption.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblOption);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        RoundedButton actionButton = new RoundedButton(buttonText, true);
        actionButton.setFont(new Font("Inter", Font.BOLD, 22));
        actionButton.setPreferredSize(new Dimension(600, 60));
        actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        actionButtons.put(option, actionButton);
        panel.add(actionButton);

        return panel;
    }

    private void personalizarItem(JMenuItem item) {
        item.setBackground(new Color(50, 50, 50));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Inter", Font.BOLD, 18));
        item.setOpaque(true);
        item.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public JMenuItem getCambiarContrasenaItem() { return cambiarContrasenaItem; }
    public JMenuItem getReportarProblemaItem() { return reportarProblemaItem; }
    public JLabel getIconoUsuario() { return iconoUsuario; }
    public JPopupMenu getMenuUsuario() { return menuUsuario; }
    public Map<String, RoundedButton> getActionButtons() { return actionButtons; }

    public void display() {
        setVisible(true);
    }

    // VENTANA CCB INTERNA
    class VentanaCCB extends JFrame {
        public VentanaCCB() {
            setTitle("Costo Cubierto por Bandeja (CCB)");
            setSize(700, 600);
            setLocationRelativeTo(null);
            setLayout(new GridBagLayout());
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
            mainPanel.setBackground(Color.WHITE);

            JLabel lblFijos = new JLabel("Costos Fijos:");
            lblFijos.setFont(new Font("Inter", Font.BOLD, 20));
            lblFijos.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(lblFijos);

            JLabel descFijos = new JLabel("Aquí se mostrará la información de costos fijos.");
            descFijos.setFont(new Font("Inter", Font.PLAIN, 14));
            descFijos.setForeground(Color.GRAY);
            descFijos.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(descFijos);
            mainPanel.add(Box.createVerticalStrut(10));

            TextInput inputFijos = new TextInput("Ingrese costo fijo");
            mainPanel.add(inputFijos);
            mainPanel.add(Box.createVerticalStrut(30));

            JLabel lblVariables = new JLabel("Costos Variables:");
            lblVariables.setFont(new Font("Inter", Font.BOLD, 20));
            lblVariables.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(lblVariables);

            JLabel descVariables = new JLabel("Aquí se mostrará la información de costos variables.");
            descVariables.setFont(new Font("Inter", Font.PLAIN, 14));
            descVariables.setForeground(Color.GRAY);
            descVariables.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(descVariables);
            mainPanel.add(Box.createVerticalStrut(10));

            TextInput inputVariables = new TextInput("Ingrese costo variable");
            mainPanel.add(inputVariables);
            mainPanel.add(Box.createVerticalStrut(40));

            RoundedButton btnCalcular = new RoundedButton("Calcular", true);
            btnCalcular.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCalcular.setPreferredSize(new Dimension(150, 45));
            mainPanel.add(btnCalcular);

            btnCalcular.addActionListener(e -> {
                String fijo = inputFijos.getText();
                String variable = inputVariables.getText();
                JOptionPane.showMessageDialog(this, "Lógica aún no implementada. Fijo: " + fijo + ", Variable: " + variable);
            });

            add(mainPanel);
            setVisible(true);
        }
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String fileName) {
        try {
            backgroundImage = new ImageIcon(getClass().getResource(fileName)).getImage();
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen de fondo: " + fileName);
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
