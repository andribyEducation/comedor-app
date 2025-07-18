package views.login;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import components.TextInput.TextInput;
import components.Button.RoundedButton;
import controllers.LoginController;

public class LoginView extends JFrame {

    private TextInput inputCedula;
    private TextInput inputContrasena;
    private RoundedButton loginButton;
    private LoginController controller;
    private JLabel logo;

    public LoginView() {
        setupUI();
    }

    public LoginView(LoginController controller) {
        this.controller = controller;
        setupUI();
        addListeners();
    }

    private void setupUI() {
        setTitle("Ingreso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);

        //Agregar flecha hacia la izquierda

        java.net.URL logoUrl = getClass().getResource("/assets/logos/logoucv.png");
        if (logoUrl != null) {
            ImageIcon originalIcon = new ImageIcon(logoUrl);
        // Define el nuevo ancho y alto según necesites.
        int newSize = 200;
        Image scaledImage = originalIcon.getImage().getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logo = new JLabel(scaledIcon);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
       mainPanel.add(logo);
        } else {
            System.err.println("Couldn't find resource: /assets/logos/Logo_Universidad_Central_de_Venezuela.svg.png");
            JLabel logo = new JLabel("Logo not found");
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);
           mainPanel.add(logo);
        }

        //topBar.add(backArrow, BorderLayout.WEST);
        topBar.add(logo, BorderLayout.EAST);

        gbc.insets = new Insets(0, 0, 50, 0);
        mainPanel.add(topBar, gbc);

        JLabel titleLabel = new JLabel("Ingreso");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 42));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets = new Insets(0, 0, 50, 0);
        mainPanel.add(titleLabel, gbc);

        inputCedula = new TextInput("Ingrese su cédula");
        gbc.insets = new Insets(0, 0, 25, 0);
        mainPanel.add(inputCedula, gbc);

        inputContrasena = new TextInput("Ingrese su contraseña");
        gbc.insets = new Insets(0, 0, 25, 0);
        mainPanel.add(inputContrasena, gbc);

        loginButton = new RoundedButton("Ingresar", true); // Asumiendo 'true' como segundo argumento
        loginButton.setPreferredSize(new Dimension(150, loginButton.getPreferredSize().height));
        gbc.insets = new Insets(0, 0, 50, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);
    }

    private void addListeners() {
        loginButton.addActionListener(e -> {
            if (controller != null) {
                controller.handleLogin(inputCedula.getText(), inputContrasena.getText());
            }
        });

        // Si backArrow fuera un miembro de la clase, podrías añadirle un listener aquí
        // backArrow.addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mouseClicked(MouseEvent e) {
        //         if (controller != null) {
        //             controller.handleBack();
        //         }
        //     }
        // });
    }

    public String getCedula() {
        return inputCedula.getText();
    }

    public String getContrasena() {
        return inputContrasena.getText();
    }
}
