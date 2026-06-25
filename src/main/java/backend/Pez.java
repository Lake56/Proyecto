package backend;

public class Pez extends Mascota{
    public Pez(String nombre, float precio) {
        super(nombre, precio);
    }

    @Override
    public void alimentar() {
        setHambre(getHambre() + 15);
        setFelicidad(getFelicidad() + 7);

        notificarObservers();
    }

    @Override
    public void limpiar() {
        setHigiene(getHigiene() + 50);
        setFelicidad(getFelicidad() - 4);

        notificarObservers();
    }

    @Override
    public void jugar() {
        setFelicidad(getFelicidad()  + 10);
        setHambre(getHambre() - 5);
        setHigiene(getHigiene() - 3);

        notificarObservers();
    }

    @Override
    protected int getDegradacionHambre() {
        return 4;
    }

    @Override
    protected int getDegradacionFelicidad() {
        return 2;
    }

    @Override
    protected int getDegradacionHigiene() {
        return 10;
    }
}
