package AppAdministrador.vista;

import javax.swing.*;

public class ProductosMasSolicitadosAdmin extends  JFrame{
    private JPanel panel;
    private JTable tablaProductosMasSolicitados;

    public ProductosMasSolicitadosAdmin() {
        super("Productos Mas Solicitados");
        setContentPane(panel);
        setResizable(false);
        this.pack();
    }
}
