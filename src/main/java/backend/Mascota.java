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

    private List<MascotaObserver> observers = new ArrayList<>();

    public Mascota(String nombre, float precio) {
        this.nombre = nombre;
        this.hambre = 100;
        this.felicidad = 100;
        this.higiene = 100;
        this.salud = 100;
        this.precio = precio;
        this.estado = new EstadoSaludable();
    }


    public abstract void alimentar();
    public abstract void limpiar();
    public abstract void jugar();


    protected abstract int getDegradacionHambre();
    protected abstract int getDegradacionFelicidad();
    protected abstract int getDegradacionHigiene();

    public void pasarTiempo() {
        this.hambre = Math.max(0, hambre - getDegradacionHambre());
        this.felicidad = Math.max(0, hambre - getDegradacionFelicidad());
        this.higiene = Math.max(0, hambre - getDegradacionHigiene());

        estado.manejarEstado(this);

        actualizarEstado();

        notificarObservers();
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

    public void agergarObserver(MascotaObserver observer) {
        observers.add(observer);
    }

    public void eliminarObserver(MascotaObserver observer) {
        observers.remove(observer);
    }

    private void notificarObservers() {
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
        this.hambre = Math.min(100, hambre);
    }

    public void setFelicidad(int felicidad) {
        this.felicidad = Math.min(100, felicidad);
    }

    public void setHigiene(int higiene) {
        this.higiene = Math.min(100, higiene);
    }

    public void setSalud(int salud) {
        this.salud = Math.min(100, salud);
    }
}
