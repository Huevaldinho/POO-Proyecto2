package AppCliente.vista;

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

    public ResumenCompraCliente(JComboBox tipoPedido, DefaultTableModel tm, ArrayList<Integer> cantidadPlatillos, Pedido pedidoCliente) {
        super("Resumen De Compra");
        setContentPane(panel);
        setTablaCarrito(tm, cantidadPlatillos);
        desglosePedido(pedidoCliente);
        this.pack();

        botonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int seleccion = tipoPedido.getSelectedIndex();
                if (seleccion == 0) {
                    JFrame ventana = new SolicitudDatosExpressCliente();
                    ventana.setVisible(true);
                } else if (seleccion == 1) {
                    JOptionPane.showMessageDialog(null, "Pedido Confirmado");
                    dispose();
                } else {
                    JFrame ventana = new SolicitudDatosRecogerCliente();
                    ventana.setVisible(true);
                }
            }
        });
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
            Peticion peticion = new Peticion(TipoAccion.DESGLOSE_PEDIDO, transferencia);
            Client conexion = new Client(peticion);
            double[] datos = (double[]) conexion.getRespuestaServer();
            etiquetaCostoTotal.setText("₡" + String.valueOf(datos[0]));
            etiquetaCostoPedido.setText("₡" + String.valueOf(datos[1]));
            etiquetaCaloriasTotales.setText(String.valueOf(datos[2] + " kcals"));
            etiquetaCostoAdicional.setText("₡" + String.valueOf(datos[3]));
        }
    }
}
