package VersionNico;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestorDeEventos {
    private HashMap<String, Evento> listadoEventos;

    public GestorDeEventos(){
        listadoEventos = new HashMap<>();
    }

    public void crearEvento(String nombreEvento, String fecha, String ubicacion, String descripcion){
        if(!listadoEventos.containsKey(nombreEvento)){
            Evento nuevoEvento = new Evento(nombreEvento, fecha, ubicacion, descripcion);
            listadoEventos.put(nombreEvento, nuevoEvento);
        }else{
            System.out.println("Error al cargar evento: el evento ya fue cargado anteriormente");
            // no esta bien poner el prints dentro de los metodos pero de alguna forma hay que saber si hubo algun error
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
                eventoAModificar.setUbicacion(datoAModificar);
                break;
            case 4:
                eventoAModificar.setDescripcion(datoAModificar);
                break;
        } 
    }

    public HashMap<String, Evento> getListadoEventos(){
        return listadoEventos;
    }

    public Evento getEvento(String nombreEvento){
        return listadoEventos.get(nombreEvento);
    }

    public void cargarDatos(){
        // Cargar datos de eventos
        String archivo = "TP\\src\\VersionNico\\datos.csv"; // Nombre del archivo

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {  // Leer cada línea
                String[] datos = linea.split(","); // Separar los datos por coma
                crearEvento(datos[0], datos[1], datos[3], datos[2]); // Crear evento con los datos
                Persona persona = new Persona(datos[4], datos[5]); // Crear persona con los datos
                getEvento(datos[0]).AgregarMiembro(persona);// Agregar persona
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage()); 
            // no esta bien poner el prints dentro de los metodos pero de alguna forma hay que saber si hubo algun error
        }

    }

    public void archivoDeSalida(){
        // Crear archivo de salida
        String archivo = "TP\\src\\salida.csv"; // Nombre del archivo

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (String nombreEvento : listadoEventos.keySet()) {
                Evento evento = listadoEventos.get(nombreEvento);
                for (Persona miembro : evento.getMiembros()) {
                    bw.write(evento.getNombreEvento() + "," + evento.getFecha() + "," + evento.getUbicacion() + "," + evento.getDescripcion() + "," + miembro.getNombre() + "," + miembro.getFeedbackEvento() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
            // no esta bien poner el prints dentro de los metodos pero de alguna forma hay que saber si hubo algun error
        }
    }
}
