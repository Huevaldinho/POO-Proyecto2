package AppAdministrador.vista;

import AppAdministrador.conexion.ClienteAdmin;
import AppCliente.conexion.Client;
import AppCliente.vista.VentanaPrincipalCliente;
import general.Peticion;
import general.TipoAccion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfaz del menu principal del administrador
 */
public class MenuAdmin extends JFrame {
    private JPanel panel;
    private JButton botonMostrarCatalogo;
    private JButton botonInformeProductos;
    private JButton botonListaPedidos;

    public MenuAdmin() {
        super("Menu Administrador");
        setContentPane(panel);
        setResizable(false);
        this.pack();

        botonMostrarCatalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new MostrarPlatillosAdmin();
                Peticion peticionMostrarProductos = new Peticion(TipoAccion.VER_PRODUCTOS_ADMIN, null);
                ClienteAdmin conexion = new ClienteAdmin(peticionMostrarProductos);
                DefaultTableModel tablaProductos = (DefaultTableModel) conexion.getRespuestaServer();
                ((MostrarPlatillosAdmin)ventana).setTablaCatalogo(tablaProductos); // casteo y seteo de tabla
                ventana.setVisible(true);
            }
        });

        botonInformeProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new InformeProductosAdmin();
                ventana.setVisible(true);
            }
        });

        botonListaPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new ListaPedidosAdmin();
                Peticion peti = new Peticion(TipoAccion.GENERAR_TABLA_PEDIDOS, null);
                ClienteAdmin conexion = new ClienteAdmin(peti);
                DefaultTableModel dtm = (DefaultTableModel) conexion.getRespuestaServer();
                ((ListaPedidosAdmin)ventana).setTablaPedidos(dtm);
                ventana.setVisible(true);
            }
        });
    }
}
