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

        //pedidos.add(pedido);
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
            Platillo cte = (Platillo) ordenado.get(i);

            dtm.setValueAt(cte.getId(), i, 0);
            dtm.setValueAt(cte.getNombrePlatillo(),i,1);
            dtm.setValueAt(cte.getDescripcion(),i,2);

            //FALTA CATEGORIA

        }
        return dtm;
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

            //FALTA CATEGORIA

        }
        return dtm;
    }
    //FALTA LA VARA DE PORCENTAJES

}
