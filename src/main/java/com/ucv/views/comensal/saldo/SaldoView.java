package com.ucv.views.comensal.saldo;

import javax.swing.*;
import com.ucv.components.Button.RoundedButton;
import com.ucv.components.Header.HeaderPanel;
import com.ucv.components.TextInput.TextInput;
import java.awt.*;

public class SaldoView extends JFrame {

    private HeaderPanel headerPanel;
    private JLabel saldoLabel;
    private JPanel panelRecarga;
    private TextInput txtMonto;
    private RoundedButton btnConfirmar;
    private RoundedButton btnCancelar;
    private RoundedButton btnVolver;
    private RoundedButton btnRecargar;

    public SaldoView() {
        setTitle("Saldo");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        headerPanel = new HeaderPanel(true, "Consultar Saldo", "Juan Perez");
        add(headerPanel, BorderLayout.NORTH);

        // Panel principal que contendrá todo
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(30, 30, 30));
        mainPanel.setOpaque(true);
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;

        // Título "Saldo"
        JLabel titulo = new JLabel("Saldo", SwingConstants.CENTER);
        titulo.setFont(new Font("Inter", Font.BOLD, 38));
        titulo.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titulo, gbc);

        // Panel para el saldo disponible
        JPanel saldoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        saldoPanel.setOpaque(false);
        JLabel saldoDisponibleLabel = new JLabel("Saldo disponible:", SwingConstants.LEFT);
        saldoDisponibleLabel.setFont(new Font("Inter", Font.PLAIN, 22));
        saldoDisponibleLabel.setForeground(Color.WHITE);
        saldoPanel.add(saldoDisponibleLabel);

        saldoLabel = new JLabel("250 Bs", SwingConstants.LEFT);
        saldoLabel.setFont(new Font("Inter", Font.BOLD, 26));
        saldoLabel.setForeground(new Color(255, 255, 255));
        saldoPanel.add(saldoLabel);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(saldoPanel, gbc);

        // Línea divisoria
        JPanel linea = new JPanel();
        linea.setBackground(new Color(0, 51, 102));
        linea.setPreferredSize(new Dimension(400, 2));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 0, 20, 0);
        mainPanel.add(linea, gbc);
        gbc.insets = new Insets(5, 5, 5, 5); // Reset insets

        // Panel de recarga
        panelRecarga = new JPanel(new GridBagLayout());
        panelRecarga.setOpaque(false);
        panelRecarga.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        panelRecarga.setVisible(false);

        GridBagConstraints gbcRecarga = new GridBagConstraints();
        gbcRecarga.insets = new Insets(15, 15, 15, 15);
        gbcRecarga.fill = GridBagConstraints.HORIZONTAL;

        txtMonto = new TextInput("Monto a recargar", true);
        txtMonto.setFont(new Font("Inter", Font.PLAIN, 18));
        gbcRecarga.gridx = 0;
        gbcRecarga.gridy = 0;
        gbcRecarga.gridwidth = 2;
        panelRecarga.add(txtMonto, gbcRecarga);

        btnCancelar = new RoundedButton("Cancelar", true);
        btnCancelar.setFont(new Font("Inter", Font.BOLD, 18));
        gbcRecarga.gridx = 0;
        gbcRecarga.gridy = 1;
        gbcRecarga.gridwidth = 1;
        gbcRecarga.weightx = 0.5;
        panelRecarga.add(btnCancelar, gbcRecarga);

        btnConfirmar = new RoundedButton("Confirmar");
        btnConfirmar.setFont(new Font("Inter", Font.BOLD, 18));
        gbcRecarga.gridx = 1;
        gbcRecarga.gridy = 1;
        gbcRecarga.gridwidth = 1;
        gbcRecarga.weightx = 0.5;
        panelRecarga.add(btnConfirmar, gbcRecarga);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(panelRecarga, gbc);

        // Panel para los botones de abajo
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setOpaque(false);

        btnVolver = new RoundedButton("Volver", true);
        btnVolver.setFont(new Font("Inter", Font.BOLD, 20));
        bottomPanel.add(btnVolver);

        btnRecargar = new RoundedButton("Recargar saldo");
        btnRecargar.setFont(new Font("Inter", Font.BOLD, 20));
        bottomPanel.add(btnRecargar);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(bottomPanel, gbc);
    }

    // Métodos para el controlador
    public JLabel getIconoUsuario() {
        return headerPanel.getIconoUsuario();
    }

    public JMenuItem getCambiarContrasenaItem() {
        return headerPanel.getCambiarContrasenaItem();
    }

    public JMenuItem getReportarProblemaItem() {
        return headerPanel.getReportarProblemaItem();
    }

    public JLabel getSaldoLabel() {
        return saldoLabel;
    }

    public JPanel getPanelRecarga() {
        return panelRecarga;
    }

    public TextInput getTxtMonto() {
        return txtMonto;
    }

    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JButton getBtnRecargar() {
        return btnRecargar;
    }
}
