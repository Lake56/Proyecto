package backend;

public class Gato extends Mascota {

    /**
     * metodo que establece el nombre y precio del gato
     * @param nombre del gato
     * @param precio del gato
     */
    public Gato(String nombre, float precio) {
        super(nombre, precio,TipoMascota.GATO);
    }

    /**
     * Metodo que actualiza los atributos hambre y felicidad del gato
     */
    @Override
    public void alimentar() {
        setHambre(getHambre() + 20);
        setFelicidad(getFelicidad() + 5);

        notificarObservers();
    }

    /**
     * Metodo que actualiza los atributos higiene y felicidad del gato
     */
    @Override
    public void limpiar() {
        setHigiene(getHigiene() + 20);
        setFelicidad(getFelicidad() - 15);

        notificarObservers();
    }

    /**
     * Metodo que actualiza los atributos felicidad, hambre y higiene del gato
     */
    @Override
    public void jugar() {
        setFelicidad(getFelicidad() + 20);
        setHambre(getHambre() - 5);
        setHigiene(getHigiene() - 10);

        notificarObservers();
    }

    /**
     * Metodo cual retorna el valor que se tiene que degradar el atributo hambre del gato
     * @return 5 puntos que se degrada este atributo
     */
    @Override
    protected int getDegradacionHambre() {
        return 5;
    }

    /**
     * Metodo cual retorna el valor que se tiene que degradar el atributo felicidad del gato
     * @return 3 puntos que se degrada este atributo
     */
    @Override
    protected int getDegradacionFelicidad() {
        return 3;
    }

    /**
     * Metodo cual retorna el valor que se tiene que degradar el atributo higiene del gato
     * @return 2 puntos que se degrada este atributo
     */
    @Override
    protected int getDegradacionHigiene() {
        return 2;
    }
}
