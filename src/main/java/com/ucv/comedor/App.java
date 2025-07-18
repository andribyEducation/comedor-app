package com.ucv.comedor;
import views.home.Home;
import javax.swing.SwingUtilities;
import controllers.home.HomeController;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Home view = new Home();
            new HomeController(view);
            view.setVisible(true);
        });
    }
}
