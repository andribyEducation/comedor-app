package controllers.registroExitoso;

import views.registroComensalView.RegistroExitosoView;
import views.login.LoginView;
import controllers.LoginController;

public class RegistroExitosoController {

    private RegistroExitosoView view;

    public RegistroExitosoController(RegistroExitosoView view) {
        this.view = view;
        initListeners();
    }

    private void initListeners() {
        view.getIngresarButton().addActionListener(e -> {
            view.setVisible(false);
            LoginView loginView = new LoginView();
            new LoginController(loginView);
            loginView.setVisible(true);
        });
    }
}
