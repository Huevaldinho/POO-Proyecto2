package AppServidora.negocio;
import general.*;

import javax.swing.*;
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
    private AdmPedidos admPedidos = new AdmPedidos();
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
            System.out.println("Actual buscador: "+actual.toString());
            System.out.println("Antes del equals");
            boolean iguales =platillo.equals(actual);
            System.out.println("IGUALES: "+iguales);
            if (iguales){
                System.out.println("Repetido: "+actual.toString()+"\n"+platillo.toString());
                return contador;
            }
            System.out.println("Despues del equals");
            contador++;
        }
        System.out.println("No se encontro ese platillo");
        return -1;
    }

    public int buscarPorCodigo(String codigo){
        int contador=0;
        for (Platillo actual:platillos){
            if (actual.getId().equals(codigo))
                return contador;
            contador++;
        }
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
            //ImageIcon imagen = new ImageIcon(cte.getRutaImagen());
            //if (imagen != null)
            //dtm.setValueAt(new JLabel(new ImageIcon(getClass().getResource(cte.getRutaImagen()))), i, 8);
            dtm.setValueAt("0", i, 9);
        }
        return dtm;
    }

    /**
     * @author: Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
     * @param platillosSeleccionados: Son los platillos seleccionados por el usuario
     * Descripcion: Genera el modelo de la tabla para mostrar los platillos a los Usuarios
     * */
    public DefaultTableModel generarTablaPlatillos(ArrayList<Platillo> platillosSeleccionados) {
        String[] encabezado = {"Código Del Platillo", "Nombre Del Platillo", "Descripcion", "Tamaño De La Porción",
                "Piezas Por Porción", "Calorías En 1 Porción", "Calorías Por Pieza", "Precio", "Imagen", "Cantidad A Pedir"};
        DefaultTableModel dtm = new DefaultTableModel(encabezado, platillosSeleccionados.size());

        for (int i = 0; i < dtm.getRowCount(); i++) {
            Platillo cte = platillosSeleccionados.get(i);
            dtm.setValueAt(cte.getId(), i, 0);
            dtm.setValueAt(cte.getNombrePlatillo(), i, 1);
            dtm.setValueAt(cte.getDescripcion(), i, 2);
            dtm.setValueAt(cte.getTamanoPorcion(), i, 3);
            dtm.setValueAt(cte.getPiezasPorcion(), i, 4);
            dtm.setValueAt(cte.getCaloriasPorcion() + " kcals", i, 5);
            dtm.setValueAt(cte.getCaloriarPieza() + " kcals", i, 6);
            dtm.setValueAt("₡" + cte.getPrecio(), i, 7);
            //ImageIcon imagen = new ImageIcon(cte.getRutaImagen());
            //if (imagen != null)
            //dtm.setValueAt(imagen, i, 8);
            dtm.setValueAt("0", i, 9);
        }
        return dtm;
    }

    /**
     * @author: Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
     * Descripcion: Genera el modelo de la tabla para mostrar los platillos a los Usuarios
     * */
    public DefaultTableModel generarTablaPlatillosAdmin() {
        String[] encabezado = {"Código Del Platillo", "Nombre Del Platillo", "Descripcion", "Tamaño De La Porción",
                "Piezas Por Porción", "Calorías En 1 Porción", "Calorías Por Pieza", "Precio", "Imagen"};
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
     *
     * @param peti: Peticion por parte del cliente
     * @return retorna el modelo de la tabla con el carrito de compras
     */
    public DefaultTableModel generarCarritoCompras(Peticion peti) {
        ArrayList<Platillo> platillosSeleccionados = new ArrayList<>();
        for (String i : (ArrayList<String>) peti.getDatosEntrada()) {
            for (Platillo j : platillos) {
                if (i.equals(j.getId())) {
                    platillosSeleccionados.add(j);
                }
            }
        }
        return generarTablaPlatillos(platillosSeleccionados);
    }

    /**
     * Metodo para almacenar cantidades y platillos en el objeto del pedido del cliente
     * @param peti: Peticion por parte del cliente hacia el servidor
     * @return retorna el pedido modificado
     */
    public Pedido guardarPlatillosCantidades(Peticion peti){
        ArrayList<Object> transferencia = (ArrayList<Object>) peti.getDatosEntrada(); // extrae la transferencia
        ArrayList<String> platosPedidos = (ArrayList<String>) transferencia.get(0);//SACA LOS CODIGOS
        ArrayList<Integer> cantidadVecesPedido = (ArrayList<Integer>) transferencia.get(2);//SACA LA CANTIDAD  DE PLATILLOS
        Pedido pedidoSolo = (Pedido) transferencia.get(1);
        Platillo tmp=null;
        ArrayList<Platillo> tmpPlatillos = new ArrayList<>();
        for (String actual:platosPedidos){//AUMENTA LA CANTIDAD DE VECES UQE SE PIDIO ESE PLATILLOS
            tmp = platillos.get(buscarPorCodigo(actual));
            tmpPlatillos.add(tmp);//lo mete al array
        }
        pedidoSolo.setCantidadPlatillosPedidos(cantidadVecesPedido);
        pedidoSolo.setPlatillosPedidos(tmpPlatillos);
        return pedidoSolo;
    }

    public Pedido meterPedidoUsuario(Pedido pedidoEntrante){

        Platillo tmp= null;
        int pos =0;//POSICION DEL PLATILLO EN EL ARRAY
        ArrayList<Platillo> tmpPlatillos = pedidoEntrante.getPlatillosPedidos();
        System.out.println("Toma los platillos del pedido"+tmpPlatillos.toString());
        for (Platillo actual:tmpPlatillos){//AUMENTA LA CANTIDAD DE VECES UQE SE PIDIO ESE PLATILLOS
            tmp = platillos.get(buscarPorCodigo(actual.getId()));

            tmp.aumentarPlatillo(pedidoEntrante.getCantidadPlatillosPedidos().get(pos));//aumenta en el total
            pos++;
        }
        pedidoEntrante.setFecha();
        pedidoEntrante.setNumeroPedido();
        System.out.println("Peticion editada: "+pedidoEntrante.toString());

        //Set costo

        admArchivos.insertarPlatillo(platillos);//TIENE QUE GUARDAR EL ARCHIVO OTRA VEZ

        return pedidoEntrante;
    }

    //Consultas

    public ArrayList<Platillo> TopTenMasPedidos(){
        ArrayList<Platillo> ordenados= selectionSort();
        return ordenados;
    }

    public ArrayList<Platillo> selectionSort() {
        int size = platillos.size();
        ArrayList<Platillo> tmp = platillos;
        for (int step = 0; step < size - 1; step++) {
            int min_idx = step;
            for (int i = step + 1; i < size; i++) {
                // To sort in descending order, change > to < in this line.
                // Select the minimum element in each loop.
                if (tmp.get(i).getCantidadDeVecesSolicitado()>tmp.get(min_idx).getCantidadDeVecesSolicitado()) {
                    min_idx = i;
                }
            }
            // put min at the correct position
            Platillo tmpPlatillo = tmp.get(step);
            //int temp = array[step];
            tmp.set(step,tmp.get(min_idx));
            //array[step] = array[min_idx];
            tmp.set(min_idx,tmpPlatillo);
            //array[min_idx] = temp;
        }
        return tmp;
    }
    public ArrayList<Platillo> TopTenNuncaPedidos(){
        ArrayList<Platillo> tmp = new ArrayList<>();
        for (Platillo actual: platillos){
            if (actual.getCantidadDeVecesSolicitado()==0)
                tmp.add(actual);
        }
        return tmp;
    }
}
