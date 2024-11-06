import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivosInscripciones extends GestorDeArchivos{
    public ArchivosInscripciones(String direccionArchivo, GestorDeEventos gestor){
        super(direccionArchivo, gestor);
    }

    @Override
    protected void cargarEnMemoria(String[] datos, GestorDeEventos gestor) {
        gestor.agregarParticipante(datos[0], datos[1], true);
    }

    public void modificarArchivo(String nombreEventoAntiguo, String nombreEventoNuevo){
        String archivoModificado = "";
        try{
            FileReader lector = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(lector);
            String linea = lectura.readLine();
            while(linea!=null){
                String[] datos = linea.split(",");
                if(datos[0].equals(nombreEventoAntiguo)){
                    archivoModificado = archivoModificado + nombreEventoNuevo + "," + datos[1] + "\n";
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
}
