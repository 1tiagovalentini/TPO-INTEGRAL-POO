import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

// revisar posibles casos de modularizar el codigo en metodos dentro de gestor de eventos

public class App {
    public static void main(String[] args) throws Exception {
        GestorDeEventos eventos = new GestorDeEventos();
        ArrayList<Persona> personasEnSistema = new ArrayList<>();

        Recurso salonRecurso = new Recurso("Salon");
        Recurso cateringRecurso = new Recurso("Catering");
        Recurso audiovisualRecurso = new Recurso("Audiovisual");

        Scanner input = new Scanner(System.in);

        int opcion;
        String nombreEvento;
        String fecha;
        String ubicacion;
        String descripcion;
        String respuesta;
        String eventoAModificar;
        do{
            System.out.print("""
            Que desea realizar:
            1.Crear un nuevo evento
            2.Modificar un evento
            3.Ver eventos
            4.Ver registro de personas de cierto evento
            5.Inscribir una persona a un evento
            6.Gestionar recursos
            7.Ver calendario
            8.Notificaciones
            9.Agregar usuario del sistema
            69.Cerrar sistema\n
            Ingrese numero de operacion a realizar: """); 
            opcion = Integer.parseInt(input.nextLine());
            System.err.println();

            switch(opcion){
                case 1:
                    System.out.println("Ingrese nombre del evento: ");   
                    nombreEvento = input.nextLine().toUpperCase();

                    System.out.println("Ingrese fecha del evento en formato (AAAA/MM/DD): ");
                    fecha = input.nextLine();

                    System.out.println("Ingrese ubicacion del evento: ");
                    ubicacion = input.nextLine();
                    
                    System.out.println("Ingrese descripcion del evento: ");
                    descripcion = input.nextLine();

                    eventos.crearEvento(nombreEvento, fecha, ubicacion, descripcion, false);
                    break;

                case 2:
                    Set<String> listaEventos = eventos.getListadoEventos().keySet();
                    
                    System.out.println("Que evento quiere modificar? (ingrese nombre), X para no hacer nada");
                    do{
                        eventoAModificar = input.nextLine().toUpperCase();
                    }while(!listaEventos.contains(eventoAModificar) && !(eventoAModificar.equals("X")));

                    if (!eventoAModificar.equals("X")) {
                        System.out.println("Ingrese nombre del evento, X para dejar el existente");   
                        nombreEvento = input.nextLine().toUpperCase();

                        System.out.println("Ingrese fecha del evento en formato (AAAA/MM/DD), X para dejar el existente ");
                        fecha = input.nextLine();

                        System.out.println("Ingrese ubicacion del evento, X para dejar el existente");
                        ubicacion = input.nextLine();
                        
                        System.out.println("Ingrese descripcion del evento, X para dejar el existente");
                        descripcion = input.nextLine();

                        String[] datosAModificar = {nombreEvento, fecha, ubicacion, descripcion};

                        eventos.editarEvento(eventoAModificar, datosAModificar);
                    }
                    break;
                case 3:
                    if (eventos.getListadoEventos().isEmpty()) {
                        System.out.println("No hay eventos registrados");
                    } else {
                        for(Iterator<Evento> i = eventos.getListadoEventos().values().iterator();i.hasNext();){
                            Evento evento = i.next();
                            System.out.println("- '" + evento.getNombreEvento() + "' [" + evento.getFecha()+
                            "] en " + evento.getUbicacion() +": " + evento.getDescripcion());
                        }
                    }
                    System.out.println();
                    break;                
                case 4:
                        System.out.println("De que evento quiere ver los participantes? (ingrese nombre)");
                        do{
                           respuesta = input.nextLine().toUpperCase();
                        }while(!eventos.getListadoEventos().containsKey(respuesta));

                        Evento evento = eventos.getEvento(respuesta);
                        for(Iterator<Persona> i = evento.getMiembros().iterator();i.hasNext();){
                            Persona participante = i.next();
                            System.out.println("- '" + participante.getNombre());
                        }
                break;
                case 5:
                    System.out.println("A que evento quiere agregar participantes? (ingrese nombre)");
                    do{
                        respuesta = input.nextLine().toUpperCase();
                    }while(!eventos.getListadoEventos().containsKey(respuesta));

                    System.out.println("Ingrese los participantes del evento (ingrese X para dejar de agregar): ");
                    String nombre;
                    do {
                        nombre = input.nextLine();
                        if (!nombre.equals("X")) {
                            Persona participante = new Persona(nombre);
                            eventos.getListadoEventos().get(respuesta).AgregarMiembro(participante);
                            personasEnSistema.add(participante);
                        }
                    } while (!nombre.equals("X"));
                break;
                case 6:
                    System.out.println("Sobre que evento quiere gestionar recursos? (ingrese nombre)");
                    do{
                        eventoAModificar = input.nextLine().toUpperCase();
                    }while(!eventos.getListadoEventos().containsKey(eventoAModificar));

                    System.out.println("Ingrese A para agregar un recurso, Q para quitar un recurso, X para salir");
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
                            respuesta = input.nextLine().toUpperCase();
                            if (respuesta.equals("Q")) {
                                eventos.getListadoEventos().get(eventoAModificar).QuitarRecurso(recurso);
                            }
                        }
                    }
                case 7:
                    break;
                case 8:
                    System.out.println("""
                    Que desea realizar:
                    1.Mandar notificaciones a participantes de un evento
                    2.Ver la bandeja de entrada de cierta persona\n
                    Ingrese numero de operacion a realizar: """);
                    opcion = Integer.parseInt(input.nextLine());
                    switch (opcion) {
                        case 1:
                        System.out.println("A paricipantes de que evento quiere mandar notificaciones? (ingrese nombre)");
                        do{
                            respuesta = input.nextLine().toUpperCase();
                        }while(!eventos.getListadoEventos().containsKey(respuesta));

                            for (Persona participante : eventos.getListadoEventos().get(respuesta).getMiembros()) {

                                String notificacion = "Hola! " + participante.getNombre() + 
                                " recuerde que el dia " + eventos.getListadoEventos().get(respuesta).getFecha() + " usted esta inscripto al evento " +
                                eventos.getListadoEventos().get(respuesta).getNombreEvento();

                                participante.setNotificacion(notificacion); 
                            }
                            break;

                        case 2:
                        System.out.println("Ingrese el numero de la persona para ver su bandeja de entrada");
                        int i = 0;
                            for (Persona persona  : personasEnSistema) {
                                System.out.println(i + "." + persona.getNombre());
                                i++;
                            }
                            i= Integer.parseInt(input.nextLine());
                            System.out.println("Notificaciones de " + personasEnSistema.get(i).getNombre());

                            for (String notificacion : personasEnSistema.get(i).getNotificacion()) {
                                
                                System.out.println(notificacion);
                            }

                            break;
                    }
                case 9:
                    System.out.println("Cual es el nombre del nuevo usuario?");
                    respuesta = input.nextLine().toUpperCase();
                    while(eventos.getListadoPersona().containsKey(respuesta)){
                        System.out.println("Usuario ya registrado. intente nuevamente");
                        respuesta = input.nextLine().toUpperCase();
                    }
                    eventos.crearPersona(respuesta, false);
                    break;
                case 69:
                    System.out.println("Gracias por visitar el sistema");
                    break;
                default:
                    System.out.println("Opcion invalida o no desarrollada");
                    break;
            }
        }while(opcion!=69);
        input.close();
    }
}
