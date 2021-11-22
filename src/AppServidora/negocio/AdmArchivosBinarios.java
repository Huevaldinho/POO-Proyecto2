package AppServidora.negocio;

import general.*;

import java.io.*;
import java.util.ArrayList;

public class AdmArchivosBinarios {
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

    public ArrayList cargarArchivosPlatillos() {
        System.out.println("Carga de platillos en memoria");
        ArrayList<Platillo> platillos = new ArrayList(); // array para almacenar platillos
        try {
            System.out.println("llegue");
            archivo = new File("ArchivosBinarios/Entradas.dat");
            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);
            Platillo obj = (Platillo) ois.readObject();
            while (obj != null) {
                platillos.add(obj);
                obj = (Platillo) ois.readObject();
            }
            fis.close();
            ois.close();


            archivo = new File("ArchivosBinarios/PlatosFuertes.dat");
            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);
            obj = (Platillo) ois.readObject();
            while (obj != null) {
                platillos.add(obj);
                obj = (Platillo) ois.readObject();
            }
            fis.close();
            ois.close();

            archivo = new File("ArchivosBinarios/Postres.dat");
            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);
            obj = (Platillo) ois.readObject();
            while (obj != null) {
                platillos.add(obj);
                obj = (Platillo) ois.readObject();
            }
            fis.close();
            ois.close();

            archivo = new File("ArchivosBinarios/Bebidas.dat");
            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);
            obj = (Platillo) ois.readObject();
            while (obj != null) {
                platillos.add(obj);
                obj = (Platillo) ois.readObject();
            }
            fis.close();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return platillos;
    }
}
