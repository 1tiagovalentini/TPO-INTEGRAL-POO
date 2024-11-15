import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

// revisar posibles casos de modularizar el codigo en metodos dentro de gestor de eventos

public class App {

    public static void menu(){
        System.out.print("""
            Que desea realizar:
            1.Crear un nuevo evento\n
            2.Modificar un evento\n
            3.Ver eventos\n
            4.Ver registro de personas de cierto evento\n
            5.Inscribir una persona a un evento\n
            6.Gestionar recursos\n
            7.Ver calendario\n
            8.Notificaciones\n
            9.Cerrar sistema\n
            Ingrese numero de operacion a realizar: """); 
    }

    public static void crearEvento(Scanner input, GestorDeEventos2 eventos){
        System.out.println("Ingrese nombre del evento: ");   
        String nombreEvento = input.nextLine().toUpperCase();

        System.out.println("Ingrese fecha del evento en formato (AAAA/MM/DD): ");
        String fecha = input.nextLine();

        System.out.println("Ingrese ubicacion del evento: ");
        String ubicacion = input.nextLine();
        
        System.out.println("Ingrese descripcion del evento: ");
        String descripcion = input.nextLine();

        eventos.crearEvento(nombreEvento, fecha, ubicacion, descripcion);
    }

    public static void modificarEvento(Scanner input, GestorDeEventos2 eventos){
        Set<String> listaEventos = eventos.getListadoEventos().keySet();
        String eventoAModificar;
        
        System.out.println("Que evento quiere modificar? (ingrese nombre), X para no hacer nada");
        do{
            eventoAModificar = input.nextLine().toUpperCase();
        }while(!listaEventos.contains(eventoAModificar) || !(eventoAModificar.equals("X")));

        if (!eventoAModificar.equals("X")) {

            System.out.println("Ingrese nombre del evento, X para dejar el existente");   
            String nombreEvento = input.nextLine().toUpperCase();

            System.out.println("Ingrese fecha del evento en formato (AAAA/MM/DD), X para dejar el existente ");
            String fecha = input.nextLine();

            System.out.println("Ingrese ubicacion del evento, X para dejar el existente");
            String ubicacion = input.nextLine();
            
            System.out.println("Ingrese descripcion del evento, X para dejar el existente");
            String descripcion = input.nextLine();

            if (nombreEvento.equals("X")) {
                eventos.editarEvento(eventoAModificar,1, nombreEvento);
            } 
            if (fecha.equals("X")) {
                eventos.editarEvento(eventoAModificar,2, fecha);
            } 
            if (ubicacion.equals("X")) {
                eventos.editarEvento(eventoAModificar,3, ubicacion);
            }
            if (descripcion.equals("X")) {
                eventos.editarEvento(eventoAModificar,4, descripcion);
            }
        }
    }

    public static void verEventos(Scanner input, GestorDeEventos2 eventos){
        if (eventos.getListadoEventos().isEmpty()) {
            System.out.println("No hay eventos registrados");
        } else {
            for(Iterator<Evento> i = eventos.getListadoEventos().values().iterator();i.hasNext();){
                Evento evento = i.next();
                System.out.println("- '" + evento.getNombreEvento());
            }
            System.out.println("Que evento quiere ver a detalle? (ingrese nombre)");
            String eventoAMostrar;
            Set<String> listaEventos = eventos.getListadoEventos().keySet();
            do{
                eventoAMostrar = input.nextLine().toUpperCase();
            }while(!listaEventos.contains(eventoAMostrar));

            Evento evento = eventos.getEvento(eventoAMostrar);
            System.out.println("Nombre: " + evento.getNombreEvento() +
                            ", Fecha: " + evento.getFecha() +
                            ", Ubicación: " + evento.getUbicacion() +
                            ", Descripción: " + evento.getDescripcion());
        }
    }

    public static void verParticipantes(Scanner input, GestorDeEventos2 eventos){
        System.out.println("De que evento quiere ver los participantes? (ingrese nombre)");
        String eventoAMostrar;
        Set<String> listaEventos = eventos.getListadoEventos().keySet();
        do{
            eventoAMostrar = input.nextLine().toUpperCase();
        }while(!listaEventos.contains(eventoAMostrar));

        Evento evento = eventos.getEvento(eventoAMostrar);
        for(Iterator<Persona> i = evento.getMiembros().iterator();i.hasNext();){
            Persona participante = i.next();
            System.out.println("- '" + participante.getNombre());
        }
    }

    public static void agregarParticipantes(Scanner input, GestorDeEventos2 eventos){
        System.out.println("A que evento quiere agregar participantes? (ingrese nombre)");
        String eventoAModificar;
        Set<String> listaEventos = eventos.getListadoEventos().keySet();
        do{
            eventoAModificar = input.nextLine().toUpperCase();
        }while(!listaEventos.contains(eventoAModificar));

        System.out.println("Ingrese los participantes del evento (ingrese X para dejar de agregar): ");
        String nombre;
        do {
            nombre = input.nextLine();
            if (!nombre.equals("X")) {
                Persona participante = new Persona(nombre);
                eventos.getListadoEventos().get(eventoAModificar).AgregarMiembro(participante);
                eventos.agregarPersonaASistema(participante);
            }
        } while (!nombre.equals("X"));
    }

    public static void gestionarRecursos(Scanner input, GestorDeEventos2 eventos, Recurso salonRecurso, Recurso cateringRecurso, Recurso audiovisualRecurso){
        System.out.println("Sobre que evento quiere gestionar recursos? (ingrese nombre)");
        String eventoAModificar;
        Set<String> listaEventos = eventos.getListadoEventos().keySet();
        do{
            eventoAModificar = input.nextLine().toUpperCase();
        }while(!listaEventos.contains(eventoAModificar));

        System.out.println("Ingrese A para agregar un recurso, Q para quitar un recurso, X para salir");
        String respuesta;
        respuesta = input.nextLine().toUpperCase();

        if (respuesta.equals("A")) {
            System.out.println("Recursos: ");
            System.out.println("- 'Salon'");
            System.out.println("- 'Catering'");
            System.out.println("- 'Audiovisual'");
            System.out.println("Ingrese el tipo de recurso a agregar (Salon, Catering o Audiovisual) o X para no agregar: ");
            String tipo = input.nextLine();

            switch (tipo.toUpperCase()) {

                case "SALON":
                if (salonRecurso.isEstaEnUso(eventos.getListadoEventos().get(eventoAModificar).getFecha())) {
                    System.out.println("El salon ya esta en uso en esa fecha");
                }else{
                    eventos.getListadoEventos().get(eventoAModificar).AgregarRecurso(salonRecurso);
                    salonRecurso.agregarFechaEnUso(eventos.getListadoEventos().get(eventoAModificar).getFecha());
                    System.out.println("El salon ha sido asignado al evento");
                }
                    break;

                case "CATERING":
                if (cateringRecurso.isEstaEnUso(eventos.getListadoEventos().get(eventoAModificar).getFecha())) {
                    System.out.println("El catering ya esta en uso en esa fecha");
                }else{
                    eventos.getListadoEventos().get(eventoAModificar).AgregarRecurso(cateringRecurso);
                    cateringRecurso.agregarFechaEnUso(eventos.getListadoEventos().get(eventoAModificar).getFecha());
                    System.out.println("El catering ha sido asignado al evento");
                }
                    break;
                
                case "AUDIOVIDUAL":
                if (audiovisualRecurso.isEstaEnUso(eventos.getListadoEventos().get(eventoAModificar).getFecha())) {
                    System.out.println("El audiovisual ya esta en uso en esa fecha");
                }else{
                    eventos.getListadoEventos().get(eventoAModificar).AgregarRecurso(audiovisualRecurso);
                    audiovisualRecurso.agregarFechaEnUso(eventos.getListadoEventos().get(eventoAModificar).getFecha());
                    System.out.println("El audiovisual ha sido asignado al evento");
                }
                    break;
            
                default:
                    break;
            }
            
        } else if (respuesta.equals("Q")) {
            System.out.println("Recursos del evento: ");
        
            for(Iterator<Recurso> i = eventos.getListadoEventos().get(eventoAModificar).getRecursos().iterator();i.hasNext();){
                Recurso recurso = i.next();
                
                System.out.println("- '" + recurso.getTipo());
                System.out.println("Ingrese Q para quitar el recurso o X para continuar");
                String rtda;
                rtda = input.nextLine().toUpperCase();
                if (rtda.equals("Q")) {
                    eventos.getListadoEventos().get(eventoAModificar).QuitarRecurso(recurso);
                }
            }
        }
    }

    public static void calendario(Scanner input, GestorDeEventos2 eventos){
        System.out.println("""
            Que desea realizar:\n
            1.Ver calendario de la empresa\n
            2.Ver calendario de una persona en sistema\n
            Ingrese numero de operacion a realizar: """);
            int opcion;
            int i = 0;
            ArrayList<String> calendario = new ArrayList<String>();
            opcion = Integer.parseInt(input.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("Calendario de la empresa");
                    
                    for (Evento evento : eventos.getListadoEventos().values()) {
                        calendario.add(evento.getFecha() + " " + evento.getNombreEvento());
                        Collections.sort(calendario);
                        i++;
                    }
                    for (String fecha : calendario) {
                        System.out.println(fecha);
                    }
                
                    break;

                case 2:
                System.out.println("Calendario de que persona quiere ver? (ingrese numero)");
                    for (Persona persona  : eventos.getPersonasEnSistema()) {
                        System.out.println(i + "." + persona.getNombre());
                        i++;
                    }
                    i= Integer.parseInt(input.nextLine());
                    Persona persona = eventos.getPersonasEnSistema().get(i);
                    System.out.println("Calendario de " + persona.getNombre());
                    i = 0;
                    for (Evento evento : eventos.getListadoEventos().values()) {
                        
                        if (evento.getMiembros().contains(persona)) {
                            calendario.add(evento.getFecha() + " " + evento.getNombreEvento());
                            Collections.sort(calendario);
                            i++;
                        }
                    }

                    for (String fecha : calendario) {
                        System.out.println(fecha);
                    }

                    break;
            }
    }

    public static void notificaciones(Scanner input, GestorDeEventos2 eventos){
        System.out.println("""
            Que desea realizar:\n
            1.Mandar notificaciones a participantes de un evento\n
            2.Ver la bandeja de entrada de cierta persona\n
            Ingrese numero de operacion a realizar: """);
            int opcion;
            String respuesta;
            opcion = Integer.parseInt(input.nextLine());
            switch (opcion) {
                case 1:
                System.out.println("A paricipantes de que evento quiere mandar notificaciones? (ingrese nombre)");
                do{
                    respuesta = input.nextLine().toUpperCase();
                }while(!eventos.getListadoEventos().containsKey(respuesta));

                Evento eventoCandidato = eventos.getListadoEventos().get(respuesta);

                    for (Persona participante : eventoCandidato.getMiembros()) {

                        String notificacion = "Hola! " + participante.getNombre() + 
                        " recuerde que el dia " + eventoCandidato.getFecha() + " usted esta inscripto al evento " +
                        eventoCandidato.getNombreEvento();

                        participante.setNotificacion(notificacion); 
                    }
                    break;

                case 2:
                System.out.println("Ingrese el numero de la persona para ver su bandeja de entrada");
                int i = 0;
                    for (Persona persona  : eventos.getPersonasEnSistema()) {
                        System.out.println(i + "." + persona.getNombre());
                        i++;
                    }
                    i= Integer.parseInt(input.nextLine());
                    Persona persona = eventos.getPersonasEnSistema().get(i);
                    System.out.println("Notificaciones de " + persona.getNombre());

                    for (String notificacion : persona.getNotificacion()) {
                        
                        System.out.println(notificacion);
                    }

                    break;
                }// si se modifica la fecha del evento que el usauario (osea el que usa el sistema) vuelva a mandar las notificaciones correspondientes
    }
    
    public static void main(String[] args) throws Exception {

        //inicializamos objetos que utilizaremos
        GestorDeEventos2 eventos = new GestorDeEventos2();
        Recurso salonRecurso = new Recurso("Salon");
        Recurso cateringRecurso = new Recurso("Catering");
        Recurso audiovisualRecurso = new Recurso("Audiovisual");

        Scanner input = new Scanner(System.in);

        String opcion;

        do{
            menu(); 
            opcion = input.nextLine();
            System.out.println();

            // asi se ve mas limpio antes mareaba un poco
            // se puede usar cualquiera de las dos opciones

            switch(opcion){
                case "1":
                    crearEvento(input, eventos);
                break;

                case "2":
                    modificarEvento(input, eventos);
                break;
                
                case "3":
                    verEventos(input, eventos);
                break;

                case "4":
                    verParticipantes(input, eventos);
                break;

                case "5":
                    agregarParticipantes(input, eventos);
                break;

                case "6":
                    gestionarRecursos(input, eventos, salonRecurso, cateringRecurso, audiovisualRecurso);
                break;

                case "7":
                    calendario(input, eventos);
                break;

                case "8":
                    notificaciones(input, eventos);
                break;
                    
                case "9":
                    System.out.println("Gracias por visitar el sistema");
                break;

                default:
                    System.out.println("Opcion invalida");
                break;
            }
        }while(!opcion.equals("9"));
        input.close();

    }
}