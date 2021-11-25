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
    private ArrayList<Object> transferencia = new ArrayList<>();
    private ArrayList<Integer> cantidadesPlatillos = new ArrayList<>();

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
                    JFrame ventana = new SolicitudDatosExpressCliente(transferencia);
                    ventana.setVisible(true);
                } else if (seleccion == 1) {
                    realizarPedido(pedidoCliente );//MANDAR A GUARDAR PEDIDO
                    JOptionPane.showMessageDialog(null, "Pedido Confirmado");
                    dispose();
                } else {
                    JFrame ventana = new SolicitudDatosRecogerCliente(transferencia);
                    ventana.setVisible(true);
                }
            }
        });
    }

    public void realizarPedido(Pedido pedidoCliente){
        //GUARDA EL PEDIDO
        System.out.println("MANDAR A GUARDAR PEDIDO"+transferencia.toString());
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
            transferencia.add(idPlatillos);
            transferencia.add(pedidoCliente);
            transferencia.add(this.cantidadesPlatillos);

            Peticion peticion = new Peticion(TipoAccion.GUARDAR_PEDIDO, transferencia);
            Client conexion = new Client(peticion);
            pedidoCliente = (Pedido) conexion.getRespuestaServer();

            peticion = new Peticion(TipoAccion.DESGLOSE_PEDIDO, pedidoCliente);
            conexion = new Client(peticion);
            pedidoCliente = (Pedido) conexion.getRespuestaServer();

            etiquetaCostoTotal.setText("₡" + String.valueOf(pedidoCliente.getCostoTotal()));
            etiquetaCostoPedido.setText("₡" + String.valueOf(pedidoCliente.getCosto()));
            etiquetaCaloriasTotales.setText(String.valueOf(pedidoCliente.getTotalCalorias() + " kcals"));
            etiquetaCostoAdicional.setText("₡" + String.valueOf(pedidoCliente.getCostoAdicional()));
        }
    }
}
