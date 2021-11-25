package AppAdministrador.vista;

import AppAdministrador.conexion.ClienteAdmin;
import AppCliente.conexion.Client;
import general.Peticion;
import general.TipoAccion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interfaz de inicio de sesion
 */
public class InicioSesionAdmin extends JFrame {
    private JPasswordField campoContrasena;
    private JButton botonIniciarSesion;
    private JPanel panel;

    public InicioSesionAdmin() {
        super("Inicio De Sesion");
        setContentPane(panel);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//BOTON ACEPTAR
                String myPass=String.valueOf(campoContrasena.getPassword());
                Peticion peticionIniciarSesion = new Peticion(TipoAccion.INGRESAR,myPass);
                ClienteAdmin conexion = new ClienteAdmin(peticionIniciarSesion);
                boolean respuestaServidor = (boolean) conexion.getRespuestaServer();
                if (respuestaServidor==false){
                    JOptionPane.showMessageDialog(null, "Ingrese el codigo correcto",
                            "Contrasena incorrecta", JOptionPane.ERROR_MESSAGE);
                }else {
                    JFrame ventana = new MenuAdmin();
                    ventana.setVisible(true);
                }
            }
        });
    }
}
