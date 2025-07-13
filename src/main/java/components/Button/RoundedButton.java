package components.Button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import listeners.StyledButtonMouseListener;

public class RoundedButton extends JButton {

    private final Color normalBackgroundColor;
    private final Color darkBackgroundColor;
    private final Color hoverBackgroundColor;
    private final Color hoverDarkBackgroundColor;
    private final Color pressedBackgroundColor;
    private final Color pressedDarkBackgroundColor;
    private int cornerRadius = 50;

    public RoundedButton(String text, boolean bgDark) {
        super(text);

        // Definición de colores
        normalBackgroundColor = new Color(0, 51, 102);
        darkBackgroundColor = new Color(255, 210, 0);
        hoverBackgroundColor = new Color(0, 82, 153);
        hoverDarkBackgroundColor = new Color(255, 220, 55);
        pressedBackgroundColor = new Color(0, 31, 77);
        pressedDarkBackgroundColor = new Color(255, 170, 0);

        // Estilos base del botón
        setFont(loadCustomFont()); // <-- AQUÍ SE APLICA LA FUENTE
        setForeground(Color.WHITE);
        //ajustar tamaño del boton
        setPreferredSize(new java.awt.Dimension(200, 50));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Asigna el color de fondo inicial
        if (bgDark) {
            setBackground(darkBackgroundColor);
        } else {
            setBackground(normalBackgroundColor);
        }

        // Listener para efectos de hover y click
        addMouseListener(new StyledButtonMouseListener(this, bgDark,
                normalBackgroundColor, hoverBackgroundColor, hoverDarkBackgroundColor,
                darkBackgroundColor, pressedDarkBackgroundColor, pressedBackgroundColor));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        super.paintComponent(g2);
        g2.dispose();
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    // Método para cargar la fuente desde un archivo
    private static Font loadCustomFont() {
        try {
            // Carga la fuente y la devuelve con un tamaño de 14pt
            return Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Inter_18pt-SemiBold.ttf")).deriveFont(14f);
        } catch (IOException | FontFormatException e) {
            System.err.println("Error: No se pudo cargar la fuente 'Inter-Regular.ttf'. Usando fuente por defecto.");
            // Si falla, devuelve una fuente de respaldo para evitar que el programa crashee
            return new Font("Arial", Font.PLAIN, 14);
        }
    }
}