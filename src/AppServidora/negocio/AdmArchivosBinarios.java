package AppServidora.negocio;

import general.*;

import java.io.*;
import java.util.ArrayList;

public class AdmArchivosBinarios implements  Serializable{
    //Estos atributos se utilizan para manejar los archivos binarios
    private File archivo;
    //Escritura
    private FileOutputStream fos;
    private ObjectOutputStream oos;
    //Lectura
    private FileInputStream fis;
    private ObjectInputStream ois;

    public AdmArchivosBinarios() {
    }

    /**
     * Metodo para guardar el archivo binario que contiene el array list de los platillos
     * @param arrayListPlatillos: array de platillos en memoria
     */
    public void insertarPlatillo(ArrayList<Platillo> arrayListPlatillos){
        archivo = new File("ArchivosBinarios/Platos.dat");
        if (!archivo.exists()) {
            System.out.println("El archivo data no existe.");
        } else {
            archivo.delete();
            System.out.println("El archivo data fue eliminado.");
        }
        try{
            fos = new FileOutputStream(archivo,true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(arrayListPlatillos);//Guarda todoo el arrayList
            System.out.println("Guarda todo el array en el archivo binario");
            oos.close();
            fos.close();
        }catch (Exception e){
            System.out.println("Error en escribir en archivo binario...");
            e.printStackTrace();
        }
    }

    public ArrayList cargarArchivosPlatillos(){
        System.out.println("Carga de platillos en memoria");
        ArrayList<Platillo> platillos = new ArrayList(); // array para almacenar platillos
        try{
            fis = new FileInputStream("ArchivosBinarios/Platos.dat");
            if (fis.available()<=0){
                System.out.println("No hay platos para guardar");
                return platillos;
            }
            ois = new ObjectInputStream(fis);
            platillos = (ArrayList<Platillo>)ois.readObject();
            System.out.println(platillos);
            fis.close();
            ois.close();
        }catch (Exception e){
            System.out.println("Error cargando platos a memoria");
            e.printStackTrace();
        }
        return platillos;
    }
}
