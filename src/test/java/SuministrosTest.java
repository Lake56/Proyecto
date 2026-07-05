import backend.Comida;
import backend.Medicamento;
import backend.Suministro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SuministrosTest {

    @Test
    @DisplayName("Test verifica que comida tenga precio correcto")
    void testComidaPrecioCorecto() {
        Comida comida = new Comida(5000);

        assertEquals(5000, comida.getPrecio(), "El precio de la comida debe ser 5000");
    }

    @Test
    @DisplayName("Test verifica que comida sea instancia de clase suministro")
    void testComidaEsInstanciaDeSuministro() {
        Comida comida = new Comida(5000);

        assertInstanceOf(Suministro.class, comida, "La comida debe ser instancia de la clase suministro");
    }

    @Test
    @DisplayName("Test verifica que medicamento tenga precio correcto")
    void testMedicamentoPrecioCorrecto() {
        Medicamento med = new Medicamento(15000);

        assertEquals(15000, med.getPrecio(), "El precio del medicamento debe ser 15000");
    }

    @Test
    @DisplayName("Test verifica que medicamento sea instancia de suministro")
    void testMedicamentoEsInstanciaDeSuministro() {
        Medicamento med = new Medicamento(15000);

        assertInstanceOf(Suministro.class, med, "El medicamento debe ser instancia de la clase medicamento");
    }

    @Test
    @DisplayName("Test verifica que comida no sea instancia de la clase medicamento")
    void testComidaNoEsMedicamento() {
        Suministro sum = new Comida(5000);

        assertFalse(sum instanceof Medicamento, "La comida no debe ser instancia de la clase medicamento");
    }

    @Test
    @DisplayName("Test verifica que medicamento no sea instancia de la clase comida")
    void testMedicamentoNoEsComida() {
        Suministro sum = new Medicamento(15000);

        assertFalse(sum instanceof Comida, "El medicamento no debe ser instancia de la clase comida");
    }
}
