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


    public Controlador() {
        admProducts.cargarPlatillos();
    }
    
    public Peticion procesarPeticion(Peticion peticionRecibida) {
        switch (peticionRecibida.getAccion()){
            case SALUDAR: 
                peticionRecibida.setDatosSalida("Saludos cliente " + peticionRecibida.getDatosEntrada() + " desde el servidor!");
                break;
            case INGRESAR:
                //revisa si la contra esta bien, utiliza AdmiUsuarios
                 peticionRecibida.setDatosSalida(admUsr.validarAdm((String) peticionRecibida.getDatosEntrada()));
                break;
            case VER_PRODUCTOS: // genera la tabla de los productos
                peticionRecibida.setDatosSalida(admProducts.generarTablaPlatillos());
                break;
            case CONSULTAR_PRODUCTO: 
                break;
            case AGREGAR_CARRITO: //    ELIMINAR_PLATILO, MODIFICAR_PLATILLO, REALIZAR_PEDIDO
                break;
            case ELIMINAR_PLATILO:
                break;
            case MODIFICAR_PLATILLO:
                break;
            case REALIZAR_PEDIDO:
                break;
            case AGREGAR_PLATILLO:
                //Debe usar AdmProductos - este mae tiene que revisar si ese platillo ya existe
                //peticioRecibida.SetDatosSalida(admProducts.)
                System.out.println("AGREGAR PLATILLO");
                peticionRecibida.setDatosSalida((admProducts.insertarNuevoPlatillo(peticionRecibida)));
                break;
            case BUSCAR_PLATILLO:
                System.out.println("BUSCAR PLATILLO");
                break;
        }
        return peticionRecibida;
    }
    
}
