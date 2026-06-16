package backend;

public class Pez extends Mascota{
    public Pez(String nombre, float precio) {
        super(nombre, precio);
    }

    @Override
    public void alimentar() {
        setHambre(getHambre() + 15);

        notificarObservers();
    }

    @Override
    public void limpiar() {
        setHigiene(getHigiene() + 50);
        setSalud(getSalud() + 10);

        notificarObservers();
    }

    @Override
    public void jugar() {
        setFelicidad(getFelicidad()  + 10);
        setHambre(getHambre() - 5);

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
