package AppServidora.negocio;

import general.Peticion;

import java.io.*;

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
        //Toma el string que se le paso en la interfaz grafica (esta todoo pegado)
        String datosPeti = (String) peti.getDatosEntrada();
        //Separa las partes de la peticion
        String [] partesPeti  = datosPeti.split("-");
        switch (partesPeti[0]){//Si es entrada, bebida, postre
            case "Entrada":{
                archivo = new File("ArchivosBinarios/Entradas.dat");
                break;
            }
            case "Plato Fuerte":{
                archivo = new File("ArchivosBinarios/PlatosFuertes.dat");
                break;
            }
            case "Postre":{
                archivo = new File("ArchivosBinarios/Postres.dat");

                break;
            }
            case "Bebida":{
                archivo = new File("ArchivosBinarios/Bebidas.dat");
                break;
            }
            default:{
                 break;
            }
        }
        try{
            fos = new FileOutputStream(archivo,true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(peti);
            oos.close();
            fos.close();
        }catch (Exception e){
            System.out.println("Error en escribir en archivo binario...");
            e.printStackTrace();
        }
    }
    public boolean buscarPlatillo(Peticion peti){
        System.out.println("BUSCAR PETICION EN ARCHIVO BINARIO");
        String datosPeti = (String) peti.getDatosEntrada();
        //Separa las partes de la peticion
        String [] partesPeti  = datosPeti.split("-");
        String datosPetiArchivo;
        String [] partesPetiArchivo;
        switch (partesPeti[0]){//Si es entrada, bebida, postre
            case "Entrada":{
                archivo = new File("ArchivosBinarios/Entradas.dat");
                break;
            }
            case "Plato Fuerte":{
                archivo = new File("ArchivosBinarios/PlatosFuertes.dat");
                break;
            }
            case "Postre":{
                archivo = new File("ArchivosBinarios/Postres.dat");

                break;
            }
            case "Bebida":{
                archivo = new File("ArchivosBinarios/Bebidas.dat");
                break;
            }
            default:{
                break;
            }
        }
        try{//AQUI HAY UN ERROR
            fis = new FileInputStream(archivo);//Si pasa bien por aca
            while (fis.available()>0){
                ois = new ObjectInputStream(fis);//ESTE ES EL ERROR
                System.out.println("Ya carga el ois");
                Peticion laPeti = (Peticion) ois.readObject();
                datosPetiArchivo = (String) laPeti.getDatosEntrada();
                partesPetiArchivo = datosPetiArchivo.split("-");
                boolean iguales =partesPetiArchivo[0].equals(partesPeti[0]) && partesPetiArchivo[1].equals(partesPeti[1])&&
                        partesPetiArchivo[2].equals(partesPeti[2]) && partesPetiArchivo[3].equals(partesPeti[3]) &&
                        partesPetiArchivo[4].equals(partesPeti[4]) && partesPetiArchivo[5].equals(partesPeti[5]) &&
                        partesPetiArchivo[6].equals(partesPeti[6]) && partesPetiArchivo[7].equals(partesPeti[7]) &&
                        partesPetiArchivo[8].equals(partesPeti[8]);
                if (iguales){
                    System.out.println("Plato repetido: "+laPeti.toString()+peti.toString());
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
}
