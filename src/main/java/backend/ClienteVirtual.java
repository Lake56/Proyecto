package backend;

import java.util.ArrayList;
import java.util.Random;

/**
 * Cliente virtual que interactuara con la tienda
 * Obtiene un dinero inicial entre 5000 y 10000
 * y generea aleatoriamente si este comprara o vendera a la tienda
 */
public class ClienteVirtual{
    Random random = new Random();

    private ArrayList<String> Mascotas;
    private ArrayList<String> Nombres;
    private int Dinero;
    private TipoMascota Interes;
    private Mascota mascota;
    private int eleccion;

    /**
     * Se un nuevo cliente virtual
     * que comprara o vendera una mascota a la tienda
     */
    public ClienteVirtual(){
        Mascotas = new ArrayList<>();
        Nombres = new ArrayList<>();
        this.Dinero = random.nextInt(5001) + 5000; // entre 5000 y 10000

        Mascotas.add("perro");
        Mascotas.add("gato");
        Mascotas.add("pez");
        Mascotas.add("pajaro");

        Nombres.add("Pinky");
        Nombres.add("Atenea");
        Nombres.add("Nieve");
        Nombres.add("Doky");
        Nombres.add("Calu");
        Nombres.add("Ricky");

        int rand1 = random.nextInt(2);
        int rand2 = random.nextInt(4);
        int rand3 = random.nextInt(Nombres.size());

        if(rand1==0){
            eleccion=1;
            if(rand2==0){
                Interes= TipoMascota.PERRO;
            }
            else if (rand2==1) {
                Interes= TipoMascota.GATO;
            }
            else if(rand2==2){
                Interes= TipoMascota.PAJARO;
            }
            else{
                Interes= TipoMascota.PEZ;
            }
        }

        else {
            eleccion = 2;
            String Nombre = Nombres.get(rand3);
            if (rand2 == 0) {
                mascota = new Perro(Nombre, "salchicha", random.nextInt(10000 - 5000 + 1) + 5000);
            } else if (rand2 == 1) {
                mascota = new Gato(Nombre, random.nextInt(10000 - 4000 + 1) + 4000);
            } else if (rand2 == 2) {
                mascota = new Pajaro(Nombre, random.nextInt(10000 - 4000 + 1) + 4000);
            } else {
                mascota = new Pez(Nombre, random.nextInt(10000 - 4000 + 1) + 4000);
            }
        }
    }

    /**
     * Devuelve la eleccion del cliente
     * si este comprara o vendera una mascota
     * @return eleccion del cliente
     */
    public int getEleccion(){
        return eleccion;
    }

    /**
     * El cliente comprara una mascota a la tienda
     * @param tienda con la que este interactua
     * @return texto resultante si la compra fue exitosa o no
     */
    public String comprarMascotaTienda(Tienda tienda){
        return (tienda.VenderMascota(Interes, Dinero));
    }

    /**
     * Se vende una mascota a la tiena
     * @param tienda con la que interactua el cliente
     */
    public void VenderMascotaTienda(Tienda tienda){
        tienda.ComprarMascota(mascota);
    }

    /**
     * Obtiene el tipo de mascota que le interesa al cliente virtual
     * @return Interes del cliente
     */
    public TipoMascota getInteres() {
        return Interes;
    }

    /**
     * Obtiene el dinero que posee el cliente virtual
     * * @return dinero del cliente
     */
    public int getDinero() {
        return Dinero;
    }
}
