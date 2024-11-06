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

    public void editarEvento(String nombreEvento, String[] datosAModificar){
        Evento eventoAModificar = listadoEventos.get(nombreEvento);
        /*
         * modificar nombre
         * modificar fecha
         * modificar ubicacion
         * descripcion
         */

        if(!datosAModificar[0].equals("-1")){
            eventoAModificar.setNombreEvento(datosAModificar[0].toUpperCase());
            listadoEventos.remove(nombreEvento);
            listadoEventos.put(datosAModificar[0].toUpperCase(), eventoAModificar);
        }

        if(!datosAModificar[1].equals("-1")){
            eventoAModificar.setFecha(datosAModificar[1]);
        }

        if(!datosAModificar[2].equals("-1")){
            eventoAModificar.setUbicacion(datosAModificar[2]);
        }

        if(!datosAModificar[3].equals("-1")){
            eventoAModificar.setUbicacion(datosAModificar[3]);
        }

        archivoEventos.modificarArchivo(nombreEvento,
        eventoAModificar.getNombreEvento()+","+eventoAModificar.getFecha()+","+eventoAModificar.getUbicacion()+","+eventoAModificar.getDescripcion()+"\n");
    }

    public HashMap<String, Evento> getListadoEventos(){
        return listadoEventos;
    }

    public Evento getEvento(String nombreEvento){
        return listadoEventos.get(nombreEvento);
    }
}
