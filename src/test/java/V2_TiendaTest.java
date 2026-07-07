import backend.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class V2_TiendaTest {

    private Tienda tienda;

    @BeforeEach
    void setUp() {
        tienda = new Tienda(100000);
    }

    @Test
    @DisplayName("Test verifica si comprar perro descuenta presupuesto")
    void testComprarPerroDescontaPresupuesto() {
        int antes = tienda.getPresupuesto();

        tienda.ComprarMascota(new Perro("Rex", "salchicha", 10000));

        assertEquals(antes - 10000, tienda.getPresupuesto(), "El presupuesto restante debe ser 90000");
    }

    @Test
    @DisplayName("Test verifica si comprar gato descuenta presupuesto")
    void testComprarGatoDescontaPresupuesto() {
        int antes = tienda.getPresupuesto();

        tienda.ComprarMascota(new Gato("Michi", 8000));

        assertEquals(antes - 8000, tienda.getPresupuesto(), "El presupuesto restante debe ser 92000");
    }

    @Test
    @DisplayName("Test verifica si comprar pajao descuente presupuesto")
    void testComprarPajaroDescontaPresupuesto() {
        int antes = tienda.getPresupuesto();

        tienda.ComprarMascota(new Pajaro("Piolín", 8000));

        assertEquals(antes - 8000, tienda.getPresupuesto(), "El presupuesto restante debe ser 92000");
    }

    @Test
    @DisplayName("Test verifica si comprar pajaro descuenta presupuesto")
    void testComprarPezDescontaPresupuesto() {
        int antes = tienda.getPresupuesto();

        tienda.ComprarMascota(new Pez("Nemo", 6000));

        assertEquals(antes - 6000, tienda.getPresupuesto(), "El presupuesto restante debe ser 94000");
    }

    @Test
    @DisplayName("Test verifica la cantidad correcta de comida en inventario")
    void testInventarioMuestraComidaCorrecta() {
        tienda.ComprarSuministros(1);
        tienda.ComprarSuministros(1);

        assertEquals(2, tienda.getCantidadComida(), "Deben haber 2 suministros en inventario");
    }

    @Test
    @DisplayName("Test verifica la cantidad correcta de medicamento en inventario")
    void testInventarioMuestraMedicamentosCorrecto() {
        tienda.ComprarSuministros(2);

        assertEquals(1, tienda.getCantidadMedicamento(), "Debe haber 1 medicamento en inventario");
    }

    @Test
    @DisplayName("Test verifica nombre y tipo de la mascota")
    void testInventarioMascotasMuestraNombreYTipo() {
        Perro perro = new Perro("Rex", "salchicha", 10000);

        tienda.AgregarMascota(perro);

        assertEquals("Rex", tienda.getMascotas().get(0).getNombre(), "El nombre de la mascota debe ser Rex");
        assertEquals(TipoMascota.PERRO, tienda.getMascotas().get(0).getTipo(), "El tipo de la mascota debe ser PERRO");
    }

    @Test
    @DisplayName("Test verifica si una venta aumenta el presupuesto")
    void testVenderMascotaAumentaPresupuesto() {
        Perro perro = new Perro("Rex", "salchicha", 8000);

        tienda.AgregarMascota(perro);

        int antes = tienda.getPresupuesto();

        tienda.VenderMascota(TipoMascota.PERRO, 10000);

        assertEquals(antes + 8000, tienda.getPresupuesto(), "El prespuesto debe ser 108000");
    }

    @Test
    @DisplayName("Test verifica si una venta elimina la mascota de la GUI")
    void testVenderMascotaEliminaDeListaGUI() {
        tienda.AgregarMascota(new Perro("Rex", "salchicha", 8000));
        tienda.VenderMascota(TipoMascota.PERRO, 10000);

        assertTrue(tienda.getMascotas().isEmpty(), "La lista no debe tener mascotas");
    }

    @Test
    @DisplayName("Test verifica si el con dinero insuficiente no hay venta")
    void testClienteSinDineroSuficienteNoVende() {
        tienda.AgregarMascota(new Perro("Rex", "salchicha", 8000));

        String resultado = tienda.VenderMascota(TipoMascota.PERRO, 100);

        assertEquals("Dinero insuficiente", resultado, "No debe haber dinero suficiente");
        assertEquals(1, tienda.getMascotas().size(), "Debe haber solo 1 mascota");
    }
}
