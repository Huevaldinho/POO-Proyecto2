package AppAdministrador.vista;

import AppAdministrador.conexion.ClienteAdmin;
import general.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Interfaz para eliminar un platillo
 */
public class EliminarPlatilloAdmin extends JFrame {
    private JTable tablaPlatillos;
    private JButton btnEliminar;
    private JPanel panel;
    /*
    private JTextField txtNombrePlatillo;
    private JComboBox comboboxTipoPlatillo;
    private JTextField txtDescripcion;
    private JTextField txtPiezasXPorcion;
    private JTextField txtTamanno;
    private JTextField txtCaloriasXPorcion;
    private JTextField txtCaloriasXPieza;
    private JTextField txtPrecio;
    private String pathImagenSeleccionada;
    * */
    public EliminarPlatilloAdmin() {
        super("Eliminar Platillo");
        setContentPane(panel);
        setResizable(true);
        this.pack();
        btnEliminar.addActionListener(new ActionListener() {//Boton ELIMINAR
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tablaPlatillos.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(null,"Error al eliminar, debe seleccionar una platillo",
                            "Ha ocurrido un error, intente de nuevo",JOptionPane.ERROR_MESSAGE);
                }else{
                    int filaSeleccionada = tablaPlatillos.getSelectedRow();
                    Peticion peti =  new Peticion(TipoAccion.ELIMINAR_PLATILO,filaSeleccionada);
                    ClienteAdmin conexion = new ClienteAdmin(peti);
                    boolean respuestaServidor = (boolean) conexion.getRespuestaServer();
                    if (respuestaServidor){
                        JOptionPane.showMessageDialog(null,"Se elimino platillo nuevo al menu",
                                "Platillo eliminado!", JOptionPane.INFORMATION_MESSAGE);
                        peti = new Peticion(TipoAccion.VER_PRODUCTOS, null);
                        conexion = new ClienteAdmin(peti);
                        setTablaCatalogo((DefaultTableModel) conexion.getRespuestaServer());
                    }else{
                        JOptionPane.showMessageDialog(null,"NO se pudo eliminar el platillo al menu, intente de nuevo",
                                "Error al  eliminar platillo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }
    public void setTablaCatalogo(DefaultTableModel catalogo) {
        tablaPlatillos.setModel(catalogo);
    }
}
