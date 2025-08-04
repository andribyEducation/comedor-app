package com.ucv.controllers.comensal;

import com.ucv.views.comensal.menusReservados.MenusReservadosView;
import com.ucv.models.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.table.DefaultTableModel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MenusReservadosController {

    private MenusReservadosView view;

    public MenusReservadosController(MenusReservadosView view) {
        this.view = view;
        cargarReservas();
    }

    private void cargarReservas() {
        Usuario usuario = Usuario.getUsuarioActual();
        if (usuario == null) return;

        try {
            String content = new String(Files.readAllBytes(Paths.get("data/reservas.json")));
            JSONArray reservas = new JSONArray(content);
            DefaultTableModel model = view.getTableModel();
            model.setRowCount(0);

            // Ordenar reservas: lunes a viernes, desayuno antes que almuerzo
            String[] diasSemana = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
            String[] tipos = {"desayuno", "almuerzo"};

            for (String dia : diasSemana) {
                for (String tipo : tipos) {
                    for (int i = 0; i < reservas.length(); i++) {
                        JSONObject r = reservas.getJSONObject(i);
                        if (
                            r.optString("cedula").equals(usuario.getCedula()) &&
                            r.optString("dia").equalsIgnoreCase(dia) &&
                            r.optString("tipo").equalsIgnoreCase(tipo)
                        ) {
                            model.addRow(new Object[]{
                                r.optString("dia"),
                                r.optString("tipo"),
                                r.optDouble("precio"),
                                r.optInt("posicionCola")
                            });
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
