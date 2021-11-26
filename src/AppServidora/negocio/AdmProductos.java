package AppServidora.negocio;
import general.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Clase especializada en manejar productos
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
     * Busca un platillo en el arrayList de platillos
     * @param peti: Peticion para buscar platillo
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

    /**
     * Metodo para buscar un platillo por el id
     * @param codigo: Id a buscar
     * @return int para saber valor booleano
     */
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
     * Inserta un platillo en el arrayList y en el archivo binario.
     * @param peti Peticion para insertar platillo
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
     * Modifica un platillo en el arrayList y en el archivo binario.
     * @param peti Peticion para modificar platillo
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
     * Elimina un platillo en el arrayList y en el archivo binario.
     * @param peti: Peticion para eliminar platillo
     * */
    public boolean eliminarPlatillo(Peticion peti){
        //Siempre lo encuentra porque toma el index de la tabla
        platillos.remove((int)peti.getDatosEntrada());//Borra plato de memoria
        admArchivos.insertarPlatillo(platillos);//Crea el nuevo archivo binario modificado
        return true;
    }

    /**
     * Solicita que cargen los platillos de los archivos binarios
     * */
    public void cargarPlatillos() {
        platillos = admArchivos.cargarArchivosPlatillos();//Carga platillos de archivos binarios
    }

    /**
     * Genera el modelo de la tabla para mostrar los platillos a los Usuarios
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
            dtm.setValueAt(cte.getRutaImagen(), i, 8);
            dtm.setValueAt("0", i, 9);
        }
        return dtm;
    }

    /**
     * Genera el modelo de la tabla para mostrar los platillos a los Usuarios
     * @param platillosSeleccionados: Son los platillos seleccionados por el usuario
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
     * Genera el modelo de la tabla para mostrar los platillos a los Usuarios
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
            dtm.setValueAt(cte.getRutaImagen(),i,8);
        }
        return dtm;
    }

    /**
     * Actualiza los contadores de los tipos de platillos
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
     * Metodo para generar el carrito de compras
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

    /**
     * Metodo para registrar un pedido
     * @param pedidoEntrante
     * @return retorna el pedido para ser actualizado
     */
    public Pedido meterPedidoUsuario(Pedido pedidoEntrante){
        int pos = 0; //POSICION DEL PLATILLO EN EL ARRAY
        for (Platillo actual : pedidoEntrante.getPlatillosPedidos()){ //AUMENTA LA CANTIDAD DE VECES QUE SE PIDIO ESE PLATILLOS
            for (Platillo i : platillos) {
                if (actual.equals(i))
                    i.aumentarPlatillo(pedidoEntrante.getCantidadPlatillosPedidos().get(pos));

            }
            pos++;
        }
        pedidoEntrante.setFecha();
        pedidoEntrante.setNumeroPedido();
        System.out.println("Peticion editada: "+pedidoEntrante.toString());

        admArchivos.insertarPlatillo(platillos);//TIENE QUE GUARDAR EL ARCHIVO OTRA VEZ

        return pedidoEntrante;
    }

    //Consultas

    /**
     * Metodo para ordenar los productos mas pedidos
     * @return retorna el array ordenado
     */
    public ArrayList<Platillo> TopTenMasPedidos(){
        ArrayList<Platillo> temporal = new ArrayList<>();
        for (Platillo i : platillos) {
            if (i.getCantidadDeVecesSolicitado() > 0) {
                temporal.add(i);
            }
        }

        Collections.sort(temporal, new Comparator<Platillo>() {
            @Override
            public int compare(Platillo p1, Platillo p2) {
                return new Integer(p2.getCantidadDeVecesSolicitado()).compareTo(new Integer(p1.getCantidadDeVecesSolicitado()));
            }
        });
        return temporal;
    }

    /**
     * Metodo para sacar una copia de los productos nunca pedidos y meterlos a un array temporal
     * @return
     */
    public ArrayList<Platillo> TopTenNuncaPedidos(){
        ArrayList<Platillo> tmp = new ArrayList<>();
        for (Platillo actual : platillos) {
            if (actual.getCantidadDeVecesSolicitado() == 0)
                tmp.add(actual);
        }
        return tmp;
    }
}
