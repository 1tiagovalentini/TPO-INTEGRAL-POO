public class ArchivosEventos extends GestorDeArchivos{
    public ArchivosEventos(String direccionArchivo, GestorDeEventos gestor){
        super(direccionArchivo, gestor);
    }

    @Override
    protected void cargarEnMemoria(String[] datos, GestorDeEventos gestor) {
        gestor.crearEvento(datos[0], datos[1], datos[2], datos[3], true);
    }
}
