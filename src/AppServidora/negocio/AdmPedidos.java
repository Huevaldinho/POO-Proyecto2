package AppServidora.negocio;

import general.Pedido;
import general.PedidoExpress;
import general.PedidoRecoger;
import general.Platillo;

import java.util.ArrayList;

/**
 * @author: Felipe Obando, Sebastian Arroniz y Sebastian Bermudez
 * Descripcion: Clase especializada en administrar pedidos.
 */
public class AdmPedidos {
    public static double costoExtraEmpaque = 25;
    public static double costoExtraEnvio = 2500;

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
}
