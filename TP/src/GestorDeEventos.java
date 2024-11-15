import java.util.HashMap;

public class GestorDeEventos {
    private HashMap<String, Evento> listadoEventos;
    private HashMap<String, Persona> listadoPersonas;
    private HashMap<String, Recurso> listadoRecursos;
    private ArchivosEventos archivoEventos;
    private ArchivosUsuarios archivoUsuarios;
    private ArchivosInscripciones archivoInscripciones;
    private ArchivosRecursos archivoRecursos;
    private ArchivosRecursosXEventos archivoRecursoXEvento;

    public GestorDeEventos(){
        listadoEventos = new HashMap<>();
        listadoPersonas = new HashMap<>();
        listadoRecursos = new HashMap<>();
        archivoEventos = new ArchivosEventos("historialEventos.txt", this);
        archivoUsuarios = new ArchivosUsuarios("historialUsuarios.txt", this);
        archivoInscripciones = new ArchivosInscripciones("historialInscripciones.txt", this);
        archivoRecursos = new ArchivosRecursos("historialRecursos.txt", this);
        archivoRecursoXEvento = new ArchivosRecursosXEventos("historialResursosEventos.txt", this);
    }

    public void crearEvento(String nombreEvento, String fecha, String ubicacion, String descripcion, boolean vieneDelArchivo){
        Evento nuevoEvento = new Evento(nombreEvento, fecha, ubicacion, descripcion);
        listadoEventos.put(nombreEvento, nuevoEvento);
        if(!vieneDelArchivo){
            archivoEventos.escribirArchivo(nombreEvento +","+ fecha +","+ ubicacion +","+ descripcion);
        }
    }
    
    public void crearPersona(String nombrePersona, boolean vieneDelArchivo){
        Persona nuevaPersona = new Persona(nombrePersona);
        listadoPersonas.put(nombrePersona, nuevaPersona);
        if(!vieneDelArchivo){
            archivoUsuarios.escribirArchivo(nombrePersona);
        }
    }

    public void agregarParticipante(String nombreEvento, String nombrePersona, boolean vieneDelArchivo){
        Evento evento = listadoEventos.get(nombreEvento);
        evento.AgregarMiembro(listadoPersonas.get(nombrePersona));
        if(!vieneDelArchivo){
            archivoInscripciones.escribirArchivo(nombreEvento+","+nombrePersona);
            for(Recurso r: evento.getRecursos().values()){
                try{
                    SalonOCatering recursoActual = (SalonOCatering) r;
                    if(!recursoActual.capacidadApta(evento)){
                        this.eliminarRecurso(nombreEvento, r.getNombre());
                        System.out.println("Se elimino "+r.getNombre()+" ya que no puede suplir su capacidad");
                    }
                }finally{}
            }
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
            archivoInscripciones.modificarArchivo(nombreEvento, datosAModificar[0]);
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
        eventoAModificar.getNombreEvento()+","+eventoAModificar.getFecha()+","+eventoAModificar.getUbicacion()+","+eventoAModificar.getDescripcion());
    }

    public void agregarRecurso(Recurso recursoAAgregar){
        listadoRecursos.put(recursoAAgregar.getNombre(), recursoAAgregar);
    }

    public void agregarRecurso(String evento, String recurso, boolean vieneDelArchivo){
        if(listadoRecursos.get(recurso).agendarEvento(listadoEventos.get(evento))){
            listadoEventos.get(evento).AgregarRecurso(listadoRecursos.get(recurso));
            if(!vieneDelArchivo){
                archivoRecursoXEvento.escribirArchivo(recurso+","+evento);
            }
        }
    }

    public void eliminarRecurso(String evento, String recurso){
        listadoEventos.get(evento).QuitarRecurso(recurso);
        listadoRecursos.get(recurso).quitarFechaEnUso(listadoEventos.get(evento).getFecha());
        archivoRecursoXEvento.eliminarDeArchivo(recurso, evento);
    }

    public HashMap<String, Evento> getListadoEventos(){
        return listadoEventos;
    }

    public Evento getEvento(String nombreEvento){
        return listadoEventos.get(nombreEvento);
    }
    
    public HashMap<String, Persona> getListadoPersonas(){
        return listadoPersonas;
    }

    public HashMap<String, Recurso> getListadoRecursos(){
        return listadoRecursos;
    }
}
