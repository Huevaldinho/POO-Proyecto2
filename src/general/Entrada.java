package general;

import static AppServidora.negocio.AdmProductos.CONTADOR_BEB;
import static AppServidora.negocio.AdmProductos.CONTADOR_ENT;

public class Entrada extends Platillo{
    public Entrada(String nombrePlatillo, String rutaImapathgen, Double precio, String descripcion, String tamannoPorcion,
                   int piezasPorcion, int caloriasPorcion, int caloriasPieza) {
        super.nombrePlatillo = nombrePlatillo;
        super.rutaImagen = rutaImagen;
        super.precio = precio;
        super.descripcion = descripcion;
        super.tamannoPorcion = tamannoPorcion;
        super.piezasPorcion = piezasPorcion;
        super.caloriasPorcion = caloriasPorcion;
        super.caloriasPieza = caloriasPieza;
    }

    public void setId() {
        String digitos = String.valueOf(CONTADOR_ENT);
        if (digitos.length() == 1)
            digitos = "00" + digitos;
        else
            digitos = "0" + digitos;
        super.id = "ENT-" + digitos;
        CONTADOR_ENT++;
    }
    public void setIDModificar(String id){
        super.id=id;
    }
    public void setNombreModificar(String nombre){
        super.nombrePlatillo=nombre;
    }
    public void setDescripcionModificar(String descrip){
        super.descripcion=descrip;
    }
    public void setRutaModificar(String modi){
        super.rutaImagen=modi;
    }
    public void setPrecioModificar(String precio){
        super.precio=Double.parseDouble(precio.substring(1));
    }
    public void setTamannoPorcioModificar (String tamano){
        super.tamannoPorcion=tamano;
    }
    public void  setPiezaxPorcion(int p){
        super.setPiezasPorcion(p);
    }
    public void setCaloriasXPorcion(int p){
        super.caloriasPorcion=p;
    }
    public void setCaloriasXPieza(int p){
        super.caloriasPieza=p;
    }

}
