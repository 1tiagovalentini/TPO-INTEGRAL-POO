import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
        GestorDeEventos eventos = new GestorDeEventos();
        Scanner input = new Scanner(System.in);
        int opcion;
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
            8.Cerrar sistema\n
            Insgrese numero de accion a realizar: """); 
            opcion = Integer.parseInt(input.nextLine());
            System.err.println();

            switch(opcion){
                case 1:
                    System.out.println("Ingrese nombre del evento: ");   
                    String nombreEvento = input.nextLine().toUpperCase();

                    System.out.println("Ingrese fecha del evento en formato (AAAA/MM/DD): ");
                    String fecha = input.nextLine();
                    /*
                    System.out.println("Ingrese el año del evento: ");
                    int AAAA = Integer.parseInt(input.nextLine());
                    System.out.println("Ingrese el mes del evento: ");
                    int MM = Integer.parseInt(input.nextLine());
                    System.out.println("Ingrese el dia del evento: ");
                    int DD = Integer.parseInt(input.nextLine());
                    */

                    System.out.println("Ingrese ubicacion del evento: ");
                    String ubicacion = input.nextLine();
                    
                    System.out.println("Ingrese descripcion del evento: ");
                    String descripcion = input.nextLine();

                    eventos.crearEvento(nombreEvento, fecha, ubicacion, descripcion, false);
                    break;

                case 2:
                    Set<String> listaEventos = eventos.getListadoEventos().keySet();
                    String eventoAModificar;
                    int opcionModificar;
                    String datoAModificar;
                    
                    System.out.println("Que evento quiere modificar? (ingrese nombre)");
                    for(Iterator<String> j = listaEventos.iterator();j.hasNext();){
                        String evento = j.next();
                        System.out.println("- '" + evento);
                    }
                    do{
                        eventoAModificar = input.nextLine().toUpperCase();
                    }while(!listaEventos.contains(eventoAModificar));
                    
                    do{
                      System.out.println("""
                        Que quiere modificar del evento seleccionado?
                        1.Nombre
                        2.Fecha
                        3.Ubicacion
                        4.Descripcion""");
                        opcionModificar = Integer.parseInt(input.nextLine());  
                    }while(opcionModificar<1 && opcionModificar>4);                    
                    switch(opcionModificar){
                        case 1:
                            System.out.println("Ingrese nombre del evento: ");
                            break;
                        case 2:
                            System.out.println("Ingrese fecha del evento en formato (AAAA/MM/DD): ");
                            break;
                        case 3:
                            System.out.println("Ingrese ubicacion del evento: ");
                            break;
                        case 4:
                            System.out.println("Ingrese descripcion del evento: ");
                            break;
                    }
                    datoAModificar = input.nextLine();

                    eventos.editarEvento(eventoAModificar, opcionModificar, datoAModificar);

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
                    break;
                case 8:
                    System.out.println("Gracias por visitar el sistema");
                    break;
                default:
                    System.out.println("Opcion invalida o no desarrollada");
                    break;
            }
        }while(opcion!=8);
        input.close();

        /*        
        try{
            int opcion;
            String respuesta;
            do{
                do {
                    System.out.println("Ingrese numero:");
                    opcion = Integer.parseInt(input.nextLine());
                } while (opcion < 1 || opcion > 5);

                switch (opcion) {
                    case 1 -> {

                        System.out.println("Ingrese nombre del evento: ");
                    
                        String nombreEvento = input.nextLine();
                        System.out.println("Ingrese el año del evento: ");
                        int AAAA = Integer.parseInt(input.nextLine());
                        System.out.println("Ingrese el mes del evento: ");
                        int MM = Integer.parseInt(input.nextLine());
                        System.out.println("Ingrese el dia del evento: ");
                        int DD = Integer.parseInt(input.nextLine());
                        System.out.println("Ingrese ubicacion del evento: ");
                        String ubicacion = input.nextLine();
                        System.out.println("Ingrese descripcion del evento: ");
                        String descripcion = input.nextLine();

                        Evento nuevoEvento = new Evento(nombreEvento, AAAA + "/" + MM + "/" + DD, ubicacion, descripcion);
                        lista.add(nuevoEvento);
                        

                        break;
                    }
                    case 2 -> {
                        do{
                            System.out.println("Que evento quiere modificar?");
                            int i = 0;
                            for (Evento evento : lista) {
                                System.out.println(i + "." + evento.getNombreEvento());
                                i++;
                            }
                            i= Integer.parseInt(input.nextLine());

                            System.out.println("Que quiere modificar?" +
                            "1.Nombre del evento" +
                            "2.Año del evento" +
                            "3.Mes del evento" +
                            "4.Dia del evento" +
                            "5.Ubicacion del evento" +
                            "6.Descripcion del evento" );
                            opcion = Integer.parseInt(input.nextLine());

                            switch (opcion) {
                                case 1 -> {
                                    System.out.println("Ingrese el nuevo nombre: ");
                                    String nombre = input.nextLine();
                                    lista.get(i).setNombreEvento(nombre);
                                    break;
                                }
                                case 2 -> {
                                    System.out.println("Ingrese el nuevo año : ");
                                    String nombre = input.nextLine();
                                    lista.get(i).setFecha(respuesta);      //terminar bien la fecha
                                    break;
                                }
                                case 3 -> {
                                    System.out.println("Ingrese el nuevo mes : ");
                                    String nombre = input.nextLine();
                                    lista.get(i).setNombreEvento(nombre);      //terminar bien la fecha
                                    break;
                                }
                                case 4 -> {
                                    System.out.println("Ingrese el nuevo dia: ");
                                    String nombre = input.nextLine();
                                    lista.get(i).setNombreEvento(nombre);       //terminar bien la fecha  
                                    break;
                                }
                                case 5 -> {
                                    System.out.println("Ingrese la nueva ubicacion: ");
                                    String ubicacion = input.nextLine();
                                    lista.get(i).setUbicacion(ubicacion);       
                                    break;
                                }
                                case 6 -> {
                                    System.out.println("Ingrese la nueva descripcion: ");
                                    String descripcion = input.nextLine();
                                    lista.get(i).setDescripcion(descripcion);         //falta setDescripcion
                                    break;
                                }
                            }
                            System.out.println("Desea continuar?");
                            respuesta = input.nextLine().toUpperCase();    
                        }while(respuesta != "NO");

                        
                    }
                    case 3 -> {
                        if (lista.isEmpty()) {
                            System.out.println("No hay eventos registrados");
                        } else {
                            System.out.println("Lista de eventos:");
                            for (int i = 0; i < lista.size(); i++) {
                                Evento evento = lista.get(i);
                                System.out.println((i + 1) + ". Nombre: " + evento.getNombreEvento() +
                                        ", Fecha: " + evento.getFecha() +
                                        ", Ubicación: " + evento.getUbicacion() +
                                        ", Descripción: " + evento.getDescripcion());
                            }
                        }
                        break;

                    }
                    case 4 -> {
                        System.out.println("Participantes de que evento desea ver?");
                            int i = 0;
                            for (Evento evento : lista) {
                                System.out.println(i + "." + evento.getNombreEvento());
                                i++;
                            }
                            i= Integer.parseInt(input.nextLine());
                        System.out.println("Lista de participantes del evento" + lista.get(i).getNombreEvento());
                            int j = 0;
                        for (Persona participante : lista.get(i).ArrayList()) {
                            System.out.println(j + "." + participante.getNombre());
                            j++;
                        }
                            
                    }
                    case 5 -> {
                        System.out.println("A que evento quiere agregar participantes?");
                            int i = 0;
                            for (Evento evento : lista) {
                                System.out.println(i + "." + evento.getNombreEvento());
                                i++;
                            }
                            i= Integer.parseInt(input.nextLine());

                        System.out.println("Ingrese los participantes del evento (ingrese STOP para dejar de agregar): ");
                        String persona;
                        do {
                            persona = input.nextLine();
                            if (!persona.equals("STOP")) {
                                Persona participante = new Persona(persona);
                                lista.get(i).ArrayList().add(participante);
                            }
                        } while (!persona.equals("STOP"));
                    }
                    case 6 -> {
                
                    }
                    case 7 -> {
                
                    } 
                }
                System.out.println("Desea continuar?");
                respuesta = input.nextLine().toUpperCase();

            } while (respuesta != "NO");
            
        }
        finally{
            input.close();
        }*/
    }
}
