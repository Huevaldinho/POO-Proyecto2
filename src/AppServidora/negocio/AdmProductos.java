package AppServidora.negocio;
import general.Peticion;
import general.Platillo;

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

    AdmProductos (){}

    public  boolean buscarPlatillo(Peticion peti){
        //Abrir archivo binario y buscar a peti

        return admArchivos.buscarPlatillo(peti);
    }
    public boolean insertarNuevoPlatillo(Peticion peti){//Revisar archivos binarios

        //Busca si ya existe el platillo
        System.out.println("Se va a buscar platillo en archivos.");
        if (!buscarPlatillo(peti)){//Como no existe lo mete al archivo
            ((Platillo) peti.getDatosEntrada()).setId();
            admArchivos.insertarPlatillo(peti);//Agrega al archivo binario
            platillos.add((Platillo) peti.getDatosEntrada());//Agrea al arrayList
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
                "Piezas Por Porción", "Calorías En 1 Porción", "Calorías Por Pieza", "Precio", "Cantidad"};
        DefaultTableModel dtm = new DefaultTableModel(encabezado, platillos.size());

        for (int i = 0; i < dtm.getRowCount(); i++) {
            Platillo cte = platillos.get(i);
            dtm.setValueAt(cte.getId(), i, 0);
            dtm.setValueAt(cte.getNombrePlatillo(), i, 1);
            dtm.setValueAt(cte.getDescripcion(), i, 2);
            dtm.setValueAt(cte.getTamanoPorcion(), i, 3);
            dtm.setValueAt(cte.getPiezasPorcion(), i, 4);
            dtm.setValueAt(cte.getCaloriasPorcion(), i, 5);
            dtm.setValueAt(cte.getCaloriarPieza(), i, 6);
            dtm.setValueAt(cte.getPrecio(), i, 7);
            dtm.setValueAt(0, i, 8);
        }
        return dtm;
    }

}
