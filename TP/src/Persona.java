public class Persona {
    private String nombre;
    private String feedbackEvento;
    private String notificacion;

    public Persona(String nombre) {
        this.nombre = nombre;
    
    }

    public void setFeedbackEvento(String feedbackEvento) {
        this.feedbackEvento = feedbackEvento;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFeedbackEvento() {
        return feedbackEvento;
    }
}
