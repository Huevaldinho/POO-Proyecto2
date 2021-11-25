package AppAdministrador.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Interfaz que muestra los 10 productos mas solicitados
 */
public class ProductosMasSolicitadosAdmin extends  JFrame{
    private JPanel panel;
    private JTable tablaProductosMasSolicitados;

    public ProductosMasSolicitadosAdmin() {
        super("Productos Mas Solicitados");
        setContentPane(panel);
        this.pack();
    }

    public void setTablaPedidos(DefaultTableModel dtm) {
        tablaProductosMasSolicitados.setModel(dtm);
    }
}
