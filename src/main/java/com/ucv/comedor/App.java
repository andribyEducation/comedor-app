package com.ucv.comedor;
import views.login.LoginView;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           LoginView loginView = new LoginView();
            loginView.setVisible(true);
        });
    }
}
