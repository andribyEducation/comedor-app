package com.ucv.components.Backgrounds;

import javax.swing.*;
import java.awt.*;

public class DarkBackground extends JPanel {

    private Image backgroundImage;
    private String imagePath = "/assets/Background/Gradient Background #2.png";
    public DarkBackground() {
        try {
            // Carga la imagen desde el classpath (idealmente desde la carpeta 'resources')
            this.backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen de fondo: " + imagePath);
            e.printStackTrace();
            // Si la imagen no se encuentra, usa un fondo negro sólido como respaldo.
            setBackground(Color.BLACK);
        }
        // Es recomendable usar un layout manager para poder añadir otros componentes encima.
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja la imagen de fondo, escalándola para que llene todo el panel.
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
