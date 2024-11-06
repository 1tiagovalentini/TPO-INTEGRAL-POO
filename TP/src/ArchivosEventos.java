import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivosEventos extends GestorDeArchivos{
    public ArchivosEventos(String direccionArchivo, GestorDeEventos gestor){
        super(direccionArchivo, gestor);
    }

    public void modificarArchivo(String idEvento, String datoAModificar){
        String archivoModificado = "";
        try{
            FileReader lector = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(lector);
            String linea = lectura.readLine();
            while(linea!=null){
                String[] datos = linea.split(",");
                if(datos[0].equals(idEvento)){
                    archivoModificado = archivoModificado + datoAModificar;
                }else{
                    archivoModificado = archivoModificado + linea;
                }
                linea = lectura.readLine();
            }
            lectura.close();

            FileWriter escritura = new FileWriter(archivo);
            escritura.write(archivoModificado);
            escritura.close();
            
        }catch(IOException excepcion){
            System.out.println(excepcion);
        }
    }

    @Override
    protected void cargarEnMemoria(String[] datos, GestorDeEventos gestor) {
        gestor.crearEvento(datos[0], datos[1], datos[2], datos[3], true);
    }
}
