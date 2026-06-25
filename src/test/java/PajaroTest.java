import backend.Gato;
import backend.Pajaro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PajaroTest {
    private Pajaro pajaro;

    @BeforeEach
    void setUp() {
        pajaro = new Pajaro("Test", 125);
    }

    @Test
    @DisplayName("Test verificar que los atributos iniciales empiezen en 100")
    void testAtributosIniciales100() {
        assertEquals(100, pajaro.getHambre(), "El estado inicial de hambre debe ser 100");
        assertEquals(100, pajaro.getFelicidad(), "El estado inicial de felicidad debe ser 100");
        assertEquals(100, pajaro.getHigiene(), "El estado inicial de higiene debe ser 100");
        assertEquals(100, pajaro.getSalud(), "El estado inicial de salud debe ser 100");
    }

    @Test
    void testAlimentarSubeHambre() {
        pajaro.setHambre(60);
        pajaro.alimentar();

        assertEquals(85, pajaro.getHambre(), "El atributo hambre deberia ser 85");
    }

    @Test
    @DisplayName("Test verificar que la felicidad suba al alimentar")
    void testAlimentarSubeFelicidad() {
        pajaro.setFelicidad(50);
        pajaro.alimentar();

        assertEquals(53, pajaro.getFelicidad(), "El atributo felicidad deberia ser 53");
    }

    @Test
    @DisplayName("Test verificar que el atributo hambre no supere 100")
    void testAlimentarNoSuperaCien() {
        pajaro.alimentar();

        assertTrue(pajaro.getHambre() <= 100, "El atributo hambre no debe superar 100");
    }

    @Test
    @DisplayName("Test verificar que el atributo higiene aumente")
    void testLimpiarSubeHigiene() {
        pajaro.setHigiene(50);
        pajaro.limpiar();

        assertEquals(85, pajaro.getHigiene(), "El atributo higiene debe ser 85");
    }

    @Test
    @DisplayName("Test verificar que limpiar baje felicidad")
    void testLimpiarBajaFelicidad() {
        pajaro.setFelicidad(50);
        pajaro.limpiar();

        assertEquals(47, pajaro.getFelicidad(), "El atributo felicidad debe 47");
    }

    @Test
    @DisplayName("Test verificar que el atributo higiene no supere 100")
    void testLimpiarNoSuperaCien() {
        pajaro.limpiar();

        assertTrue(pajaro.getHigiene() <= 100, "El atributo higiene no debe superar 100");
    }

    @Test
    @DisplayName("Test verificar que jugar aumente el atributo felicidad")
    void testJugarSubeFelicidad() {
        pajaro.setFelicidad(50);
        pajaro.jugar();

        assertEquals(80, pajaro.getFelicidad(), "El atributo felicidad debe ser 80");
    }

    @Test
    @DisplayName("Test verificar que jugar baje el atributo hambre")
    void testJugarBajaHambre() {
        pajaro.setHambre(50);
        pajaro.jugar();

        assertEquals(44, pajaro.getHambre(), "El atributo hambre debe ser 44");
    }

    @Test
    @DisplayName("Test verificar que jugar baje el atributo higiene")
    void testJugarBajaHigiene() {
        pajaro.setHigiene(50);
        pajaro.jugar();

        assertEquals(45, pajaro.getHigiene(), "El atributo higiene debe ser 45");
    }

    @Test
    @DisplayName("Test verificar que jugar no vuelve negativo los atributos")
    void testJugarNoBajaAtributosDeCero() {
        pajaro.setHambre(5);
        pajaro.setHigiene(1);
        pajaro.jugar();

        assertTrue(pajaro.getHambre() >= 0, "El atributo hambre no puede ser negativo");
        assertTrue(pajaro.getHigiene() >= 0, "El atributo higiene no puede ser negativo" );
    }

    @Test
    @DisplayName("Test verificar que la degradacion del atributo hambre sea de 5")
    void testDegradacionHambreEsCuatro() {
        pajaro.pasarTiempo();

        assertEquals(96, pajaro.getHambre(), "El atributo hambre debe degradarse a 96");
    }

    @Test
    @DisplayName("Test verificar que la degradacion del atributo felicidad sea de 3")
    void testDegradacionFelicidadEsSeis() {
        pajaro.pasarTiempo();

        assertEquals(94, pajaro.getFelicidad(), "El atributo hambre debe degradarse a 94");
    }

    @Test
    @DisplayName("Test verificar que la degradacion del atributo higiene sea de 2")
    void testDegradacionHigieneEsSiete() {
        pajaro.pasarTiempo();

        assertEquals(93, pajaro.getHigiene(), "El atributo hambre debe degradarse a 93");
    }

}
