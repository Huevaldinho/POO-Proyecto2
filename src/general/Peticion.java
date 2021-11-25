package general;

import java.io.Serializable;


public class Peticion implements Serializable{
    
    private TipoAccion accion;
    private Object datosEntrada;
    private Object datosSalida;

    public Peticion() {
    }

    
    public Peticion(TipoAccion accion, Object datosEntrada) {
        this.accion = accion;
        this.datosEntrada = datosEntrada;
    }

    public TipoAccion getAccion() {
        return accion;
    }

    public void setAccion(TipoAccion accion) {
        this.accion = accion;
    }

    public Object getDatosEntrada() {
        return datosEntrada;
    }

    public void setDatosEntrada(Object datosEntrada) {
        this.datosEntrada = datosEntrada;
    }

    public Object getDatosSalida() {
        return datosSalida;
    }

    public void setDatosSalida(Object datosSalida) {
        this.datosSalida = datosSalida;
    }

    @Override
    public String toString() {
        return "Peticion{" + "accion=" + accion + ", datosEntrada=" + datosEntrada + ", datosSalida=" + datosSalida + '}';
    }
    
    
}
