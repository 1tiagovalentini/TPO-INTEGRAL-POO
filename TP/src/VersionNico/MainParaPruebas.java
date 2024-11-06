
import java.util.HashMap;
import java.util.Scanner;

public class MainParaPruebas {
    // esta clase esta meramente para hacer pruebas unitarias de los metodos de las clases 
    // no es necesario que se mantenga en el proyecto final
    // de esta manera nos podemos asegurar que los metodos de las clases funcionan correctamente 
    // sin ejecutarlos todos juntos
    public static void main(String[] args) {

        GestorDeEventos gestor = new GestorDeEventos();
        gestor.cargarDatos();

        HashMap<String, Evento> eventos = gestor.getListadoEventos();
        for (String nombreEvento : eventos.keySet()) {
            Evento evento = eventos.get(nombreEvento);
            System.out.println("Nombre: " + evento.getNombreEvento());
            System.out.println("Fecha: " + evento.getFecha());
            System.out.println("Ubicacion: " + evento.getUbicacion());
            System.out.println("Descripcion: " + evento.getDescripcion());
            System.out.println("Miembros: ");
            for (Persona miembro : evento.getMiembros()) {
                System.out.println("    Nombre: " + miembro.getNombre());
                System.out.println("    Feedback: " + miembro.getFeedbackEvento());
                System.out.println("    Notificacion: " + miembro.getNotificacion());
                System.out.println();
            }
            System.out.println();
            //System.out.println("Recursos: ");
            //for (Recurso recurso : evento.getRecursos()) {
                //System.out.println("Nombre: " + recurso.getNombre());
                //.out.println("Descripcion: " + recurso.getDescripcion());
            //}
            //si se quiere se podrian agregar los recursos tambien pero no se si es necesario
        }
        gestor.archivoDeSalida(); // creamos un archivo de salida con los datos de los eventos en formato csv
        
    }

}
