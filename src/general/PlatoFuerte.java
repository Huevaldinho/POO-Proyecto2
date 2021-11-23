package general;

import static AppServidora.negocio.AdmProductos.CONTADOR_BEB;
import static AppServidora.negocio.AdmProductos.CONTADOR_PRN;

public class PlatoFuerte extends Platillo {
    public PlatoFuerte(String nombrePlatillo, String rutaImagen, Double precio, String descripcion, String tamannoPorcion,
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

    @Override
    public void setId() {
        String digitos = String.valueOf(CONTADOR_PRN);
        if (digitos.length() == 1)
            digitos = "00" + digitos;
        else if (digitos.length() == 2)
            digitos = "0" + digitos;
        this.id = "PRN-" + digitos;
        CONTADOR_PRN++;
    }
}
