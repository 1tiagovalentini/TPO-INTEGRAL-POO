public class ArchivosUsuarios extends GestorDeArchivos{
    public ArchivosUsuarios(String direccionArchivo, GestorDeEventos gestor){
        super(direccionArchivo, gestor);
    }

    @Override
    protected void cargarEnMemoria(String[] datos, GestorDeEventos gestor) {
        gestor.crearPersona(datos[0], true);
    }
}
