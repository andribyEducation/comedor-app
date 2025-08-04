package com.ucv.controllers.admin.menu;

import com.ucv.views.admin.menu.GestionarMenuView;
import com.ucv.components.Button.RoundedButton;
import com.ucv.components.TextInput.TextInput;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GestionarMenuControllerTest {

    private GestionarMenuView view;
    private GestionarMenuController controller;

    @BeforeEach
    void setUp() {
        view = mock(GestionarMenuView.class);

        // Mock tabbedPane con 1 día y componentes mínimos para pruebas
        JTabbedPane tabbedPane = mock(JTabbedPane.class);
        when(tabbedPane.getTabCount()).thenReturn(1);
        when(tabbedPane.getTitleAt(0)).thenReturn("Lunes");

        // Panel de desayuno
        JPanel desayunoPanel = new JPanel();
        JPanel desayunoFields = new JPanel();
        desayunoFields.add(new JLabel("Plato 1"));
        desayunoFields.add(new TextInput("Plato 1"));
        desayunoFields.add(new JLabel("Plato 2"));
        desayunoFields.add(new TextInput("Plato 2"));
        desayunoPanel.add(desayunoFields);
        JPanel desayunoDetails = new JPanel();
        desayunoDetails.add(new JLabel("Cupos"));
        desayunoDetails.add(new JSpinner());
        desayunoDetails.add(new JLabel("Precio"));
        desayunoDetails.add(new JLabel("Bs"));
        desayunoDetails.add(new JSpinner());
        desayunoPanel.add(desayunoDetails);

        // Panel de almuerzo (igual estructura)
        JPanel almuerzoPanel = new JPanel();
        JPanel almuerzoFields = new JPanel();
        almuerzoFields.add(new JLabel("Plato 1"));
        almuerzoFields.add(new TextInput("Plato 1"));
        almuerzoFields.add(new JLabel("Plato 2"));
        almuerzoFields.add(new TextInput("Plato 2"));
        almuerzoPanel.add(almuerzoFields);
        JPanel almuerzoDetails = new JPanel();
        almuerzoDetails.add(new JLabel("Cupos"));
        almuerzoDetails.add(new JSpinner());
        almuerzoDetails.add(new JLabel("Precio"));
        almuerzoDetails.add(new JLabel("Bs"));
        almuerzoDetails.add(new JSpinner());
        almuerzoPanel.add(almuerzoDetails);

        // Panel del día con desayuno y almuerzo
        JPanel dayPanel = new JPanel();
        dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
        dayPanel.add(desayunoPanel);
        dayPanel.add(new JPanel()); // Separador
        dayPanel.add(almuerzoPanel);

        when(tabbedPane.getComponentAt(0)).thenReturn(dayPanel);
        when(view.getTabbedPane()).thenReturn(tabbedPane);

        // Usa RoundedButton en vez de JButton si tu vista espera ese tipo
        RoundedButton guardarButton = mock(RoundedButton.class);
        when(view.getGuardarButton()).thenReturn(guardarButton);

        controller = new GestionarMenuController(view);
    }

    @Test
    void testControllerNotNull() {
        assertNotNull(controller);
    }

    @Test
    void testPopulateMealPanel() {
        JPanel mealPanel = new JPanel();
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.add(new JLabel("Plato 1"));
        fieldsPanel.add(new TextInput("Plato 1"));
        mealPanel.add(fieldsPanel);
        JPanel detailsPanel = new JPanel();
        detailsPanel.add(new JLabel("Cupos"));
        detailsPanel.add(new JSpinner());
        detailsPanel.add(new JLabel("Precio"));
        detailsPanel.add(new JLabel("Bs"));
        detailsPanel.add(new JSpinner());
        mealPanel.add(detailsPanel);

        JSONObject mealData = new JSONObject();
        mealData.put("platos", new JSONArray().put("Arepa").put("Jugo"));
        mealData.put("cupos", 10);
        mealData.put("precio", 5.5);

        // Llama al método privado populateMealPanel usando reflexión
        try {
            java.lang.reflect.Method method = GestionarMenuController.class.getDeclaredMethod("populateMealPanel", JPanel.class, JSONObject.class);
            method.setAccessible(true);
            method.invoke(controller, mealPanel, mealData);
        } catch (Exception e) {
            fail("populateMealPanel lanzó excepción: " + e.getMessage());
        }
    }
}
