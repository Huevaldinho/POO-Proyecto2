package general;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Pedido implements Serializable {
    protected String nombre;
    protected int numeroPedido;
    protected Date fecha;
    protected double costo;
    protected ArrayList<Platillo> platillosPedidos;
    protected  ArrayList<Integer> cantidadPlatillosPedidos = new ArrayList<>();


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

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
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

    @Override
    public String toString() {
        return "Pedido{" +
                "nombre='" + nombre + '\'' +
                ", numeroPedido=" + numeroPedido +
                ", fecha=" + fecha +
                ", costo=" + costo +
                ", platillosPedidos=" + platillosPedidos +
                '}';
    }
}
