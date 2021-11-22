package general;

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
        String digitos = String.valueOf(contadorId);
        if (digitos.length() == 1)
            digitos = "00" + digitos;
        else if (digitos.length() == 2)
            digitos = "0" + digitos;
        this.id = "PRN-" + digitos;
        contadorId++;;
    }
}
