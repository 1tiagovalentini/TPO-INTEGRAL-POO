public class EquipoAudiovisual extends Recurso{

    public EquipoAudiovisual(String nombre){
        super(nombre);
    }

    @Override
    public boolean agendarEvento(Evento eventoConsultante){
        boolean resultadoOperacion;
        if(super.EstaEnUso(eventoConsultante.getFecha())){
            resultadoOperacion=false;
        }else{
            resultadoOperacion=true;
            super.agregarFechaEnUso(eventoConsultante.getFecha());
        }
        return resultadoOperacion;
    }

    @Override
    public boolean editarUsoEvento(Evento eventoConsultante) {
        boolean resultadoOperacion;
        super.quitarFechaEnUso(eventoConsultante.getFecha());

        if(super.EstaEnUso(eventoConsultante.getFecha())){
            resultadoOperacion = false;
        }else{
            resultadoOperacion = true;
            super.agregarFechaEnUso(nombre);
        }
        
        return resultadoOperacion;
    }

}
