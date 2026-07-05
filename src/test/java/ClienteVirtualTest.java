import backend.ClienteVirtual;
import backend.Tienda;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteVirtualTest {

    @Test
    @DisplayName("Test verificar que la eleccion sea 1 o 2")
    void testEleccionEsUnoODos() {
        for (int i = 0; i < 20; i++) {
            ClienteVirtual cliente = new ClienteVirtual();

            int eleccion = cliente.getEleccion();

            assertTrue(eleccion == 1 || eleccion == 2, "La eleccion solo puede ser 1 o 2");
        }
    }

    @Test
    @DisplayName("Test verificar dinero este en rango correcto")
    void testDineroEstaEnRangoCorrecto() {
        for (int i = 0; i < 20; i++) {
            ClienteVirtual cliente = new ClienteVirtual();

            int dinero = cliente.getDinero();

            assertTrue(dinero >= 5000 && dinero <= 10000, "El dinero debe estar entre 5000 - 10000");
        }
    }

    @Test
    @DisplayName("Test verificar cliente con eleccion 1 tenga interes")
    void testClienteConEleccion1TieneInteres() {
        for (int i = 0; i < 30; i++) {
            ClienteVirtual cliente = new ClienteVirtual();

            if (cliente.getEleccion() == 1) {
                assertNotNull(cliente.getInteres(), "El cliente con eleccion 1 debe tener interes");
            }
        }
    }

    @Test
    @DisplayName("Test verificar compra en tienda sin mascotas ")
    void testComprarMascotaTiendaSinMascotas() {
        ClienteVirtual cliente = new ClienteVirtual();

        if (cliente.getEleccion() == 1) {
            Tienda tienda = new Tienda(50000);

            String resultado = cliente.comprarMascotaTienda(tienda);

            assertNotNull(resultado, "El resultado no debe ser null");
            assertFalse(resultado.isEmpty(), "El resultado no debe estar vacio");
        }
    }

    @Test
    @DisplayName("Test verificar venta de mascota agregue la mascota")
    void testVenderMascotaTiendaAgregaMascota() {
        for (int i = 0; i < 30; i++) {
            ClienteVirtual cliente = new ClienteVirtual();

            if (cliente.getEleccion() == 2) {
                Tienda tienda = new Tienda(50000);

                int antes = tienda.getMascotas().size();

                cliente.VenderMascotaTienda(tienda);

                assertEquals(antes + 1, tienda.getMascotas().size(), "El resultado debe tener el size correcto");

                break;
            }
        }
    }
}
