package com.ucv.comedor;
import views.home.Home;
import controllers.home.HomeController;
import javax.swing.SwingUtilities;
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Home view = new Home();
            new HomeController(view);
            view.setVisible(true);
        });
    }
}
