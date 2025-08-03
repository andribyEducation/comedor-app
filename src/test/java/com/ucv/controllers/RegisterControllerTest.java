package com.ucv.controllers;

import com.ucv.controllers.register.RegisterController;
import com.ucv.views.registroView.RegistroView;
import org.junit.jupiter.api.*;
import com.ucv.components.Button.RoundedButton;
import static org.mockito.Mockito.*;

class RegisterControllerTest {

    private RegisterController controller;
    private RegistroView mockRegistroView;

    @BeforeEach
    void setUp() {
        mockRegistroView = mock(RegistroView.class);
        when(mockRegistroView.getRegisterButton()).thenReturn(mock(RoundedButton.class));
        controller = new RegisterController(mockRegistroView);
    }

    @Test
    void testValidarCorreo() {
        Assertions.assertTrue(controller.validarCorreo("test@mail.com"));
        Assertions.assertFalse(controller.validarCorreo("testmail.com"));
        Assertions.assertFalse(controller.validarCorreo("test@.com"));
    }
}

