package AppAdministrador.vista;

import javax.swing.*;

public class ListaPedidosAdmin extends JFrame{
    private JPanel panel;
    private JTable tablaPedidos;

    public ListaPedidosAdmin() {
        super("Lista Pedidos");
        setContentPane(panel);
        setResizable(false);
        this.pack();
    }
}
