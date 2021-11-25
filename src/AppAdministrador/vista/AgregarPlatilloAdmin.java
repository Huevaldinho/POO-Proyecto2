package AppAdministrador.vista;

import AppAdministrador.conexion.ClienteAdmin;
import general.*;

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
    private JComboBox comboboxMedida;

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
                    String selectedImagePath = selectedImageFile.getPath();
                    String[] trozos = selectedImagePath.split("\\\\");
                    String busqueda = "POO-Proyecto2";
                    String relativePath = "";
                    boolean bandera = false;
                    for (int i = 0; i < trozos.length; i++) {
                        String temporal = trozos[i];
                        if (temporal.equals(busqueda) || bandera) {
                            bandera = true;
                            if (i == trozos.length - 1) {
                                relativePath += temporal;
                            } else {
                                relativePath += temporal + "\\";
                            }
                        }
                    }
                    pathImagenSeleccionada = relativePath;
                    System.out.println(pathImagenSeleccionada);
                    //QUITAR JOPtionPane
                    JOptionPane.showMessageDialog(null,pathImagenSeleccionada);
                }

            }
        });
    }

    public boolean validarDatos(){
        int piezasPorPorcion;
        int caloriasPorPorcion;
        int caloriasPorPieza;
        double precio;
        try{//Solo se declaran para probobar el error
            piezasPorPorcion = Integer.parseInt(txtPiezasXPorcion.getText());
            caloriasPorPorcion = Integer.parseInt(txtCaloriasXPorcion.getText());
            caloriasPorPieza = Integer.parseInt(txtCaloriasXPieza.getText());
            precio = Double.parseDouble(txtPrecio.getText());
            double tamannoPorcion = Double.parseDouble(txtTamanno.getText());
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Debe ingresar numeros",
                    "Debe ingresar datos correctos. Intente de nuevo.", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int tipoPlatillo = comboboxTipoPlatillo.getSelectedIndex();
        Platillo nuevoPlatillo;
        switch (tipoPlatillo) { // switch para crear objeto correspondiente
            case 0: {
                Entrada platillo = new Entrada(txtNombrePlatillo.getText(), this.pathImagenSeleccionada, precio,
                        txtDescripcion.getText(),
                        txtTamanno.getText() + " " + comboboxMedida.getSelectedItem(), piezasPorPorcion,
                        caloriasPorPorcion, caloriasPorPieza);
                nuevoPlatillo = platillo;
                break;
            }
            case 1: {
                PlatoFuerte platillo = new PlatoFuerte(txtNombrePlatillo.getText(), this.pathImagenSeleccionada, precio,
                        txtDescripcion.getText(),
                        txtTamanno.getText() + " " + comboboxMedida.getSelectedItem(), piezasPorPorcion,
                        caloriasPorPorcion, caloriasPorPieza);
                nuevoPlatillo = platillo;
                break;
            }
            case 2: {
                Postre platillo = new Postre(txtNombrePlatillo.getText(), this.pathImagenSeleccionada, precio,
                        txtDescripcion.getText(),
                        txtTamanno.getText() + " " + comboboxMedida.getSelectedItem(), piezasPorPorcion,
                        caloriasPorPorcion, caloriasPorPieza);
                nuevoPlatillo = platillo;
                break;
            }
            case 3: {
                Bebida platillo = new Bebida(txtNombrePlatillo.getText(), this.pathImagenSeleccionada, precio,
                        txtDescripcion.getText(),
                        txtTamanno.getText() + " " + comboboxMedida.getSelectedItem(), piezasPorPorcion,
                        caloriasPorPorcion, caloriasPorPieza);
                nuevoPlatillo = platillo;
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + tipoPlatillo);
        }
        Peticion peticionAgregarPlatillo = new Peticion(TipoAccion.AGREGAR_PLATILLO,nuevoPlatillo);
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