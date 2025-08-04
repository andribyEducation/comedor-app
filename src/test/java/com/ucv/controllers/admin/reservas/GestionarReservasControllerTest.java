package com.ucv.controllers.admin.reservas;

import com.ucv.views.admin.reservas.GestionarReservasView;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GestionarReservasControllerTest {

    private GestionarReservasView view;
    private GestionarReservasController controller;

    @BeforeEach
    void setUp() throws Exception {
        // Prepara un archivo de reservas temporal para pruebas
        JSONArray reservas = new JSONArray();
        JSONObject reserva = new JSONObject();
        reserva.put("cedula", "30513493");
        reserva.put("dia", "Lunes");
        reserva.put("tipo", "desayuno");
        reserva.put("precio", 10);
        reserva.put("posicionCola", 1);
        reservas.put(reserva);
        try (FileWriter writer = new FileWriter("data/reservas.json")) {
            writer.write(reservas.toString(4));
        }

        // Prepara un archivo de comensales temporal para pruebas
        JSONArray comensales = new JSONArray();
        JSONObject comensal = new JSONObject();
        comensal.put("ID", "30513493");
        comensal.put("saldo", 100);
        comensales.put(comensal);
        try (FileWriter writer = new FileWriter("data/comensales.json")) {
            writer.write(comensales.toString(4));
        }

        view = mock(GestionarReservasView.class);
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Cédula", "Día", "Tipo", "Precio", "Posición en Cola", "Imagen"}, 0);
        JTable table = new JTable(model);
        when(view.getTableModel()).thenReturn(model);
        when(view.getReservasTable()).thenReturn(table);
        when(view.getDiaCombo()).thenReturn(new JComboBox<>(new String[]{"Todos"}));
        when(view.getTipoCombo()).thenReturn(new JComboBox<>(new String[]{"Todos"}));
        controller = new GestionarReservasController(view);
    }

    @Test
    void testActualizarTablaFiltraPorDiaYTipo() throws Exception {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);

        // Agrega otra reserva de tipo diferente
        JSONArray reservas = new JSONArray();
        JSONObject reserva1 = new JSONObject();
        reserva1.put("cedula", "30513493");
        reserva1.put("dia", "Lunes");
        reserva1.put("tipo", "desayuno");
        reserva1.put("precio", 10);
        reserva1.put("posicionCola", 1);
        reservas.put(reserva1);

        JSONObject reserva2 = new JSONObject();
        reserva2.put("cedula", "12345678");
        reserva2.put("dia", "Martes");
        reserva2.put("tipo", "almuerzo");
        reserva2.put("precio", 15);
        reserva2.put("posicionCola", 2);
        reservas.put(reserva2);

        try (FileWriter writer = new FileWriter("data/reservas.json")) {
            writer.write(reservas.toString(4));
        }

        // Vuelve a crear el controlador para recargar reservas
        controller = new GestionarReservasController(view);

        // Simula seleccionar "Martes" y "almuerzo"
        JComboBox<String> diaCombo = new JComboBox<>(new String[]{"Todos", "Martes"});
        JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"Todos", "almuerzo"});
        when(view.getDiaCombo()).thenReturn(diaCombo);
        when(view.getTipoCombo()).thenReturn(tipoCombo);

        // Llama a actualizarTabla por reflexión
        java.lang.reflect.Method method = GestionarReservasController.class.getDeclaredMethod("actualizarTabla", String.class, String.class);
        method.setAccessible(true);
        method.invoke(controller, "Martes", "almuerzo");

        assertEquals(1, model.getRowCount());
        assertEquals("12345678", model.getValueAt(0, 0));
    }

    @Test
    void testIsValidImageExtension() throws Exception {
        java.lang.reflect.Method method = GestionarReservasController.class.getDeclaredMethod("isValidImageExtension", String.class);
        method.setAccessible(true);
        assertTrue((boolean) method.invoke(controller, "foto.jpg"));
        assertTrue((boolean) method.invoke(controller, "foto.jpeg"));
        assertTrue((boolean) method.invoke(controller, "foto.png"));
        assertFalse((boolean) method.invoke(controller, "foto.bmp"));
        assertFalse((boolean) method.invoke(controller, "foto.txt"));
    }

    @Test
    void testEliminarReservaEliminaCorrectamente() throws Exception {
        // Agrega dos reservas
        JSONArray reservas = new JSONArray();
        JSONObject reserva1 = new JSONObject();
        reserva1.put("cedula", "30513493");
        reserva1.put("dia", "Lunes");
        reserva1.put("tipo", "desayuno");
        reserva1.put("precio", 10);
        reserva1.put("posicionCola", 1);
        reservas.put(reserva1);

        JSONObject reserva2 = new JSONObject();
        reserva2.put("cedula", "12345678");
        reserva2.put("dia", "Martes");
        reserva2.put("tipo", "almuerzo");
        reserva2.put("precio", 15);
        reserva2.put("posicionCola", 2);
        reservas.put(reserva2);

        try (FileWriter writer = new FileWriter("data/reservas.json")) {
            writer.write(reservas.toString(4));
        }

        controller = new GestionarReservasController(view);

        // Elimina la primera reserva
        java.lang.reflect.Method method = GestionarReservasController.class.getDeclaredMethod("eliminarReserva", String.class, String.class, String.class, int.class);
        method.setAccessible(true);
        method.invoke(controller, "30513493", "Lunes", "desayuno", 1);

        // Guarda y verifica que solo queda una reserva
        java.lang.reflect.Method guardar = GestionarReservasController.class.getDeclaredMethod("guardarReservas");
        guardar.setAccessible(true);
        guardar.invoke(controller);

        String content = new String(Files.readAllBytes(Paths.get("data/reservas.json")));
        JSONArray result = new JSONArray(content);
        assertEquals(1, result.length());
        assertEquals("12345678", result.getJSONObject(0).getString("cedula"));
    }

    @Test
    void testGuardarReservasNoExcepcion() throws Exception {
        java.lang.reflect.Method method = GestionarReservasController.class.getDeclaredMethod("guardarReservas");
        method.setAccessible(true);
        assertDoesNotThrow(() -> method.invoke(controller));
    }

    @Test
    void testDescontarSaldoComensal() throws Exception {
        // Simula el flujo de validación exitosa
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        model.addRow(new Object[]{"30513493", "Lunes", "desayuno", 10.0, 1, "Cargar Imagen"});

        // Llama al método privado usando reflexión para probarlo directamente
        java.lang.reflect.Method method = GestionarReservasController.class.getDeclaredMethod("descontarSaldoComensal", String.class, double.class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(controller, "30513493", 10.0);
        assertTrue(result);

        // Verifica que el saldo fue descontado
        String content = new String(Files.readAllBytes(Paths.get("data/comensales.json")));
        JSONArray comensales = new JSONArray(content);
        JSONObject comensal = comensales.getJSONObject(0);
        assertEquals(90, comensal.getInt("saldo"));
    }

    @Test
    void testDescontarSaldoComensalSaldoInsuficiente() throws Exception {
        // Simula saldo insuficiente
        JSONArray comensales = new JSONArray();
        JSONObject comensal = new JSONObject();
        comensal.put("ID", "30513493");
        comensal.put("saldo", 5);
        comensales.put(comensal);
        try (FileWriter writer = new FileWriter("data/comensales.json")) {
            writer.write(comensales.toString(4));
        }

        java.lang.reflect.Method method = GestionarReservasController.class.getDeclaredMethod("descontarSaldoComensal", String.class, double.class);
        method.setAccessible(true);
        boolean result = (boolean) method.invoke(controller, "30513493", 10);
        assertFalse(result);
    }
}