package backend;

import java.util.ArrayList;
import java.util.Random;
/*
    Prototipo Cliente virtual
 */
public class ClienteVirtual{
    Random random= new Random(1);
    Random random2= new Random(3);
    Random random3= new Random(6);

    private ArrayList<String> Mascotas;
    private ArrayList<String> Nombres;
    private int Dinero;
    private TipoMascota Interes;
    private Mascota mascota;

    public ClienteVirtual(int Dinero){

        this.Dinero=Dinero;

        Mascotas.add("perro");
        Mascotas.add("Gato");
        Mascotas.add("pez");
        Mascotas.add("pajaro");

        Nombres.add("Pinky");
        Nombres.add("Atenea");
        Nombres.add("Nieve");
        Nombres.add("Doky");
        Nombres.add("Calu");
        Nombres.add("Ricky");

        int rand1= random.nextInt();
        int rand2= random2.nextInt();
        int rand3= random3.nextInt();

        //aun en testeo
        if(rand1==0){
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
                Interes= TipoMascota.PAJARO;
            }
        }
        else{
            // Precio aun por definir
            String Nombre= Nombres.get(rand3);
            if(rand2==0){
                mascota= new Perro(Nombre,"salchicha",10000);
            }
            else if (rand2==1) {
                mascota= new Gato(Nombre,10000);
            }
            else if(rand2==2){
                mascota= new Pajaro(Nombre,10000);
            }
            else{
                mascota= new Pez(Nombre,10000);
            }
        }
    }

    //Prototipo de metodos, falta corregir y mejorar
    public String comprarMascotaTienda(Tienda tienda){
        return (tienda.VenderMascota(Interes, Dinero));
    }

    public void VenderMascotaTienda(Tienda tienda){
        tienda.ComprarMascota(mascota);

    }
}
