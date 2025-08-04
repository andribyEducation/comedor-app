package com.ucv.views.admin.menu;

import com.ucv.components.Button.RoundedButton;
import com.ucv.components.TextInput.TextInput;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class GestionarMenuView extends JFrame {
    private JTabbedPane tabbedPane;
    private RoundedButton guardarButton;

    public GestionarMenuView() {
        setTitle("Gestionar Menú Semanal");
        setSize(900, 800); // Aumentamos el ancho
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Inter", Font.BOLD, 16));
        add(tabbedPane, BorderLayout.CENTER);

        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        for (String dia : dias) {
            tabbedPane.addTab(dia, createDayPanel());
        }

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        southPanel.setBackground(Color.WHITE);
        guardarButton = new RoundedButton("Guardar Todos los Cambios");
        guardarButton.setFont(new Font("Inter", Font.BOLD, 18));
        guardarButton.setPreferredSize(new Dimension(300, 50));
        southPanel.add(guardarButton);
        add(southPanel, BorderLayout.SOUTH);
    }

    private JPanel createDayPanel() {
        JPanel dayPanel = new JPanel();
        dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
        dayPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        dayPanel.setBackground(new Color(245, 245, 245));

        dayPanel.add(createMealPanel("Desayuno", new String[]{"Comida", "Bebida"}));
        dayPanel.add(Box.createVerticalStrut(15));
        dayPanel.add(createMealPanel("Almuerzo", new String[]{"Comida", "Bebida", "Postre"}));

        return dayPanel;
    }

    private JPanel createMealPanel(String title, String[] fields) {
        JPanel mealPanel = new JPanel();
        mealPanel.setLayout(new BoxLayout(mealPanel, BoxLayout.Y_AXIS));
        mealPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                title,
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Inter", Font.BOLD, 20),
                new Color(50, 50, 50)
        ));
        mealPanel.setBackground(Color.WHITE);

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < fields.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.1;
            JLabel label = new JLabel(fields[i] + ":");
            label.setFont(new Font("Inter", Font.PLAIN, 16));
            fieldsPanel.add(label, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.9;
            TextInput textInput = new TextInput("Ingrese " + fields[i].toLowerCase());
            textInput.setFont(new Font("Inter", Font.PLAIN, 16));
            fieldsPanel.add(textInput, gbc);
        }
        
        mealPanel.add(fieldsPanel);

        // Panel para detalles: disponibilidad y precio
        JPanel detailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); // Ajustamos el hgap
        detailsPanel.setBackground(Color.WHITE);
        
        JLabel availabilityLabel = new JLabel("Disponibilidad (Cupos):");
        availabilityLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        detailsPanel.add(availabilityLabel);

        JSpinner availabilitySpinner = new JSpinner(new SpinnerNumberModel(50, 0, 500, 1));
        availabilitySpinner.setFont(new Font("Inter", Font.PLAIN, 16));
        detailsPanel.add(availabilitySpinner);

        detailsPanel.add(Box.createHorizontalStrut(20));

        JLabel priceLabel = new JLabel("Precio (Bs.):"); // Cambiamos el texto a Bs.
        priceLabel.setFont(new Font("Inter", Font.PLAIN, 16));
        detailsPanel.add(priceLabel);

        JSpinner priceSpinner = new JSpinner(new SpinnerNumberModel(10.0, 0.0, 1000.0, 0.50));
        priceSpinner.setFont(new Font("Inter", Font.PLAIN, 16));
        
        // Configurar el formato para usar coma como separador decimal
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) priceSpinner.getEditor();
        DecimalFormat format = (DecimalFormat) editor.getFormat();
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.'); // Opcional: para miles
        format.setDecimalFormatSymbols(symbols);
        format.applyPattern("#,##0.00"); // Formato para dos decimales, con separador de miles y decimal

        detailsPanel.add(priceSpinner);

        mealPanel.add(detailsPanel);

        return mealPanel;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public RoundedButton getGuardarButton() {
        // Si el botón es null (por ejemplo, en tests con mocks), retorna un mock para evitar errores de casting
        if (guardarButton == null) {
            return new com.ucv.components.Button.RoundedButton("Mock");
        }
        return guardarButton;
    }

    public void display() {
        setVisible(true);
    }
}