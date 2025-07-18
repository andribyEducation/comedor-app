package com.ucv.comedor;
import views.Registro_Comensal_view.RegistroComensalView;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegistroComensalView view = new RegistroComensalView();
            // new AdminDashboardController(view);
            view.setVisible(true);
        });
    }
}
