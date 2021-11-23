package AppServidora.negocio;
import general.*;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ersolano
 */
public class AdmProductos{
    private AdmArchivosBinarios admArchivos = new AdmArchivosBinarios();
    private ArrayList<Platillo> platillos = new ArrayList();
    public static int CONTADOR_BEB = 0;
    public static int CONTADOR_ENT = 0;
    public static int CONTADOR_PRN = 0;
    public static int CONTADOR_PTR = 0;

    AdmProductos (){}

    public  boolean buscarPlatillo(Peticion peti){
        //Abrir archivo binario y buscar a peti
        Platillo platillo = (Platillo) peti.getDatosEntrada();
        for (Platillo actual: platillos){
            if (platillo.equals(actual)){
                System.out.println("Repetido: "+actual.toString()+"\n"+platillo.toString());
                return true;
            }
        }
        System.out.println("Es un plato nuevo");
        return false;
    }

    public boolean insertarNuevoPlatillo(Peticion peti){//Revisar archivos binarios
        if (buscarPlatillo(peti)==false){//Como no existe lo mete al archivo
            ((Platillo) peti.getDatosEntrada()).setId();
            platillos.add((Platillo) peti.getDatosEntrada());//Agrega el platillo al arrayList
            admArchivos.insertarPlatillo(platillos);//Agrega al archivo binario
            System.out.println("Se agrego platillo nuevo");
            return true;
        }
        return false;
    }

    public boolean modificarPlatillo(){
        return  false;
    }

    public void cargarPlatillos() {
        platillos = admArchivos.cargarArchivosPlatillos();//Carga platillos de archivos binarios
    }

    public DefaultTableModel generarTablaPlatillos() {
        String[] encabezado = {"Código Del Platillo", "Nombre Del Platillo", "Descripcion", "Tamaño De La Porción",
                "Piezas Por Porción", "Calorías En 1 Porción", "Calorías Por Pieza", "Precio", "Imagen", "Cantidad A Pedir"};
        DefaultTableModel dtm = new DefaultTableModel(encabezado, platillos.size());

        for (int i = 0; i < dtm.getRowCount(); i++) {
            Platillo cte = platillos.get(i);
            dtm.setValueAt(cte.getId(), i, 0);
            dtm.setValueAt(cte.getNombrePlatillo(), i, 1);
            dtm.setValueAt(cte.getDescripcion(), i, 2);
            dtm.setValueAt(cte.getTamanoPorcion(), i, 3);
            dtm.setValueAt(cte.getPiezasPorcion(), i, 4);
            dtm.setValueAt(cte.getCaloriasPorcion() + " kcals", i, 5);
            dtm.setValueAt(cte.getCaloriarPieza() + " kcals", i, 6);
            dtm.setValueAt("₡" + cte.getPrecio(), i, 7);
            dtm.setValueAt(0, i, 9);
        }
        return dtm;
    }

    public DefaultTableModel generarTablaPlatillos(ArrayList<Platillo> platillosFiltrados) {
        String[] encabezado = {"Código Del Platillo", "Nombre Del Platillo", "Descripcion", "Tamaño De La Porción",
                "Piezas Por Porción", "Calorías En 1 Porción", "Calorías Por Pieza", "Precio", "Imagen", "Cantidad A Pedir"};
        DefaultTableModel dtm = new DefaultTableModel(encabezado, platillosFiltrados.size());

        for (int i = 0; i < dtm.getRowCount(); i++) {
            Platillo cte = platillosFiltrados.get(i);
            dtm.setValueAt(cte.getId(), i, 0);
            dtm.setValueAt(cte.getNombrePlatillo(), i, 1);
            dtm.setValueAt(cte.getDescripcion(), i, 2);
            dtm.setValueAt(cte.getTamanoPorcion(), i, 3);
            dtm.setValueAt(cte.getPiezasPorcion(), i, 4);
            dtm.setValueAt(cte.getCaloriasPorcion() + " kcals", i, 5);
            dtm.setValueAt(cte.getCaloriarPieza() + " kcals", i, 6);
            dtm.setValueAt("₡" + cte.getPrecio(), i, 7);
            dtm.setValueAt(0, i, 9);
        }
        return dtm;
    }

    public void actualizarContadoresId() {
        for (Platillo i : platillos) {
            if (i instanceof Bebida) {
                CONTADOR_BEB++;
            } else if (i instanceof PlatoFuerte) {
                CONTADOR_PRN++;
            } else if (i instanceof Entrada) {
                CONTADOR_ENT++;
            } else {
                CONTADOR_PTR++;
            }
        }
    }

    public DefaultTableModel filtrarProductos(Peticion peti) {
        int filtro = (int) peti.getDatosEntrada();
        ArrayList<Platillo> platillosFiltrados = new ArrayList();
        for (Platillo i : platillos) {
            if (filtro == 1 && i instanceof Entrada) {
                platillosFiltrados.add(i);
            } else if (filtro == 2 && i instanceof PlatoFuerte) {
                platillosFiltrados.add(i);
            } else if (filtro == 3 && i instanceof Postre) {
                platillosFiltrados.add(i);
            } else if (filtro == 4 && i instanceof Bebida) {
                platillosFiltrados.add(i);
            }
        }
        return generarTablaPlatillos(platillosFiltrados);
    }
}
