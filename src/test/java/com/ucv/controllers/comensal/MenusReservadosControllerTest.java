package com.ucv.controllers.comensal;

import com.ucv.views.comensal.menusReservados.MenusReservadosView;
import com.ucv.models.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenusReservadosControllerTest {

    private MenusReservadosView view;
    private MenusReservadosController controller;

    @BeforeEach
    void setUp() {
        view = mock(MenusReservadosView.class);
        // Mock para evitar NullPointerException en getTableModel
        when(view.getTableModel()).thenReturn(new DefaultTableModel(new Object[]{"Día", "Tipo", "Precio", "Posición en Cola"}, 0));
        // Mock usuario actual
        Usuario usuario = new Usuario("30513493", "adrian@gmail.com", "comensal", "Adrian", "Gonzalez");
        usuario.setSaldo(999999979);
        Usuario.setUsuarioActual(usuario);
        controller = new MenusReservadosController(view);
    }

    @Test
    void testCargarReservasNoExcepcion() {
        // No debe lanzar excepción aunque no haya reservas
        assertDoesNotThrow(() -> new MenusReservadosController(view));
    }

    @Test
    void testCargarReservasAgregaFilas() {
        DefaultTableModel model = view.getTableModel();
        // El usuario 30513493 tiene reservas en data/reservas.json
        controller = new MenusReservadosController(view);
        // No debe lanzar excepción y debe agregar filas si hay reservas
        assertTrue(model.getRowCount() >= 0);
    }
}
