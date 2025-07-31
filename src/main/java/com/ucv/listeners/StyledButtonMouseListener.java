package com.ucv.listeners;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class StyledButtonMouseListener extends MouseAdapter {

    private final JButton button;
    private final Color normalBackgroundColor;
    private final Color hoverBackgroundColor;
    private final Color pressedBackgroundColor;

    private final boolean border;

    public StyledButtonMouseListener(JButton button, boolean border,
            Color normalBackgroundColor, Color hoverBackgroundColor,
            Color pressedBackgroundColor) {
        this.button = button;
        this.border = border;
        this.normalBackgroundColor = normalBackgroundColor;
        this.hoverBackgroundColor = hoverBackgroundColor;
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!border) {
            button.setBackground(hoverBackgroundColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!border) {
            button.setBackground(normalBackgroundColor);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!border) {
            button.setBackground(pressedBackgroundColor);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!border) {
            mouseEntered(e); // Vuelve al color de hover cuando se suelta el click
        }
    }

}
