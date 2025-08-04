package com.ucv.comedor;
import com.ucv.views.home.Home;
import com.ucv.controllers.home.HomeController;
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
