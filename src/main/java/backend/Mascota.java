package backend;

import java.util.ArrayList;
import java.util.List;

public abstract class Mascota {
    private String nombre;
    private int hambre;
    private int felicidad;
    private int higiene;
    private int salud;
    private float precio;
    private EstadoMascota estado;
    private TipoMascota tipo;

    private List<MascotaObserver> observers = new ArrayList<>();

    public Mascota(String nombre, float precio,TipoMascota tipo) {
        this.nombre = nombre;
        this.hambre = 100;
        this.felicidad = 100;
        this.higiene = 100;
        this.salud = 100;
        this.precio = precio;
        this.estado = new EstadoSaludable();
        this.tipo=tipo;
    }


    public abstract void alimentar();
    public abstract void limpiar();
    public abstract void jugar();


    protected abstract int getDegradacionHambre();
    protected abstract int getDegradacionFelicidad();
    protected abstract int getDegradacionHigiene();

    public void pasarTiempo() {
        this.hambre = Math.max(0, hambre - getDegradacionHambre());
        this.felicidad = Math.max(0, felicidad - getDegradacionFelicidad());
        this.higiene = Math.max(0, higiene - getDegradacionHigiene());

        estado.manejarEstado(this);

        actualizarEstado();

        notificarObservers();
    }

    public boolean atenderSalud() {
        if(estado instanceof EstadoEnfermo || estado instanceof EstadoCritico) {
            setSalud(getSalud() + 40);
            setFelicidad(getFelicidad() + 10);

            actualizarEstado();

            notificarObservers();

            return true;
        }

        return false;
    }

    private void actualizarEstado() {
        if(salud < 25 || hambre < 15) {
            this.estado = new EstadoCritico();
        }
        else if (salud < 60) {
            this.estado = new EstadoEnfermo();
        }
        else if(felicidad < 40) {
            this.estado = new EstadoTriste();
        }
        else {
            this.estado = new EstadoSaludable();
        }
    }

    public void agregarObserver(MascotaObserver observer) {
        observers.add(observer);
    }

    public void eliminarObserver(MascotaObserver observer) {
        observers.remove(observer);
    }

    protected void notificarObservers() {
        for(MascotaObserver observer : observers) {
            observer.actualizar(this);
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getHambre() {
        return this.hambre;
    }

    public int getFelicidad() {
        return this.felicidad;
    }

    public int getHigiene() {
        return this.higiene;
    }

    public int getSalud() {
        return this.salud;
    }

    public float getPrecio() {
        return this.precio;
    }

    public EstadoMascota getEstado() {
        return this.estado;
    }

    public void setHambre(int hambre) {
        this.hambre = Math.max(0, Math.min(100, hambre));
    }

    public void setFelicidad(int felicidad) {
        this.felicidad = Math.max(0, Math.min(100, felicidad));
    }

    public void setHigiene(int higiene) {
        this.higiene = Math.max(0, Math.min(100, higiene));
    }

    public void setSalud(int salud) {
        this.salud = Math.max(0, Math.min(100, salud));
    }

    public TipoMascota getTipo(){
        return tipo;
    }
}
