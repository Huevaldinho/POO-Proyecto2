package AppAdministrador.vista;

import AppAdministrador.conexion.ClienteAdmin;
import general.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfaz para modificar un platillo
 */
public class ModificarPlatilloAdmin  extends JFrame {
    private JTable tablaPlatillos;
    private JPanel panel;
    private JButton btnModificar;

    public ModificarPlatilloAdmin(){
        super("Modificar Platillo");
        setContentPane(panel);
        setResizable(true);
        this.pack();
        btnModificar.addActionListener(new ActionListener() {//BOTON MODIFICAR
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tablaPlatillos.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(null,"Error al modificar, debe seleccionar una platillo",
                            "Ha ocurrido un error, intente de nuevo",JOptionPane.ERROR_MESSAGE);
                }else{
                    int filaSeleccionada = tablaPlatillos.getSelectedRow();//Selecciona la fila
                    //Se mete la fila seleccionada en el dato salida
                    //Se mete las modificaciones en  dato entrada y se reemplaza en admProductoss
                    Platillo modificado=null;
                    Bebida tmp = new Bebida();
                    tmp.setIDModificar(tablaPlatillos.getModel().getValueAt(filaSeleccionada,0).toString());
                    tmp.setNombreModificar(tablaPlatillos.getModel().getValueAt(filaSeleccionada,1).toString());
                    tmp.setDescripcionModificar(tablaPlatillos.getModel().getValueAt(filaSeleccionada,2).toString());
                    tmp.setTamannoPorcioModificar(tablaPlatillos.getModel().getValueAt(filaSeleccionada,3).toString());
                    tmp.setPiezaxPorcion(Integer.parseInt(tablaPlatillos.getModel().getValueAt(filaSeleccionada,4).toString()));
                    tmp.setCaloriasXPorcion(Integer.parseInt(tablaPlatillos.getModel().getValueAt(filaSeleccionada,5).toString().substring(0,
                            tablaPlatillos.getModel().getValueAt(filaSeleccionada,5).toString().length()-6)));
                    tmp.setCaloriasXPieza(Integer.parseInt(tablaPlatillos.getModel().getValueAt(filaSeleccionada,6).toString().substring(0,
                            tablaPlatillos.getModel().getValueAt(filaSeleccionada,5).toString().length()-6)));
                    tmp.setPrecioModificar(tablaPlatillos.getModel().getValueAt(filaSeleccionada,7).toString());
                    try {
                        tmp.setRutaModificar(tablaPlatillos.getModel().getValueAt(filaSeleccionada,8).toString());
                    }catch (Exception exp){
                        System.out.println("Error en ruta en modificar platillo");
                        exp.printStackTrace();
                        tmp.setRutaModificar("");
                    }
                    modificado=tmp;
                    Peticion peti =  new Peticion(TipoAccion.MODIFICAR_PLATILLO,modificado);
                    peti.setDatosSalida(filaSeleccionada);
                    ClienteAdmin conexion = new ClienteAdmin(peti);
                    boolean respuestaServidor = (boolean) conexion.getRespuestaServer();
                    if (respuestaServidor){
                        JOptionPane.showMessageDialog(null,"Se modifico platillo nuevo al menu",
                                "Platillo modicado!", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null,"NO se pudo modificar el platillo al menu, intente de nuevo",
                                "Error al  modificar platillo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            }
        });
    }

    public void setTablaCatalogo(DefaultTableModel catalogo) {
        tablaPlatillos.setModel(catalogo);
    }
}
