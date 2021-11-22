package AppAdministrador.vista;

import AppAdministrador.conexion.ClienteAdmin;
import general.Peticion;
import general.TipoAccion;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AgregarPlatilloAdmin extends JFrame {
    private JPanel panel;
    private JTextField txtNombrePlatillo;
    private JComboBox comboboxTipoPlatillo;
    private JTextField txtDescripcion;
    private JTextField txtPiezasXPorcion;
    private JTextField txtTamanno;
    private JTextField txtCaloriasXPorcion;
    private JTextField txtCaloriasXPieza;
    private JTextField txtPrecio;
    private String pathImagenSeleccionada;

    private JLabel lblPiezasXPorcion;
    private JLabel lblDescripcion;
    private JLabel lblTamano;
    private JLabel lblCaloriasXPorcion;
    private JLabel lblTipoDePlatillo;
    private JLabel lblNombrePlatillo;
    private JLabel lblCaloriasXPiezas;
    private JLabel lblPrecio;

    private JButton btnAceptar;
    private JButton btnBuscarImagen;
    private JLabel lblImagenPlato;

    public AgregarPlatilloAdmin() {
        super("Agregar Platillo");
        setContentPane(panel);
        setResizable(false);
        this.pack();
        btnAceptar.addActionListener(new ActionListener() {//BOTON ACEPTAR, CREAR PLATILLO
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarDatos()){
                    System.out.println("Datos correctos, enviar al servidor...");
                }else{
                    System.out.println("Error, algun campo esta malo");
                }
            }
        });
        btnBuscarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//BUSCAR IMAGEN EN DIRECTORIO
                JFileChooser browseImageFile = new JFileChooser();
                FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES",
                        "png","jpg","jpeg");
                browseImageFile.addChoosableFileFilter(fnef);

                int showOpenDialogue = browseImageFile.showOpenDialog(null);

                if (showOpenDialogue == JFileChooser.APPROVE_OPTION){

                    File selectedImageFile = browseImageFile.getSelectedFile();
                    String selectedImagePath = selectedImageFile.getAbsolutePath();
                    pathImagenSeleccionada = selectedImagePath;
                    //QUITAR JOPtionPane
                    JOptionPane.showMessageDialog(null,selectedImagePath);
                }

            }
        });
    }
    public boolean validarDatos(){
        try{//Solo se declaran para probobar el error
            int piezasPorPorcion = Integer.parseInt(txtPiezasXPorcion.getText());
            int caloriasPorPorcion = Integer.parseInt(txtCaloriasXPorcion.getText());
            int caloriasPorPieza = Integer.parseInt(txtCaloriasXPieza.getText());
            double precio = Double.parseDouble(txtPrecio.getText());
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Debe ingresar numeros",
                    "Debe ingresar datos correctos. Intente de nuevo.", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Orden Tipo Peticion, nombre, descripcion, tamanno, pieza por porcion, calorias por porcion,
        //calorias por pieza y precion (FALTA LA IMAGEN)
        //SI LLEGO AQUI ES PORQUE LOS DATOS SI ESTAN BIEn
        String textoPeticion = comboboxTipoPlatillo.getSelectedItem()+"-"+txtNombrePlatillo.getText()+"-"+
                txtDescripcion.getText()+"-"+txtTamanno.getText()+"-"+txtPiezasXPorcion.getText()+"-"+
                txtCaloriasXPorcion.getText()+"-"+ txtCaloriasXPieza.getText()+"-"+txtPrecio.getText()+
                "-"+pathImagenSeleccionada;
        System.out.println("DATOS INGRESADOS: "+textoPeticion);
        Peticion peticionAgregarPlatillo = new Peticion(TipoAccion.AGREGAR_PLATILLO,textoPeticion);
        ClienteAdmin conexion = new ClienteAdmin(peticionAgregarPlatillo);
        boolean respuestaServidor = (boolean) conexion.getRespuestaServer();
        if (respuestaServidor){
            JOptionPane.showMessageDialog(null,"Se agrego platillo nuevo al menu",
                    "Platillo agregado!", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(null,"NO se pudo agregar el platillo al menu, intente de nuevo",
                    "Error al agregar platillo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
}