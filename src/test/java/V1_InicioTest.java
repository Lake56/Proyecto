import backend.Tienda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class V1_InicioTest {
    private Tienda tienda;

    @BeforeEach
    void setUp() {
        tienda = new Tienda(100000);
    }

    @Test
    @DisplayName("Test verificar que el presupuesto inicial sea correcto")
    void testPresupuestoInicialCorrecto() {
        assertEquals(100000, tienda.getPresupuesto(), "El presupuesto debe ser 100000");
    }

    @Test
    @DisplayName("Test verificar que la lista de mascotas este vacia")
    void testListaMascotasInicialVacia() {
        assertTrue(tienda.getMascotas().isEmpty(), "La lista debe estar vacia");
    }

    @Test
    @DisplayName("Test verificar que el inventario este vacio")
    void testInventarioInicialVacio() {
        assertTrue(tienda.getInventario().isEmpty(), "El inventario debe estar vacio");
    }
}
