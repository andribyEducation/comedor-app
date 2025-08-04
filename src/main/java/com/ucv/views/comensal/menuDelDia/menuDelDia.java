package com.ucv.views.comensal.menuDelDia;

import com.ucv.components.Button.RoundedButton;
import com.ucv.components.CheckBox.CheckBox;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class menuDelDia extends JFrame {

    private JSONObject menus;
    private RoundedButton btnReservar;
    private CheckBox chkDesayuno;
    private CheckBox chkAlmuerzo;

    public menuDelDia(String dia) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("data/menus.json")));
            menus = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar error, por ejemplo, mostrando un mensaje al usuario
            JOptionPane.showMessageDialog(this, "Error al cargar los menús", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        setTitle("Menú del Día - " + dia);
        setSize(800, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        add(mainPanel, BorderLayout.CENTER);

        JLabel titulo = new JLabel("Menú del " + dia);
        titulo.setFont(new Font("Inter", Font.BOLD, 32));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titulo);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSONObject menuDia = menus.getJSONObject(dia);

        // Guardar los checkboxes como atributos para validación
        chkDesayuno = null;
        chkAlmuerzo = null;

        menuPanel.add(createMenuSeccion("Desayuno", menuDia.getJSONObject("desayuno"), "De 7:20am - 9:20am", true));
        menuPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        menuPanel.add(createMenuSeccion("Almuerzo", menuDia.getJSONObject("almuerzo"), "De 12:00pm - 2:00pm", false));
        mainPanel.add(menuPanel);

        mainPanel.add(Box.createVerticalGlue());

        JLabel nota = new JLabel(
                "<html><strong>Nota:</strong> No olvides presentar tu identificación y llevar tus cubiertos</html>");
        nota.setFont(new Font("Inter", Font.ITALIC, 16));
        nota.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(nota);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        btnReservar = new RoundedButton("Reservar");
        btnReservar.setFont(new Font("Inter", Font.BOLD, 22));
        btnReservar.setPreferredSize(new Dimension(200, 50));
        btnReservar.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(btnReservar);
    }

    public RoundedButton getBtnReservar() {
        return btnReservar;
    }

    public boolean isDesayunoReservado() {
        return chkDesayuno != null && chkDesayuno.isSelected();
    }

    public boolean isAlmuerzoReservado() {
        return chkAlmuerzo != null && chkAlmuerzo.isSelected();
    }

    private JPanel createMenuSeccion(String tipo, JSONObject menu, String horario, boolean esDesayuno) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        panel.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel lblTipo = new JLabel(tipo);
        lblTipo.setFont(new Font("Inter", Font.BOLD, 24));
        lblTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTipo);

        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel lblHorario = new JLabel(horario);
        lblHorario.setFont(new Font("Inter", Font.PLAIN, 14));
        lblHorario.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblHorario);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        StringBuilder platosHtml = new StringBuilder(
                "<html><ul style=\"list-style-type: disc; padding-left: 16px; padding-bottom:5px\">");
        for (Object item : menu.getJSONArray("platos")) {
            platosHtml.append("<li style=\"font-family: 'Inter'; font-size: 14px;padding-bottom:5px\">")
                    .append(item.toString()).append("</li>");
        }
        platosHtml.append("</ul></html>");
        JLabel lblPlatos = new JLabel(platosHtml.toString());
        lblPlatos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblPlatos);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel lblPrecio = new JLabel("Precio: " + String.format("%.2f", menu.getDouble("precio")) + " Bs.");
        lblPrecio.setFont(new Font("Inter", Font.BOLD, 16));
        lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblPrecio);

        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel lblCupos = new JLabel("Cupos disponibles: " + menu.getInt("cupos"));
        lblCupos.setFont(new Font("Inter", Font.PLAIN, 14));
        lblCupos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblCupos);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        if (menu.getInt("cupos") > 0) {
            CheckBox chkReservar = new CheckBox("Reservar este menú");
            chkReservar.setForeground(Color.BLACK);
            chkReservar.setFont(new Font("Inter", Font.PLAIN, 14));
            chkReservar.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(chkReservar);

            if (esDesayuno) {
                chkDesayuno = chkReservar;
            } else {
                chkAlmuerzo = chkReservar;
            }
        }

        return panel;
    }
}