package com.ucv.controllers.login;

import com.ucv.views.admin.dashboards.AdminDashboardView;
import com.ucv.views.comensal.consultaMenu.ConsultaMenu;
import com.ucv.views.home.Home;
import com.ucv.views.login.LoginView;
import com.ucv.services.AuthService;
import com.ucv.views.registroView.RegistroView;
import javax.swing.JOptionPane;
import com.ucv.controllers.home.HomeController;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;
import com.ucv.models.Usuario;

public class LoginController {

    private LoginView view;
    protected AuthService authService;
    private RegistroView registroView;

    private static final String ADMINS_DATA_PATH = "data/admins.json";
    private static final String COMENSALES_DATA_PATH = "data/comensales.json";

    public LoginController(LoginView view) {
        this.view = view;
        this.authService = new AuthService();

        // Listener para el botón de login
        this.view.getLoginButton().addActionListener(e -> {
            String cedula = view.getCedula();
            String contrasena = view.getContrasena();
            String tipo = view.getTipo();
            if (tipo == null || tipo.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Debe seleccionar un tipo de usuario.", "Tipo de usuario requerido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            handleLogin(cedula, contrasena, tipo);
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
            String tipo = view.getTipo();
            if (tipo == null || tipo.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Debe seleccionar un tipo de usuario.", "Tipo de usuario requerido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            handleLogin(cedula, contrasena, tipo);
        });

        this.view.getBackLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                handleBack();
            }
        });
    }

    public void handleLogin(String cedula, String contrasena, String tipo) {
        if (tipo != null && authService.autenticar(cedula, contrasena, tipo)) {
            setUsuario(cedula, tipo);
            Usuario usuarioActual = Usuario.getUsuarioActual();
            String nombreCompleto = "";
            String rol = "";
            if (usuarioActual != null) {
                nombreCompleto = usuarioActual.getNombre() + " " + usuarioActual.getApellido();
                rol = usuarioActual.getRol();
            }

            String displayText = rol + ": " + nombreCompleto;

            if ("administrador".equalsIgnoreCase(tipo)) {
                AdminDashboardView adminDashboard = new AdminDashboardView(displayText);
                new com.ucv.controllers.adminDashboard.AdminDashboardController(adminDashboard);
                adminDashboard.setVisible(true);
            } else if ("comensal".equalsIgnoreCase(tipo)) {
                ConsultaMenu consultaMenu = new ConsultaMenu(displayText);
                // new ConsultaMenuController(consultaMenu);
                consultaMenu.setVisible(true);
            }
            setUsuario(cedula, tipo);
            if (view != null) {
                view.setVisible(false);
            }
            if (registroView != null) {
                registroView.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Cédula o contraseña incorrectos.", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setUsuario(String cedula, String tipo){
        String dataPath = "administrador".equalsIgnoreCase(tipo) ? ADMINS_DATA_PATH : COMENSALES_DATA_PATH;
        try {
            String content = new String(Files.readAllBytes(Paths.get(dataPath)));
            JSONArray users = new JSONArray(content);

            String nombre = "";
            String apellido = "";
            String rol = "";

            // Buscar datos en secretaria.json
            try {
                String secretariaContent = new String(Files.readAllBytes(Paths.get("data/secretaria.json")));
                JSONArray secretarias = new JSONArray(secretariaContent);
                for (int j = 0; j < secretarias.length(); j++) {
                    JSONObject persona = secretarias.getJSONObject(j);
                    if (cedula.equals(persona.optString("cedula"))) {
                        nombre = persona.optString("nombre", "");
                        apellido = persona.optString("apellido", "");
                        rol = persona.optString("tipo", "");
                        break;
                    }
                }
            } catch (Exception ex) {
                // Si no se encuentra, deja los campos vacíos
            }

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                String userCedula = user.getString("ID");
                if (userCedula.equals(cedula)) {
                    String correo = user.getString("correo");
                    Usuario usuario;
                    if ("comensal".equalsIgnoreCase(tipo)) {
                        double saldo = user.has("saldo") ? user.getDouble("saldo") : 0.0;
                        usuario = new Usuario(userCedula, correo, tipo, nombre, apellido);
                        usuario.setSaldo(saldo);
                        usuario.setRol(rol);
                    } else {
                        usuario = new Usuario(userCedula, correo, tipo, nombre, apellido);
                        usuario.setRol(rol);
                    }
                    Usuario.setUsuarioActual(usuario);
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