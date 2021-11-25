package AppServidora.negocio;

import general.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Clase especializada en manejar archivos binarios
 */
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

    /**
     * Metodo apra cargar platillos en la memoria
     * @return Retorna el array cargado en memoria
     */
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

    /**
     * Metodo para guardar el array de pedidos en el archivo binario
     * @param arrayListPedidos: array con los pedidos en memoria
     */
    public void insertarPedido(ArrayList<Pedido> arrayListPedidos){
        archivo = new File("ArchivosBinarios/Pedidos.dat");
        if (!archivo.exists()) {
            System.out.println("El archivo data no existe.");
        } else {
            archivo.delete();
            System.out.println("El archivo data fue eliminado.");
        }
        try{
            fos = new FileOutputStream(archivo,true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(arrayListPedidos);//Guarda todoo el arrayList
            System.out.println("Guarda todo el array en el archivo binario");
            oos.close();
            fos.close();
        }catch (Exception e){
            System.out.println("Error en escribir en archivo binario...");
            e.printStackTrace();
        }
    }

    /**
     * Metodo para cargar del disco a memoria el array con los pedidos
     * @return retorna el array en memoria
     */
    public ArrayList cargarArchivosPedidos(){
        System.out.println("Carga de pedidos en memoria");
        ArrayList<Pedido> pedidos = new ArrayList(); // array para almacenar platillos
        try{
            fis = new FileInputStream("ArchivosBinarios/Pedidos.dat");
            if (fis.available()<=0){
                System.out.println("No hay pedidos para guardar");
                return pedidos;
            }
            ois = new ObjectInputStream(fis);
            pedidos = (ArrayList<Pedido>)ois.readObject();
            System.out.println(pedidos);
            fis.close();
            ois.close();
        }catch (Exception e){
            System.out.println("Error cargando pedidos a memoria");
            e.printStackTrace();
        }
        return pedidos;
    }
}
