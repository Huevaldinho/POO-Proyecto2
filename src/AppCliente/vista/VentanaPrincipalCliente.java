package AppCliente.vista;

import AppCliente.conexion.Client;
import general.Peticion;
import general.TipoAccion;

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
        botonFiltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filtro = comboboxFiltro.getSelectedIndex();
                if (filtro == 0) {
                    Peticion peticion = new Peticion(TipoAccion.VER_PRODUCTOS, filtro);
                    Client conexion = new Client(peticion);
                    DefaultTableModel tablaProductos = (DefaultTableModel) conexion.getRespuestaServer();
                    setTablaCatalogo(tablaProductos);
                } else {
                    Peticion peticion = new Peticion(TipoAccion.FILTRAR_PRODUCTOS, filtro);
                    Client conexion = new Client(peticion);
                    DefaultTableModel tablaFiltrada = (DefaultTableModel) conexion.getRespuestaServer();
                    setTablaCatalogo(tablaFiltrada);
                }
            }
        });
    }

    public void setTablaCatalogo(DefaultTableModel catalogo) {
        tablaCatalogo.setModel(catalogo);
    }
}
