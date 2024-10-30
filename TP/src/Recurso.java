public class Recurso {
    private String tipo;
    private boolean estaEnUso;

    public Recurso(String tipo, boolean estaEnUso) {
        this.tipo = tipo;
        this.estaEnUso = estaEnUso;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isEstaEnUso() {
        return estaEnUso;
    }

    public void setEstaEnUso(boolean estaEnUso) {
        this.estaEnUso = estaEnUso;
    }
}
