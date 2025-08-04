package com.ucv.controllers.comensal;

import com.ucv.views.comensal.menuDelDia.menuDelDia;
import com.ucv.models.Usuario;
import org.json.JSONObject;
import org.json.JSONArray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalTime;

public class MenuDelDiaController {

    private menuDelDia view;

    public MenuDelDiaController(menuDelDia view) {
        this.view = view;
        this.view.getBtnReservar().addActionListener(new ReservarListener());
    }

    public class ReservarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!view.isDesayunoReservado() && !view.isAlmuerzoReservado()) {
                JOptionPane.showMessageDialog(view, "Debe seleccionar al menos un menú para reservar.", "Reserva", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String dia = view.getTitle().replace("Menú del Día - ", "");
            // Validar si el día ya pasó
            if (diaYaPaso(dia)) {
                JOptionPane.showMessageDialog(view, "El menú o el día seleccionado ya no se encuentra disponible.", "Menú no disponible", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validar hora solo si es el mismo día de la semana
            int hoyIdx = LocalDate.now().getDayOfWeek().getValue() - 1; // Lunes=0
            int menuIdx = getDiaSemanaIndex(dia);
            if (menuIdx == hoyIdx) {
                LocalTime ahora = LocalTime.now();
                boolean desayunoFueraDeHora = false;
                boolean almuerzoFueraDeHora = false;
                if (view.isDesayunoReservado() && ahora.isAfter(LocalTime.of(9, 20))) {
                    desayunoFueraDeHora = true;
                }
                if (view.isAlmuerzoReservado() && ahora.isAfter(LocalTime.of(14, 0))) {
                    almuerzoFueraDeHora = true;
                }
                if (desayunoFueraDeHora && almuerzoFueraDeHora) {
                        JOptionPane.showMessageDialog(view, "El menú de desayuno y almuerzo ya no están disponibles para hoy.", "Menú no disponible", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (view.isDesayunoReservado() && desayunoFueraDeHora) {
                        JOptionPane.showMessageDialog(view, "El menú de desayuno ya no está disponible para hoy.", "Menú no disponible", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (view.isAlmuerzoReservado() && almuerzoFueraDeHora) {
                        JOptionPane.showMessageDialog(view, "El menú de almuerzo ya no está disponible para hoy.", "Menú no disponible", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            Usuario usuario = Usuario.getUsuarioActual();
            if (usuario == null) {
                JOptionPane.showMessageDialog(view, "No se encontró el usuario actual.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Leer reservas existentes
            JSONArray reservas;
            File reservasFile = new File("data/reservas.json");
            try {
                if (reservasFile.exists() && reservasFile.length() > 0) {
                    String reservasContent = new String(Files.readAllBytes(reservasFile.toPath()));
                    reservas = reservasContent.trim().isEmpty() ? new JSONArray() : new JSONArray(reservasContent);
                } else {
                    reservas = new JSONArray();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error al leer las reservas.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            dia = view.getTitle().replace("Menú del Día - ", "");
            boolean yaReservoDesayuno = false;
            boolean yaReservoAlmuerzo = false;

            // Validar si ya reservó desayuno o almuerzo ese día
            for (int i = 0; i < reservas.length(); i++) {
                JSONObject r = reservas.getJSONObject(i);
                if (r.optString("cedula").equals(usuario.getCedula()) && r.optString("dia").equals(dia)) {
                    if (r.optString("tipo").equals("desayuno")) {
                        yaReservoDesayuno = true;
                    }
                    if (r.optString("tipo").equals("almuerzo")) {
                        yaReservoAlmuerzo = true;
                    }
                }
            }

            if (view.isDesayunoReservado() && yaReservoDesayuno) {
                JOptionPane.showMessageDialog(view, "Ya ha reservado el desayuno para este día.", "Reserva duplicada", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (view.isAlmuerzoReservado() && yaReservoAlmuerzo) {
                JOptionPane.showMessageDialog(view, "Ya ha reservado el almuerzo para este día.", "Reserva duplicada", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double total = 0.0;
            boolean cupoDesayuno = true;
            boolean cupoAlmuerzo = true;
            JSONObject menuDia = null;
            JSONObject menus = null;
            double precioDesayuno = 0.0;
            double precioAlmuerzo = 0.0;
            try {
                String content = new String(Files.readAllBytes(Paths.get("data/menus.json")));
                menus = new JSONObject(content);
                menuDia = menus.getJSONObject(dia);

                if (view.isDesayunoReservado()) {
                    JSONObject desayuno = menuDia.getJSONObject("desayuno");
                    if (desayuno.getInt("cupos") <= 0) {
                        cupoDesayuno = false;
                    }
                    precioDesayuno = desayuno.getDouble("precio");
                    total += precioDesayuno;
                }
                if (view.isAlmuerzoReservado()) {
                    JSONObject almuerzo = menuDia.getJSONObject("almuerzo");
                    if (almuerzo.getInt("cupos") <= 0) {
                        cupoAlmuerzo = false;
                    }
                    precioAlmuerzo = almuerzo.getDouble("precio");
                    total += precioAlmuerzo;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error al validar precios o cupos del menú.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (view.isDesayunoReservado() && !cupoDesayuno) {
                JOptionPane.showMessageDialog(view, "No hay cupos disponibles para el desayuno.", "Cupos insuficientes", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (view.isAlmuerzoReservado() && !cupoAlmuerzo) {
                JOptionPane.showMessageDialog(view, "No hay cupos disponibles para el almuerzo.", "Cupos insuficientes", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (usuario.getSaldo() < total) {
                JOptionPane.showMessageDialog(view, "No tiene saldo suficiente para reservar el/los menú(s) seleccionados.", "Saldo insuficiente", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Descontar cupos y guardar archivo
            try {
                boolean actualizado = false;
                if (view.isDesayunoReservado()) {
                    JSONObject desayuno = menuDia.getJSONObject("desayuno");
                    desayuno.put("cupos", desayuno.getInt("cupos") - 1);
                    actualizado = true;
                }
                if (view.isAlmuerzoReservado()) {
                    JSONObject almuerzo = menuDia.getJSONObject("almuerzo");
                    almuerzo.put("cupos", almuerzo.getInt("cupos") - 1);
                    actualizado = true;
                }
                if (actualizado) {
                    try (FileWriter writer = new FileWriter("data/menus.json")) {
                        writer.write(menus.toString(4));
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error al actualizar los cupos del menú.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Guardar reserva en reservas.json
            try {
                if (view.isDesayunoReservado()) {
                    int posicion = calcularPosicionCola(reservas, dia, "desayuno");
                    JSONObject reserva = new JSONObject();
                    reserva.put("cedula", usuario.getCedula());
                    reserva.put("precio", precioDesayuno);
                    reserva.put("dia", dia);
                    reserva.put("tipo", "desayuno");
                    reserva.put("posicionCola", posicion);
                    reservas.put(reserva);
                }
                if (view.isAlmuerzoReservado()) {
                    int posicion = calcularPosicionCola(reservas, dia, "almuerzo");
                    JSONObject reserva = new JSONObject();
                    reserva.put("cedula", usuario.getCedula());
                    reserva.put("precio", precioAlmuerzo);
                    reserva.put("dia", dia);
                    reserva.put("tipo", "almuerzo");
                    reserva.put("posicionCola", posicion);
                    reservas.put(reserva);
                }

                try (FileWriter writer = new FileWriter(reservasFile)) {
                    writer.write(reservas.toString(4));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error al guardar la reserva.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (view != null && javax.swing.SwingUtilities.getWindowAncestor(view) != null) {
                JOptionPane.showMessageDialog(view, "¡Reserva realizada con éxito!", "Reserva", JOptionPane.INFORMATION_MESSAGE);
            }
            view.dispose();
        }

        private int calcularPosicionCola(JSONArray reservas, String dia, String tipo) {
            int max = 0;
            for (int i = 0; i < reservas.length(); i++) {
                JSONObject r = reservas.getJSONObject(i);
                if (r.optString("dia").equals(dia) && r.optString("tipo").equals(tipo)) {
                    int pos = r.optInt("posicionCola", 0);
                    if (pos > max) max = pos;
                }
            }
            return max + 1;
        }

        // Valida si el día del menú ya pasó respecto al día actual, considerando ciclo semanal
        private boolean diaYaPaso(String diaMenu) {
            String[] diasSemana = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
            int hoyIdx = LocalDate.now().getDayOfWeek().getValue() - 1; // Lunes=0
            int menuIdx = -1;
            for (int i = 0; i < diasSemana.length; i++) {
                if (diasSemana[i].equalsIgnoreCase(diaMenu)) {
                    menuIdx = i;
                    break;
                }
            }
            if (menuIdx == -1) return true; // Si no reconoce el día, lo considera pasado

            // Si hoy es sábado o domingo, nunca debe decir que el menú de lunes a viernes ya pasó
            if (hoyIdx == 5 || hoyIdx == 6) { // 5=Sabado, 6=Domingo
                return false;
            }
            // Si el índice del menú es menor que el de hoy, ya pasó
            return menuIdx < hoyIdx;
        }

        private int getDiaSemanaIndex(String diaMenu) {
            String[] diasSemana = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
            for (int i = 0; i < diasSemana.length; i++) {
                if (diasSemana[i].equalsIgnoreCase(diaMenu)) {
                    return i;
                }
            }
            return -1;
        }
    }
}