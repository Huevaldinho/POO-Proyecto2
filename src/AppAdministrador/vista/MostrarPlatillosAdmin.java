package AppAdministrador.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarPlatillosAdmin extends JFrame {
    private JPanel panel;
    private JComboBox comboboxFiltros;
    private JButton botonAgregarPlatillo;
    private JButton botonFiltrar;
    private JPanel panel1;
    private JTable tablaPlatillos;

    public MostrarPlatillosAdmin() {
        super("Platillos");
        setContentPane(panel);
        this.pack();
        botonAgregarPlatillo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new AgregarPlatilloAdmin();
                ventana.setVisible(true);
            }
        });
    }
}
