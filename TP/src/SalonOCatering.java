public class SalonOCatering extends Recurso{
    int cantidadMaximaDelServicio;

    public SalonOCatering(String nombre, int cantidadMaximaDelServicio){
        super(nombre);
        this.cantidadMaximaDelServicio = cantidadMaximaDelServicio;
    }

    public boolean capacidadApta(Evento eventoConsultante){
        return eventoConsultante.getMiembros().size() <= cantidadMaximaDelServicio;
    }

    @Override
    public boolean agendarEvento(Evento eventoConsultante) {
        boolean resultadoOperacion;
        if(!super.EstaEnUso(eventoConsultante.getFecha()) && capacidadApta(eventoConsultante)){
            resultadoOperacion=true;
            super.agregarFechaEnUso(eventoConsultante.getFecha());
        }else{
            resultadoOperacion=false;
        }
        return resultadoOperacion;
    }

}
