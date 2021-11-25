package AppAdministrador.vista;

import AppAdministrador.conexion.ClienteAdmin;
import AppCliente.conexion.Client;
import AppCliente.vista.VentanaPrincipalCliente;
import general.Peticion;
import general.TipoAccion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfaz que muestra el catalogo de productos
 */
public class MostrarPlatillosAdmin extends JFrame {
    private JPanel panel;
    private JComboBox comboboxFiltros;
    private JButton botonAgregarPlatillo;
    private JButton botonFiltrar;
    private JPanel panel1;
    private JTable tablaPlatillos;
    private JButton btnModificarPlatillo;
    private JButton btnEliminarPlatillo;
    private JButton actualizarTablaButton;

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
                filtro(comboboxFiltros.getSelectedIndex(), tablaPlatillos);
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
        actualizarTablaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Peticion peticion = new Peticion(TipoAccion.VER_PRODUCTOS_ADMIN, null);
                ClienteAdmin conexion = new ClienteAdmin(peticion);
                DefaultTableModel dtm = (DefaultTableModel) conexion.getRespuestaServer();
                setTablaCatalogo(dtm);
            }
        });
    }

    /**
     *
     * @param tipoFiltro: Indica cual es el filtro elegido en el combobox segun el indice
     * @param tabla: La tabla que contiene el catalogo de productos
     */
    public void filtro(int tipoFiltro, JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) tabla.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(modelo);
        tabla.setRowSorter(trs);
        if (tipoFiltro == 1) {
            trs.setRowFilter(RowFilter.regexFilter("ENT", 0));
        } else if (tipoFiltro == 2) {
            trs.setRowFilter(RowFilter.regexFilter("PRN", 0));
        } else if (tipoFiltro == 3) {
            trs.setRowFilter(RowFilter.regexFilter("PTR", 0));
        } else if (tipoFiltro == 4) {
            trs.setRowFilter(RowFilter.regexFilter("BEB", 0));
        } else {
            trs.setRowFilter(RowFilter.regexFilter(".", 0)); // mostrar todo
        }
    }

    public void setTablaCatalogo(DefaultTableModel catalogo) {
        tablaPlatillos.setModel(catalogo);
    }
}
