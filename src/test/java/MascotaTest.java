import backend.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MascotaTest {

    static class MascotaT extends Mascota {
        public MascotaT(String nombre, float precio) {
            super(nombre, precio, TipoMascota.PERRO);
        }

        @Override
        public void alimentar() {
            setHambre(getHambre() + 20);

            notificarObservers();
        }

        @Override
        public void limpiar() {
            setHigiene(getHigiene() + 20);

            notificarObservers();
        }

        @Override
        public void jugar() {
            setFelicidad(getFelicidad() + 20);

            notificarObservers();
        }

        @Override
        protected int getDegradacionHambre() {
            return 15;
        }

        @Override
        protected int getDegradacionHigiene() {
            return 15;
        }

        @Override
        protected int getDegradacionFelicidad() {
            return 15;
        }
    }
    private Mascota mascota;

    @BeforeEach
    void setUp() {
        mascota = new MascotaT("MascotaTest", 100);
    }

    @Test
    @DisplayName("Test verificacion de atributos iniciales")
    void testAtributosIniciales() {
        assertEquals(100, mascota.getHambre(), "El atributo hambre debe estar en 100");
        assertEquals(100, mascota.getHigiene(), "El atributo higiene debe estar en 100");
        assertEquals(100, mascota.getHigiene(), "El atributo felicidad debe estar en 100");
        assertEquals(100, mascota.getSalud(), "El atributo salud debe estar en 100");
    }

    @Test
    @DisplayName("Test verificacion estado inicial")
    void testEstadoInicial() {
        assertInstanceOf(EstadoSaludable.class, mascota.getEstado(), "El estado inicial debe ser saludable");
    }

    @Test
    @DisplayName("Test atributo hambre no supera 100")
    void testHambreNoSuperaCien() {
        mascota.setHambre(150);
        assertEquals(100, mascota.getHambre(), "El atributo hambre no puede superar 100");
    }

    @Test
    @DisplayName("Test atributo higiene no supera 100")
    void testHigieneNoSuperaCien() {
        mascota.setHigiene(150);
        assertEquals(100, mascota.getHigiene(), "El atributo higiene no puede superar 100");
    }

    @Test
    @DisplayName("Test atributo felicidad no supera 100")
    void testFelicidadNoSuperaCien() {
        mascota.setFelicidad(150);
        assertEquals(100, mascota.getFelicidad(), "El atributo felicidad no puede superar 100");
    }

    @Test
    @DisplayName("Test atributo salud no supera 100")
    void testSaludNoSuperaCien() {
        mascota.setSalud(150);
        assertEquals(100, mascota.getSalud(), "El atributo salud no puede superar 100");
    }

    @Test
    @DisplayName("Test degradacion atributo hambre")
    void testDegradacionHambre() {
        mascota.pasarTiempo();
        assertEquals(85, mascota.getHambre(), "El atributo hambre tiene que estar en 85");
    }

    @Test
    @DisplayName("Test degradacion atributo higiene")
    void testDegradacionHigiene() {
        mascota.pasarTiempo();
        assertEquals(85, mascota.getHigiene(), "El atributo higiene tiene que estar en 85");
    }

    @Test
    @DisplayName("Test degradacion atributo felicidad")
    void testDegradacionFelicidad() {
        mascota.pasarTiempo();
        assertEquals(85, mascota.getFelicidad(), "El atributo felicidad tiene que estar en 85");
    }

    @Test
    @DisplayName("Test atributos no bajan de cero")
    void TestAtributosNoBajanCero() {
        for(int i = 0; i < 10; i++) {
            mascota.pasarTiempo();
        }

        assertTrue(mascota.getHambre() >= 0, "El atributo hambre tiene que ser positivo");
        assertTrue(mascota.getHigiene() >= 0, "El atributo higiene tiene que ser positivo");
        assertTrue(mascota.getFelicidad() >= 0, "El atributo felicidad tiene que ser positivo");
    }

    @Test
    @DisplayName("Test cambiar estado a triste")
    void testCambiarEstadoTriste() {
        mascota.setFelicidad(30);
        mascota.pasarTiempo();

        assertInstanceOf(EstadoTriste.class, mascota.getEstado(), "El estado debe ser triste");
    }

    @Test
    @DisplayName("Test cambiar estado a enfermo")
    void testCambiarEstadoEnfermo() {
        mascota.setSalud(50);
        mascota.pasarTiempo();

        assertInstanceOf(EstadoEnfermo.class, mascota.getEstado(), "El estado debe ser enfermo");
    }

    @Test
    @DisplayName("Test cambiar estado a critico por salud")
    void testCambiarEstadoCriticoSalud() {
        mascota.setSalud(10);
        mascota.pasarTiempo();

        assertInstanceOf(EstadoCritico.class, mascota.getEstado(), "El estado debe ser critico");
    }

    @Test
    @DisplayName("Test cambiar estado a critico por hambre")
    void testCambiarEstadoCriticoHambre() {
        mascota.setHambre(5);
        mascota.pasarTiempo();

        assertInstanceOf(EstadoCritico.class, mascota.getEstado(), "El estado debe ser critico");
    }

    @Test
    @DisplayName("Test verificar atendersalud() retorna true para enfermo")
    void testAtenderSaludEnfermoTrue() {
        mascota.setSalud(50);
        mascota.pasarTiempo();

        assertTrue(mascota.atenderSalud(), "La funcion atendersalud() debe devolver true");
    }

    @Test
    @DisplayName("Test verificar atendersalud() retorna true para critico")
    void testAtenderSaludCriticoTrue() {
        mascota.setSalud(20);
        mascota.pasarTiempo();

        assertTrue(mascota.atenderSalud(), "La funcion atendersalud() debe devolver true");
    }

    @Test
    @DisplayName("Test verificar atendersalud() retorna false")
    void testAtenderSaludEnfermoFalse() {
        assertFalse(mascota.atenderSalud(), "La funcion atendersalud() debe devolver false");
    }

    @Test
    @DisplayName("Test verificar aumenta salud")
    void testAtenderSaludSubeSalud() {
        mascota.setSalud(60);
        mascota.pasarTiempo();

        int salud = mascota.getSalud();

        mascota.atenderSalud();

        assertTrue(mascota.getFelicidad() > salud, "la salud debe ser mayor a la anterior");
    }

    @Test
    @DisplayName("Test el observer es notificado")
    void observerNotificado() {
        boolean[] notificado = { false };

        mascota.agregarObserver(m -> notificado[0] = true );
        mascota.pasarTiempo();

        assertTrue(notificado[0], "El observer debe ser notificado");
    }

    @Test
    @DisplayName("Test verificar observer eliminado no sea notificado")
    void observerEliminadoNoNotificado() {
        boolean[] notificado = { false };

        MascotaObserver observer = m -> notificado[0] = true;

        mascota.agregarObserver(observer);
        mascota.eliminarObserver(observer);
        mascota.pasarTiempo();

        assertFalse(notificado[0], "El observer eliminado no debe ser notificado");
    }
}
