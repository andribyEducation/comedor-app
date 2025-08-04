package com.ucv.views.admin.reservas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionarReservasView extends JDialog {

    private JTable reservasTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> diaCombo;
    private JComboBox<String> tipoCombo;

    public GestionarReservasView() {
        setTitle("Gestión de Reservas");
        setModal(true);
        setSize(900, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Panel superior con título y filtros
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Todas las Reservas", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 51, 102));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        headerPanel.add(titleLabel, BorderLayout.NORTH);

        // Filtros
        JPanel filtrosPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        filtrosPanel.setBackground(Color.WHITE);

        diaCombo = new JComboBox<>(new String[]{"Todos", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes"});
        tipoCombo = new JComboBox<>(new String[]{"Todos", "desayuno", "almuerzo"});

        filtrosPanel.add(new JLabel("Filtrar por día:"));
        filtrosPanel.add(diaCombo);
        filtrosPanel.add(new JLabel("Filtrar por tipo:"));
        filtrosPanel.add(tipoCombo);

        headerPanel.add(filtrosPanel, BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);

        // Tabla de reservas (agrega columna para botón de imagen)
        tableModel = new DefaultTableModel(new Object[]{"Cédula", "Día", "Tipo", "Precio", "Posición en Cola", "Imagen"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };
        reservasTable = new JTable(tableModel) {
            @Override
            public Object getValueAt(int row, int column) {
                if (column == 5) {
                    return "Cargar Imagen";
                }
                return super.getValueAt(row, column);
            }
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 5) return String.class;
                return super.getColumnClass(column);
            }
        };
        reservasTable.setFont(new Font("Inter", Font.PLAIN, 16));
        reservasTable.getTableHeader().setFont(new Font("Inter", Font.BOLD, 16));
        reservasTable.setRowHeight(28);
        reservasTable.setGridColor(new Color(220, 220, 220));
        reservasTable.setShowGrid(true);
        reservasTable.setBackground(Color.WHITE);
        reservasTable.setSelectionBackground(new Color(230, 240, 255));
        JScrollPane scrollPane = new JScrollPane(reservasTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));
        add(scrollPane, BorderLayout.CENTER);

        // Botón cerrar
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Inter", Font.BOLD, 16));
        btnCerrar.setBackground(new Color(0, 51, 102));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorder(BorderFactory.createEmptyBorder(8, 30, 8, 30));
        btnCerrar.addActionListener(e -> dispose());
        footerPanel.add(btnCerrar);
        add(footerPanel, BorderLayout.SOUTH);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getReservasTable() {
        return reservasTable;
    }

    public JComboBox<String> getDiaCombo() {
        return diaCombo;
    }

    public JComboBox<String> getTipoCombo() {
        return tipoCombo;
    }
}
