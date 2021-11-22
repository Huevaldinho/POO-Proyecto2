package general;

import java.io.Serializable;

public abstract class Platillo implements Serializable {
    protected String nombrePlatillo;
    protected String rutaImagen;
    protected static int contadorId = 0;
    protected String id;
    protected Double precio;
    protected String descripcion;
    protected String tamannoPorcion;
    protected int piezasPorcion;
    protected int caloriasPorcion;
    protected int caloriasPieza;

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

    public void setId(String id) {
        this.id = this.id + id;
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
                ", caloriasPieza=" + caloriasPieza +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platillo platillo = (Platillo) o;
        return getId().equals(platillo.getId());
    }
}
