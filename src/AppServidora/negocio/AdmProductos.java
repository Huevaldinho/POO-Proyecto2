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
    /**
     * @author: Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
     * @param peti: Peticion para buscar platillo
     * Descripcion: Busca un platillo en el arrayList de platillos
     * */
    public  int buscarPlatillo(Peticion peti){
        //Abrir archivo binario y buscar a peti
        Platillo platillo = (Platillo) peti.getDatosEntrada();
        int contador=0;
        for (Platillo actual: platillos){
            System.out.println("Actual: "+actual.toString());
            if (platillo.equals(actual)){
                System.out.println("Repetido: "+actual.toString()+"\n"+platillo.toString());
                return contador;
            }
            contador++;
        }
        System.out.println("No se encontro ese platillo");
        return -1;
    }
    /**
     * @author: Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
     * @param peti: Peticion para insertar platillo
     * Descripcion: Inserta un platillo en el arrayList y en el archivo binario.
     * */
    public boolean insertarNuevoPlatillo(Peticion peti){//Revisar archivos binarios
        if (buscarPlatillo(peti)==-1){//Como no existe lo mete al archivo
            ((Platillo) peti.getDatosEntrada()).setId();
            platillos.add((Platillo) peti.getDatosEntrada());//Agrega el platillo al arrayList
            admArchivos.insertarPlatillo(platillos);//Agrega al archivo binario
            return true;
        }
        return false;
    }
    /**
     * @author: Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
     * @param peti: Peticion para modificar platillo
     * Descripcion: Modifica un platillo en el arrayList y en el archivo binario.
    * */
    public boolean modificarPlatillo(Peticion peti){
        System.out.println("MODIFICAR PLATILLOS ");
        int fila = (int) peti.getDatosSalida();
        Platillo buscado = platillos.get(fila);
        System.out.println("Buscado antes de modificar: "+platillos.get(fila).toString());

        Platillo tmp = (Platillo) peti.getDatosEntrada();
        //HACER CAMBIOS
        buscado.setIdModificado(tmp.getId());
        buscado.setNombrePlatillo(tmp.getNombrePlatillo());
        buscado.setDescripcion(tmp.getDescripcion());
        buscado.setTamanoPorcion(tmp.getTamanoPorcion());
        buscado.setPiezasPorcion(tmp.getPiezasPorcion());
        buscado.setCaloriasPorcion(tmp.getCaloriasPorcion());
        buscado.setCaloriarPieza(tmp.getCaloriarPieza());
        buscado.setPrecio(tmp.getPrecio());
        buscado.setRutaImagen(tmp.getRutaImagen());
        platillos.set(fila,buscado);
        System.out.println("Buscado modificado: "+platillos.get(fila));
        admArchivos.insertarPlatillo(platillos);
        return  true;
    }
    /**
     * @author: Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
     * @param peti: Peticion para eliminar platillo
     * Descripcion: Elimina un platillo en el arrayList y en el archivo binario.
     * */
    public boolean eliminarPlatillo(Peticion peti){
        //Siempre lo encuentra porque toma el index de la tabla
        platillos.remove((int)peti.getDatosEntrada());//Borra plato de memoria
        admArchivos.insertarPlatillo(platillos);//Crea el nuevo archivo binario modificado
        return true;
    }
    /**
     * @author: Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
     * Descripcion: Solicita que cargen los platillos de los archivos binarios
     * */
    public void cargarPlatillos() {
        platillos = admArchivos.cargarArchivosPlatillos();//Carga platillos de archivos binarios
    }
    /**
     * @author: Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
     * Descripcion: Genera el modelo de la tabla para mostrar los platillos a los Usuarios
     * */
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
    /**
     * @author: Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
     * @param platillosFiltrados: ArrayList de platillos
     * Descripcion: Genera el modelo de la tabla para mostrar los platillos a los Usuarios
     * */
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
    /**
     * @author: Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
     * Descripcion: Actualiza los contadores de los tipos de platillos
     * */
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
    /**
     * Descripcion: Genera un modelo de tabla filtrado por tipo de platillo
     * @param peti: Lista de sismos que se mostraran en la interfaz.
     * @return DefaultTableModel: Modelo de la tabla filtrado por tipo de platillo
     */
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
