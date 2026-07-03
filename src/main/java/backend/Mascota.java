package backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Mascota que sirve como modelo para los tipos de mascota que se utilizan en la tienda
 */
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

    /**
     * Constructor de la clase mascota
     * se inicializan las variables de la clase
     * @param nombre nombre de la mascota
     * @param precio precio de la mascota
     * @param tipo tipo de mascota
     */
    public Mascota(String nombre, float precio,TipoMascota tipo) {
        this.nombre = nombre;
        this.hambre = 100;
        this.felicidad = 100;
        this.higiene = 100;
        this.salud = 100;
        this.precio = precio;
        this.estado = new EstadoSaludable();
        this.tipo = tipo;
    }

    /**
     * metodos abstractos que actualizan los atributos de la mascota
     * estos metodos se deben definir en cada clase respectiva de la mascota
     */
    public abstract void alimentar();
    public abstract void limpiar();
    public abstract void jugar();

    /**
     * metodos abstractos que retornan la cantidad de degradacion de su respectivo atribut
     * @return la degradacion de su respectivo atributo
     */
    protected abstract int getDegradacionHambre();
    protected abstract int getDegradacionFelicidad();
    protected abstract int getDegradacionHigiene();

    /**
     * Metodo que provoca que se degraden los atributos de cada mascota
     */
    public void pasarTiempo() {
        this.hambre = Math.max(0, hambre - getDegradacionHambre());
        this.felicidad = Math.max(0, felicidad - getDegradacionFelicidad());
        this.higiene = Math.max(0, higiene - getDegradacionHigiene());

        estado.manejarEstado(this);

        actualizarEstado();

        notificarObservers();
    }

    /**
     * metodo cual actualiza el atributo de salud y felicidad de la mascota
     * @return booleano dependiendo si la mascota esta en un estado que necesite atender su salud
     */
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

    /**
     * Metodo que actualiza el estado de la mascota dependiendo del valor de sus atributos (salud, hambre, felicidad(
     */
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

    /**
     * metodo que agregar un observer a la lista
     * @param observer el observer a agregar a la lista
     */
    public void agregarObserver(MascotaObserver observer) {
        observers.add(observer);
    }

    /**
     *  metodo que elimina un observer especifico de la lista
     * @param observer el observer a eliminar de la lista
     */
    public void eliminarObserver(MascotaObserver observer) {
        observers.remove(observer);
    }

    /**
     * metodo que actualiza los observers
     */
    protected void notificarObservers() {
        for(MascotaObserver observer : observers) {
            observer.actualizar(this);
        }
    }

    /**
     * metodo que retorna el nombre de la mascota
     * @return nombre de la mascota
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * metodo que retorna el valor del atributo hambre de la mascota
     * @return hambre de la mascota
     */
    public int getHambre() {
        return this.hambre;
    }

    /**
     * metodo que retorna el valor del atributo felicidad de la mascota
     * @return felicidad de la mascota
     */
    public int getFelicidad() {
        return this.felicidad;
    }

    /**
     * metodo que retorna el valor del atributo higiene de la mascota
     * @return higiene de la mascota
     */
    public int getHigiene() {
        return this.higiene;
    }

    /**
     * metodo que retorna el valor del atributo salud de la mascota
     * @return salud de la mascota
     */
    public int getSalud() {
        return this.salud;
    }

    /**
     * metodo que retorna el valor el precio de la mascota
     * @return precio de la mascota
     */
    public float getPrecio() {
        return this.precio;
    }

    /**
     * metodo que retorna el estado en que se encuentra la mascota
     * @return estado de la mascota
     */
    public EstadoMascota getEstado() {
        return this.estado;
    }

    /**
     * metodo que establece un valor especifico al atributo hambre de la mascota
     * @param hambre que se establece en la mascota
     */
    public void setHambre(int hambre) {
        this.hambre = Math.max(0, Math.min(100, hambre));
    }

    /**
     * metodo que establece un valor especifico al atributo felicidad de la mascota
     * @param felicidad que se establece en la mascota
     */
    public void setFelicidad(int felicidad) {
        this.felicidad = Math.max(0, Math.min(100, felicidad));
    }

    /**
     * metodo que establece un valor especifico al atributo higiene de la mascota
     * @param higiene que se establece en la mascota
     */
    public void setHigiene(int higiene) {
        this.higiene = Math.max(0, Math.min(100, higiene));
    }

    /**
     * metodo que establece un valor especifico al atributo salud de la mascota
     * @param salud que se establece en la mascota
     */
    public void setSalud(int salud) {
        this.salud = Math.max(0, Math.min(100, salud));
    }

    /**
     * metodo que retorna el tipo de mascota
     * @return tipo de la mascota
     */
    public TipoMascota getTipo(){
        return tipo;
    }
}
