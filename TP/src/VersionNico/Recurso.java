
import java.util.HashSet;

public class Recurso {
    private String tipo;
    private HashSet<String> fechasEnUso;

    public Recurso(String tipo) {
        this.tipo = tipo;
        fechasEnUso = new HashSet<String>();
    }

    public String getTipo() {
        return tipo;
    }

    public void agregarFechaEnUso(String fechaAUsar) {
        fechasEnUso.add(fechaAUsar);
    }

    public void quitarFechaEnUso(String fechaAUsar) {
        fechasEnUso.remove(fechaAUsar);
    }

    public boolean isEstaEnUso(String fechaAUsar) {
        return fechasEnUso.contains(fechaAUsar);
    }
}
