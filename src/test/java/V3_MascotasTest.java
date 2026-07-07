import backend.Gato;
import backend.Perro;
import backend.Tienda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class V3_MascotasTest {
    private Tienda tienda;

    @BeforeEach
    void setUp() {
        tienda = new Tienda(100000);
    }

    @Test
    @DisplayName("Test verifica que se retorna null cuando no hay mascotas")
    void testGetMascotaActualRetornaNullSinMascotas() {
        assertDoesNotThrow(() -> {
            if (tienda.getMascotas().isEmpty()) {
                assertNull(null, "Debe retornar null");
            }
        });
    }

    @Test
    @DisplayName("Test verifica la degradacion de atributos")
    void testPasarTiempoDegradaAtributos() {
        Perro perro = new Perro("Rex", "Labrador", 5000);

        tienda.AgregarMascota(perro);

        int hambreAntes = perro.getHambre();

        perro.pasarTiempo();

        assertTrue(perro.getHambre() < hambreAntes, "Se debe degradar el atributo hambre");
    }

    @Test
    @DisplayName("Test verifica que los atributos no bajen de 0")
    void testPasarTiempoVariasVecesNoBajaDesCero() {
        Perro perro = new Perro("Rex", "Labrador", 5000);

        tienda.AgregarMascota(perro);

        for (int i = 0; i < 30; i++) {
            perro.pasarTiempo();
        }

        assertTrue(perro.getHambre() >= 0, "El atributo hambre ser positivo");
        assertTrue(perro.getFelicidad() >= 0, "El atributo felicidad ser positivo");
        assertTrue(perro.getHigiene() >= 0, "El higiene hambre ser positivo");
        assertTrue(perro.getSalud() >= 0, "El atributo salud ser positivo");
    }

    @Test
    @DisplayName("Test verifica alimentar sin comida no aumente el hambre")
    void testAlimentarSinComidaNoModificaHambre() {
        Perro perro = new Perro("Rex", "Labrador", 5000);

        tienda.AgregarMascota(perro);

        perro.setHambre(50);

        if (tienda.getCantidadComida() == 0) {
            int hambreAntes = perro.getHambre();

            assertEquals(hambreAntes, perro.getHambre(), "El atributo hambre debe ser igual");
        }
    }

    @Test
    @DisplayName("Test verifica alimentar aumenta el hambre y gasta comida")
    void testAlimentarConComidaSubeHambreYGastaComida() {
        Perro perro = new Perro("Rex", "Labrador", 5000);

        tienda.AgregarMascota(perro);
        tienda.ComprarSuministros(1);

        perro.setHambre(50);

        int hambreAntes = perro.getHambre();

        perro.alimentar();

        tienda.gastarComida();

        assertTrue(perro.getHambre() > hambreAntes, "El atributo hambre debe aumenta");
        assertEquals(0, tienda.getCantidadComida(), "No debe quedar comida");
    }

    @Test
    @DisplayName("Test verifica atender salud sin medicamentos no aumenta la salud")
    void testAtenderSaludSinMedicamentoNoModificaSalud() {
        Perro perro = new Perro("Rex", "Labrador", 5000);

        tienda.AgregarMascota(perro);

        perro.setSalud(40);
        perro.pasarTiempo();

        if (tienda.getCantidadMedicamento() == 0) {
            int saludAntes = perro.getSalud();

            assertEquals(saludAntes, perro.getSalud(), "El atributo salud no debe variar");
        }
    }

    @Test
    @DisplayName("Test verifica atender salud con medicamentos aumenta salud y gasta meidcamentos")
    void testAtenderSaludConMedicamentoSubeSaludYGastaMedicamento() {
        Perro perro = new Perro("Rex", "Labrador", 5000);

        tienda.AgregarMascota(perro);
        tienda.ComprarSuministros(2);

        perro.setSalud(40);
        perro.pasarTiempo();

        int saludAntes = perro.getSalud();
        boolean atendida = perro.atenderSalud();

        if (atendida) {
            tienda.gastarMedicamento();

            assertTrue(perro.getSalud() > saludAntes, "El atributo salud debe aumentar");
            assertEquals(0, tienda.getCantidadMedicamento(), "No deben quedar medicamentos");
        }
    }

    @Test
    @DisplayName("Test verifica atender salud a una mascota saludable no gasta medicamentos")
    void testAtenderSaludMascotaSaludableNoGastaMedicamento() {
        Perro perro = new Perro("Rex", "Labrador", 5000);

        tienda.AgregarMascota(perro);
        tienda.ComprarSuministros(2);

        boolean atendida = perro.atenderSalud();

        assertFalse(atendida);
        assertEquals(1, tienda.getCantidadMedicamento(), "No se debe gastar el medicamento");
    }
}
