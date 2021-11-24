package AppCliente.vista;

import AppAdministrador.conexion.ClienteAdmin;
import general.Peticion;
import general.TipoAccion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SolicitudDatosRecogerCliente extends JFrame {
    private JTextField textoCelular;
    private JTextField textoNombreRecoge;
    private JButton botonConfirmar;
    private JPanel panel;
    private ArrayList<Object> transferencia = new ArrayList<>();

    public SolicitudDatosRecogerCliente(ArrayList<Object> transferencia ) {
        super("Solicitud Datos");
        setContentPane(panel);
        setResizable(false);
        this.pack();
        this.transferencia=transferencia;

        botonConfirmar.addActionListener(new ActionListener() {//CONFIRMAR PEDIDO
            @Override
            public void actionPerformed(ActionEvent e) {
                //MANDAR A GUARDAR PEDIDO
                realizarPedidio();
                JOptionPane.showMessageDialog(null, "Pedido Confirmado");
                dispose();
            }
            public void realizarPedidio(){
                System.out.println("MANDAR A GUARDAR PEDIDO"+transferencia.toString());
                Peticion peticionAgregarPlatillo = new Peticion(TipoAccion.REALIZAR_PEDIDO,transferencia);
                ClienteAdmin conexion = new ClienteAdmin(peticionAgregarPlatillo);
                boolean respuestaServidor = (boolean) conexion.getRespuestaServer();
            }
        });
    }
}
