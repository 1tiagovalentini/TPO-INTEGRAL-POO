import java.util.HashMap;

public class GestorDeEventos {
    private HashMap<String, Evento> listadoEventos;
    private ArchivosEventos archivoEventos;

    public GestorDeEventos(){
        listadoEventos = new HashMap<>();
        archivoEventos = new ArchivosEventos("historialEventos.txt", this);
    }

    public void crearEvento(String nombreEvento, String fecha, String ubicacion, String descripcion, boolean vieneDelArchivo){
        if(!listadoEventos.containsKey(nombreEvento)){
            Evento nuevoEvento = new Evento(nombreEvento, fecha, ubicacion, descripcion);
            listadoEventos.put(nombreEvento, nuevoEvento);
            if(!vieneDelArchivo){
                archivoEventos.escribirArchivo(nombreEvento +","+ fecha +","+ ubicacion +","+ descripcion);
            }
        }else{
            System.out.println("Error al cargar evento: el evento ya fue cargado anteriormente");
        }
        
    }

    public void editarEvento(String nombreEvento, int opcionModificacion, String datoAModificar){
        Evento eventoAModificar = listadoEventos.get(nombreEvento);
        switch(opcionModificacion){
            case 1:
                eventoAModificar.setNombreEvento(datoAModificar.toUpperCase());
                listadoEventos.remove(nombreEvento);
                listadoEventos.put(datoAModificar.toUpperCase(), eventoAModificar);
                break;
            case 2:
                eventoAModificar.setFecha(datoAModificar);
                break;
            case 3:
                eventoAModificar.setFecha(datoAModificar);
                break;
            case 4:
                eventoAModificar.setFecha(datoAModificar);
                break;
        } 
    }

    public HashMap<String, Evento> getListadoEventos(){
        return listadoEventos;
    }
}
