import java.util.ArrayList;

public class Persona {
    private String nombre;
    private ArrayList<String> notificaciones;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public void setNotificacion(String notificacion) {
        this.notificaciones.add(notificacion);
    }

    public ArrayList<String> getNotificacion() {
        return notificaciones;
    }

    public String getNombre() {
        return nombre;
    }
}