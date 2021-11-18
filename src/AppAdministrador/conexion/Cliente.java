package AppAdministrador.conexion;

import AppAdministrador.vista.InicioSesionAdmin;

import javax.swing.*;

public class Cliente {
    public Cliente(){
        // codigo
    }

    public static void main(String[] args) {
        JFrame ventanaPrincipal = new InicioSesionAdmin();
        ventanaPrincipal.setVisible(true);
    }
}
