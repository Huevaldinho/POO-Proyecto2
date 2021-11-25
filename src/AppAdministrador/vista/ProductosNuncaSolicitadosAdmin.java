package AppAdministrador.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Interfaz que muestra los productos nunca solicitados
 */
public class ProductosNuncaSolicitadosAdmin extends JFrame {
    private JPanel panel;
    private JTable tablaProductosNuncaSolicitados;

    public ProductosNuncaSolicitadosAdmin() {
        super("Productos Nunca Solicitados");
        setContentPane(panel);
        this.pack();
    }

    public void setTablaPedidos(DefaultTableModel dtm) {
        tablaProductosNuncaSolicitados.setModel(dtm);
    }
}
