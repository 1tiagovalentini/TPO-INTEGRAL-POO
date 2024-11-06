package VersionNico;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

// revisar posibles casos de modularizar el codigo en metodos dentro de gestor de eventos

public class App {

    public static void menu(){
        System.out.println("Que desea realizar:\n");
        System.out.println("1.Crear un nuevo evento\n");
        System.out.println("2.Modificar un evento\n");
        System.out.println("3.Ver eventos\n");
        System.out.println("4.Ver registro de personas de cierto evento\n");
        System.out.println("5.Inscribir una persona a un evento\n");
        System.out.println("6.Gestionar recursos\n");
        System.out.println("7.Ver calendario\n");
        System.out.println("8.Cerrar sistema\n");
        System.out.print("Ingrese numero de operacion a realizar: ");
    }

    public static void crearEvento(Scanner input, GestorDeEventos eventos){
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

    public static void modificarEvento(Scanner input, GestorDeEventos eventos){
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

            if (nombreEvento != "-1") {
                eventos.editarEvento(eventoAModificar,1, nombreEvento);
            } 
            if (fecha != "-1") {
                eventos.editarEvento(eventoAModificar,2, fecha);
            } 
            if (ubicacion != "-1") {
                eventos.editarEvento(eventoAModificar,3, ubicacion);
            }
            if (descripcion != "-1") {
                eventos.editarEvento(eventoAModificar,4, descripcion);
            }
        }
    }

    public static void verEventos(Scanner input, GestorDeEventos eventos){
        if (eventos.getListadoEventos().isEmpty()) {
            System.out.println("No hay eventos registrados");
        } else {
            for(Iterator<Evento> i = eventos.getListadoEventos().values().iterator();i.hasNext();){
                Evento evento = i.next();
                System.out.println("- '" + evento.getNombreEvento());
            }
            System.out.println("Que evento quiere ver a detalle? (ingrese nombre)");
            String eventoAModificar;
            Set<String> listaEventos = eventos.getListadoEventos().keySet();
            do{
                eventoAModificar = input.nextLine().toUpperCase();
            }while(!listaEventos.contains(eventoAModificar));

            Evento evento = eventos.getEvento(eventoAModificar);
            System.out.println("Nombre: " + evento.getNombreEvento() +
                            ", Fecha: " + evento.getFecha() +
                            ", Ubicación: " + evento.getUbicacion() +
                            ", Descripción: " + evento.getDescripcion());
        }
    }

    public static void verParticipantes(Scanner input, GestorDeEventos eventos){
        System.out.println("De que evento quiere ver los participantes? (ingrese nombre)");
        String eventoAModificar;
        Set<String> listaEventos = eventos.getListadoEventos().keySet();
        do{
            eventoAModificar = input.nextLine().toUpperCase();
        }while(!listaEventos.contains(eventoAModificar));

        Evento evento = eventos.getEvento(eventoAModificar);
        for(Iterator<Persona> i = evento.getMiembros().iterator();i.hasNext();){
            Persona participante = i.next();
            System.out.println("- '" + participante.getNombre());
        }
    }

    public static void agregarParticipantes(Scanner input, GestorDeEventos eventos){
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
                Persona participante = new Persona(nombre, "feedback");
                eventos.getListadoEventos().get(eventoAModificar).AgregarMiembro(participante);
            }
        } while (!nombre.equals("X"));
    }

    public static void gestionarRecursos(Scanner input, GestorDeEventos eventos, Recurso salonRecurso, Recurso cateringRecurso, Recurso audiovisualRecurso){
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


    public static void main(String[] args) throws Exception {

        //inicializamos objetos que utilizaremos
        GestorDeEventos eventos = new GestorDeEventos();
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
                    
                case "8":
                    System.out.println("Gracias por visitar el sistema");
                break;

                default:
                    System.out.println("Opcion invalida");
                break;
            }
        }while(!opcion.equals("8"));
        input.close();

    }
}