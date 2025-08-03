package com.ucv.controllers.login;

import com.ucv.views.admin.dashboards.AdminDashboardView;
import com.ucv.views.comensal.consultaMenu.ConsultaMenu;
import com.ucv.views.home.Home;
import com.ucv.views.login.LoginView;
import com.ucv.services.AuthService;
<<<<<<< HEAD
import com.ucv.views.registroView.RegistroView;

=======
import com.ucv.services.ConexionService;
>>>>>>> 7b6030e (Conexion service)
import javax.swing.JOptionPane;

import com.ucv.controllers.home.HomeController;
import com.ucv.models.Usuario;

public class LoginController {

    private LoginView view;
    protected AuthService authService;
<<<<<<< HEAD
    private RegistroView registroView;

=======
    private ConexionService conexionService = new ConexionService();
>>>>>>> 7b6030e (Conexion service)

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
    public LoginController(LoginView view, RegistroView registroView) {
        this.view = view;
        this.registroView = registroView;
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
                new com.ucv.controllers.adminDashboard.AdminDashboardController(adminDashboard);
                adminDashboard.setVisible(true);
            } else if ("comensal".equalsIgnoreCase(tipo)) {
                ConsultaMenu consultaMenu = new ConsultaMenu();
                // new ConsultaMenuController(consultaMenu);
                consultaMenu.setVisible(true);
            }
            setUsuario(cedula);
            view.setVisible(false);
            if (registroView != null) {
                registroView.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Cédula o contraseña incorrectos.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setUsuario(String cedula){
        try (java.io.BufferedReader reader = conexionService.obtenerLectorArchivo("data/comensales.txt")) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                System.out.println("Leyendo línea: " + line);
                if (parts.length >= 5 && parts[2].equals(cedula)) {
                    // parts[0]=correo, parts[1]=password, parts[2]=cedula, parts[3]=tipo, parts[4]=saldo
                    Usuario.setUsuarioActual(new Usuario(
                        parts[2], // cedula
                        parts[0], // correo
                        parts[3], // tipo
                        Integer.parseInt(parts[4]) // saldo
                    ));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleBack() {
        Home homeView = new Home();
        new HomeController(homeView);
        view.setVisible(false);
        homeView.setVisible(true);
    }
}