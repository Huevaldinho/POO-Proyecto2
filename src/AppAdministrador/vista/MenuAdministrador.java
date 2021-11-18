package AppAdministrador.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdministrador extends JFrame {
    private JPanel panel;
    private JButton botonMostrarCatalogo;
    private JButton botonInformeProductos;
    private JButton botonListaPedidos;

    public MenuAdministrador() {
        super("Menu Administrador");
        setContentPane(panel);
        setResizable(false);
        this.pack();
        botonMostrarCatalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new MostrarPlatillos();
                ventana.setVisible(true);
            }
        });
    }
}
