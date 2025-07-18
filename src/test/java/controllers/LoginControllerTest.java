package controllers;

import org.junit.jupiter.api.*;

import views.login.LoginView;
import services.AuthService;

class LoginControllerTest {

    static class StubLoginView extends LoginView {
        private String cedula;
        private String contrasena;
        private boolean visible = true;
        public String mensajeMostrado = null;

        public void setCedula(String cedula) { this.cedula = cedula; }
        public void setContrasena(String contrasena) { this.contrasena = contrasena; }
        @Override public String getCedula() { return cedula; }
        @Override public String getContrasena() { return contrasena; }
        @Override public void setVisible(boolean b) { visible = b; }
    }

    static class StubAuthService extends AuthService {
        private String tipoRetorno;
        public void setTipoRetorno(String tipo) { this.tipoRetorno = tipo; }
        @Override
        public String autenticarYObtenerTipo(String cedula, String contrasena) {
            return tipoRetorno;
        }
    }

    private StubLoginView stubView;
    private StubAuthService stubAuthService;
    private LoginController controller;

    @BeforeEach
    void setUp() {
        stubView = new StubLoginView();
        stubAuthService = new StubAuthService();
        controller = new LoginController(stubView) {
            {
                this.authService = stubAuthService;
            }
        };
    }

    @Test
    void testLoginAdministrador() {
        stubView.setCedula("1234");
        stubView.setContrasena("pass");
        stubAuthService.setTipoRetorno("administrador");
        controller.handleLogin("1234", "pass");
        Assertions.assertFalse(stubView.visible); // La vista debe ocultarse
    }

    @Test
    void testLoginComensal() {
        stubView.setCedula("1234");
        stubView.setContrasena("pass");
        stubAuthService.setTipoRetorno("comensal");
        controller.handleLogin("1234", "pass");
        Assertions.assertFalse(stubView.visible); // La vista debe ocultarse
    }

    @Test
    void testLoginIncorrecto() {
        stubView.setCedula("1234");
        stubView.setContrasena("pass");
        stubAuthService.setTipoRetorno(null);
        controller.handleLogin("1234", "pass");
        Assertions.assertTrue(stubView.visible); // La vista debe seguir visible
    }
}
