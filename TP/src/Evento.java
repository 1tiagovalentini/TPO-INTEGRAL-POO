
import java.util.ArrayList;
import java.util.HashMap;

public class Evento implements Comparable<Evento>{
    private String nombreEvento;
    private String fecha;
    private String ubicacion;
    private String descripcion;
    private ArrayList<Persona> miembros;
    private HashMap<String, Recurso> recursos;

    public Evento(String nombreEvento, String fecha, String ubicacion, String descripcion){ 
        this.nombreEvento = nombreEvento;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        miembros = new ArrayList<Persona>();
        recursos = new HashMap<>();
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

    public ArrayList<Persona> getMiembros(){
        return this.miembros;
    }

    public HashMap<String, Recurso> getRecursos(){
        return this.recursos;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
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

    public void AgregarRecurso(Recurso recursoNuevo){
        this.recursos.put(recursoNuevo.getNombre(),recursoNuevo);
    }

    public void QuitarRecurso(String recurso){
        this.recursos.remove(recurso);
    }

    @Override
    public int compareTo(Evento other){
        return this.fecha.compareTo(other.getFecha());
    }
}
