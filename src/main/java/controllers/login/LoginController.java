package controllers.login;

import views.admin.dashboards.AdminDashboardView;
import views.comensal.consultaMenu.ConsultaMenu;
import views.home.Home;
import views.login.LoginView;
import services.AuthService;
import javax.swing.JOptionPane;

import controllers.home.HomeController;

public class LoginController {

    private LoginView view;
    protected AuthService authService;

    public LoginController(LoginView view) {
        this.view = view;
        this.authService = new AuthService();

        // Listener para el botón de login
        this.view.getLoginButton().addActionListener(e -> {
            String cedula = view.getCedula();
            String contrasena = view.getContrasena();
            handleLogin(cedula, contrasena);
        });

        this.view.getBackLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                handleBack();
            }
        });
    }

    public void handleLogin(String cedula, String contrasena) {
        String tipo = authService.autenticarYObtenerTipo(cedula, contrasena);
        if (tipo != null) {
            if ("administrador".equalsIgnoreCase(tipo)) {
                AdminDashboardView adminDashboard = new AdminDashboardView();
                new controllers.adminDashboard.AdminDashboardController(adminDashboard);
                adminDashboard.setVisible(true);
            } else if ("comensal".equalsIgnoreCase(tipo)) {
                ConsultaMenu consultaMenu = new ConsultaMenu();
                // new ConsultaMenuController(consultaMenu);
                consultaMenu.setVisible(true);
            }
            view.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(view, "Cédula o contraseña incorrectos.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleBack() {
        Home homeView = new Home();
        new HomeController(homeView);
        view.setVisible(false);
        homeView.setVisible(true);
    }
}