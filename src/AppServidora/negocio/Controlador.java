/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServidora.negocio;

import general.Pedido;
import general.Peticion;
import general.Platillo;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Clase que se encarga de llevar el control general
 */
public class Controlador {

    private AdmUsuarios admUsr = new AdmUsuarios();
    private AdmProductos admProducts = new AdmProductos();
    private AdmPedidos admPedidos = new AdmPedidos();

    public Controlador() {
        admProducts.cargarPlatillos();
        admProducts.actualizarContadoresId();
        admPedidos.setAmdProducts(this.admProducts);
        admPedidos.cargarPedidos();
    }

    /**
     * Metodo que se encarga de gestionar la peticion recibida
     * @param peticionRecibida: Peticion del cliente
     * @return Retorna la peticion actualizada
     */
    public Peticion procesarPeticion(Peticion peticionRecibida) {
        switch (peticionRecibida.getAccion()){
            case INGRESAR: {
                //revisa si la contra esta bien, utiliza AdmiUsuarios
                peticionRecibida.setDatosSalida(admUsr.validarAdm((String) peticionRecibida.getDatosEntrada()));
                break;
            }
            case VER_PRODUCTOS: { // genera la tabla de los productos
                peticionRecibida.setDatosSalida(admProducts.generarTablaPlatillos());
                break;
            }
            case VER_PRODUCTOS_ADMIN: { // genera la tabla de los productos
                peticionRecibida.setDatosSalida(admProducts.generarTablaPlatillosAdmin());
                break;
            }
            case CONSULTAR_PRODUCTO: {
                break;
            }
            case ELIMINAR_PLATILO: {
                peticionRecibida.setDatosSalida(admProducts.eliminarPlatillo(peticionRecibida));
                break;
            }
            case MODIFICAR_PLATILLO: {
                peticionRecibida.setDatosSalida(admProducts.modificarPlatillo(peticionRecibida));
                break;
            }
            case REALIZAR_PEDIDO: {
                peticionRecibida.setDatosSalida(admPedidos.realizarNuevoPedido((Pedido) peticionRecibida.getDatosEntrada()));
                break;
            }
            case AGREGAR_PLATILLO: {
                //Debe usar AdmProductos - este mae tiene que revisar si ese platillo ya existe
                //peticioRecibida.SetDatosSalida(admProducts.)
                peticionRecibida.setDatosSalida((admProducts.insertarNuevoPlatillo(peticionRecibida)));
                break;
            }
            case BUSCAR_PLATILLO: {
                peticionRecibida.setDatosSalida(admProducts.buscarPlatillo(peticionRecibida));
                break;
            }
            case GENERAR_CARRITO: {
                peticionRecibida.setDatosSalida(admProducts.generarCarritoCompras(peticionRecibida));
                break;
            }
            case GUARDAR_PEDIDO: {
                peticionRecibida.setDatosSalida(admProducts.guardarPlatillosCantidades(peticionRecibida));
                break;
            }
            case DESGLOSE_PEDIDO: {
                peticionRecibida.setDatosSalida(admPedidos.calcularDesglose(peticionRecibida));
                break;
            }
            case GENERAR_TABLA_NUNCA_SOLICITADOS: {
                peticionRecibida.setDatosSalida(admPedidos.modeloTablaNuncaOrdenados());
                break;
            }
            case GENERAR_TABLA_PEDIDOS: {
                peticionRecibida.setDatosSalida(admPedidos.modeloTablaPedidos());
                break;
            }
            case GENERAR_TABLA_TOP_TEN: {
                peticionRecibida.setDatosSalida(admPedidos.modeloTablaTopTen());
                break;
            }
        }
        return peticionRecibida;
    }
    
}
