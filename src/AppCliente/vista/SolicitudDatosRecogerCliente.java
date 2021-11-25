package AppCliente.vista;

import AppAdministrador.conexion.ClienteAdmin;
import general.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Interfaz que solicita datos al cliente
 */
public class SolicitudDatosRecogerCliente extends JFrame {
    private JTextField textoCelular;
    private JTextField textoNombreRecoge;
    private JButton botonConfirmar;
    private JPanel panel;
    private ArrayList<Object> transferencia = new ArrayList<>();

    public SolicitudDatosRecogerCliente(Pedido pedidoCliente ) {
        super("Solicitud Datos");
        setContentPane(panel);
        setResizable(false);
        this.pack();
        this.transferencia=transferencia;

        botonConfirmar.addActionListener(new ActionListener() {//CONFIRMAR PEDIDO
            @Override
            public void actionPerformed(ActionEvent e) {
                //MANDAR A GUARDAR PEDIDO
                if (textoCelular.getText().trim().isEmpty() || textoNombreRecoge.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No puede dejar campos vacios");
                } else {
                    realizarPedidio();
                    JOptionPane.showMessageDialog(null, "Pedido Confirmado");
                    dispose();
                }
            }
            public void realizarPedidio(){
                ((PedidoRecoger)pedidoCliente).setCelular(textoCelular.getText());
                ((PedidoRecoger)pedidoCliente).setNombreRecoge(textoNombreRecoge.getText());
                Peticion peticionAgregarPlatillo = new Peticion(TipoAccion.REALIZAR_PEDIDO,pedidoCliente);
                ClienteAdmin conexion = new ClienteAdmin(peticionAgregarPlatillo);
                boolean respuestaServidor = (boolean) conexion.getRespuestaServer();
            }
        });
    }
}
