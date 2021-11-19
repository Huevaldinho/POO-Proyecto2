package AppCliente.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolicitudDatosExpressCliente extends JFrame {
    private JTextField textoNombre;
    private JTextField textoCelular;
    private JButton botonConfirmar;
    private JTextArea textoDireccion;
    private JPanel panel;

    public SolicitudDatosExpressCliente() {
        super("Solicitud Datos");
        setContentPane(panel);
        setResizable(false);
        textoDireccion.setLineWrap(true);
        textoDireccion.setWrapStyleWord(true);
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
