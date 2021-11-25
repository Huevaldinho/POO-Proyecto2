package AppServidora.negocio;

import general.Pedido;
import general.PedidoExpress;
import general.PedidoRecoger;
import general.Platillo;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * @author: Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
 * Descripcion: Clase especializada en administrar pedidos.
 */
public class AdmPedidos {
    public static double costoExtraEmpaque = 25;
    public static double costoExtraEnvio = 2500;
    private AdmProductos amdProducts;
    private ArrayList<Object> pedidos = new ArrayList<>();
    private int numeroPedido = 0;

    public AdmPedidos() {
    }

    public double[] calcularDesglose(ArrayList<Platillo> platillos, Pedido pedidoCliente) {
        double precioPedido = 0;
        double precioTotal = 0;
        double caloriasTotales = 0;
        for (Platillo i : platillos) {
            precioPedido += i.getPrecio();
            caloriasTotales += i.getCaloriasPorcion();
        }
        if (pedidoCliente instanceof Pedido) {
            precioTotal += precioPedido;
            double[] array = {precioTotal, precioPedido, caloriasTotales, 0};
            return array;
        } else if (pedidoCliente instanceof PedidoExpress) {
            double costoExtra = (costoExtraEmpaque * platillos.size()) + costoExtraEnvio;
            System.out.println(costoExtra);
            precioTotal += precioPedido + costoExtra;
            double[] array = {precioTotal, precioPedido, caloriasTotales, costoExtra};
            return array;
        } else { // PedidoRecoger
            precioTotal += precioPedido + (costoExtraEmpaque * platillos.size());
            double costoExtra = costoExtraEmpaque * platillos.size();
            double[] array = {precioTotal, precioPedido, caloriasTotales, costoExtraEmpaque * platillos.size()};
            return array;
        }
    }
    public boolean realizarNuevoPedido(Object peti){
        //platillosPedidos=null esta vara no hace falta, con solo los codigos y numeros se puede hacer
        ArrayList<Object> pedido;
        pedido= (ArrayList<Object>) peti;
        pedido = amdProducts.meterPedidoUsuario(peti);
        //PEDIDO TRAE TODA LA INFO ACTUALIZADA
        pedidos.add(pedido);
        System.out.println(pedido);
        return true;
    }
    public void setAmdProducts(AdmProductos admin){
        this.amdProducts=admin;
        System.out.println(this.amdProducts);
    }
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
