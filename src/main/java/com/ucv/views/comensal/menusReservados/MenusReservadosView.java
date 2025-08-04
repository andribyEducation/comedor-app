package com.ucv.views.comensal.menusReservados;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MenusReservadosView extends JDialog {

    private JTable reservasTable;
    private DefaultTableModel tableModel;

    public MenusReservadosView() {
        setTitle("Menus Reservados");
        setModal(true);
        setSize(700, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Panel superior con título
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Menus Reservados", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 51, 102));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Tabla de reservas
        tableModel = new DefaultTableModel(new Object[]{"Día", "Tipo", "Precio", "Posición en Cola"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no editables
            }
        };
        reservasTable = new JTable(tableModel);
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
}
