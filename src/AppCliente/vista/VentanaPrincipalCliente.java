package AppCliente.vista;

import AppCliente.conexion.Client;
import general.Peticion;
import general.TipoAccion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaPrincipalCliente extends JFrame {
    private JPanel panel;
    private JComboBox comboboxFiltro;
    private JButton botonFiltar;
    private JComboBox comboboxTipoPedido;
    private JTable tablaCatalogo;
    private TableRowSorter<TableModel> modeloOrdenado;
    private JButton botonResumen;
    private JPanel panel1;
    private JPanel panel2;

    public VentanaPrincipalCliente() {
        super("Catalogo");
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        botonResumen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new ResumenCompraCliente(comboboxTipoPedido);
                ventana.setVisible(true);
            }
        });
        botonFiltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtro(comboboxFiltro.getSelectedIndex(), tablaCatalogo);
                /*
                int filtro = comboboxFiltro.getSelectedIndex();
                if (filtro == 0) {
                    Peticion peticion = new Peticion(TipoAccion.VER_PRODUCTOS, filtro);
                    Client conexion = new Client(peticion);
                    DefaultTableModel tablaProductos = (DefaultTableModel) conexion.getRespuestaServer();
                    setTablaCatalogo(tablaProductos);
                    tablaCatalogo.setRowFilter();
                } else {
                    Peticion peticion = new Peticion(TipoAccion.FILTRAR_PRODUCTOS, filtro);
                    Client conexion = new Client(peticion);
                    DefaultTableModel tablaFiltrada = (DefaultTableModel) conexion.getRespuestaServer();
                    setTablaCatalogo(tablaFiltrada);
                }
                filtroTabla.setRowFilter(RowFilter.regexFilter("ENT", 1));
                */

            }
        });
        botonResumen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableModel modelo = tablaCatalogo.getModel();
                
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
        tablaCatalogo.setModel(catalogo);
    }
}
