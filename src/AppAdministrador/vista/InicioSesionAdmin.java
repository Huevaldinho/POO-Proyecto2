package AppAdministrador.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioSesionAdmin extends JFrame {
    private JPasswordField campoContrasena;
    private JButton botonIniciarSesion;
    private JPanel panel;

    public InicioSesionAdmin() {
        super("Inicio De Sesion");
        setContentPane(panel);
        setResizable(false);
        this.pack();
        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new MenuAdministrador();
                ventana.setVisible(true);
            }
        });
    }
}
