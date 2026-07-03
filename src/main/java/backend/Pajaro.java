package backend;

public class Pajaro extends Mascota {

    /**
     * metodo que establece el nombre y precio del pajaro
     * @param nombre del pajaro
     * @param precio del pajaro
     */
    public Pajaro(String nombre, float precio) {
        super(nombre, precio,TipoMascota.PAJARO);
    }

    /**
     * Metodo que actualiza los atributos hambre y felicidad del pajaro
     */
    @Override
    public void alimentar() {
        setHambre(getHambre() + 25);
        setFelicidad(getFelicidad() + 3);

        notificarObservers();
    }

    /**
     * Metodo que actualiza los atributos higiene y felicidad del pajaro
     */
    @Override
    public void limpiar() {
        setHigiene(getHigiene() + 35);
        setFelicidad(getFelicidad() - 3);

        notificarObservers();
    }

    /**
     * Metodo que actualiza los atributos felicidad, hambre y higiene del pajaro
     */
    @Override
    public void jugar() {
        setFelicidad(getFelicidad()  + 30);
        setHambre(getHambre() - 6);
        setHigiene(getHigiene() - 5);

        notificarObservers();
    }

    /**
     * Metodo cual retorna el valor que se tiene que degradar el atributo hambre del pajaro
     * @return 4 puntos que se degrada este atributo
     */
    @Override
    protected int getDegradacionHambre() {
        return 4;
    }

    /**
     * Metodo cual retorna el valor que se tiene que degradar el atributo felicidad del pajaro
     * @return 6 puntos que se degrada este atributo
     */
    @Override
    protected int getDegradacionFelicidad() {
        return 6;
    }

    /**
     * Metodo cual retorna el valor que se tiene que degradar el atributo higiene del pajaro
     * @return 8 puntos que se degrada este atributo
     */
    @Override
    protected int getDegradacionHigiene() {
        return 7;
    }
}
