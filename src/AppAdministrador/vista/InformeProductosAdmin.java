package AppAdministrador.vista;

import AppAdministrador.conexion.ClienteAdmin;
import general.Peticion;
import general.PieChart;
import general.TipoAccion;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfaz que contiene el menu de informes de los productos
 */
public class InformeProductosAdmin extends JFrame {
    private JButton botonProductosMasSolicitados;
    private JButton botonProductosNuncaSolicitados;
    private JButton botonRelacion;
    private JPanel panel;

    public InformeProductosAdmin() {
        super("Informe Productos");
        setContentPane(panel);
        setResizable(false);
        this.pack();

        botonProductosMasSolicitados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new ProductosMasSolicitadosAdmin();
                Peticion peti = new Peticion(TipoAccion.GENERAR_TABLA_TOP_TEN, null);
                ClienteAdmin conexion = new ClienteAdmin(peti);
                DefaultTableModel dtm = (DefaultTableModel) conexion.getRespuestaServer();
                ((ProductosMasSolicitadosAdmin)ventana).setTablaPedidos(dtm);
                ventana.setVisible(true);
            }
        });

        botonProductosNuncaSolicitados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new ProductosNuncaSolicitadosAdmin();
                Peticion peti = new Peticion(TipoAccion.GENERAR_TABLA_NUNCA_SOLICITADOS, null);
                ClienteAdmin conexion = new ClienteAdmin(peti);
                DefaultTableModel dtm = (DefaultTableModel) conexion.getRespuestaServer();
                ((ProductosNuncaSolicitadosAdmin)ventana).setTablaPedidos(dtm);
                ventana.setVisible(true);
            }
        });

        botonRelacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Peticion peti = new Peticion(TipoAccion.GENERAR_TABLA_NUNCA_SOLICITADOS, null);
                ClienteAdmin conexion = new ClienteAdmin(peti);
                PieChart pch = new PieChart("Relación entre lugares de pedido", (double[]) conexion.getRespuestaServer());
                pch.setSize( 560 , 367 );
                RefineryUtilities.centerFrameOnScreen(pch);
                pch.setVisible( true );
            }
        });
    }
}
