package AppAdministrador.vista;

import AppCliente.conexion.Client;
import AppCliente.vista.VentanaPrincipalCliente;
import general.Peticion;
import general.TipoAccion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarPlatillosAdmin extends JFrame {
    private JPanel panel;
    private JComboBox comboboxFiltros;
    private JButton botonAgregarPlatillo;
    private JButton botonFiltrar;
    private JPanel panel1;
    private JTable tablaPlatillos;
    private JButton btnModificarPlatillo;
    private JButton btnEliminarPlatillo;

    public MostrarPlatillosAdmin() {
        super("Platillos");
        setContentPane(panel);
        this.pack();
        botonAgregarPlatillo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new AgregarPlatilloAdmin();
                ventana.setVisible(true);
            }
        });
        botonFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filtro = comboboxFiltros.getSelectedIndex();
                if (filtro == 0) {
                    Peticion peticion = new Peticion(TipoAccion.VER_PRODUCTOS, filtro);
                    Client conexion = new Client(peticion);
                    DefaultTableModel tablaProductos = (DefaultTableModel) conexion.getRespuestaServer();
                    setTablaCatalogo(tablaProductos);
                } else {
                    Peticion peticion = new Peticion(TipoAccion.FILTRAR_PRODUCTOS, filtro);
                    Client conexion = new Client(peticion);
                    DefaultTableModel tablaFiltrada = (DefaultTableModel) conexion.getRespuestaServer();
                    setTablaCatalogo(tablaFiltrada);
                }
            }
        });
        btnEliminarPlatillo.addActionListener(new ActionListener() {//Boton Eliminar
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventanaPrincipal = new EliminarPlatilloAdmin();
                Peticion peticionMostrarProductos = new Peticion(TipoAccion.VER_PRODUCTOS, null);
                Client conexion = new Client(peticionMostrarProductos);
                DefaultTableModel tablaProductos = (DefaultTableModel) conexion.getRespuestaServer();
                ((EliminarPlatilloAdmin) ventanaPrincipal).setTablaCatalogo(tablaProductos);
                ventanaPrincipal.setVisible(true);
            }
        });
        btnModificarPlatillo.addActionListener(new ActionListener() {//BOTON MODIFICAR
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventanaModificar = new ModificarPlatilloAdmin();
                Peticion peti = new Peticion(TipoAccion.VER_PRODUCTOS,null);
                Client conexion  = new Client(peti);
                DefaultTableModel  tablaProductos = (DefaultTableModel) conexion.getRespuestaServer();
                ((ModificarPlatilloAdmin)ventanaModificar).setTablaCatalogo(tablaProductos);
                ventanaModificar.setVisible(true);
            }
        });
    }

    public void setTablaCatalogo(DefaultTableModel catalogo) {
        tablaPlatillos.setModel(catalogo);
    }
}
