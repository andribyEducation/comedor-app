package com.ucv.comedor;
import javax.swing.SwingUtilities;
import views.home.Home;

public class App 
{
    public static void main( String[] args )
    {
         SwingUtilities.invokeLater(() -> {
        new Home().setVisible(true);
    });
    }
}
