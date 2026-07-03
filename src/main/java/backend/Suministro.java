package backend;

public class Suministro{
    private int Precio;

    /**
     * metodo que inicializa el precio del suministro
     * @param Precio del suministro
     */
    public Suministro(int Precio){
        this.Precio = Precio;
    }

    /**
     * metodo que retorna el valor del precio del suministro
     * @return precio del suministro
     */
    public int getPrecio(){
        return Precio;
    }
}
