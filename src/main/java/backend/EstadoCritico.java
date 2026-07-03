package backend;

public class EstadoCritico implements EstadoMascota {

    /**
     * Actualiza los atributos de la mascota
     * @param mascota la mascota cuyo cuyos atributos van a ser modificados
     */
    @Override
    public void manejarEstado(Mascota mascota) {
        mascota.setSalud(mascota.getSalud() - 7);
        mascota.setFelicidad(mascota.getFelicidad() - 8);
        mascota.setHambre(mascota.getHambre() - 5);
    }

    /**
     * metodo que retorna el estado "critico" de la mascota
     * @return el estado de la mascota
     */
    @Override
    public String getEstado() {
        return "Critico";
    }

    /**
     * metodo que retorna una descripcion del estado critico
     * @return la descripcion del estado en que se encuentra la mascota
     */
    @Override
    public String getDescripcion() {
        return "La mascota esta en estado critico. Debes actuar de inmediato";
    }
}
