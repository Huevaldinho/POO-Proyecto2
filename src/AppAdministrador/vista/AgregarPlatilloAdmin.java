package AppAdministrador.vista;

import javax.swing.*;

public class AgregarPlatilloAdmin extends JFrame {
    private JPanel panel;
    private JTextField textoNombrePlatillo;
    private JComboBox comboboxTipoPlatillo;

    public AgregarPlatilloAdmin() {
        super("Agregar Platillo");
        setContentPane(panel);
        setResizable(false);
        this.pack();
    }
}
