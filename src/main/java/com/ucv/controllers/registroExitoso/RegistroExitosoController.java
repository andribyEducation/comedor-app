package com.ucv.controllers.registroExitoso;

import com.ucv.views.registroView.RegistroExitosoView;
import com.ucv.views.login.LoginView;
import com.ucv.controllers.login.LoginController;
import com.ucv.views.registroView.RegistroView;

public class RegistroExitosoController {

    private RegistroExitosoView view;
    private RegistroView registroView;

    public RegistroExitosoController(RegistroExitosoView view, RegistroView registroView) {
        this.view = view;
        this.registroView = registroView;
        initListeners();
    }

    private void initListeners() {
        view.getIngresarButton().addActionListener(e -> {
            view.setVisible(false);
            LoginView loginView = new LoginView();
            new LoginController(loginView, registroView);
            loginView.setVisible(true);
        });
    }
}
