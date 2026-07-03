package backend;

public interface EstadoMascota {
    /**
     * Actualiza los atributos de la mascota dependiendo de su estado
     * @param mascota la mascota cuyo cuyos atributos van a ser modificados
     */
    public void manejarEstado(Mascota mascota);

    /**
     * metodo que retorna el nombre del estado que se encuentra la mascota
     * @return el nombre del estado
     */
    public String getEstado();

    /**
     * metodo que retorna una breve descripcion del estado que se encuentra la mascota
     * @return una descripcion del estado
     */
    public String getDescripcion();
}
