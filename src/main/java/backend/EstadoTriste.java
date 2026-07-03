package backend;

public class EstadoTriste implements EstadoMascota {

    /**
     * Actualiza los atributos de la mascota
     * @param mascota la mascota cuyo cuyos atributos van a ser modificados
     */
    @Override
    public void manejarEstado(Mascota mascota) {
        mascota.setSalud(mascota.getSalud() - 1);
    }

    /**
     * metodo que retorna el estado "triste" de la mascota
     * @return el estado de la mascota
     */
    @Override
    public String getEstado() {
        return "Triste";
    }

    /**
     * metodo que retorna una descripcion del estado triste
     * @return la descripcion del estado en que se encuentra la mascota
     */
    @Override
    public String getDescripcion() {
        return "La mascota esta triste. Juega con ella para animarla";
    }
}
