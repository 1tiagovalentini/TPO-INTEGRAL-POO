public class Persona {
    private String nombre;
    private String feedbackEvento;

    public Persona(String nombre, String feedbackEvento) {
        this.nombre = nombre;
        this.feedbackEvento = feedbackEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFeedbackEvento() {
        return feedbackEvento;
    }
}
