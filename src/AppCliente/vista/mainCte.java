package AppCliente.vista;


import AppCliente.conexion.Client;
import AppCliente.conexion.Client;
import general.Peticion;
import general.TipoAccion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ersolano
 */
public class mainCte {
    public static void main(String[] args) {
        //new Client();
        JFrame ventanaPrincipal = new VentanaPrincipalCliente();
        Peticion peticionMostrarProductos = new Peticion(TipoAccion.VER_PRODUCTOS, null);
        Client conexion = new Client(peticionMostrarProductos);
        DefaultTableModel tablaProductos = (DefaultTableModel) conexion.getRespuestaServer();
        ((VentanaPrincipalCliente)ventanaPrincipal).setTablaCatalogo(tablaProductos); // casteo y seteo de tabla
        ventanaPrincipal.setVisible(true);
    }
 }
