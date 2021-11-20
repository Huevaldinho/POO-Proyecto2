package AppAdministrador.conexion;

import AppAdministrador.vista.InicioSesionAdmin;
import general.IConstantes;
import general.Peticion;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ClienteAdmin {
    private Peticion nuevaPeticion;

    public void setNuevaPeticion(Peticion nuevaPeticion) {
        this.nuevaPeticion = nuevaPeticion;
    }

    public Peticion getNuevaPeticion() {
        return nuevaPeticion;
    }

    public Object getRespuestaServer(){
        return nuevaPeticion.getDatosSalida();
    }
    public ClienteAdmin(){
        // codigo
    }

    public ClienteAdmin(Peticion laPeticion) {//USAR ESTE PORQUE YA TIENE LO DE ENVIAR OBJETOS

        nuevaPeticion = laPeticion;
        try {
            // establezco comunicacion con el servidor
            Socket skCliente = new Socket(IConstantes.HOST, IConstantes.PUERTO);

            // abrir el canal de envío del socket que va hacia el servidor
            OutputStream auxSalida = skCliente.getOutputStream();
            ObjectOutputStream flujoSalida = new ObjectOutputStream(auxSalida);

            // abrir el canal de recepcion del socket que viene desde el servidor
            InputStream auxEntrada = skCliente.getInputStream();
            ObjectInputStream flujoEntrada = new ObjectInputStream(auxEntrada);

            // envio al servidor
            flujoSalida.writeObject(nuevaPeticion);
            try {
                // recibiendo la respuesta del servidor
                nuevaPeticion = (Peticion) flujoEntrada.readObject();
                System.out.println("Datos salida servidor: "+nuevaPeticion.getDatosSalida());
            } catch (ClassNotFoundException ex) {
                System.out.println("problemas de casting");
            }
            //desconecto el socket
            skCliente.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame ventanaPrincipal = new InicioSesionAdmin();
        ventanaPrincipal.setVisible(true);
    }
}
