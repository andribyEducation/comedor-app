// Nombre del archivo: AdminDashboardView.java
package views.admin.dashboards;

import components.Button.RoundedButton; // Se asume que esta clase personalizada existe
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * La clase Vista se encarga exclusivamente de la presentación de la interfaz de usuario.
 * No contiene lógica de eventos; solo construye y muestra los componentes.
 */
public class AdminDashboardView extends JFrame {

    // Componentes que el controlador necesitará manipular
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
    }

    // --- MÉTODOS DE CONFIGURACIÓN DE LA UI ---

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

        // Logo UCV
        ImageIcon iconoLogo = new ImageIcon(getClass().getResource("/assets/logos/logoucv.png"));
        Image imagenEscalada = iconoLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        headerPanel.add(new JLabel(new ImageIcon(imagenEscalada)));

        // Icono de usuario
        iconoUsuario = new JLabel();
        iconoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/assets/logos/usericon.png"));
        Image iconoEscalado = iconoOriginal.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
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
        menu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menu.setOpaque(false);

        JLabel nombreUsuario = new JLabel("Usuario: Juan Pérez");
        nombreUsuario.setFont(new Font("Inter", Font.BOLD, 15));
        nombreUsuario.setForeground(Color.WHITE);
        menu.add(nombreUsuario);
        menu.add(Box.createVerticalStrut(8));

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
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
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
        gbcPanel.gridx = 0; gbcPanel.gridy = 2; gbcPanel.gridwidth = 2;
        gbcPanel.weightx = 1.0; gbcPanel.weighty = 1.0;
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
        lblOption.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblOption);
        
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        RoundedButton consultarButton = new RoundedButton("Consultar", true);
        consultarButton.setFont(new Font("Inter", Font.BOLD, 18));
        consultarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Almacena el botón para que el controlador pueda añadirle un listener
        actionButtons.put(option, consultarButton);
        panel.add(consultarButton);
        
        return panel;
    }
    
    private void personalizarItem(JMenuItem item) {
        item.setBackground(new Color(50, 50, 50));
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Inter", Font.BOLD, 15));
        item.setOpaque(true);
        item.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // --- GETTERS PARA EL CONTROLADOR ---

    public JMenuItem getCambiarContrasenaItem() { return cambiarContrasenaItem; }
    public JMenuItem getReportarProblemaItem() { return reportarProblemaItem; }
    public JLabel getIconoUsuario() { return iconoUsuario; }
    public JPopupMenu getMenuUsuario() { return menuUsuario; }
    public Map<String, RoundedButton> getActionButtons() { return actionButtons; }

    // Método para hacer visible la ventana
    public void display() {
        setVisible(true);
    }
}

/**
 * Clase auxiliar para dibujar un fondo con imagen. Es parte de la Vista.
 */
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