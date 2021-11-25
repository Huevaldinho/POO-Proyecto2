package AppCliente.vista;

import AppAdministrador.conexion.ClienteAdmin;
import AppCliente.conexion.Client;
import general.Pedido;
import general.Peticion;
import general.TipoAccion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Interfaz que muestra el carrito de compras
 */
public class ResumenCompraCliente extends JFrame {
    private JPanel panel;
    private JTable tablaResumenPedido;
    private JButton botonConfirmar;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel etiquetaCostoTotal;
    private JLabel etiquetaCostoPedido;
    private JLabel etiquetaCostoAdicional;
    private JLabel etiquetaCaloriasTotales;
    private JButton actualizarDesgloseButton;
    private ArrayList<Integer> cantidadesPlatillos = new ArrayList<>();
    private Pedido pedi;

    public ResumenCompraCliente(JComboBox tipoPedido, DefaultTableModel tm, ArrayList<Integer> cantidadPlatillos, Pedido pedidoCliente) {
        super("Resumen De Compra");
        setContentPane(panel);
        setTablaCarrito(tm, cantidadPlatillos);
        desglosePedido(pedidoCliente);
        this.cantidadesPlatillos=cantidadPlatillos;
        this.pack();

        botonConfirmar.addActionListener(new ActionListener() {//CONFIRMAR
            @Override
            public void actionPerformed(ActionEvent e) {
                int seleccion = tipoPedido.getSelectedIndex();
                if (seleccion == 0) {
                    JFrame ventana = new SolicitudDatosExpressCliente(pedi);
                    ventana.setVisible(true);
                } else if (seleccion == 1) {
                    realizarPedido(pedi );//MANDAR A GUARDAR PEDIDO
                    JOptionPane.showMessageDialog(null, "Pedido Confirmado");
                    dispose();
                } else {
                    JFrame ventana = new SolicitudDatosRecogerCliente(pedi);
                    ventana.setVisible(true);
                }
            }
        });

        actualizarDesgloseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pedi.resetearArrays();
                cantidadesPlatillos.clear();
                System.out.println("RESETEO");
                System.out.println(pedi);
                desglosePedido(pedi);
            }
        });
    }

    public void realizarPedido(Pedido pedidoCliente){
        //GUARDA EL PEDIDO
        Peticion peticionAgregarPlatillo = new Peticion(TipoAccion.REALIZAR_PEDIDO,pedidoCliente);
        ClienteAdmin conexion = new ClienteAdmin(peticionAgregarPlatillo);
        boolean respuestaServidor = (boolean) conexion.getRespuestaServer();
    }

    public void setTablaCarrito(DefaultTableModel tm, ArrayList<Integer> cantidadPlatillos) {
        for (int i = 0; i < tm.getRowCount(); i++) {
            tm.setValueAt(String.valueOf(cantidadPlatillos.get(i)), i, 9);
        }
        tablaResumenPedido.setModel(tm);
    }

    /**
     * Metodo para calcular el desglose del pedido
     * @param pedidoCliente:
     */
    public void desglosePedido(Pedido pedidoCliente) {
        ArrayList<String> idPlatillos = new ArrayList();
        TableModel tm = tablaResumenPedido.getModel();
        boolean banderaError = false;
        for (int i = 0; i < tm.getRowCount(); i++) {
            String celda = (String) tm.getValueAt(i, 9);
            if (celda.trim().isEmpty()) {
                celda = "0";
            }
            int cantidad = Integer.parseInt(celda);
            if (cantidad < 0) {
                banderaError = true;
                break;
            } else if (cantidad > 0) {
                String id = (String) tm.getValueAt(i, 0);
                idPlatillos.add(id);
                cantidadesPlatillos.add(cantidad);
            }
        }
        if (banderaError) {
            JOptionPane.showMessageDialog(null, "Cantidad erronea, corrija el error");
        } else if (idPlatillos.size() == 0) {
            JOptionPane.showMessageDialog(null, "Agregue al menos un platillo");
        } else {
            ArrayList<Object> transferencia = new ArrayList<>();
            transferencia.add(idPlatillos);
            transferencia.add(pedidoCliente);
            transferencia.add(cantidadesPlatillos);
            System.out.println("TRANSFERENCIA");
            System.out.println(transferencia);

            Peticion peticion = new Peticion(TipoAccion.GUARDAR_PEDIDO, transferencia);
            Client conexion = new Client(peticion);
            pedidoCliente = (Pedido) conexion.getRespuestaServer();

            System.out.println("GUARDAR PEDIDO");
            System.out.println(pedidoCliente);

            peticion = new Peticion(TipoAccion.DESGLOSE_PEDIDO, pedidoCliente);
            conexion = new Client(peticion);
            pedidoCliente = (Pedido) conexion.getRespuestaServer();

            etiquetaCostoTotal.setText("₡" + String.valueOf(pedidoCliente.getCostoTotal()));
            etiquetaCostoPedido.setText("₡" + String.valueOf(pedidoCliente.getCosto()));
            etiquetaCaloriasTotales.setText(String.valueOf(pedidoCliente.getTotalCalorias() + " kcals"));
            etiquetaCostoAdicional.setText("₡" + String.valueOf(pedidoCliente.getCostoAdicional()));

            pedi = pedidoCliente;
        }
    }
}
