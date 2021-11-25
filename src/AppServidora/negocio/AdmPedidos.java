package AppServidora.negocio;

import general.*;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Clase especializada en administrar pedidos
 * @author Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
 */
public class AdmPedidos {
    private static double costoExtraEmpaque = 25;
    private static double costoExtraEnvio = 2500;
    private AdmProductos amdProducts;
    private AdmArchivosBinarios admArchivosBinarios = new AdmArchivosBinarios();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private int numeroPedido = 0;

    public AdmPedidos() {
    }

    /**
     * Metodo para cargar en memoria el array de los pedidos
     */
    public void cargarPedidos() {
        pedidos = admArchivosBinarios.cargarArchivosPedidos();
    }

    /**
     * Metodo para calcular los valores del desglose del pedido
     * @param peti: Peticion del cliente hacia el server
     * @return Retorna el pedido actualizado
     */
    public Pedido calcularDesglose(Peticion peti) {
        Pedido pedidoCliente = (Pedido) peti.getDatosEntrada();
        double tmpPrecioPedido = 0;
        double tmpCaloriasTotales = 0;
        int pos = 0;
        int cantidadTotalPlatillos = 0;
        for (Platillo i : pedidoCliente.getPlatillosPedidos()) {
            tmpPrecioPedido += i.getPrecio() * pedidoCliente.getCantidadPlatillosPedidos().get(pos);
            tmpCaloriasTotales += i.getCaloriasPorcion() * pedidoCliente.getCantidadPlatillosPedidos().get(pos);
            cantidadTotalPlatillos += pedidoCliente.getCantidadPlatillosPedidos().get(pos);
            pos++;
        }
        pedidoCliente.setCosto(tmpPrecioPedido);
        pedidoCliente.setTotalCalorias(tmpCaloriasTotales);
        if (pedidoCliente.getClass() == Pedido.class) {
            pedidoCliente.setCostoTotal(pedidoCliente.getCosto());
            pedidoCliente.setCostoAdicional(0);
            return pedidoCliente;
        } else if (pedidoCliente.getClass() == PedidoExpress.class) {
            pedidoCliente.setCostoAdicional(costoExtraEnvio + (cantidadTotalPlatillos * costoExtraEmpaque));
            pedidoCliente.setCostoTotal(pedidoCliente.getCosto() + pedidoCliente.getCostoAdicional());
            return pedidoCliente;
        } else { // PedidoRecoger
            pedidoCliente.setCostoAdicional((cantidadTotalPlatillos * costoExtraEmpaque));
            pedidoCliente.setCostoTotal(pedidoCliente.getCosto() + pedidoCliente.getCostoAdicional());
            return pedidoCliente;
        }
    }

    /**
     * Direcciona la peticion y guarda el pedido en el array
     * @param pedido: Objeto pedidod del cliente
     * @return booleano
     */
    public boolean realizarNuevoPedido(Pedido pedido){
        pedido = amdProducts.meterPedidoUsuario(pedido);
        //PEDIDO TRAE TODA LA INFO ACTUALIZADA
        pedidos.add(pedido);
        admArchivosBinarios.insertarPedido(pedidos);

        System.out.println("Pedido registrado: "+pedido);
        return true;
    }

    /**
     * Setea la instancia de la clase AdmProductos
     * @param admin instancia previamente creada
     */
    public void setAmdProducts(AdmProductos admin){
        this.amdProducts=admin;
    }

    /**
     * Genera la tabla de los productos Top Ten mas pedidos
     * @return
     */
    public DefaultTableModel modeloTablaTopTen() {
        ArrayList<Platillo> ordenado = amdProducts.TopTenMasPedidos();
        System.out.println(ordenado);
        //HACER TABLA
        String[] encabezado = {"Codigo","Nombre","Descripcion","Categoria", "Cantidad De Veces Solicitado"};
        DefaultTableModel dtm = null;
        if (ordenado.size() >= 10) {
            dtm = new DefaultTableModel(encabezado, 10);
        } else {
            dtm = new DefaultTableModel(encabezado, ordenado.size());
        }
        for (int i = 0; i < dtm.getRowCount(); i++) {
            Platillo cte = ordenado.get(i);

            dtm.setValueAt(cte.getId(), i, 0);
            dtm.setValueAt(cte.getNombrePlatillo(),i,1);
            dtm.setValueAt(cte.getDescripcion(),i,2);

            if (cte instanceof Entrada)
                dtm.setValueAt("Entrada",i,3);
            else if (cte instanceof Bebida)
                dtm.setValueAt("Bebida",i,3);
            else if (cte instanceof Postre)
                dtm.setValueAt("Postre",i,3);
            else if (cte instanceof PlatoFuerte)
                dtm.setValueAt("Plato Fuerte",i,3);

            dtm.setValueAt(cte.getCantidadDeVecesSolicitado(), i, 4);
        }
        return dtm;//Meter en la gui
    }

    /**
     * Genera la tabla de los productos nunca solicitados
     * @return
     */
    public DefaultTableModel modeloTablaNuncaOrdenados(){
        ArrayList<Platillo> nuncaPedidos = amdProducts.TopTenNuncaPedidos();
        //HACER TABLA
        String[] encabezado = {"Codigo","Nombre","Categoria"};
        DefaultTableModel dtm = new DefaultTableModel(encabezado, nuncaPedidos.size());
        for (int i = 0; i < dtm.getRowCount(); i++) {
            Platillo cte = nuncaPedidos.get(i);
            dtm.setValueAt(cte.getId(), i, 0);
            dtm.setValueAt(cte.getNombrePlatillo(),i,1);
            if (cte instanceof Entrada)
                dtm.setValueAt("Entrada",i,2);
            else if (cte instanceof Bebida)
                dtm.setValueAt("Bebida",i,2);
            else if (cte instanceof Postre)
                dtm.setValueAt("Postre",i,2);
            else if (cte instanceof PlatoFuerte)
                dtm.setValueAt("Plato Fuerte",i,2);
        }
        return dtm;
    }

    /**
     * Metodo para generar la tabla de todos los pedidos
     * @return retorna el modelo de la tabla
     */
    public DefaultTableModel modeloTablaPedidos() {
        String[] encabezado = {"Fecha", "Numero Pedido", "Nombre", "Nombre De Quién Recoge", "Celular", "Dirección", "Platillos"};
        DefaultTableModel dtm = new DefaultTableModel(encabezado, pedidos.size());

        for (int i = 0; i < dtm.getRowCount(); i++) {
            Pedido cte = pedidos.get(i);
            dtm.setValueAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(cte.getFecha()), i, 0);

            dtm.setValueAt(cte.getNumeroPedido(), i, 1);

            dtm.setValueAt(cte.getNombre(), i, 2);
            if (cte.getClass() == PedidoRecoger.class) {
                dtm.setValueAt((((PedidoRecoger) cte).getNombreRecoge()), i, 3);
            } else {
                dtm.setValueAt("No Aplica", i, 3);
            }

            if (cte.getClass() == PedidoExpress.class) {
                dtm.setValueAt(((PedidoExpress)cte).getCelular(), i, 4);
            } else if (cte.getClass() == PedidoRecoger.class) {
                dtm.setValueAt(((PedidoRecoger)cte).getCelular(), i, 4);
            } else {
                dtm.setValueAt("No Aplica", i, 4);
            }

            if (cte.getClass() == PedidoExpress.class) {
                dtm.setValueAt(((PedidoExpress) cte).getDireccion(), i, 5);
            } else {
                dtm.setValueAt("No Aplica", i, 5);
            }

            String textoPlatillo = "";
            int pos = 0;
            for (Platillo j : cte.getPlatillosPedidos()) {
                textoPlatillo += j.getNombrePlatillo() + " - Cantidad: " + cte.getCantidadPlatillosPedidos().get(pos) + "\n";
                pos++;
            }
            String convertido = null;
            String sinSaltos = textoPlatillo.replaceAll("\n", "<br> ");
            convertido = "<HTML> " + sinSaltos + " </HTML>";
            dtm.setValueAt(convertido, i, 6);
        }
        return dtm;
    }

    public double[] reporteRelacionEntreLugaresDePedido(){
        double[] datos =new double[3];

        for (int i=0;i<3;i++)
            datos[i]=0; //para limpiar por si acaso

        for (Pedido pedido : pedidos){
            if (pedido.getClass() == PedidoRecoger.class)
                datos[0]++;
            else if (pedido.getClass()==PedidoExpress.class)
                datos[1]++;
            else
                datos[2]++;
        }
        return datos;
    }
    //FALTA LA VARA DE PORCENTAJES
//    private static PieDataset createDataset( ) {
//        DefaultPieDataset dataset = new DefaultPieDataset( );
//        dataset.setValue( "San José" , new Double( 20 ) );
//        dataset.setValue( "Alajuela" , new Double( 20 ) );
//        dataset.setValue( "Heredia" , new Double( 10 ) );
//        dataset.setValue( "Cartago" , new Double( 15 ) );
//        dataset.setValue( "Guanacaste" , new Double( 25 ) );
//        dataset.setValue( "Limón" , new Double( 7 ) );
//        dataset.setValue( "Puntarenas" , new Double( 3 ) );
//        return dataset;
//    }

}
