package com.ucv.listeners;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class StyledButtonMouseListener extends MouseAdapter {

    private final JButton button;
    private final boolean bgDark;
    private final Color normalBackgroundColor;
    private final Color hoverBackgroundColor;
    private final Color hoverDarkBackgroundColor;
    private final Color darkBackgroundColor;
    private final Color pressedDarkBackgroundColor;
    private final Color pressedBackgroundColor;

    public StyledButtonMouseListener(JButton button, boolean bgDark,
                                     Color normalBackgroundColor, Color hoverBackgroundColor,
                                     Color hoverDarkBackgroundColor, Color darkBackgroundColor,
                                     Color pressedDarkBackgroundColor, Color pressedBackgroundColor) {
        this.button = button;
        this.bgDark = bgDark;
        this.normalBackgroundColor = normalBackgroundColor;
        this.hoverBackgroundColor = hoverBackgroundColor;
        this.hoverDarkBackgroundColor = hoverDarkBackgroundColor;
        this.darkBackgroundColor = darkBackgroundColor;
        this.pressedDarkBackgroundColor = pressedDarkBackgroundColor;
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (bgDark) {
            button.setBackground(hoverDarkBackgroundColor);
        } else {
            button.setBackground(hoverBackgroundColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (bgDark) {
            button.setBackground(darkBackgroundColor);
        } else {
            button.setBackground(normalBackgroundColor);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (bgDark) {
            button.setBackground(pressedDarkBackgroundColor);
        } else {
            button.setBackground(pressedBackgroundColor);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseEntered(e); // Vuelve al color de hover cuando se suelta el click
    }
}
