package general;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.Serializable;
import java.util.Locale;

/**
 * Modelo de un platillo
 */
public abstract class Platillo implements Serializable {
    protected String nombrePlatillo;
    protected String rutaImagen;
    protected String id;
    protected Double precio;
    protected String descripcion;
    protected String tamannoPorcion;
    protected int piezasPorcion;
    protected int caloriasPorcion;
    protected int caloriasPieza;
    protected int cantidadDeVecesSolicitado = 0;

    public Platillo(){}

    public Platillo(Platillo plati) {
        this.nombrePlatillo = plati.getNombrePlatillo();
        this.rutaImagen = plati.getRutaImagen();
        this.id = plati.getId();
        this.precio = plati.getPrecio();
        this.descripcion = plati.getDescripcion();
        this.tamannoPorcion = plati.getTamanoPorcion();
        this.piezasPorcion = plati.getPiezasPorcion();
        this.caloriasPorcion = plati.getCaloriasPorcion();
        this.caloriasPieza = plati.getCaloriarPieza();
    }

    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getId() {
        return id;
    }

    public abstract void setId();

    public void setIdModificado(String modi){
        this.id=modi;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTamanoPorcion() {
        return tamannoPorcion;
    }

    public void setTamanoPorcion(String tamanoPorcion) {
        this.tamannoPorcion = tamanoPorcion;
    }

    public int getPiezasPorcion() {
        return piezasPorcion;
    }

    public void setPiezasPorcion(int piezasPorcion) {
        this.piezasPorcion = piezasPorcion;
    }

    public int getCaloriasPorcion() {
        return caloriasPorcion;
    }

    public void setCaloriasPorcion(int caloriasPorcion) {
        this.caloriasPorcion = caloriasPorcion;
    }

    public int getCaloriarPieza() {
        return caloriasPieza;
    }

    public void setCaloriarPieza(int caloriarPieza) {
        this.caloriasPieza = caloriarPieza;
    }

    public int getCantidadDeVecesSolicitado(){
        return cantidadDeVecesSolicitado;
    }

    public void setCantidadDeVecesSolicitado(int cantidad){
        cantidadDeVecesSolicitado=cantidad;
    }

    public void aumentarPlatillo(int cantidad){
        cantidadDeVecesSolicitado += cantidad;
    }



    @Override
    public String toString() {
        return "Platillo{" +
                "nombrePlatillo='" + nombrePlatillo + '\'' +
                ", rutaImagen='" + rutaImagen + '\'' +
                ", id='" + id + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", tamannoPorcion='" + tamannoPorcion + '\'' +
                ", piezasPorcion=" + piezasPorcion +
                ", caloriasPorcion=" + caloriasPorcion +
                ", caloriasPieza=" + caloriasPieza + '\'' +
                " cantidad de veces pedido="+cantidadDeVecesSolicitado+
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platillo platillo = (Platillo) o;
        System.out.println("Platillo casteado"+platillo.toString());
        String nombre1 = getNombrePlatillo().toLowerCase();
        String nombre2 = platillo.getNombrePlatillo().toLowerCase();
        try {
            boolean n1 = nombre1.equals(nombre2);
        }catch (Exception e){
            System.out.println("ERROR EN EQUALS");
            e.printStackTrace();
            return false;
        }
        return nombre1.equals(nombre2);
    }
}
