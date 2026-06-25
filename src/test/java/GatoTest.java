import backend.Gato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GatoTest {
    private Gato gato;

    @BeforeEach
    void setUp() {
        gato = new Gato("Test", 125);
    }

    @Test
    @DisplayName("Test verificar que los atributos iniciales empiezen en 100")
    void testAtributosIniciales100() {
        assertEquals(100, gato.getHambre(), "El estado inicial de hambre debe ser 100");
        assertEquals(100, gato.getFelicidad(), "El estado inicial de felicidad debe ser 100");
        assertEquals(100, gato.getHigiene(), "El estado inicial de higiene debe ser 100");
        assertEquals(100, gato.getSalud(), "El estado inicial de salud debe ser 100");
    }

    @Test
    void testAlimentarSubeHambre() {
        gato.setHambre(60);
        gato.alimentar();

        assertEquals(80, gato.getHambre(), "El atributo hambre deberia ser 80");
    }

    @Test
    @DisplayName("Test verificar que la felicidad suba al alimentar")
    void testAlimentarSubeFelicidad() {
        gato.setFelicidad(50);
        gato.alimentar();

        assertEquals(55, gato.getFelicidad(), "El atributo felicidad deberia ser 55");
    }

    @Test
    @DisplayName("Test verificar que el atributo hambre no supere 100")
    void testAlimentarNoSuperaCien() {
        gato.alimentar();

        assertTrue(gato.getHambre() <= 100, "El atributo hambre no debe superar 100");
    }

    @Test
    @DisplayName("Test verificar que el atributo higiene aumente")
    void testLimpiarSubeHigiene() {
        gato.setHigiene(50);
        gato.limpiar();

        assertEquals(70, gato.getHigiene(), "El atributo higiene debe ser 70");
    }

    @Test
    @DisplayName("Test verificar que limpiar baje felicidad")
    void testLimpiarBajaFelicidad() {
        gato.setFelicidad(50);
        gato.limpiar();

        assertEquals(35, gato.getFelicidad(), "El atributo felicidad debe 35");
    }

    @Test
    @DisplayName("Test verificar que el atributo higiene no supere 100")
    void testLimpiarNoSuperaCien() {
        gato.limpiar();

        assertTrue(gato.getHigiene() <= 100, "El atributo higiene no debe superar 100");
    }

    @Test
    @DisplayName("Test verificar que jugar aumente el atributo felicidad")
    void testJugarSubeFelicidad() {
        gato.setFelicidad(50);
        gato.jugar();

        assertEquals(70, gato.getFelicidad(), "El atributo felicidad debe ser 70");
    }

    @Test
    @DisplayName("Test verificar que jugar baje el atributo hambre")
    void testJugarBajaHambre() {
        gato.setHambre(50);
        gato.jugar();

        assertEquals(45, gato.getHambre(), "El atributo hambre debe ser 45");
    }

    @Test
    @DisplayName("Test verificar que jugar baje el atributo higiene")
    void testJugarBajaHigiene() {
        gato.setHigiene(50);
        gato.jugar();

        assertEquals(40, gato.getHigiene(), "El atributo higiene debe ser 40");
    }

    @Test
    @DisplayName("Test verificar que jugar no vuelve negativo los atributos")
    void testJugarNoBajaAtributosDeCero() {
        gato.setHambre(5);
        gato.setHigiene(1);
        gato.jugar();

        assertTrue(gato.getHambre() >= 0, "El atributo hambre no puede ser negativo");
        assertTrue(gato.getHigiene() >= 0, "El atributo higiene no puede ser negativo" );
    }

    @Test
    @DisplayName("Test verificar que la degradacion del atributo hambre sea de 5")
    void testDegradacionHambreEsCinco() {
        gato.pasarTiempo();

        assertEquals(95, gato.getHambre(), "El atributo hambre debe degradarse a 95");
    }

    @Test
    @DisplayName("Test verificar que la degradacion del atributo felicidad sea de 3")
    void testDegradacionFelicidadEsTres() {
        gato.pasarTiempo();

        assertEquals(97, gato.getFelicidad(), "El atributo hambre debe degradarse a 97");
    }

    @Test
    @DisplayName("Test verificar que la degradacion del atributo higiene sea de 2")
    void testDegradacionHigieneEsDos() {
        gato.pasarTiempo();

        assertEquals(98, gato.getHigiene(), "El atributo hambre debe degradarse a 98");
    }
}
