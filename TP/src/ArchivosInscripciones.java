public class ArchivosInscripciones extends GestorDeArchivos{
    public ArchivosInscripciones(String direccionArchivo, GestorDeEventos gestor){
        super(direccionArchivo, gestor);
    }

    @Override
    protected void cargarEnMemoria(String[] datos, GestorDeEventos gestor) {
        gestor.agregarParticipante(datos[0], datos[1], true);
    }

}
