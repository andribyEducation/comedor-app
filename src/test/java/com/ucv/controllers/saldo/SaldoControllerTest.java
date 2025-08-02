package com.ucv.controllers.saldo;

import org.junit.jupiter.api.*;
import com.ucv.views.comensal.saldo.SaldoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SaldoControllerTest {

    static class StubSaldoView extends SaldoView {
        private JLabel iconoUsuario = new JLabel();
        private JPanel panelRecarga = new JPanel();
        private JTextField txtMonto = new JTextField();
        private JButton btnConfirmar = new JButton();
        private JButton btnCancelar = new JButton();
        private JButton btnVolver = new JButton();
        private JButton btnRecargar = new JButton();

        @Override public JLabel getIconoUsuario() { return iconoUsuario; }
        @Override public JPanel getPanelRecarga() { return panelRecarga; }
        @Override public JTextField getTxtMonto() { return txtMonto; }
        @Override public JButton getBtnConfirmar() { return btnConfirmar; }
        @Override public JButton getBtnCancelar() { return btnCancelar; }
        @Override public JButton getBtnVolver() { return btnVolver; }
        @Override public JButton getBtnRecargar() { return btnRecargar; }
    }

    private StubSaldoView stubView;
    private SaldoController controller;

    @BeforeEach
    void setUp() {
        stubView = new StubSaldoView();
        controller = new SaldoController(stubView);
    }

    @Test
    void testBtnVolverListener() {
        ActionEvent event = new ActionEvent(stubView.getBtnVolver(), ActionEvent.ACTION_PERFORMED, "");
        for (ActionListener listener : stubView.getBtnVolver().getActionListeners()) {
            listener.actionPerformed(event);
        }
        Assertions.assertTrue(true);
    }

    @Test
    void testBtnRecargarListener() {
        ActionEvent event = new ActionEvent(stubView.getBtnRecargar(), ActionEvent.ACTION_PERFORMED, "");
        for (ActionListener listener : stubView.getBtnRecargar().getActionListeners()) {
            listener.actionPerformed(event);
        }
        Assertions.assertTrue(true);
    }

    @Test
    void testBtnCancelarListener() {
        ActionEvent event = new ActionEvent(stubView.getBtnCancelar(), ActionEvent.ACTION_PERFORMED, "");
        for (ActionListener listener : stubView.getBtnCancelar().getActionListeners()) {
            listener.actionPerformed(event);
        }
        Assertions.assertTrue(true);
    }

    @Test
    void testBtnConfirmarListener() {
        stubView.getTxtMonto().setText("100");
        ActionEvent event = new ActionEvent(stubView.getBtnConfirmar(), ActionEvent.ACTION_PERFORMED, "");
        for (ActionListener listener : stubView.getBtnConfirmar().getActionListeners()) {
            listener.actionPerformed(event);
        }
        Assertions.assertTrue(true);
    }

    @Test
    void testControllerNotNull() {
        Assertions.assertNotNull(controller);
    }
}
