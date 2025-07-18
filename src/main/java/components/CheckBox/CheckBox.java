package components.CheckBox;

import javax.swing.*;
import java.awt.*;

public class CheckBox extends JCheckBox {

    public CheckBox(String text) {
        super(text);

        setOpaque(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Personaliza el ícono seleccionado/no seleccionado
        setIcon(new CustomIcon(false, Color.BLACK));
        setSelectedIcon(new CustomIcon(true, Color.BLACK));
    }

    public CheckBox(String text, Color checkColor) {
        super(text);

        setOpaque(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Personaliza el ícono seleccionado/no seleccionado
        setIcon(new CustomIcon(false, checkColor));
        setSelectedIcon(new CustomIcon(true, checkColor));
    }

    // Clase interna para crear íconos personalizados
    private static class CustomIcon implements Icon {
        private final boolean selected;
        private final int size = 16;
        private final Color checkColor;

        public CustomIcon(boolean selected, Color checkColor) {
            this.selected = selected;
            this.checkColor = checkColor;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Caja del checkbox
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(x, y, size, size, 4, 4);
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(x, y, size, size, 4, 4);

            // Check
            if (selected) {
                g2.setColor(checkColor);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(x + 4, y + 8, x + 7, y + 12);
                g2.drawLine(x + 7, y + 12, x + 12, y + 4);
            }
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }
    }
}