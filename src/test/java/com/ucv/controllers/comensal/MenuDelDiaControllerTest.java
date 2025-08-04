package com.ucv.controllers.comensal;

import com.ucv.views.comensal.menuDelDia.menuDelDia;
import com.ucv.models.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MenuDelDiaControllerTest {

    private menuDelDia view;
    private MenuDelDiaController controller;

    @BeforeEach
    void setUp() {
        view = mock(menuDelDia.class);
        // Mock necesarios para evitar NullPointerException en el constructor de MenuDelDiaController
        when(view.getBtnReservar()).thenReturn(mock(com.ucv.components.Button.RoundedButton.class));
        Usuario usuario = new Usuario("30513493", "adrian@gmail.com", "comensal", "Adrian", "Gonzalez");
        usuario.setSaldo(999999979);
        Usuario.setUsuarioActual(usuario);
        controller = new MenuDelDiaController(view);
    }

    @Test
    void testReservarSinSeleccion() {
        when(view.isDesayunoReservado()).thenReturn(false);
        when(view.isAlmuerzoReservado()).thenReturn(false);
        // No debe lanzar excepción
        controller.new ReservarListener().actionPerformed(null);
    }

    @Test
    void testReservarConDesayunoSinSaldo() {
        when(view.isDesayunoReservado()).thenReturn(true);
        when(view.isAlmuerzoReservado()).thenReturn(false);
        when(view.getTitle()).thenReturn("Menú del Día - Lunes");
        Usuario usuario = new Usuario("30513493", "adrian@gmail.com", "comensal", "Adrian", "Gonzalez");
        usuario.setSaldo(0);
        Usuario.setUsuarioActual(usuario);
        controller = new MenuDelDiaController(view);
        controller.new ReservarListener().actionPerformed(null);
    }

    @Test
    void testReservarConDesayunoConSaldo() {
        when(view.isDesayunoReservado()).thenReturn(true);
        when(view.isAlmuerzoReservado()).thenReturn(false);
        when(view.getTitle()).thenReturn("Menú del Día - Lunes");
        Usuario usuario = new Usuario("30513493", "adrian@gmail.com", "comensal", "Adrian", "Gonzalez");
        usuario.setSaldo(999999979);
        Usuario.setUsuarioActual(usuario);
        controller = new MenuDelDiaController(view);
        // No debe lanzar excepción
        controller.new ReservarListener().actionPerformed(null);
    }
}
