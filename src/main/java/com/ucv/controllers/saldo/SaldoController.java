package com.ucv.controllers.saldo;

import com.ucv.views.comensal.saldo.SaldoView;
import com.ucv.views.comensal.consultaMenu.ConsultaMenu;
import com.ucv.models.Usuario;
import com.ucv.services.AuthService;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaldoController {

    private SaldoView view;
    private String displayText;

    public SaldoController(SaldoView view, String displayText) {
        this.view = view;
        this.displayText = displayText;
        initListeners();
    }

    private void initListeners() {

        // Botón volver
        view.getBtnVolver().addActionListener(e -> {
            new ConsultaMenu(displayText).setVisible(true);
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
            try {
                double montoRecarga = Double.parseDouble(monto);
                if (montoRecarga <= 0) {
                    JOptionPane.showMessageDialog(view, "El monto a recargar debe ser mayor a 0.");
                    return;
                }

                Usuario usuarioActual = Usuario.getUsuarioActual();
                double saldoActual = usuarioActual.getSaldo();
                double nuevoSaldo = saldoActual + montoRecarga;
                usuarioActual.setSaldo(nuevoSaldo);

                view.getSaldoLabel().setText(String.format("%.2f Bs", nuevoSaldo));
                JOptionPane.showMessageDialog(view, "Recarga exitosa. Nuevo saldo: " + String.format("%.2f Bs", nuevoSaldo));

                // Save the updated balance to the data file
                try {
                    String comensalesContent = new String(Files.readAllBytes(Paths.get("data/comensales.json")));
                    JSONArray comensalesArray = new JSONArray(comensalesContent);

                    for (int i = 0; i < comensalesArray.length(); i++) {
                        JSONObject comensal = comensalesArray.getJSONObject(i);
                        if (comensal.getString("ID").equals(usuarioActual.getID())) {
                            comensal.put("saldo", nuevoSaldo);
                            break;
                        }
                    }
                    AuthService.saveComensalesData(comensalesArray);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    JOptionPane.showMessageDialog(view, "Error al guardar los datos de saldo.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Ingrese un monto numérico válido.");
            } finally {
                view.getPanelRecarga().setVisible(false);
                view.getTxtMonto().setText("");
            }
        });
    }
}
