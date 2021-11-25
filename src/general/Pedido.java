package general;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase madre Pedido
 */
public class Pedido implements Serializable {
    protected String nombre;
    protected int numeroPedido;
    protected Date fecha;
    protected double costo;
    protected double costoTotal;
    protected double costoAdicional;
    protected double totalCalorias;
    protected ArrayList<Platillo> platillosPedidos;
    protected  ArrayList<Integer> cantidadPlatillosPedidos = new ArrayList<>();
    protected static int contadorID=1;


    public Pedido() {}

    public Pedido(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCantidadPlatillosPedidos(ArrayList<Integer> lista){
        cantidadPlatillosPedidos=lista;
    }

    public  ArrayList<Integer> getCantidadPlatillosPedidos(){
        return cantidadPlatillosPedidos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido() {
        this.numeroPedido = contadorID;
        contadorID++;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha() {
        this.fecha  = Calendar.getInstance().getTime();
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public ArrayList<Platillo> getPlatillosPedidos() {
        return platillosPedidos;
    }

    public void setPlatillosPedidos(ArrayList<Platillo> platillosPedidos) {
        this.platillosPedidos = platillosPedidos;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public double getCostoAdicional() {
        return costoAdicional;
    }

    public void setCostoAdicional(double costoAdicional) {
        this.costoAdicional = costoAdicional;
    }

    public double getTotalCalorias() {
        return totalCalorias;
    }

    public void setTotalCalorias(double totalCalorias) {
        this.totalCalorias = totalCalorias;
    }

    public void resetearArrays() {
        platillosPedidos.clear();
        cantidadPlatillosPedidos.clear();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "nombre='" + nombre + '\'' +
                ", numeroPedido=" + numeroPedido +
                ", fecha=" + fecha +
                ", costo=" + costo +
                ", costoTotal=" + costoTotal +
                ", costoAdicional=" + costoAdicional +
                ", totalCalorias=" + totalCalorias +
                ", platillosPedidos=" + platillosPedidos +
                ", cantidadPlatillosPedidos=" + cantidadPlatillosPedidos +
                '}';
    }
}
