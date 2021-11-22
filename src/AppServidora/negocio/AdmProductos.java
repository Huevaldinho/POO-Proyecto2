package AppServidora.negocio;
import general.Peticion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author ersolano
 */
public class AdmProductos{
    private AdmArchivosBinarios admArchivos = new AdmArchivosBinarios();
    AdmProductos (){}
    public  boolean buscarPlatillo(Peticion peti){
        //Abrir archivo binario y buscar a peti

        return admArchivos.buscarPlatillo(peti);
    }
    public boolean insertarNuevoPlatillo(Peticion peti){//Revisar archivos binarios

        //Busca si ya existe el platillo
        System.out.println("Se va a buscar peticion en archivos.");
        if (!buscarPlatillo(peti)){//Como no existe lo mete al archivo
            admArchivos.insertarPlatillo(peti);
            return true;
        }
        return false;
    }
    public boolean modificarPlatillo(){
        return  false;
    }

}
