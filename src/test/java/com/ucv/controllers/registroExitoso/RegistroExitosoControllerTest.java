package com.ucv.controllers.registroExitoso;

import org.junit.jupiter.api.*;
import com.ucv.views.registroView.RegistroExitosoView;
import com.ucv.components.Button.RoundedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RegistroExitosoControllerTest {

    static class StubRegistroExitosoView extends RegistroExitosoView {
        private boolean visible = true;
        private RoundedButton ingresarButton = new RoundedButton("Ingresar", false);

        @Override
        public void setVisible(boolean b) { visible = b; }
        @Override
        public RoundedButton getIngresarButton() { return ingresarButton; }
    }

    private StubRegistroExitosoView stubView;
    private RegistroExitosoController controller;

    @BeforeEach
    void setUp() {
        stubView = new StubRegistroExitosoView();
        controller = new RegistroExitosoController(stubView);
    }

    @Test
    void testIngresarButtonListener() {
        ActionEvent event = new ActionEvent(stubView.getIngresarButton(), ActionEvent.ACTION_PERFORMED, "");
        for (ActionListener listener : stubView.getIngresarButton().getActionListeners()) {
            listener.actionPerformed(event);
        }
        Assertions.assertFalse(stubView.visible); // La vista debe ocultarse
    }
}
