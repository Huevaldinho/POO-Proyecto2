package AppCliente.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResumenCompraCliente extends JFrame {
    private JPanel panel;
    private JTable tablaResumenPedido;
    private JButton botonConfirmar;
    private JPanel panel1;
    private JPanel panel2;

    public ResumenCompraCliente(JComboBox tipoPedido) {
        super("Resumen De Compra");
        setContentPane(panel);
        setResizable(false);
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
}
