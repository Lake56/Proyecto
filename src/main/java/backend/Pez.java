package backend;

public class Pez extends Mascota{
    /**
     * metodo que establece el nombre y precio del pez
     * @param nombre del pez
     * @param precio del pez
     */
    public Pez(String nombre, float precio) {
        super(nombre, precio,TipoMascota.PEZ);
    }

    /**
     * Metodo que actualiza los atributos hambre y felicidad del pez
     */
    @Override
    public void alimentar() {
        setHambre(getHambre() + 15);
        setFelicidad(getFelicidad() + 7);

        notificarObservers();
    }

    /**
     * Metodo que actualiza los atributos higiene y felicidad del pez
     */
    @Override
    public void limpiar() {
        setHigiene(getHigiene() + 50);
        setFelicidad(getFelicidad() - 4);

        notificarObservers();
    }

    /**
     * Metodo que actualiza los atributos felicidad, hambre y higiene del pez
     */
    @Override
    public void jugar() {
        setFelicidad(getFelicidad()  + 10);
        setHambre(getHambre() - 5);
        setHigiene(getHigiene() - 3);

        notificarObservers();
    }

    /**
     * Metodo cual retorna el valor que se tiene que degradar el atributo hambre del pez
     * @return 4 puntos que se degrada este atributo
     */
    @Override
    protected int getDegradacionHambre() {
        return 4;
    }

    /**
     * Metodo cual retorna el valor que se tiene que degradar el atributo felicidad del pez
     * @return 2 puntos que se degrada este atributo
     */
    @Override
    protected int getDegradacionFelicidad() {
        return 2;
    }

    /**
     * Metodo cual retorna el valor que se tiene que degradar el atributo higiene del pez
     * @return 10 puntos que se degrada este atributo
     */
    @Override
    protected int getDegradacionHigiene() {
        return 10;
    }
}
