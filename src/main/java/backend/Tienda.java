package backend;

import java.util.ArrayList;

/**prototipo del codigo
 **aun no funcional
 */
public class Tienda{

    static private ArrayList<Mascota> Mascota;
    private ArrayList<Suministro> Inventario;
    //cambiar dinero por presupuesto
    private int Presupuesto;

    public Tienda(int dinero){
        this.Presupuesto =dinero;
        Inventario= new ArrayList<>();
        Mascota=new ArrayList<>();
    }

    public String ComprarSuministros(int n){
        if(n==1){
            //se evaluara tener distintos tipos de comida
            Suministro com= new Comida(5000);
            if (Presupuesto < com.getPrecio()) {
                return "Dinero insuficiente. Necesitas $" + com.getPrecio() + " y tienes $" + Presupuesto;
            }
            Presupuesto -= com.getPrecio();
            Inventario.add(com);
            return"compra exitosa";
        }
        else if (n==2){
            Suministro med= new Medicamento(15000);
            if (Presupuesto < med.getPrecio()) {
                return "Dinero insuficiente. Necesitas $" + med.getPrecio() + " y tienes $" + Presupuesto;
            }
            Inventario.add(med);
            return"compra exitosa";
        }
        else{
            return"producto invalido";
        }
    }

    public String VenderMascota(Mascota m,int Dinero){
        if(m.getPrecio()<=Dinero){
            Mascota.remove(m);
            Presupuesto += m.getPrecio();
            return "venta exitosa";
        }
        else{
            return "dinero insuficiente";
        }
    }

    public String ComprarMascota(Mascota m){
        if(Presupuesto>m.getPrecio()) {
            Mascota.add(m);
            Presupuesto -= m.getPrecio();
            return "compra exitosa";
        }
        else{
            return "dinero insuficiente";
        }
    }
    public ArrayList<Mascota> getMascotas() {
        return Mascota;
    }

    public int getPresupuesto(){
        return Presupuesto;
    }
}
