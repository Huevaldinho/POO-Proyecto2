package AppCliente.vista;

import AppCliente.conexion.Client;
import general.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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
    private JTextField txtNombre;

    public VentanaPrincipalCliente() {
        super("Catalogo");
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        botonResumen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtNombre.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese su nombre");
                } else {
                    ArrayList<String> idPlatillos = new ArrayList();
                    ArrayList<Integer> cantidadSeleccionada = new ArrayList<>();
                    TableModel tm = tablaCatalogo.getModel();
                    boolean banderaError = false;
                    for (int i = 0; i < tm.getRowCount(); i++) {
                        String celda = (String) tm.getValueAt(i, 9);
                        int cantidad = Integer.parseInt(celda);
                        if (cantidad < 0) {
                            banderaError = true;
                            break;
                        } else if (cantidad > 0) {
                            String id = (String) tm.getValueAt(i, 0);
                            idPlatillos.add(id);
                            cantidadSeleccionada.add(cantidad);
                        }
                    }
                    if (banderaError) {
                        JOptionPane.showMessageDialog(null, "Cantidad erronea, corrija el error");
                    } else if (idPlatillos.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Agregue al menos un platillo");
                    } else {
                        if (comboboxTipoPedido.getSelectedIndex() == 0) {
                            PedidoExpress pedidoCliente = new PedidoExpress(txtNombre.getText());
                            Peticion peticion = new Peticion(TipoAccion.GENERAR_CARRITO, idPlatillos);
                            Client conexion = new Client(peticion);
                            JFrame ventana = new ResumenCompraCliente(comboboxTipoPedido,
                                    (DefaultTableModel) conexion.getRespuestaServer(), cantidadSeleccionada, pedidoCliente);
                            ventana.setVisible(true);
                        } else if (comboboxTipoPedido.getSelectedIndex() == 1) {
                            Pedido pedidoCliente = new Pedido(txtNombre.getText());
                            Peticion peticion = new Peticion(TipoAccion.GENERAR_CARRITO, idPlatillos);
                            Client conexion = new Client(peticion);
                            JFrame ventana = new ResumenCompraCliente(comboboxTipoPedido,
                                    (DefaultTableModel) conexion.getRespuestaServer(), cantidadSeleccionada, pedidoCliente);
                            ventana.setVisible(true);
                        } else {
                            PedidoRecoger pedidoCliente = new PedidoRecoger(txtNombre.getText());
                            Peticion peticion = new Peticion(TipoAccion.GENERAR_CARRITO, idPlatillos);
                            Client conexion = new Client(peticion);
                            JFrame ventana = new ResumenCompraCliente(comboboxTipoPedido,
                                    (DefaultTableModel) conexion.getRespuestaServer(), cantidadSeleccionada, pedidoCliente);
                            ventana.setVisible(true);
                        }

                    }
                }
            }
        });

        botonFiltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtro(comboboxFiltro.getSelectedIndex(), tablaCatalogo);
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
        tablaCatalogo.setDefaultRenderer(Object.class, new ImgTabla());
        tablaCatalogo.setModel(catalogo);
    }
}
