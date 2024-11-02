import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaDeEventos{
    private File archivo;

    public PersistenciaDeEventos(String direccionArchivo, GestorDeEventos gestor){
        archivo = new File(direccionArchivo);
        if(archivo.exists()){
            this.cargarArchivoEnMemoria(gestor);
        }else{
            try{
                archivo.createNewFile();
            }catch(IOException exception){
                System.out.println(exception);
            }
        }
    }

    public void escribirEnArchivo(String datosAEscribir) {
        try{
            FileWriter escritura = new FileWriter(archivo, true);;
            escritura.write(datosAEscribir+"\n");
            escritura.close();
        }catch(IOException exception){
            System.out.println(exception);
        }
        
    }

    public void cargarArchivoEnMemoria(GestorDeEventos gestor) {
        try{
            FileReader lector = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(lector);
            String linea = lectura.readLine();
            while (linea != null){
                String[] datos = linea.split(",");
                gestor.crearEvento(datos[0], datos[1], datos[2], datos[3], true);
                linea = lectura.readLine();
            }
            lectura.close();
        }catch(IOException exception){
            System.out.println(exception);
        }
    }

}
