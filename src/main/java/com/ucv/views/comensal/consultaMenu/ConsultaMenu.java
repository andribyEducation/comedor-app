package com.ucv.views.comensal.consultaMenu;

import com.ucv.components.Button.RoundedButton;
import com.ucv.components.Header.HeaderPanel;
import com.ucv.controllers.comensal.MenuDelDiaController;
import com.ucv.controllers.login.LoginController;
import com.ucv.controllers.saldo.SaldoController;
import com.ucv.views.comensal.menuDelDia.menuDelDia;
import com.ucv.views.comensal.saldo.SaldoView;
import com.ucv.views.login.LoginView;
import java.awt.event.MouseEvent;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class ConsultaMenu extends JFrame {
    private HeaderPanel headerPanel;

    public ConsultaMenu() {
        setTitle("Turnos disponibles");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        headerPanel = new HeaderPanel(false,"Turnos Disponibles", "Pedro",true);
        add(headerPanel, BorderLayout.NORTH);

         headerPanel.getBackButtonLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginView view=new LoginView();
                new LoginController(view);
                view.setVisible(true);
                dispose(); 
            }
        });

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();


        // Panel para los días
        JPanel diasPanel = new JPanel(new GridLayout(2, 3, 60, 60));
        diasPanel.setBackground(Color.WHITE);
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        for (String dia : dias) {
            diasPanel.add(createDiaPanel(dia));
        }
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 100, 0, 100);
        mainPanel.add(diasPanel, gbc);

        // Botón Saldo Disponible
        RoundedButton btnSaldo = new RoundedButton("Saldo Disponible");
        btnSaldo.setFont(new Font("Inter", Font.BOLD, 22));
        btnSaldo.setPreferredSize(new Dimension(250, 50));
        btnSaldo.addActionListener(e -> {
            SaldoView saldoView = new SaldoView();
            saldoView.setVisible(true);
            new SaldoController(saldoView);
            this.dispose();
        });
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.insets = new Insets(50, 0, 50, 50);
        mainPanel.add(btnSaldo, gbc);

        // Botón Menus Reservados
        RoundedButton btnMenusReservados = new RoundedButton("Menus Reservados");
        btnMenusReservados.setFont(new Font("Inter", Font.BOLD, 22));
        btnMenusReservados.setPreferredSize(new Dimension(250, 50));
        btnMenusReservados.addActionListener(e -> {
            com.ucv.views.comensal.menusReservados.MenusReservadosView view = new com.ucv.views.comensal.menusReservados.MenusReservadosView();
            new com.ucv.controllers.comensal.MenusReservadosController(view);
            view.setVisible(true);
        });
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.insets = new Insets(0, 0, 50, 50);
        mainPanel.add(btnMenusReservados, gbc);
    }

    private JPanel createDiaPanel(String dia) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);

        JLabel lblDia = new JLabel(dia, SwingConstants.CENTER);
        lblDia.setFont(new Font("Inter", Font.BOLD, 30));
        panel.add(lblDia, BorderLayout.CENTER);

        RoundedButton verMenu = new RoundedButton("Consultar");
        verMenu.setFont(new Font("Inter", Font.BOLD, 20));
        verMenu.setPreferredSize(new Dimension(300, 50));
        verMenu.addActionListener(e -> {
            menuDelDia menuView = new menuDelDia(dia);
            new MenuDelDiaController(menuView);
            menuView.setVisible(true);
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(verMenu);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
}


