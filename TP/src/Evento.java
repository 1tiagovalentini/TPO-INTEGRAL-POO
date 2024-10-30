import java.util.ArrayList;

public class Evento {
    private String nombreEvento;
    private String fecha;
    private String ubicacion;
    private String descripcion;
    private ArrayList<Persona> miembros;
    /*private ArrayList<Boolean> asistencia;*/

    public Evento(String nombreEvento, String fecha, String ubicacion, String descripcion){
        this.nombreEvento = nombreEvento;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        miembros = new ArrayList<Persona>();
    }

    public String getNombreEvento(){
        return this.nombreEvento;
    }

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
    
    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }

    public void AgregarMiembro(Persona miembroNuevo){
        this.miembros.add(miembroNuevo);
    }
}
