package components.TextInput;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class TextInput extends JPanel {

    private JTextPane textPane;
    private JLabel label;
    private String validationRegex;

    public TextInput(String description) {
        this(description, 1, 20);
    }

    public TextInput(String description, int rows, int columns) {
        super(new BorderLayout(0, 5));
        setOpaque(false);

        label = new JLabel(description);
        label.setFont(new Font("Inter", Font.PLAIN, 18));
        label.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));

        textPane = new JTextPane();
        initializeTextPane();

        add(label, BorderLayout.NORTH);
        add(textPane, BorderLayout.CENTER);
    }

    private void initializeTextPane() {
        textPane.setBorder(new RoundedCornerBorder());
        // Hacemos el JTextPane transparente para que no pinte un fondo gris.
        textPane.setOpaque(false);
        textPane.setBackground(new Color(0,0,0,0));
    }

    public void setTextPaneOpaque(boolean isOpaque) {
        textPane.setOpaque(isOpaque);
    }

    public String getText() {
        return textPane.getText();
    }

    public void setText(String text) {
        textPane.setText(text);
    
    }

    public void setValidationRegex(String regex) {
        this.validationRegex = regex;
    }

    public boolean isValidInput() {
        String input = getText();
        if (validationRegex != null) {
            return input.matches(validationRegex);
        }
        return input != null && !input.trim().isEmpty();
    }

    // Clase interna para un borde redondeado más robusto que maneja el fondo.
    private static class RoundedCornerBorder extends AbstractBorder {
        private static final Color ALPHA_ZERO = new Color(0x0, true);
        private final int cornerRadius = 50;
        private final Insets insets = new Insets(13, 12, 13, 12); // Top, Left, Bottom, Right padding

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Dibuja solo el borde, sin rellenar el fondo.
            Shape border = new RoundRectangle2D.Double(x, y, width - 1, height - 1, cornerRadius, cornerRadius);
            
            g2.setColor(Color.GRAY);
            g2.draw(border);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return insets;
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.set(this.insets.top, this.insets.left, this.insets.bottom, this.insets.right);
            return insets;
        }
    }
}