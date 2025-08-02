package com.ucv.controllers.saldo;

import com.ucv.views.comensal.saldo.SaldoView;
import com.ucv.views.comensal.consultaMenu.ConsultaMenu;

import javax.swing.*;
import java.awt.event.*;

public class SaldoController {

    private SaldoView view;

    public SaldoController(SaldoView view) {
        this.view = view;
        initListeners();
    }

    private void initListeners() {
        // Menú usuario (icono)
        view.getIconoUsuario().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JPopupMenu menu = view.getMenuUsuario();
                JLabel icon = view.getIconoUsuario();
                int x = -menu.getPreferredSize().width + icon.getWidth();
                int y = icon.getHeight();
                menu.show(icon, x, y);
            }
        });

        // Botón volver
        view.getBtnVolver().addActionListener(e -> {
            new ConsultaMenu().setVisible(true);
            view.dispose();
        });

        // Botón recargar saldo
        view.getBtnRecargar().addActionListener(e -> {
            view.getPanelRecarga().setVisible(true);
            view.getTxtMonto().setText("");
        });

        // Botón cancelar recarga
        view.getBtnCancelar().addActionListener(e -> {
            view.getPanelRecarga().setVisible(false);
            view.getTxtMonto().setText("");
        });

        // Botón confirmar recarga
        view.getBtnConfirmar().addActionListener(e -> {
            String monto = view.getTxtMonto().getText().trim();
            if (monto.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Ingrese un monto válido.");
                return;
            }
            // Aquí puedes agregar la lógica real de recarga
            JOptionPane.showMessageDialog(view, "Aun en desarrollo la lógica de recarga.");
            view.getPanelRecarga().setVisible(false);
            view.getTxtMonto().setText("");
        });
    }
}
