import backend.Pajaro;
import backend.Pez;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PezTest {
    private Pez pez;

    @BeforeEach
    void setUp() {
        pez = new Pez("Test", 75);
    }

    @Test
    @DisplayName("Test verificar que los atributos iniciales empiezen en 100")
    void testAtributosIniciales100() {
        assertEquals(100, pez.getHambre(), "El estado inicial de hambre debe ser 100");
        assertEquals(100, pez.getFelicidad(), "El estado inicial de felicidad debe ser 100");
        assertEquals(100, pez.getHigiene(), "El estado inicial de higiene debe ser 100");
        assertEquals(100, pez.getSalud(), "El estado inicial de salud debe ser 100");
    }

    @Test
    void testAlimentarSubeHambre() {
        pez.setHambre(60);
        pez.alimentar();

        assertEquals(75, pez.getHambre(), "El atributo hambre deberia ser 75");
    }

    @Test
    @DisplayName("Test verificar que la felicidad suba al alimentar")
    void testAlimentarSubeFelicidad() {
        pez.setFelicidad(50);
        pez.alimentar();

        assertEquals(57, pez.getFelicidad(), "El atributo felicidad deberia ser 57");
    }

    @Test
    @DisplayName("Test verificar que el atributo hambre no supere 100")
    void testAlimentarNoSuperaCien() {
        pez.alimentar();

        assertTrue(pez.getHambre() <= 100, "El atributo hambre no debe superar 100");
    }

    @Test
    @DisplayName("Test verificar que el atributo higiene aumente")
    void testLimpiarSubeHigiene() {
        pez.setHigiene(50);
        pez.limpiar();

        assertEquals(100, pez.getHigiene(), "El atributo higiene debe ser 100");
    }

    @Test
    @DisplayName("Test verificar que limpiar baje felicidad")
    void testLimpiarBajaFelicidad() {
        pez.setFelicidad(50);
        pez.limpiar();

        assertEquals(46, pez.getFelicidad(), "El atributo felicidad debe 46");
    }

    @Test
    @DisplayName("Test verificar que el atributo higiene no supere 100")
    void testLimpiarNoSuperaCien() {
        pez.limpiar();

        assertTrue(pez.getHigiene() <= 100, "El atributo higiene no debe superar 100");
    }

    @Test
    @DisplayName("Test verificar que jugar aumente el atributo felicidad")
    void testJugarSubeFelicidad() {
        pez.setFelicidad(50);
        pez.jugar();

        assertEquals(60, pez.getFelicidad(), "El atributo felicidad debe ser 60");
    }

    @Test
    @DisplayName("Test verificar que jugar baje el atributo hambre")
    void testJugarBajaHambre() {
        pez.setHambre(50);
        pez.jugar();

        assertEquals(45, pez.getHambre(), "El atributo hambre debe ser 45");
    }

    @Test
    @DisplayName("Test verificar que jugar baje el atributo higiene")
    void testJugarBajaHigiene() {
        pez.setHigiene(50);
        pez.jugar();

        assertEquals(47, pez.getHigiene(), "El atributo higiene debe ser 47");
    }

    @Test
    @DisplayName("Test verificar que jugar no vuelve negativo los atributos")
    void testJugarNoBajaAtributosDeCero() {
        pez.setHambre(5);
        pez.setHigiene(1);
        pez.jugar();

        assertTrue(pez.getHambre() >= 0, "El atributo hambre no puede ser negativo");
        assertTrue(pez.getHigiene() >= 0, "El atributo higiene no puede ser negativo" );
    }

    @Test
    @DisplayName("Test verificar que la degradacion del atributo hambre sea de 5")
    void testDegradacionHambreEsCuatro() {
        pez.pasarTiempo();

        assertEquals(96, pez.getHambre(), "El atributo hambre debe degradarse a 96");
    }

    @Test
    @DisplayName("Test verificar que la degradacion del atributo felicidad sea de 3")
    void testDegradacionFelicidadEsDos() {
        pez.pasarTiempo();

        assertEquals(98, pez.getFelicidad(), "El atributo hambre debe degradarse a 98");
    }

    @Test
    @DisplayName("Test verificar que la degradacion del atributo higiene sea de 2")
    void testDegradacionHigieneEsDies() {
        pez.pasarTiempo();

        assertEquals(90, pez.getHigiene(), "El atributo hambre debe degradarse a 90");
    }
}
