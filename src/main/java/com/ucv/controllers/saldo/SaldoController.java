package com.ucv.controllers.saldo;

import com.ucv.views.comensal.saldo.SaldoView;
import com.ucv.views.comensal.consultaMenu.ConsultaMenu;

import javax.swing.*;

public class SaldoController {

    private SaldoView view;

    public SaldoController(SaldoView view) {
        this.view = view;
        initListeners();
    }

    private void initListeners() {

        // Cambiar contraseña
        view.getCambiarContrasenaItem().addActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Lógica para cambiar contraseña.");
        });

        // Reportar problema
        view.getReportarProblemaItem().addActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Aquí va la lógica para reportar un problema.");
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
