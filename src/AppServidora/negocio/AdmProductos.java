/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServidora.negocio;

import general.Peticion;

/**
 *
 * @author ersolano
 */
public class AdmProductos {
    AdmProductos (){}
    public  boolean buscarPlatillo(){
        return false;//RETURN OBJETO BINARIO (retorne la pos del platillo en el archivo o algo asi)
    }
    public boolean insertarNuevoPlatillo(Peticion peti){//Revisar archivos binarios
        //Toma el string que se le paso en la interfaz grafica (esta todoo pegado)
        String datosPeti = (String) peti.getDatosEntrada();
        //Separa las partes de la peticion
        String [] partesPeti  = datosPeti.split("-");
        //Buscar si ya existe ese platillo
        //buscarPlatillo()
        for (String i : partesPeti){//SI RECIBE BIE LA INFO
            System.out.println("Dato peticion para insertar: "+i);
        }
        return true;
    }
    public boolean modificarPlatillo(){
        return  false;
    }

}
