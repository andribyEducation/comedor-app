package com.ucv.controllers.registroExitoso;

import com.ucv.views.registroView.RegistroExitosoView;
import com.ucv.views.login.LoginView;
import com.ucv.controllers.login.LoginController;

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
