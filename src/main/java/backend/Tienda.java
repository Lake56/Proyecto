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

    public String VenderMascota(TipoMascota Interes,int Dinero) {
        for (int i = 0; i < Mascota.size(); i++){
            Mascota m= Mascota.get(i);
            if(m.getTipo()==Interes){
                if(m.getPrecio()<=Dinero){
                    Presupuesto+=m.getPrecio();
                    return "Venta Exitosa";
                }
                else{
                    return "Dinero insuficiente";
                }
            }
        }
        return "NO hya disponibilidad de esa mascota";
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
