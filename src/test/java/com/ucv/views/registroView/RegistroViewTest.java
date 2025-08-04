package com.ucv.views.registroView;

import com.ucv.components.Button.RoundedButton;
import com.ucv.components.CheckBox.CheckBox;
import com.ucv.components.TextInput.TextInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.*;

class RegistroViewTest {

    private RegistroView registroView;

    @BeforeEach
    void setUp() {
        registroView = new RegistroView();
    }

    @Test
    void testGetTipoComensal() {
        CheckBox comensalCheck = (CheckBox) getCheckBox(registroView, "comensalCheck");
        comensalCheck.setSelected(true);
        assertEquals("comensal", registroView.getTipo());
    }

    @Test
    void testGetTipoAdministrador() {
        CheckBox adminCheck = (CheckBox) getCheckBox(registroView, "adminCheck");
        adminCheck.setSelected(true);
        assertEquals("administrador", registroView.getTipo());
    }

    @Test
    void testGetTipoNinguno() {
        CheckBox comensalCheck = (CheckBox) getCheckBox(registroView, "comensalCheck");
        CheckBox adminCheck = (CheckBox) getCheckBox(registroView, "adminCheck");
        comensalCheck.setSelected(false);
        adminCheck.setSelected(false);
        assertEquals("", registroView.getTipo());
    }

    @Test
    void testGetRegisterButton() {
        assertNotNull(registroView.getRegisterButton());
        assertTrue(registroView.getRegisterButton() instanceof RoundedButton);
    }

    @Test
    void testGetBackLabel() {
        assertNotNull(registroView.getBackLabel());
        assertTrue(registroView.getBackLabel() instanceof JLabel);
    }

    @Test
    void testAddBackListener() {
        JLabel backLabel = registroView.getBackLabel();
        MouseAdapter adapter = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {}
        };
        int before = backLabel.getMouseListeners().length;
        registroView.addBackListener(adapter);
        int after = backLabel.getMouseListeners().length;
        assertEquals(before + 1, after);
    }

    // Métodos utilitarios para acceder a campos privados
    private Object getTextInputField(RegistroView view, String fieldName) {
        try {
            java.lang.reflect.Field field = RegistroView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            TextInput input = (TextInput) field.get(view);
            java.lang.reflect.Field textField = TextInput.class.getDeclaredField("textField");
            textField.setAccessible(true);
            return textField.get(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object getCheckBox(RegistroView view, String fieldName) {
        try {
            java.lang.reflect.Field field = RegistroView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(view);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
