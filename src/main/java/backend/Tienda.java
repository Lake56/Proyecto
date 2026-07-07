package backend;

import java.util.ArrayList;

public class Tienda{

    private int Presupuesto;
    private ArrayList<Mascota> Mascota;
    private ArrayList<Suministro> Inventario;

    public Tienda(int dinero){

        this.Presupuesto =dinero;

        Inventario= new ArrayList<>();
        Mascota=new ArrayList<>();
    }

    public String ComprarSuministros(int n){
        if(n==1){
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
            Presupuesto-= med.getPrecio();
            Inventario.add(med);
            return"Compra exitosa";
        }
        else{
            return"Producto invalido";
        }
    }

    public String VenderMascota(TipoMascota Interes,int Dinero) {
        for (int i = 0; i < Mascota.size(); i++){
            Mascota m= Mascota.get(i);
            if(m.getTipo()==Interes){
                if(m.getPrecio()<=Dinero){
                    Presupuesto+=m.getPrecio();
                    Mascota.remove(m);
                    return "Venta exitosa";
                }
                else{
                    return "Dinero insuficiente";
                }
            }
        }
        return "No hay disponibilidad de esa mascota";
    }

    public String ComprarMascota(Mascota mascota){
        if(Presupuesto> mascota.getPrecio()) {
            Mascota.add(mascota);
            Presupuesto -= mascota.getPrecio();
            return "Compra exitosa";
        }
        else{
            return "Dinero insuficiente";
        }
    }

    public void gastarComida() {
        for (int i = 0; i < Inventario.size(); i++) {
            if (Inventario.get(i) instanceof Comida) {
                Inventario.remove(i);

                return;
            }
        }
    }

    public void gastarMedicamento() {
        for (int i = 0; i < Inventario.size(); i++) {
            if (Inventario.get(i) instanceof Medicamento) {
                Inventario.remove(i);

                return;
            }
        }
    }

    public void AgregarMascota(Mascota mascota){
        Mascota.add(mascota);
    }

    public void QuitarMascota(Mascota mascota){
        Mascota.remove(mascota);
    }

    public ArrayList<Mascota> getMascotas() {
        return Mascota;
    }

    public ArrayList<Suministro> getInventario(){
        return Inventario;
    }

    public int getPresupuesto(){
        return Presupuesto;
    }

    public int getCantidadComida() {
        int cantidad=0;
        for (Suministro s : Inventario) {
            if (s instanceof Comida) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int getCantidadMedicamento() {
        int cantidad=0;
        for (Suministro s : Inventario) {
            if (s instanceof Medicamento) {
                cantidad++;
            }
        }
        return cantidad;
    }
}
