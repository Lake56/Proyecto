package backend;

public class Perro extends Mascota {
    private String raza;

    public Perro(String nombre, String raza, float precio) {
        super(nombre, precio,TipoMascota.PERRO);
        this.raza = raza;
    }

    @Override
    public void alimentar() {
        setHambre(getHambre() + 35);
        setFelicidad(getFelicidad() + 5);

        notificarObservers();
    }

    @Override
    public void limpiar() {
        setHigiene(getHigiene() + 40);
        setFelicidad(getFelicidad() - 7);

        notificarObservers();
    }

    @Override
    public void jugar() {
        setFelicidad(getFelicidad() + 35);
        setHambre(getHambre() - 15);
        setHigiene(getHigiene() - 5);

        notificarObservers();
    }

    @Override
    protected int getDegradacionHambre() {
        return 8;
    }

    @Override
    protected int getDegradacionFelicidad() {
        return 5;
    }

    @Override
    protected int getDegradacionHigiene() {
        return 6;
    }

    public String getRaza() {
        return this.raza;
    }
}
