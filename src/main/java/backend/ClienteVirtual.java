package backend;

import java.util.ArrayList;
import java.util.Random;

public class ClienteVirtual{
    Random random = new Random();

    private ArrayList<String> Mascotas;
    private ArrayList<String> Nombres;
    private int Dinero;
    private TipoMascota Interes;
    private Mascota mascota;
    private int eleccion;

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

    public int getEleccion(){
        return eleccion;
    }

    public String comprarMascotaTienda(Tienda tienda){
        return (tienda.VenderMascota(Interes, Dinero));
    }

    public void VenderMascotaTienda(Tienda tienda){
        tienda.ComprarMascota(mascota);
    }

    public TipoMascota getInteres() {
        return Interes;
    }

    public int getDinero() {
        return Dinero;
    }
}
