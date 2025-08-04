package com.ucv.controllers.admin.reservas;

import com.ucv.views.admin.reservas.GestionarReservasView;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.table.DefaultTableModel;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
public class GestionarReservasController {

    private GestionarReservasView view;
    private JSONArray reservas;

    public GestionarReservasController(GestionarReservasView view) {
        this.view = view;
        cargarReservas();
        addFilterListeners();
        addImageButtonListeners();
    }

    private void cargarReservas() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("data/reservas.json")));
            reservas = new JSONArray(content);
            actualizarTabla("Todos", "Todos");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addFilterListeners() {
        JComboBox<String> diaCombo = view.getDiaCombo();
        JComboBox<String> tipoCombo = view.getTipoCombo();

        diaCombo.addActionListener(e -> actualizarTabla(
                (String) diaCombo.getSelectedItem(),
                (String) tipoCombo.getSelectedItem()
        ));
        tipoCombo.addActionListener(e -> actualizarTabla(
                (String) diaCombo.getSelectedItem(),
                (String) tipoCombo.getSelectedItem()
        ));
    }

    private void actualizarTabla(String diaFiltro, String tipoFiltro) {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);

        String[] diasSemana = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        String[] tipos = {"desayuno", "almuerzo"};

        for (String dia : diasSemana) {
            if (!diaFiltro.equals("Todos") && !dia.equalsIgnoreCase(diaFiltro)) continue;
            for (String tipo : tipos) {
                if (!tipoFiltro.equals("Todos") && !tipo.equalsIgnoreCase(tipoFiltro)) continue;
                for (int i = 0; i < reservas.length(); i++) {
                    JSONObject r = reservas.getJSONObject(i);
                    if (
                        r.optString("dia").equalsIgnoreCase(dia) &&
                        r.optString("tipo").equalsIgnoreCase(tipo)
                    ) {
                        model.addRow(new Object[]{
                            r.optString("cedula"),
                            r.optString("dia"),
                            r.optString("tipo"),
                            r.optDouble("precio"),
                            r.optInt("posicionCola"),
                            "Cargar Imagen"
                        });
                    }
                }
            }
        }
    }

    private void addImageButtonListeners() {
        JTable table = view.getReservasTable();
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int column = table.getColumnModel().getColumnIndex("Imagen");
                int row = table.rowAtPoint(evt.getPoint());
                if (row >= 0 && table.getSelectedColumn() == column) {
                    Object value = table.getValueAt(row, column);
                    if ("Cargar Imagen".equals(value)) {
                        handleImageValidation(table, row);
                    }
                }
            }
        });
    }

    private void handleImageValidation(JTable table, int row) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes JPG, JPEG, PNG", "jpg", "jpeg", "png"));
        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getName();
            String fileNameLower = fileName.toLowerCase();
            if (!isValidImageExtension(fileNameLower)) {
                JOptionPane.showMessageDialog(view, "Solo se permiten archivos JPG, JPEG o PNG.", "Archivo no válido", JOptionPane.ERROR_MESSAGE);
                return;
            }
            java.io.File assetFile = new java.io.File("data/assets/" + fileName);
            if (assetFile.exists()) {
                processReservationValidation(table, row, fileName);
            } else {
                JOptionPane.showMessageDialog(view, "La imagen seleccionada no coincide", "Archivo no encontrado", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isValidImageExtension(String fileNameLower) {
        return fileNameLower.endsWith(".jpg") || fileNameLower.endsWith(".jpeg") || fileNameLower.endsWith(".png");
    }

    private void processReservationValidation(JTable table, int row, String fileName) {
        int modelRow = table.convertRowIndexToModel(row);
        String cedula = (String) table.getModel().getValueAt(modelRow, 0);
        String diaReserva = (String) table.getModel().getValueAt(modelRow, 1);
        String tipoReserva = (String) table.getModel().getValueAt(modelRow, 2);
        int posicionCola = (int) table.getModel().getValueAt(modelRow, 4);
        double precioMenu = (double) table.getModel().getValueAt(modelRow, 3);

        // Primero intenta descontar el saldo
        if (!descontarSaldoComensal(cedula, precioMenu)) {
            // Si no hay saldo suficiente, no elimina la reserva ni muestra validación exitosa
            return;
        }

        // Si el saldo fue descontado correctamente, elimina la reserva
        eliminarReserva(cedula, diaReserva, tipoReserva, posicionCola);
        guardarReservas();
        ((DefaultTableModel) table.getModel()).removeRow(modelRow);

        JOptionPane.showMessageDialog(view, "¡Validación exitosa!");
    }

    private boolean descontarSaldoComensal(String cedula, double precioMenu) {
        String comensalesPath = "data/comensales.json";
        JSONArray comensalesArr;
        try {
            String comensalesContent = new String(Files.readAllBytes(Paths.get(comensalesPath)));
            comensalesArr = new JSONArray(comensalesContent);
        } catch (Exception ex) {
            // Solo mostrar JOptionPane si hay UI (evita NullPointerException en tests)
            if (view != null && SwingUtilities.getWindowAncestor(view) != null) {
                JOptionPane.showMessageDialog(view, "Error al leer comensales.json: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }

        boolean saldoActualizado = false;
        for (int i = 0; i < comensalesArr.length(); i++) {
            JSONObject comensal = comensalesArr.getJSONObject(i);
            if (comensal.optString("ID").equals(cedula)) {
                int saldoActual = comensal.optInt("saldo", 0);
                if (saldoActual < precioMenu) {
                    if (view != null && SwingUtilities.getWindowAncestor(view) != null) {
                        JOptionPane.showMessageDialog(view, "El usuario no tiene saldo suficiente para validar la reserva.", "Saldo insuficiente", JOptionPane.ERROR_MESSAGE);
                    }
                    return false;
                }
                comensal.put("saldo", saldoActual - (int)precioMenu);
                saldoActualizado = true;
                break;
            }
        }
        if (!saldoActualizado) {
            if (view != null && SwingUtilities.getWindowAncestor(view) != null) {
                JOptionPane.showMessageDialog(view, "No se encontró el usuario en comensales.json.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
        try {
            Files.write(Paths.get(comensalesPath), comensalesArr.toString(4).getBytes());
        } catch (Exception ex) {
            if (view != null && SwingUtilities.getWindowAncestor(view) != null) {
                JOptionPane.showMessageDialog(view, "Error al actualizar comensales.json: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
        return true;
    }

    private void eliminarReserva(String cedula, String diaReserva, String tipoReserva, int posicionCola) {
        for (int i = 0; i < reservas.length(); i++) {
            JSONObject r = reservas.getJSONObject(i);
            if (
                r.optString("cedula").equals(cedula) &&
                r.optString("dia").equalsIgnoreCase(diaReserva) &&
                r.optString("tipo").equalsIgnoreCase(tipoReserva) &&
                r.optInt("posicionCola") == posicionCola
            ) {
                reservas.remove(i);
                break;
            }
        }
    }

    private void guardarReservas() {
        try {
            Files.write(Paths.get("data/reservas.json"), reservas.toString(4).getBytes());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al actualizar reservas.json: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}