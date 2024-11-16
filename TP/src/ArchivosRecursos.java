public class ArchivosRecursos extends GestorDeArchivos{
    public ArchivosRecursos(String direccionArchivo, GestorDeEventos gestor){
        super(direccionArchivo, gestor);
    }

    
    @Override
    protected void cargarEnMemoria(String[] datos, GestorDeEventos gestor) {
        switch(datos[0]){
            case "1":
                gestor.agregarRecurso(new EquipoAudiovisual(datos[1]));
                break;
            case "2":
                gestor.agregarRecurso(new SalonOCatering(datos[1], Integer.parseInt(datos[2])));
                break;
        }
    }

}
