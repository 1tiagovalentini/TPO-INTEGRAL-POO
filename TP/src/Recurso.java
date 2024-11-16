import java.util.HashSet;

public abstract class Recurso {
    protected String nombre;
    private HashSet<String> fechasEnUso;

    public Recurso(String nombre) {
        this.nombre = nombre;
        fechasEnUso = new HashSet<String>();
    }

    public String getNombre() {
        return this.nombre;
    }

    public abstract boolean agendarEvento(Evento eventoConsultante);

    public boolean editarFechaUsoEvento(Evento eventoConsultante, String nuevaFecha){
        boolean resultadoOperacion;
        
        if(EstaEnUso(nuevaFecha)){
            resultadoOperacion = false;
            quitarFechaEnUso(eventoConsultante.getFecha());
        }else{
            resultadoOperacion = true;
            agregarFechaEnUso(nuevaFecha);
        }
        
        return resultadoOperacion;
    };

    public void agregarFechaEnUso(String fechaAUsar) {
        fechasEnUso.add(fechaAUsar);
    }

    public void quitarFechaEnUso(String fechaAUsar) {
        fechasEnUso.remove(fechaAUsar);
    }

    public boolean EstaEnUso(String fechaAUsar) {
        return fechasEnUso.contains(fechaAUsar);
    }
}
