import java.util.ArrayList;

public class GestorDeEventos {
    private ArrayList<Evento> listadoEventos;
    private PersistenciaDeEventos archivoEventos;

    public GestorDeEventos(){
        listadoEventos = new ArrayList<Evento>();
        archivoEventos = new PersistenciaDeEventos("historialEventos.txt", this);
    }

    public void crearEvento(String nombreEvento, String fecha, String ubicacion, String descripcion, boolean vieneDelArchivo){
        Evento nuevoEvento = new Evento(nombreEvento, fecha, ubicacion, descripcion);
        listadoEventos.add(nuevoEvento);
        if(!vieneDelArchivo){
            archivoEventos.escribirEnArchivo(nombreEvento +","+ fecha +","+ ubicacion +","+ descripcion);
        }
    }

    public ArrayList<Evento> getListadoEventos(){
        return listadoEventos;
    }
}
