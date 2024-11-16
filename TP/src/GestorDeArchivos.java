import java.io.*;

public abstract class GestorDeArchivos {
    protected File archivo;

    public GestorDeArchivos(String direccion, GestorDeEventos gestor){
        archivo = new File(direccion);
        if(archivo.exists()){
            this.leerArchivo(gestor);
        }else{
            try{
                archivo.createNewFile();
            }catch(IOException excepcion){
                System.out.println(excepcion);
            }
        }
    }

    public void escribirArchivo(String datosAEscribir){
        try{
            FileWriter escritura = new FileWriter(archivo,true);
            escritura.write(datosAEscribir+'\n');
            escritura.close();
        }catch(IOException excepcion){
            System.out.println(excepcion);
        }
    }

    protected abstract void cargarEnMemoria(String[] datos, GestorDeEventos gestor);

    public void leerArchivo(GestorDeEventos gestor){
        try{
            FileReader lector = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(lector);
            String linea = lectura.readLine();
            while(linea!=null){
                String[] datos = linea.split(",");
                this.cargarEnMemoria(datos, gestor);
                linea = lectura.readLine();
            }
            lectura.close();
        }catch(IOException excepcion){
            System.out.println(excepcion);
        }
    }
}
