package AppCliente.vista;

import AppAdministrador.conexion.ClienteAdmin;
import general.Pedido;
import general.PedidoExpress;
import general.Peticion;
import general.TipoAccion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Interfaz que solicita datos al cliente
 */
public class SolicitudDatosExpressCliente extends JFrame {
    private JTextField textoCelular;
    private JButton botonConfirmar;
    private JTextArea textoDireccion;
    private JPanel panel;
    private ArrayList<Object> transferencia = new ArrayList<>();

    public SolicitudDatosExpressCliente(Pedido pedidoCliente ) {
        super("Solicitud Datos");
        setContentPane(panel);
        setResizable(false);
        textoDireccion.setLineWrap(true);
        textoDireccion.setWrapStyleWord(true);
        this.transferencia=transferencia;
        this.pack();

        botonConfirmar.addActionListener(new ActionListener() {//CONFIRMAR PEDIDO
            @Override
            public void actionPerformed(ActionEvent e) {
                //MANDAR A GUARDAR EL PEDIDO
                if (textoCelular.getText().trim().isEmpty() || textoDireccion.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No puede dejar campos vacios");
                } else {
                    realizarPedido();
                    JOptionPane.showMessageDialog(null, "Pedido Confirmado");
                    dispose();
                }
            }
            public void realizarPedido(){
                //GUARDA EL PEDIDO
                ((PedidoExpress)pedidoCliente).setCelular(textoCelular.getText());
                ((PedidoExpress)pedidoCliente).setDireccion(textoDireccion.getText());
                Peticion peticionAgregarPlatillo = new Peticion(TipoAccion.REALIZAR_PEDIDO,pedidoCliente);
                ClienteAdmin conexion = new ClienteAdmin(peticionAgregarPlatillo);
                boolean respuestaServidor = (boolean) conexion.getRespuestaServer();
            }
        });
    }
}
