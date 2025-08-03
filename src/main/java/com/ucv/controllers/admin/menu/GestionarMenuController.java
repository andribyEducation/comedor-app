package com.ucv.controllers.admin.menu;

import com.ucv.views.admin.menu.GestionarMenuView;
import com.ucv.components.TextInput.TextInput; // Importamos TextInput
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GestionarMenuController {
    private final String menuDataPath = "data/menus.json";
    private GestionarMenuView view;

    public GestionarMenuController(GestionarMenuView view) {
        this.view = view;
        loadMenuData();
        this.view.getGuardarButton().addActionListener(e -> saveMenuData());
    }

    private void loadMenuData() {
        try {
            if (!Files.exists(Paths.get(menuDataPath)) || new String(Files.readAllBytes(Paths.get(menuDataPath))).trim().isEmpty()) {
                return; // No hay menú para cargar o el archivo está vacío
            }
            String content = new String(Files.readAllBytes(Paths.get(menuDataPath)));
            JSONObject fullMenu = new JSONObject(content);

            for (int i = 0; i < view.getTabbedPane().getTabCount(); i++) {
                String dia = view.getTabbedPane().getTitleAt(i);
                JPanel dayPanel = (JPanel) view.getTabbedPane().getComponentAt(i);
                if (fullMenu.has(dia)) {
                    JSONObject diaMenu = fullMenu.getJSONObject(dia);
                    // Cargar datos de Desayuno
                    if (diaMenu.has("desayuno")) {
                        populateMealPanel((JPanel) dayPanel.getComponent(0), diaMenu.getJSONObject("desayuno"));
                    }
                    // Cargar datos de Almuerzo
                    if (diaMenu.has("almuerzo")) {
                        populateMealPanel((JPanel) dayPanel.getComponent(2), diaMenu.getJSONObject("almuerzo"));
                    }
                }
            }
        } catch (IOException | org.json.JSONException e) {
            JOptionPane.showMessageDialog(view, "Error crítico al cargar datos del menú: " + e.getMessage(), "Error de Carga", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void populateMealPanel(JPanel mealPanel, JSONObject mealData) {
        JSONArray platos = mealData.optJSONArray("platos");
        System.out.println(platos);
        int cupos = mealData.optInt("cupos", 0);
        double precio = mealData.optDouble("precio", 0.0);

        JPanel fieldsPanel = (JPanel) mealPanel.getComponent(0);
        if (platos != null) {
            for (int i = 0; i < platos.length(); i++) {
                // Asegurarse de que el componente es un TextInput antes de intentar setText
                Component comp = fieldsPanel.getComponent(i * 2 + 1);
                if (comp instanceof TextInput) {
                    ((TextInput) comp).setText(platos.optString(i, ""));
                }
            }
        }

        JPanel detailsPanel = (JPanel) mealPanel.getComponent(1);
        JSpinner availabilitySpinner = (JSpinner) detailsPanel.getComponent(1);
        availabilitySpinner.setValue(cupos);

        JSpinner priceSpinner = (JSpinner) detailsPanel.getComponent(4); // El 4to componente es el JSpinner del precio
        priceSpinner.setValue(precio);
    }

    private void saveMenuData() {
        try {
            String content = "{}";
            if (Files.exists(Paths.get(menuDataPath)) && !new String(Files.readAllBytes(Paths.get(menuDataPath))).trim().isEmpty()) {
                content = new String(Files.readAllBytes(Paths.get(menuDataPath)));
            }
            JSONObject fullMenu = new JSONObject(content);

            for (int i = 0; i < view.getTabbedPane().getTabCount(); i++) {
                String dia = view.getTabbedPane().getTitleAt(i);
                JPanel dayPanel = (JPanel) view.getTabbedPane().getComponentAt(i);
                JSONObject diaMenu = fullMenu.optJSONObject(dia, new JSONObject());

                // Guardar datos de Desayuno
                diaMenu.put("desayuno", extractMealData((JPanel) dayPanel.getComponent(0)));
                // Guardar datos de Almuerzo
                diaMenu.put("almuerzo", extractMealData((JPanel) dayPanel.getComponent(2)));

                fullMenu.put(dia, diaMenu);
            }

            try (FileWriter writer = new FileWriter(menuDataPath)) {
                writer.write(fullMenu.toString(4));
            }

            JOptionPane.showMessageDialog(view, "Menú actualizado correctamente.", "Guardado Exitoso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | org.json.JSONException e) {
            JOptionPane.showMessageDialog(view, "Error crítico al guardar el menú: " + e.getMessage(), "Error de Guardado", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private JSONObject extractMealData(JPanel mealPanel) {
        JSONObject mealData = new JSONObject();
        JSONArray platos = new JSONArray();

        JPanel fieldsPanel = (JPanel) mealPanel.getComponent(0);
        for (int i = 0; i < fieldsPanel.getComponentCount(); i++) {
            Component comp = fieldsPanel.getComponent(i);
            if (comp instanceof TextInput) {
                platos.put(((TextInput) comp).getText());
            }
        }
        mealData.put("platos", platos);

        JPanel detailsPanel = (JPanel) mealPanel.getComponent(1);
        JSpinner availabilitySpinner = (JSpinner) detailsPanel.getComponent(1);
        mealData.put("cupos", availabilitySpinner.getValue());
        
        JSpinner priceSpinner = (JSpinner) detailsPanel.getComponent(4); // El 4to componente es el JSpinner del precio
        mealData.put("precio", priceSpinner.getValue());

        return mealData;
    }
}