package backend;

public class EstadoTriste implements EstadoMascota {
    @Override
    public void manejarEstado(Mascota mascota) {
        mascota.setSalud(mascota.getSalud() - 1);
    }

    @Override
    public String getEstado() {
        return "Triste";
    }

    @Override
    public String getDescripcion() {
        return "La mascota esta triste. Juega con ella para animarla";
    }
}
