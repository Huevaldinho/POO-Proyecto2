package AppAdministrador.vista;

import javax.swing.*;

public class ProductosNuncaSolicitadosAdmin extends JFrame {
    private JPanel panel;
    private JTable tablaProductosNuncaSolicitados;

    public ProductosNuncaSolicitadosAdmin() {
        super("Productos Nunca Solicitados");
        setContentPane(panel);
        setResizable(false);
        this.pack();
    }
}
