package AppServidora.negocio;

import general.*;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * @author: Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
 * Descripcion: Clase especializada en administrar pedidos.
 */
public class AdmPedidos {
    private static double costoExtraEmpaque = 25;
    private static double costoExtraEnvio = 2500;
    private AdmProductos amdProducts;
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private int numeroPedido = 0;

    public AdmPedidos() {
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
     * Direcciona la peticion
     * @param pedido: Objeto pedidod del cliente
     * @return booleano
     */
    public boolean realizarNuevoPedido(Pedido pedido){
        pedido = amdProducts.meterPedidoUsuario(pedido);
        //PEDIDO TRAE TODA LA INFO ACTUALIZADA

        pedidos.add(pedido);
        System.out.println("Pedido modificado: "+pedido);

        return true;
    }

    /**
     * Setea la instancia de la clase AdmProductos
     * @param admin instancia previamente creada
     */
    public void setAmdProducts(AdmProductos admin){
        this.amdProducts=admin;
        System.out.println(this.amdProducts);
    }

    /**
     * Genera la tabla de los productos Top Ten mas pedidos
     * @return
     */
    public DefaultTableModel modeloTablaTopTen(){
        ArrayList<Platillo> ordenado = amdProducts.TopTenMasPedidos();
        //HACER TABLA
        String[] encabezado = {"Codigo","Nombre","Descripcion","Categoria"};
        DefaultTableModel dtm = new DefaultTableModel(encabezado, ordenado.size());
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
            Platillo cte = (Platillo) nuncaPedidos.get(i);
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
        }
        return dtm;
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
