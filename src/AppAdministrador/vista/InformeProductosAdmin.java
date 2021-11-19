package AppAdministrador.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformeProductosAdmin extends JFrame {
    private JButton botonProductosMasSolicitados;
    private JButton botonProductosNuncaSolicitados;
    private JButton botonRelacion;
    private JPanel panel;

    public InformeProductosAdmin() {
        super("Informe Productos");
        setContentPane(panel);
        setResizable(false);
        this.pack();
        botonProductosMasSolicitados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new ProductosMasSolicitadosAdmin();
                ventana.setVisible(true);
            }
        });
        botonProductosNuncaSolicitados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new ProductosNuncaSolicitadosAdmin();
                ventana.setVisible(true);
            }
        });
        botonRelacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new RelacionEntreLugaresPedidosAdmin();
                ventana.setVisible(true);
            }
        });
    }
}
