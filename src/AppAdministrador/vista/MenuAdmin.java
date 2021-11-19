package AppAdministrador.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdmin extends JFrame {
    private JPanel panel;
    private JButton botonMostrarCatalogo;
    private JButton botonInformeProductos;
    private JButton botonListaPedidos;

    public MenuAdmin() {
        super("Menu Administrador");
        setContentPane(panel);
        setResizable(false);
        this.pack();
        botonMostrarCatalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new MostrarPlatillosAdmin();
                ventana.setVisible(true);
            }
        });
        botonInformeProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new InformeProductosAdmin();
                ventana.setVisible(true);
            }
        });
        botonListaPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new ListaPedidosAdmin();
                ventana.setVisible(true);
            }
        });
    }
}
