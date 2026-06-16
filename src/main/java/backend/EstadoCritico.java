package backend;

public class EstadoCritico implements EstadoMascota {
    @Override
    public void manejarEstado(Mascota mascota) {
        mascota.setSalud(mascota.getSalud() - 7);
        mascota.setFelicidad(mascota.getFelicidad() - 8);
        mascota.setHambre(mascota.getHambre() - 5);
    }

    @Override
    public String getEstado() {
        return "Critico";
    }

    @Override
    public String getDescripcion() {
        return "La mascota esta en estado critico. Debes actuar de inmediato";
    }
}
