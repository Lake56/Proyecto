package backend;

import java.util.ArrayList;

/**
 * Logica detras de la tienda de mascotas
 * y la gestion de esta
 */
public class Tienda{

    private int Presupuesto;
    private ArrayList<Mascota> Mascota;
    private ArrayList<Suministro> Inventario;

    /**
     * Se crea una nueva tienda con un presupuesto inicial
     * @param dinero presuupuesto inicial de la tienda
     */
    public Tienda(int dinero){

        this.Presupuesto =dinero;

        Inventario= new ArrayList<>();
        Mascota=new ArrayList<>();
    }

    /**
     * Compra suministros para la tienda
     * con eleccion 1 de comida y 2 de medicamento
     * @param n el suministro a escoger
     * @return mensaje con compra exitosa, si no posee
     * suficiente dinero o el producto no existe
     */
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

    /**
     * Se vende una mascota al Cliente Virtual con su preferencia
     * @param Interes de el cliente virual
     * @param Dinero de el cliente virtual para adquirir la mascota
     * @return texto con venta exitosa, si no tiene suficiente o si no hay disponibilidad de la mascota
     */
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

    /**
     * Comprar una mascota a un cliente virtual
     * @param mascota que posee el cliente
     * @return texto con compra exitosa o si no posee suficiente dinero la tienda
     */
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

    /**
     * Agrega una mascota a la lista de la tienda
     * @param mascota que se agregara
     */
    public void AgregarMascota(Mascota mascota){
        Mascota.add(mascota);
    }

    /**
     * Quita una mascota de la lista de la tienda
     * @param mascota que se quitara de la lista
     */
    public void QuitarMascota(Mascota mascota){
        Mascota.remove(mascota);
    }

    /**
     * Obtiene la lista de mascotas de la tienda
     * @return lista de las mascotas
     */
    public ArrayList<Mascota> getMascotas() {
        return Mascota;
    }

    /**
     * Obtiene el inventario de suministros de la tienda
     * @return lista del inventario
     */
    public ArrayList<Suministro> getInventario(){
        return Inventario;
    }

    /**
     * Obtiene el presupuesto que tiene la tienda
     * @return presupuesto de la tienda
     */
    public int getPresupuesto(){
        return Presupuesto;
    }

    /**
     * Cuenta la cantidad de comida que posee la tienda
     * @return canditad de comida
     */
    public int getCantidadComida() {
        int cantidad=0;
        for (Suministro s : Inventario) {
            if (s instanceof Comida) {
                cantidad++;
            }
        }
        return cantidad;
    }

    /**
     * Cuenta la cantidad de medicamento que posee la tienda
     * @return cantidad de medicamentos
     */
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
