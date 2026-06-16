package backend;

public class EstadoSaludable implements EstadoMascota {
    @Override
    public void manejarEstado(Mascota mascota) {

    }

    @Override
    public String getEstado() {
        return "Saludable";
    }

    @Override
    public String getDescripcion() {
        return "La mascota esta en buenas condiciones";
    }
}
