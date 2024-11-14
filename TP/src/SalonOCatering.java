public class SalonOCatering extends Recurso{
    int cantidadMaximaDelServicio;

    public SalonOCatering(String nombre, int cantidadMaximaDelServicio){
        super(nombre);
        this.cantidadMaximaDelServicio = cantidadMaximaDelServicio;
    }

    private boolean capacidadApta(Evento eventoConsultante){
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

    @Override
    public boolean editarUsoEvento(Evento eventoConsultante) {
        boolean resultadoOperacion;
        super.quitarFechaEnUso(eventoConsultante.getFecha());

        if(!super.EstaEnUso(eventoConsultante.getFecha()) && capacidadApta(eventoConsultante)){
            resultadoOperacion = true;
            super.agregarFechaEnUso(nombre);
        }else{
            resultadoOperacion = false;
        }
        
        return resultadoOperacion;
    }

}
