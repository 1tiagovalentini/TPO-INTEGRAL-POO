import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

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

        if(!datosAModificar[0].equals("X")){
            eventoAModificar.setNombreEvento(datosAModificar[0].toUpperCase());
            listadoEventos.remove(nombreEvento);
            listadoEventos.put(datosAModificar[0].toUpperCase(), eventoAModificar);
            archivoInscripciones.modificarArchivo(nombreEvento, datosAModificar[0]);
            archivoRecursoXEvento.modificarArchivo(nombreEvento, datosAModificar[0]);
        }

        if(!datosAModificar[1].equals("X")){
            for(Recurso r: eventoAModificar.getRecursos().values()){
                if(!r.editarFechaUsoEvento(eventoAModificar, datosAModificar[1])){
                    this.eliminarRecurso(nombreEvento, r.getNombre());
                    System.out.println("Se elimino "+r.getNombre()+" ya que el recurso esta reservado en la nueva fecha");
                }
            }
            eventoAModificar.setFecha(datosAModificar[1]);
        }

        if(!datosAModificar[2].equals("X")){
            eventoAModificar.setUbicacion(datosAModificar[2]);
        }

        if(!datosAModificar[3].equals("X")){
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

    public ArrayList<Evento> generarNotificaciones(String nombrePersona){
        ArrayList<Evento> eventosPersona = new ArrayList<>();
        Persona personaActual = listadoPersonas.get(nombrePersona);
        String fechaActual = LocalDate.now().toString();// obtengo fecha actual
        
        for(Evento e: listadoEventos.values()){
            if(e.getMiembros().contains(personaActual) && e.getFecha().compareTo(fechaActual) >= 0){
                eventosPersona.add(e);
            }
        }
        
        Collections.sort(eventosPersona);
        
        return eventosPersona;
    }

    private String mesATexto(int mes){
        String mesTexto = "";
        switch(mes){
            case 1:
                mesTexto = "Enero";
                break;
            case 2:
                mesTexto = "Febrero";
                break;
            case 3:
                mesTexto = "Marzo";
                break;
            case 4:
                mesTexto = "Abril";
                break;
            case 5:
                mesTexto = "Mayo";
                break;
            case 6:
                mesTexto = "Junio";
                break;
            case 7:
                mesTexto = "Julio";
                break;
            case 8:
                mesTexto = "Agosto";
                break;
            case 9:
                mesTexto = "Septiembre";
                break;
            case 10:
                mesTexto = "Octubre";
                break;
            case 11:
                mesTexto = "Nomviembre";
                break;
            case 12:
                mesTexto = "Diciembre";
                break;
        }
        return mesTexto;
    }

    private int cantDiasMes(int mes){
        int cantDias = 0;

        if (mes ==1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
            cantDias = 31;
        }else if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
            cantDias = 30;
        }else{
            cantDias = 28;
        }
        
        return cantDias;
    }

    public ArrayList<Evento> generarCalendario(){
        String calendario = "";        
        
        String[] fechaActual = LocalDate.now().toString().split("-");//obtener fecha actual partida en año, mes y dia
        String[] fechaEventoPartida;
        HashSet<Integer> diasDondeHayEvento = new HashSet<>();
        ArrayList<Evento> eventosDelMes = new ArrayList<>();

        for(Evento e: this.listadoEventos.values()){
            fechaEventoPartida = e.getFecha().split("-");
            if(fechaEventoPartida[0].equals(fechaActual[0]) && fechaEventoPartida[1].equals(fechaActual[1])){
                eventosDelMes.add(e);
                diasDondeHayEvento.add(Integer.parseInt(fechaEventoPartida[2]));
            }
        }

        Collections.sort(eventosDelMes);
        
        calendario = calendario + String.format("\u001B[37;41m%19s%-4s%19s\u001B[0m\n", "",fechaActual[0],"");
        calendario = calendario + String.format("\u001B[37;41m%16s%-10s%16s\u001B[0m\n", "",mesATexto(Integer.parseInt(fechaActual[1])),"");
        
        int dia = 1;
        int semana = 1;
        String semanaDias = "\u001B[30;47m";
        String semanaEventos = "\u001B[30;47m";
        while(dia <= cantDiasMes(Integer.parseInt(fechaActual[1]))){
            if(semana <= 7){
                semanaDias = semanaDias + String.format("%2s%2s%2s", "",dia,"");
                if(diasDondeHayEvento.contains(dia)){
                    semanaEventos = semanaEventos + String.format("%2s%2s%2s", "","**","");
                }else{
                    semanaEventos = semanaEventos + String.format("%6s","");
                }
                dia++;
                semana++;
            }else{
                semana = 1;
                calendario = calendario + semanaDias + "\u001B[0m\n";
                calendario = calendario + semanaEventos + "\u001B[0m\n";
                semanaDias = "\u001B[30;47m";
                semanaEventos = "\u001B[30;47m";
            }
        }
        /*Agrego la ultima semana que nunca que llega a cargar*/
        calendario = calendario + String.format("%-50s\u001B[0m\n",semanaDias);
        calendario = calendario + String.format("%-50s\u001B[0m\n",semanaEventos);
        System.out.println(calendario);

        return eventosDelMes;
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
