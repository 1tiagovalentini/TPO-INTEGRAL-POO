
import java.util.ArrayList;

public class Evento {
    private String nombreEvento;
    private String fecha;
    private String ubicacion;
    private String descripcion;
    private ArrayList<Persona> miembros;
    private ArrayList<Recurso> recursos;

    public Evento(String nombreEvento, String fecha, String ubicacion, String descripcion){ 
        this.nombreEvento = nombreEvento;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        miembros = new ArrayList<Persona>();
        recursos = new ArrayList<Recurso>();
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

    public ArrayList<Recurso> getRecursos(){
        return this.recursos;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public void setFecha(String fecha){
        for (Recurso recurso : this.recursos) {
            recurso.getFechasEnUso().remove(this.fecha);
        }
        this.fecha = fecha;
        this.recursos.clear();
    }
    
    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }

    public void AgregarMiembro(Persona miembroNuevo){
        this.miembros.add(miembroNuevo);
    }

    public void AgregarRecurso(Recurso recursoNuevo){
        this.recursos.add(recursoNuevo);
    }

    public void QuitarMiembro(Persona miembro){
        this.miembros.remove(miembro);
    }

    public void QuitarRecurso(Recurso recurso){
        this.recursos.remove(recurso);
    }

    @Override
    public String toString(){
        return "'"+this.nombreEvento+"' ["+this.fecha+"] en "+this.ubicacion+": "+this.descripcion;
    }
}
