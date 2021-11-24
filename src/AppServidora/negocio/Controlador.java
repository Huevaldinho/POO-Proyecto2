/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServidora.negocio;

import general.Peticion;
import general.Platillo;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author ersolano
 */
public class Controlador {
    
    private AdmUsuarios admUsr = new AdmUsuarios();
    private AdmProductos admProducts = new AdmProductos();
    private AdmPedidos admPedidos = new AdmPedidos();


    public Controlador() {
        admProducts.cargarPlatillos();
        admProducts.actualizarContadoresId();
    }
    
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
            case DESGLOSE_PEDIDO: {
                peticionRecibida.setDatosSalida(admProducts.extraerProductosPedido(peticionRecibida));
                break;
            }
        }
        return peticionRecibida;
    }
    
}
