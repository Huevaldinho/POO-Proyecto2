package AppAdministrador.vista;

import general.PieChart;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;

/**
 * Interfaz para mostrar la relacion entre tipos de pedidos
 */
public class RelacionEntreLugaresPedidosAdmin extends JFrame {
    private JPanel panel;
    public RelacionEntreLugaresPedidosAdmin() {
        super("Relacion Entre Lugares De Pedidos");
        setContentPane(panel);
        setResizable(false);
        this.pack();
    }
}
