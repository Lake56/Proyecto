package backend;

import java.util.ArrayList;

/**prototipo del codigo
 **aun no funcional
 */
public class Tienda{

    private ArrayList<Mascota> Mascota;
    private ArrayList<Suministro> Inventario;
    private int Dinero;

    public Tienda(int dinero){
        this.Dinero=dinero;
        Inventario= new ArrayList<>();
        Mascota=new ArrayList<>();
    }

    public String ComprarSuministros(int n){
        if(n==1){
            //se evaluara tener distintos tipos de comida
            Suministro Com= new Comida(5000);
            Dinero-=Com.getPrecio();
            return"compra exitosa";
        }
        else if (n==2){
            Suministro Med= new Medicamento(15000);
            Dinero-=Med.getPrecio();
            Inventario.add(Med);
            return"compra exitosa";
        }
        else{
            return"producto invalido";
        }
    }

    public String VenderMascota(Mascota m){
        Mascota.remove(m);
        Dinero-=m.getPrecio();
        return "venta exitosa";
    }

    public String ComprarMascota(Mascota m){
        Mascota.add(m);
        Dinero+=m.getPrecio();
        return "compra exitosa";
    }

}
