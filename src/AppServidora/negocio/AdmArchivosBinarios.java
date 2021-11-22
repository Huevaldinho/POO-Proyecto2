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
    public void insertarPlatillo(Peticion peti){
        Platillo platillo = (Platillo) peti.getDatosEntrada(); // toma el objeto
        if (platillo instanceof Entrada) {
            archivo = new File("ArchivosBinarios/Entradas.dat");
        } else if (platillo instanceof PlatoFuerte) {
            archivo = new File("ArchivosBinarios/PlatosFuertes.dat");
        } else if (platillo instanceof Postre) {
            archivo = new File("ArchivosBinarios/Postres.dat");
        } else {
            archivo = new File("ArchivosBinarios/Bebidas.dat");
        }

        try{
            fos = new FileOutputStream(archivo,true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(platillo);
            oos.close();
            fos.close();
        }catch (Exception e){
            System.out.println("Error en escribir en archivo binario...");
            e.printStackTrace();
        }
    }
    public boolean buscarPlatillo(Peticion peti){
        System.out.println("BUSCAR PETICION EN ARCHIVO BINARIO");
        Platillo platillo = (Platillo) peti.getDatosEntrada();
        if (platillo instanceof Entrada) {
            archivo = new File("ArchivosBinarios/Entradas.dat");
        } else if (platillo instanceof PlatoFuerte) {
            archivo = new File("ArchivosBinarios/PlatosFuertes.dat");
        } else if (platillo instanceof Postre) {
            archivo = new File("ArchivosBinarios/Postres.dat");
        } else {
            archivo = new File("ArchivosBinarios/Bebidas.dat");
        }
        try{//AQUI HAY UN ERROR
            fis = new FileInputStream(archivo);//Si pasa bien por aca
            while (fis.available()>0){
                ois = new ObjectInputStream(fis);//ESTE ES EL ERROR
                System.out.println("Ya carga el ois");
                Platillo platilloAlmacenado = (Platillo) ois.readObject();
                boolean iguales = platillo.equals(platilloAlmacenado);
                if (iguales){
                    System.out.println("Plato repetido: " + platillo.toString());
                    return true;
                }
            }
        }catch (Exception e){
            System.out.println("Error en buscar en el archivo binario");
            e.printStackTrace();
            return true;
        }
        return  false;//True es que ya existe
    }


    public ArrayList cargarArchivosPlatillos(){
        System.out.println("Carga de platillos en memoria");
        ArrayList<Platillo> platillos = new ArrayList(); // array para almacenar platillos
        try {
            archivo = new File("ArchivosBinarios/Entradas.dat");
            fis = new FileInputStream(archivo);
            Platillo obj = null;
            if (fis.available()>0){
                ois = new ObjectInputStream(fis);
                while (fis.available()>0) {
                    obj = (Platillo) ois.readObject();//Este es el error, castea algo que ya esta casteado?
                    platillos.add(obj);
                    System.out.println("Platillo cargado: "+obj.toString());
                }
                fis.close();
                ois.close();
                System.out.println("\tCargo todas las entradas correctamente\n");
            }else
                System.out.println("Archivo Entradas no tiene nada");

            archivo = new File("ArchivosBinarios/PlatosFuertes.dat");
            fis = new FileInputStream(archivo);
            if (fis.available()>0){
                ois = new ObjectInputStream(fis);//Error
                System.out.println("ois: "+ois.available());

                while (fis.available()>0) {
                    System.out.println("Entra al while");
                    obj = (Platillo) ois.readObject();//Este es el error, castea algo que ya esta casteado?
                    System.out.println("caste el objeto dentro del while");
                    platillos.add(obj);
                    System.out.println("Platillo cargado: "+obj.toString());
                }
                fis.close();
                ois.close();
                System.out.println("\tCargo todos los Platos fuetes correctamente\n");
            }else{
                System.out.println("Archivo Platos fuertes no tiene nada");
            }


            archivo = new File("ArchivosBinarios/Postres.dat");
            fis = new FileInputStream(archivo);
            if (fis.available()>0){
                ois = new ObjectInputStream(fis);
                obj = (Platillo) ois.readObject();
                while (fis.available()>0) {
                    platillos.add(obj);
                    obj = (Platillo) ois.readObject();
                }
                fis.close();
                ois.close();
                System.out.println("\tCargo todos los Postres correctamente\n");

            }else
                System.out.println("Archivo Postres no tiene nada");

            archivo = new File("ArchivosBinarios/Bebidas.dat");
            fis = new FileInputStream(archivo);
            if (fis.available()>0){
                ois = new ObjectInputStream(fis);
                obj = (Platillo) ois.readObject();
                while (fis.available()>0) {
                    platillos.add(obj);
                    obj = (Platillo) ois.readObject();
                }
                fis.close();
                ois.close();
                System.out.println("\tCargo todas las Bebidas correctamente\n");
            }else
                System.out.println("Archivo Bebidas no tiene nada");

        } catch (FileNotFoundException e) {
            System.out.println("Error File not fund");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.out.println("Error ioexception"+e.toString());
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Error class not found");
            e.printStackTrace();
            return null;
        }
        return platillos;
    }
}
