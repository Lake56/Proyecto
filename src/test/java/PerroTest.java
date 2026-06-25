import backend.Perro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerroTest {

    private Perro perro;

    @BeforeEach
    void setUp() {
        perro = new Perro("Test", "Pastor Belgo", 125);
    }

    @Test
    @DisplayName("Test verificar que los atributos iniciales empiezen en 100")
    void testAtributosIniciales100() {
        assertEquals(100, perro.getHambre(), "El estado inicial de hambre debe ser 100");
        assertEquals(100, perro.getFelicidad(), "El estado inicial de felicidad debe ser 100");
        assertEquals(100, perro.getHigiene(), "El estado inicial de higiene debe ser 100");
        assertEquals(100, perro.getSalud(), "El estado inicial de salud debe ser 100");
    }

    @Test
    @DisplayName("Test verificar que se retorne la raza correcta")
    void testRaza() {
        assertEquals("Pastor Belgo", perro.getRaza());
    }

    @Test
    void testAlimentarSubeHambre() {
        perro.setHambre(60);
        perro.alimentar();

        assertEquals(95, perro.getHambre(), "El atributo hambre deberia ser 95");
    }

    @Test
    @DisplayName("Test verificar que la felicidad suba al alimentar")
    void testAlimentarSubeFelicidad() {
        perro.setFelicidad(50);
        perro.alimentar();

        assertEquals(55, perro.getFelicidad(), "El atributo felicidad deberia ser 55");
    }

    @Test
    @DisplayName("Test verificar que el atributo hambre no supere 100")
    void testAlimentarNoSuperaCien() {
        perro.alimentar();

        assertTrue(perro.getHambre() <= 100, "El atributo hambre no debe superar 100");
    }

    @Test
    @DisplayName("Test verificar que el atributo higiene aumente")
    void testLimpiarSubeHigiene() {
        perro.setHigiene(50);
        perro.limpiar();

        assertEquals(90, perro.getHigiene(), "El atributo higiene debe ser 90");
    }

    @Test
    @DisplayName("Test verificar que el atributo felicidad baje al limpiar")
    void testLimpiarBajaFelicidad() {
        perro.setFelicidad(50);
        perro.limpiar();

        assertEquals(43, perro.getFelicidad(), "El atributo felicidad debe 43");
    }

    @Test
    @DisplayName("Test verificar que el atributo higiene no supere 100")
    void testLimpiarNoSuperaCien() {
        perro.limpiar();

        assertTrue(perro.getHigiene() <= 100, "El atributo higiene no debe superar 100");
    }

    @Test
    @DisplayName("Test verificar que jugar aumente el atributo felicidad")
    void testJugarSubeFelicidad() {
        perro.setFelicidad(50);
        perro.jugar();

        assertEquals(85, perro.getFelicidad(), "El atributo felicidad debe ser 85");
    }

    @Test
    @DisplayName("Test verificar que jugar baje el atributo hambre")
    void testJugarBajaHambre() {
        perro.setHambre(50);
        perro.jugar();

        assertEquals(35, perro.getHambre(), "El atributo hambre debe ser 35");
    }

    @Test
    @DisplayName("Test verificar que  jugar baje el atributo higiene")
    void testJugarBajaHigiene() {
        perro.setHigiene(50);
        perro.jugar();

        assertEquals(45, perro.getHigiene(), "El atributo higiene debe ser 45");
    }

    @Test
    @DisplayName("Test verificar que jugar no vuelve negativo los atributos")
    void testJugarNoBajaAtributosDeCero() {
        perro.setHambre(5);
        perro.setHigiene(1);
        perro.jugar();

        assertTrue(perro.getHambre() >= 0, "El atributo hambre no puede ser negativo");
        assertTrue(perro.getHigiene() >= 0, "El atributo higiene no puede ser negativo" );
    }

    @Test
    @DisplayName("Test verificar que la degradacion del atributo hambre sea de 8")
    void testDegradacionHambreEsOcho() {
        perro.pasarTiempo();

        assertEquals(92, perro.getHambre(), "El atributo hambre debe degradarse a 92");
    }

    @Test
    @DisplayName("Test verificar que la degradacion del atributo felicidad sea de 5")
    void testDegradacionFelicidadEsCinco() {
        perro.pasarTiempo();

        assertEquals(95, perro.getFelicidad(), "El atributo hambre debe degradarse a 95");
    }

    @Test
    @DisplayName("Test verificar que la degradacion del atributo higiene sea de 6")
    void testDegradacionHigieneEsSeis() {
        perro.pasarTiempo();

        assertEquals(94, perro.getHigiene(), "El atributo hambre debe degradarse a 94");
    }
}
