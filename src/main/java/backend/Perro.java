package backend;

public class Perro extends Mascota {
    private String raza;

    /**
     * metodo que establece el nombre, precio y raza del perro
     * @param nombre del perro
     * @param precio del perro
     * @param raza del perro
     */
    public Perro(String nombre, String raza, float precio) {
        super(nombre, precio,TipoMascota.PERRO);
        this.raza = raza;
    }

    /**
     * Metodo que actualiza los atributos hambre y felicidad del perro
     */
    @Override
    public void alimentar() {
        setHambre(getHambre() + 35);
        setFelicidad(getFelicidad() + 5);

        notificarObservers();
    }

    /**
     * Metodo que actualiza los atributos higiene y felicidad del perro
     */
    @Override
    public void limpiar() {
        setHigiene(getHigiene() + 40);
        setFelicidad(getFelicidad() - 7);

        notificarObservers();
    }

    /**
     * Metodo que actualiza los atributos felicidad, hambre y higiene del perro
     */
    @Override
    public void jugar() {
        setFelicidad(getFelicidad() + 35);
        setHambre(getHambre() - 15);
        setHigiene(getHigiene() - 5);

        notificarObservers();
    }

    /**
     * Metodo cual retorna el valor que se tiene que degradar el atributo hambre del perro
     * @return 8 puntos que se degrada este atributo
     */
    @Override
    protected int getDegradacionHambre() {
        return 8;
    }

    /**
     * Metodo cual retorna el valor que se tiene que degradar el atributo felicidad del perro
     * @return 5 puntos que se degrada este atributo
     */
    @Override
    protected int getDegradacionFelicidad() {
        return 5;
    }

    /**
     * Metodo cual retorna el valor que se tiene que degradar el atributo higiene del perro
     * @return 6 puntos que se degrada este atributo
     */
    @Override
    protected int getDegradacionHigiene() {
        return 6;
    }

    /**
     *  Metodo cual retorna la raza que pertenezca el perro
     * @return raza del perro
     */
    public String getRaza() {
        return this.raza;
    }
}
