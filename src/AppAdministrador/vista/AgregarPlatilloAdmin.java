package AppAdministrador.vista;

import javax.swing.*;

public class AgregarPlatilloAdmin extends JFrame {
    private JPanel panel;
    private JTextField txtNombrePlatillo;
    private JComboBox comboboxTipoPlatillo;
    private JTextField txtDescripcion;
    private JLabel lblDescripcion;
    private JLabel lblTamano;
    private JTextField txtTamanno;
    private JLabel lblPiezasXPorcion;
    private JTextField txtPiezasXPorcion;
    private JLabel lblCaloriasXPorcion;
    private JTextField txtCaloriasXPorcion;
    private JLabel lblTipoDePlatillo;
    private JLabel lblNombrePlatillo;
    private JLabel lblCaloriasXPiezas;
    private JTextField txtCaloriasXPieza;
    private JLabel lblPrecio;
    private JTextField txtPrecio;
    private JLabel lblImagen;
    private JLabel txtInsertarImagen;
    private JButton btnAceptar;

    public AgregarPlatilloAdmin() {
        super("Agregar Platillo");
        setContentPane(panel);
        setResizable(false);
        this.pack();
    }
}
