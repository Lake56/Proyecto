package backend;

public class Pajaro extends Mascota {
    public Pajaro(String nombre, float precio) {
        super(nombre, precio);
    }

    @Override
    public void alimentar() {
        setHambre(getHambre() + 25);
        setFelicidad(getFelicidad() + 3);

        notificarObservers();
    }

    @Override
    public void limpiar() {
        setHigiene(getHigiene() + 35);
        setFelicidad(getFelicidad() - 3);

        notificarObservers();
    }

    @Override
    public void jugar() {
        setFelicidad(getFelicidad()  + 30);
        setHambre(getHambre() - 6);

        notificarObservers();
    }


    @Override
    protected int getDegradacionHambre() {
        return 4;
    }

    @Override
    protected int getDegradacionFelicidad() {
        return 6;
    }

    @Override
    protected int getDegradacionHigiene() {
        return 7;
    }
}
