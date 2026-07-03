package backend;

public class EstadoSaludable implements EstadoMascota {
    /**
     * Como es el estado saludable ninguna atributo se actualiza
     * @param mascota la mascota cuyo cuyos atributos van a ser modificados
     */
    @Override
    public void manejarEstado(Mascota mascota) {

    }

    /**
     * metodo que retorna el estado "saludable" de la mascota
     * @return el estado de la mascota
     */
    @Override
    public String getEstado() {
        return "Saludable";
    }

    /**
     * metodo que retorna una descripcion del estado saludable
     * @return la descripcion del estado en que se encuentra la mascota
     */
    @Override
    public String getDescripcion() {
        return "La mascota esta en buenas condiciones";
    }
}
