package AppCliente.vista;

import AppAdministrador.conexion.ClienteAdmin;
import general.Pedido;
import general.Peticion;
import general.TipoAccion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
                realizarPedido();
                JOptionPane.showMessageDialog(null, "Pedido Confirmado");
                dispose();
            }
            public void realizarPedido(){
                //GUARDA EL PEDIDO
                System.out.println("MANDAR A GUARDAR PEDIDO"+transferencia.toString());
                Peticion peticionAgregarPlatillo = new Peticion(TipoAccion.REALIZAR_PEDIDO,pedidoCliente);
                ClienteAdmin conexion = new ClienteAdmin(peticionAgregarPlatillo);
                boolean respuestaServidor = (boolean) conexion.getRespuestaServer();
            }
        });
    }
}
