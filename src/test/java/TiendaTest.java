import backend.Gato;
import backend.Perro;
import backend.Tienda;
import backend.TipoMascota;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TiendaTest {
    private Tienda tienda;
    private Perro perro;
    private Gato gato;

    @BeforeEach
    void setUp() {
        tienda = new Tienda(20000);
        perro = new Perro("Lake", "Pastor Aleman", 7000);
        gato = new Gato("Michi", 5000);
    }

    @Test
    @DisplayName("Test verficiar presupuesto inicial es correcto")
    void testPresupuestoInicialCorrecto() {
        assertEquals(20000, tienda.getPresupuesto(), "El presupuesto debe ser 20000");
    }

    @Test
    @DisplayName("Test verificar inventario inicial esta vacio")
    void testInventarioInicialVacio() {
        assertTrue(tienda.getInventario().isEmpty(), "El inventario debe estar vacio");
    }

    @Test
    @DisplayName("Test verifica lista de mascotas esta vacia")
    void testListaMascotasInicialVacia() {
        assertTrue(tienda.getMascotas().isEmpty(), "La lista de mascotas debe estar vacia");
    }

    @Test
    @DisplayName("Test verificar si agregar una mascota aumenta la lista")
    void testAgregarMascotaAumentaLista() {
        tienda.AgregarMascota(perro);

        assertEquals(1, tienda.getMascotas().size(), "Debe haber 1 mascota en la lista");
    }

    @Test
    @DisplayName("Test verificar si quitar una mascota reduce la lista")
    void testQuitarMascotaReduceLista() {
        tienda.AgregarMascota(perro);
        tienda.QuitarMascota(perro);

        assertTrue(tienda.getMascotas().isEmpty(), "La lista de mascotas debe estar vacia");
    }

    @Test
    @DisplayName("Test verificar aumento de lista con multiples mascotas")
    void testAgregarVariasMascotas() {
        tienda.AgregarMascota(perro);
        tienda.AgregarMascota(gato);

        assertEquals(2, tienda.getMascotas().size(), "La lista debe tener 2 mascotas");
    }

    @Test
    @DisplayName("Test verificar la compra de mascota")
    void testComprarMascotaExitosa() {
        String resultado = tienda.ComprarMascota(perro);

        assertEquals("Compra exitosa", resultado, "Se debe comprar la mascota");
    }

    @Test
    @DisplayName("Testt verificar la compra de mascota disminuye presupuesto")
    void testComprarMascotaDisminuyePresupuesto() {
        tienda.ComprarMascota(perro);

        assertEquals(13000, tienda.getPresupuesto(), "El presupuesto restante debe ser 13000");
    }

    @Test
    @DisplayName("Test verificar comprar mascota aumenta la lista")
    void testComprarMascotaAgregaALista() {
        tienda.ComprarMascota(perro);

        assertTrue(tienda.getMascotas().contains(perro), "La lista debe tener a la mascota perro");
    }

    @Test
    @DisplayName("Test verificar una compra sin dinero")
    void testComprarMascotaSinDinero() {
        Tienda tiendaPobre = new Tienda(100);

        String resultado = tiendaPobre.ComprarMascota(perro);

        assertEquals("Dinero insuficiente", resultado, "Debe haber dinero insuficiente");
    }

    @Test
    @DisplayName("Test verificar compra sin dinero no agrega mascota a la lista")
    void testComprarMascotaSinDineroNoAgregaALista() {
        Tienda tiendaPobre = new Tienda(100);
        tiendaPobre.ComprarMascota(perro);

        assertTrue(tiendaPobre.getMascotas().isEmpty(), "La lista debe estar vacia");
    }

    @Test
    @DisplayName("Test verificar la venta de una mascota")
    void testVenderMascotaExitosa() {
        tienda.AgregarMascota(perro);

        String resultado = tienda.VenderMascota(TipoMascota.PERRO, 10000);

        assertEquals("Venta exitosa", resultado, "La comprar se tuvo que realizar exitosamente");
    }

    @Test
    @DisplayName("Test verificar la venta de una mascota aumenta presupuesto")
    void testVenderMascotaAumentaPresupuesto() {
        tienda.AgregarMascota(perro);

        int presupuestoAntes = tienda.getPresupuesto();

        tienda.VenderMascota(TipoMascota.PERRO, 10000);

        assertEquals(presupuestoAntes + 7000, tienda.getPresupuesto(), "El presupuesto debe a umentar");
    }

    @Test
    @DisplayName("Test verifica vender una mascota la elimina de la lista")
    void testVenderMascotaEliminaDeLista() {
        tienda.AgregarMascota(perro);
        tienda.VenderMascota(TipoMascota.PERRO, 10000);

        assertTrue(tienda.getMascotas().isEmpty(), "La lista debe estar vacia");
    }

    @Test
    @DisplayName("Test vender mascota no disponible")
    void testVenderMascotaNoDisponible() {
        String resultado = tienda.VenderMascota(TipoMascota.PERRO, 10000);

        assertEquals("No hay disponibilidad de esa mascota", resultado, "La mascota no debe estar disponible para la venta");
    }

    @Test
    @DisplayName("Test vender mascota incorrecta")
    void testVenderMascotaTipoCorrectoIgnoraOtros() {
        tienda.AgregarMascota(gato);

        String resultado = tienda.VenderMascota(TipoMascota.PERRO, 10000);

        assertEquals("No hay disponibilidad de esa mascota", resultado, "No debe haber disponibilidad de la mascota Perro");
    }

    @Test
    @DisplayName("Test verificar compra de comida")
    void testComprarComidaExitosa() {
        String resultado = tienda.ComprarSuministros(1);

        assertEquals("compra exitosa", resultado, "La compra de comida debe ser exitosa");
    }

    @Test
    @DisplayName("Test verificar compra de comida disminuye presupuesto")
    void testComprarComidaDisminuyePresupuesto() {
        tienda.ComprarSuministros(1);

        assertEquals(15000, tienda.getPresupuesto(), "La compra comida debe disminuir el presupuesto");
    }

    @Test
    @DisplayName("Test verificar compra de comida aumenta inventario")
    void testComprarComidaAumentaInventario() {
        tienda.ComprarSuministros(1);

        assertEquals(1, tienda.getCantidadComida(), "La compra de comida debe aumentar el inventario");
    }

    @Test
    @DisplayName("Test verificar compra de medicamente")
    void testComprarMedicamentoExitoso() {
        String resultado = tienda.ComprarSuministros(2);

        assertEquals("Compra exitosa", resultado, "La compra de medicamentos debe ser exitosa");
    }

    @Test
    @DisplayName("Test verificar compra de medicamento disminuye presupuesto")
    void testComprarMedicamentoDisminuyePresupuesto() {
        tienda.ComprarSuministros(2);

        assertEquals(5000, tienda.getPresupuesto(), "La compra de medicamentos debe disminuir el presupuesto");
    }

    @Test
    @DisplayName("Test verificar compra de medicamente aumenta inventario")
    void testComprarMedicamentoAumentaInventario() {
        tienda.ComprarSuministros(2);

        assertEquals(1, tienda.getCantidadMedicamento(), "La compra de medicamente debe aumentar el inventario");
    }

    @Test
    @DisplayName("Test verificar compra de suministro sin dinero suficiente")
    void testComprarSuministroSinDinero() {
        Tienda tiendaPobre = new Tienda(100);

        String resultado = tiendaPobre.ComprarSuministros(1);

        assertTrue(resultado.contains("Dinero insuficiente"), "La compra no debe ser exitosa");
    }

    @Test
    @DisplayName("Test verificar compra de suministros invalidos")
    void testComprarSuministroInvalido() {
        String resultado = tienda.ComprarSuministros(99);

        assertEquals("Producto invalido", resultado, "El producto debe ser invalido");
    }

    @Test
    @DisplayName("Test verificar cantidad de comida inicial")
    void testCantidadComidaInicialEsCero() {
        assertEquals(0, tienda.getCantidadComida(), "La cantidad de comida debe ser 0");
    }

    @Test
    @DisplayName("Test verificar cantidad de medicamentos inicial")
    void testCantidadMedicamentoInicialEsCero() {
        assertEquals(0, tienda.getCantidadMedicamento(), "La cantidad de medicamentos debe ser 0");
    }

    @Test
    @DisplayName("Test verificar multiples comidas en la list")
    void testCantidadComidaAcumula() {
        tienda.ComprarSuministros(1);
        tienda.ComprarSuministros(1);

        assertEquals(2, tienda.getCantidadComida(), "La cantidad de comida debe ser 2");
    }

    @Test
    @DisplayName("Test la lista de medicamentos no cuenta comida")
    void testCantidadMedicamentoNoContaComida() {
        tienda.ComprarSuministros(1);

        assertEquals(0, tienda.getCantidadMedicamento(), "La cantidad de medicamentos debe ser 0");
    }
}
