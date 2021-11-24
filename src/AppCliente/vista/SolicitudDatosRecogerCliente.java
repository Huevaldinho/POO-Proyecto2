package AppCliente.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolicitudDatosRecogerCliente extends JFrame {
    private JTextField textoCelular;
    private JTextField textoNombreRecoge;
    private JButton botonConfirmar;
    private JPanel panel;

    public SolicitudDatosRecogerCliente() {
        super("Solicitud Datos");
        setContentPane(panel);
        setResizable(false);
        this.pack();

        botonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Pedido Confirmado");
                dispose();
            }
        });
    }
}
