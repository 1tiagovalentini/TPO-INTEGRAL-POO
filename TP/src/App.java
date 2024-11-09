import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

// revisar posibles casos de modularizar el codigo en metodos dentro de gestor de eventos

public class App {
    public static void menu(){
        System.out.print("""
            Que desea realizar:
            1.Crear un nuevo evento +
            2.Modificar un evento +
            3.Ver eventos +
            4.Ver registro de personas de cierto evento +
            5.Inscribir una persona a un evento +
            6.Gestionar recursos -
            7.Ver calendario -
            8.Notificaciones -
            9.Agregar usuario del sistema +
            69.Cerrar sistema +\n
            Ingrese numero de operacion a realizar: """);  
    }

    public static String[] formularioIngresarEvento(Scanner input, Set<String> eventosCreados){
        String[] eventoNuevo = new String[4];
        
        System.out.print("Ingrese nombre del evento: ");   
        eventoNuevo[0] = input.nextLine().toUpperCase();
        System.out.println();
        while(eventosCreados.contains(eventoNuevo[0])){
            System.out.println("El evento ya fue creado antes. Pruebe otro nombre");
            System.out.print("Ingrese nombre del evento: ");
            eventoNuevo[0] = input.nextLine().toUpperCase();
            System.out.println();
        }

        System.out.print("Ingrese fecha del evento en formato (AAAA/MM/DD): ");
        eventoNuevo[1] = input.nextLine();
        System.out.println();

        System.out.print("Ingrese ubicacion del evento: ");
        eventoNuevo[2] = input.nextLine();
        System.out.println();
        
        System.out.print("Ingrese descripcion del evento: ");
        eventoNuevo[3] = input.nextLine();
        System.out.println();
        
        return eventoNuevo;
    }

    public static String[] formularioEditarEvento(Scanner input){
        String[] datosAModificar = new String[4];

        System.out.print("Ingrese nombre del evento, X para dejar el existente");   
        datosAModificar[0] = input.nextLine().toUpperCase();
        System.out.println();

        System.out.print("Ingrese fecha del evento en formato (AAAA/MM/DD), X para dejar el existente ");
        datosAModificar[1] = input.nextLine();
        System.out.println();

        System.out.print("Ingrese ubicacion del evento, X para dejar el existente");
        datosAModificar[2] = input.nextLine();
        System.out.println();
        
        System.out.print("Ingrese descripcion del evento, X para dejar el existente");
        datosAModificar[3] = input.nextLine();
        System.out.println();

        return datosAModificar;
    }

    public static String existeEvento (Scanner input, Set<String> eventosCreados){
        String respuesta;
        System.out.println("En que evento desea hacer la accion? (ingrese nombre), X para no hacer nada");
        do{
            respuesta = input.nextLine().toUpperCase();
        }while(!eventosCreados.contains(respuesta) && !(respuesta.equals("X")));
        System.out.println();
        return respuesta;
    }
    public static void main(String[] args) throws Exception {
        GestorDeEventos eventos = new GestorDeEventos();

        Recurso salonRecurso = new Recurso("Salon");
        Recurso cateringRecurso = new Recurso("Catering");
        Recurso audiovisualRecurso = new Recurso("Audiovisual");

        Scanner input = new Scanner(System.in);

        int opcion;
        
        String respuesta;
        String eventoAModificar;
        do{
            menu();
            opcion = Integer.parseInt(input.nextLine());
            System.out.println();

            switch(opcion){
                case 1:
                    String[] datosEvento = formularioIngresarEvento(input, eventos.getListadoEventos().keySet());
                    eventos.crearEvento(datosEvento[0],datosEvento[1],datosEvento[2],datosEvento[3], false);
                    break;
                case 2:
                    eventoAModificar = existeEvento(input, eventos.getListadoEventos().keySet());
                    if(!eventoAModificar.equals("X")){
                        eventos.editarEvento(eventoAModificar, formularioEditarEvento(input));
                    }
                    break;
                case 3:
                    if (eventos.getListadoEventos().isEmpty()) {
                        System.out.println("No hay eventos registrados");
                    } else {
                        System.out.println(String.format("\u001B[37;41m%52s%s%52s\u001B[0m", "", "Listado de eventos", ""));
                        for(Iterator<Evento> i = eventos.getListadoEventos().values().iterator();i.hasNext();){
                            Evento evento = i.next();
                            System.out.println(String.format("\u001B[30;47m- %-120s\u001B[0m\n\u001B[30;47m%-122s\u001B[0m","'"+evento.getNombreEvento()+
                            "' ["+evento.getFecha()+"] en "+evento.getUbicacion(), evento.getDescripcion()));
                        }
                    }
                    break;
                case 4:
                    eventoAModificar = existeEvento(input, eventos.getListadoEventos().keySet());
                    if(!eventoAModificar.equals("X")){
                        System.out.println(String.format("\u001B[37;44m%22s%s%22s\u001B[0m", "", "Listado de participantes", ""));
                        for(Iterator<Persona> i = eventos.getEvento(eventoAModificar).getMiembros().iterator();i.hasNext();){
                            Persona participante = i.next();
                            System.out.println(String.format("\u001B[30;47m- %-66s\u001B[0m", participante.getNombre()));
                        }
                    }
                    break;
                case 5:
                    eventoAModificar = existeEvento(input, eventos.getListadoEventos().keySet());
                    System.out.println("Ingrese los participantes del evento (ingrese X para dejar de agregar): ");
                    String personaAAgregar = input.nextLine().toUpperCase();
                    while(!personaAAgregar.equals("X")){
                        if(eventos.getListadoPersonas().containsKey(personaAAgregar)){
                            eventos.agregarParticipante(eventoAModificar,personaAAgregar,false);
                        }
                        personaAAgregar = input.nextLine().toUpperCase();
                    }
                    break;
                /*
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
                */
                case 9:
                    System.out.println("Cual es el nombre del nuevo usuario?");
                    respuesta = input.nextLine().toUpperCase();
                    while(eventos.getListadoPersonas().containsKey(respuesta)){
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
            System.out.println();
        }while(opcion!=69);
        input.close();
    }
}