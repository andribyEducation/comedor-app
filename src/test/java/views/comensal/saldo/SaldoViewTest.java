package views.comensal.saldo;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.*;

public class SaldoViewTest {

    private SaldoView saldoView;

    @BeforeEach
    public void setUp() {
        saldoView = new SaldoView();
    }

    @AfterEach
    public void tearDown() {
        saldoView.dispose();
    }

    @Test
    public void testVentanaCreada() {
        assertEquals("Saldo", saldoView.getTitle());
        assertEquals(1920, saldoView.getWidth());
        assertEquals(1080, saldoView.getHeight());
    }

    @Test
    public void testComponentesPrincipalesVisibles() {
        boolean tituloEncontrado = false;
        boolean saldoLabelEncontrado = false;
        for (Component comp : saldoView.getContentPane().getComponents()) {
            if (comp instanceof JLabel) {
                JLabel lbl = (JLabel) comp;
                if ("Saldo".equals(lbl.getText())) tituloEncontrado = true;
                if ("250 Bs".equals(lbl.getText())) saldoLabelEncontrado = true;
            }
        }
        assertTrue(tituloEncontrado, "Debe mostrar el título 'Saldo'");
        assertTrue(saldoLabelEncontrado, "Debe mostrar el saldo '250 Bs'");
    }

    @Test
    public void testBotonRecargarMuestraPanel() {
        JButton btnRecargar = null;
        JPanel panelRecarga = null;
        for (Component comp : saldoView.getContentPane().getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                if ("Recargar saldo".equals(btn.getText())) btnRecargar = btn;
            }
            if (comp instanceof JPanel) {
                panelRecarga = (JPanel) comp;
            }
        }
        assertNotNull(btnRecargar, "Debe existir el botón 'Recargar saldo'");
        assertNotNull(panelRecarga, "Debe existir el panel de recarga");

        // Simula click
        btnRecargar.doClick();
        assertTrue(panelRecarga.isVisible(), "El panel de recarga debe ser visible tras el click");
    }

    @Test
    public void testBotonCancelarOcultaPanel() {
        JPanel panelRecarga = null;
        JButton btnCancelar = null;
        for (Component comp : saldoView.getContentPane().getComponents()) {
            if (comp instanceof JPanel) {
                panelRecarga = (JPanel) comp;
                for (Component subComp : panelRecarga.getComponents()) {
                    if (subComp instanceof JButton) {
                        JButton btn = (JButton) subComp;
                        if ("Cancelar".equals(btn.getText())) btnCancelar = btn;
                    }
                }
            }
        }
        assertNotNull(panelRecarga);
        assertNotNull(btnCancelar);

        // Muestra el panel y luego cancela
        panelRecarga.setVisible(true);
        btnCancelar.doClick();
        assertFalse(panelRecarga.isVisible(), "El panel de recarga debe ocultarse tras cancelar");
    }
}