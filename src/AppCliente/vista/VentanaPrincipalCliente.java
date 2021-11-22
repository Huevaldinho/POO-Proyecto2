package AppCliente.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipalCliente extends JFrame {
    private JPanel panel;
    private JComboBox comboboxFiltro;
    private JButton botonFiltar;
    private JComboBox comboboxTipoPedido;
    private JTable tablaCatalogo;
    private JButton botonResumen;
    private JPanel panel1;
    private JPanel panel2;

    public VentanaPrincipalCliente() {
        super("Catalogo");
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        botonResumen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new ResumenCompraCliente(comboboxTipoPedido);
                ventana.setVisible(true);
            }
        });
    }

    public void setTablaCatalogo(DefaultTableModel catalogo) {
        tablaCatalogo.setModel(catalogo);
    }
}
