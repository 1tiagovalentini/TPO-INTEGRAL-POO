import java.util.ArrayList;

public class Evento {
    private String nombreEvento;
    private String fecha;
    private String ubicacion;
    private String descripcion;
    private ArrayList<Persona> miembros;
    /*private ArrayList<Boolean> asistencia;*/

    public String getFecha(){
        return this.fecha;
    }

    public String getUbicacion(){
        return this.ubicacion;
    }

    public String getDescripcion(){
       return this.descripcion; 
    }

    public ArrayList<Persona> ArrayList(){
        return this.miembros;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    
    
}
