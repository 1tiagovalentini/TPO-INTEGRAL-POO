import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

// revisar posibles casos de modularizar el codigo en metodos dentro de gestor de eventos

public class App {
    public static void menu(){
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
            10.Cerrar sistema\n
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

        System.out.print("Ingrese fecha del evento en formato (AAAA-MM-DD): ");
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

        System.out.print("Ingrese nombre del evento, X para dejar el existente: ");   
        datosAModificar[0] = input.nextLine().toUpperCase();
        System.out.println();

        System.out.println("Ingrese fecha del evento en formato (AAAA-MM-DD), X para dejar el existente \n Advertencia: al cambiar la fecha los recursos que no puedan modificar la reserva seran eliminados");
        datosAModificar[1] = input.nextLine();

        System.out.print("Ingrese ubicacion del evento, X para dejar el existente: ");
        datosAModificar[2] = input.nextLine();
        System.out.println();
        
        System.out.print("Ingrese descripcion del evento, X para dejar el existente: ");
        datosAModificar[3] = input.nextLine();
        System.out.println();

        return datosAModificar;
    }


    public static String existeEnConjunto(Scanner input, Set<String> conjunto, String texto){
        String respuesta;
        do{
            System.out.println(texto + "(ingrese X para cancelar)");
            respuesta = input.nextLine().toUpperCase();
        }while(!conjunto.contains(respuesta) && !(respuesta.equals("X")));
        System.out.println();
        return respuesta;
    }
    
    public static void mostrarRecursos(Set<String> recursos){
        System.out.println(String.format("\u001B[37;44m%50s%s%50s\u001B[0m", "", "Listado de recursos", ""));
        for(String r: recursos){
            System.out.println(String.format("\u001B[30;47m %-118s\u001B[0m",r));
        }
        System.out.println();
    }

    public static void listarEventos(ArrayList<Evento> eventos){
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos registrados");
        } else {
            System.out.println(String.format("\u001B[37;41m%52s%s%52s\u001B[0m", "", "Listado de eventos", ""));
            for(Iterator<Evento> i = eventos.iterator();i.hasNext();){
                Evento evento = i.next();
                System.out.println(String.format("\u001B[30;47m- %-120s\u001B[0m\n\u001B[30;47m%-122s\u001B[0m","'"+evento.getNombreEvento()+
                "' ["+evento.getFecha()+"] en "+evento.getUbicacion(), evento.getDescripcion()));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        GestorDeEventos eventos = new GestorDeEventos();

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
                    eventoAModificar = existeEnConjunto(input, eventos.getListadoEventos().keySet(),"En que evento desea hacer la accion? (ingrese nombre)");
                    if(!eventoAModificar.equals("X")){
                        eventos.editarEvento(eventoAModificar, formularioEditarEvento(input));
                    }
                    break;

                case 3:
                    listarEventos(new ArrayList<>(eventos.getListadoEventos().values()));
                    break;

                case 4:
                    eventoAModificar = existeEnConjunto(input, eventos.getListadoEventos().keySet(),"En que evento desea hacer la accion? (ingrese nombre)");
                    
                    if(!eventoAModificar.equals("X")){
                        System.out.println(String.format("\u001B[37;44m%22s%s%22s\u001B[0m", "", "Listado de participantes", ""));
                        for(Iterator<Persona> i = eventos.getEvento(eventoAModificar).getMiembros().iterator();i.hasNext();){
                            Persona participante = i.next();
                            System.out.println(String.format("\u001B[30;47m  %-66s\u001B[0m", participante.getNombre()));
                        }
                    }
                    break;

                case 5:
                    eventoAModificar = existeEnConjunto(input, eventos.getListadoEventos().keySet(),"En que evento desea hacer la accion? (ingrese nombre)");
                    System.out.println("\u001B[30;41mADVERTENCIA:Si usted contrato un salon o catering tenga en cuenta de no exceder el numero permitido de personas para el servicio\u001B[0m\nIngrese los participantes del evento (ingrese X para dejar de agregar): ");
                    String personaAAgregar = input.nextLine().toUpperCase();
                    while(!personaAAgregar.equals("X")){
                        if(eventos.getListadoPersonas().containsKey(personaAAgregar)){
                            eventos.agregarParticipante(eventoAModificar,personaAAgregar,false);
                        }else{
                            System.out.println("La persona que solicito no se encuentra en sistema. Por favor agregela antes");
                        }
                        System.out.println("Ingrese participantes del evento (ingrese X para dejar de agregar): ");
                        personaAAgregar = input.nextLine().toUpperCase();
                    }
                    break;

                case 6:
                    eventoAModificar = existeEnConjunto(input, eventos.getListadoEventos().keySet(),"En que evento desea hacer la accion? (ingrese nombre)");

                    System.out.println("Ingrese A para agregar un recurso, Q para quitar un recurso, cualquier otra tecla para salir");
                    respuesta = input.nextLine().toUpperCase();
                    System.out.println();

                    switch (respuesta){
                        case "A":
                            mostrarRecursos(eventos.getListadoRecursos().keySet());
                            do{
                                System.out.print("ingrese recurso a agregar: ");
                                respuesta = input.nextLine().toUpperCase();
                                System.out.println();
                            }while(!eventos.getListadoRecursos().keySet().contains(respuesta));
                            eventos.agregarRecurso(eventoAModificar, respuesta, false);
                            break;
                        case "Q":
                            mostrarRecursos(eventos.getEvento(eventoAModificar).getRecursos().keySet());
                            do{
                                System.out.print("ingrese recurso a eliminar: ");
                                respuesta = input.nextLine().toUpperCase();
                                System.out.println();
                            }while(!eventos.getListadoRecursos().keySet().contains(respuesta));
                            eventos.eliminarRecurso(eventoAModificar, respuesta);
                            break;
                    }       
                    break;

                case 7:
                    listarEventos(eventos.generarCalendario());
                    break;

                case 8:
                    respuesta = existeEnConjunto(input, eventos.getListadoPersonas().keySet(),"En que persona desea hacer la accion? (ingrese nombre)");
                    listarEventos(eventos.generarNotificaciones(respuesta));

                case 9:
                    System.out.println("Cual es el nombre del nuevo usuario?");
                    respuesta = input.nextLine().toUpperCase();
                    while(eventos.getListadoPersonas().containsKey(respuesta)){
                        System.out.println("Usuario ya registrado. intente nuevamente");
                        respuesta = input.nextLine().toUpperCase();
                    }
                    eventos.crearPersona(respuesta, false);
                    break;
                    
                case 10:
                    System.out.println("Gracias por visitar el sistema");
                    break;
                default:
                    System.out.println("Opcion invalida o no desarrollada");
                    break;
            }
            System.out.println();
        }while(opcion!=10);
        input.close();
    }
}