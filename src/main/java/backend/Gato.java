package backend;

public class Gato extends Mascota {
    public Gato(String nombre, float precio) {
        super(nombre, precio);
    }

    @Override
    public void alimentar() {
        setHambre(getHambre() + 20);
        setFelicidad(getFelicidad() + 5);

        notificarObservers();
    }

    @Override
    public void limpiar() {
        setHigiene(getHigiene() + 20);
        setFelicidad(getFelicidad() - 15);

        notificarObservers();
    }

    @Override
    public void jugar() {
        setFelicidad(getFelicidad() + 20);
        setHambre(getHambre() - 5);
        setHigiene(getHigiene() - 10);

        notificarObservers();
    }

    @Override
    protected int getDegradacionHambre() {
        return 5;
    }

    @Override
    protected int getDegradacionFelicidad() {
        return 3;
    }

    @Override
    protected int getDegradacionHigiene() {
        return 2;
    }
}
