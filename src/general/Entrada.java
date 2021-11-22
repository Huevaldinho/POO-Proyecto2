package general;

public class Entrada extends Platillo {
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
        System.out.println("set id");
        String digitos = String.valueOf(contadorId);
        if (digitos.length() == 1)
            digitos = "00" + digitos;
        else
            digitos = "0" + digitos;
        super.id = "ENT-" + digitos;
        contadorId++;
        System.out.println(super.id);
    }
}
