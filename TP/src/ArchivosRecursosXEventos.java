import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivosRecursosXEventos extends GestorDeArchivos{
    public ArchivosRecursosXEventos(String direccionArchivo, GestorDeEventos gestor){
        super(direccionArchivo, gestor);
    }

    public void eliminarDeArchivo(String recursoAEliminar, String eventoAEliminar){
        String archivoModificado = "";
        try{
            FileReader lector = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(lector);
            String linea = lectura.readLine();
            while(linea!=null){
                String[] datos = linea.split(",");
                if(!(datos[1].equals(eventoAEliminar) && datos[0].equals(recursoAEliminar))){
                    archivoModificado = archivoModificado + linea + "\n";
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

    public void modificarArchivo(String idEvento, String datoAModificar){
        String archivoModificado = "";
        try{
            FileReader lector = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(lector);
            String linea = lectura.readLine();
            while(linea!=null){
                String[] datos = linea.split(",");
                if(datos[0].equals(idEvento)){
                    archivoModificado = archivoModificado + datoAModificar + "\n";
                }else{
                    archivoModificado = archivoModificado + linea + "\n";
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
        gestor.agregarRecurso(datos[1], datos[0], true);
    }

}
