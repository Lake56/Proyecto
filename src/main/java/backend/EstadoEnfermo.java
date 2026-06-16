package backend;

public class EstadoEnfermo implements EstadoMascota {
    @Override
    public void manejarEstado(Mascota mascota) {
        mascota.setSalud(mascota.getSalud() - 3);
        mascota.setFelicidad(mascota.getFelicidad() - 3);
    }

    @Override
    public String getEstado() {
        return "Enfermo";
    }

    @Override
    public String getDescripcion() {
        return "La mascota esta enferma. Necesita atencion medica";
    }
}
