package AppAdministrador.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Interfaz que muestra todos los pedidos
 */
public class ListaPedidosAdmin extends JFrame{
    private JPanel panel;
    private JTable tablaPedidos;

    public ListaPedidosAdmin() {
        super("Lista Pedidos");
        setContentPane(panel);
        this.pack();
    }

    public void setTablaPedidos(DefaultTableModel dtm) {
        tablaPedidos.setModel(dtm);
    }
}
