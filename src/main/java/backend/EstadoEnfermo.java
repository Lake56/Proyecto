package backend;

public class EstadoEnfermo implements EstadoMascota {

    /**
     *Actualiza los atributos de la mascto
     * @param mascota la mascota cuyo cuyos atributos van a ser modificados
     */
    @Override
    public void manejarEstado(Mascota mascota) {
        mascota.setSalud(mascota.getSalud() - 3);
        mascota.setFelicidad(mascota.getFelicidad() - 3);
    }

    /**
     * metodo que retorna el estado "enfermo" de la mascota
     * @return el estado de la mascota
     */
    @Override
    public String getEstado() {
        return "Enfermo";
    }

    /**
     * metodo que retorna una descripcion del estado enfermo
     * @return la descripcion del estado en que se encuentra la mascota
     */
    @Override
    public String getDescripcion() {
        return "La mascota esta enferma. Necesita atencion medica";
    }
}
