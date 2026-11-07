package controlador;
import modelo.Reporte; //Importamos la Clase Reporte 
import java.util.ArrayList;

//Clase GestionReportes(concreta controladora), administra la colección de listaReportes de avistamiento y valida las reglas de negocio del módulo.
public class GestionReportes {

    // ATRIBUTOS DE LA CLASE
    // Colección dinámica en memoria para almacenar objetos de tipo Reporte
    private ArrayList<Reporte> listaReportes;

    // MÉTODOS DE LA CLASE - CONSTRUCTOR
    public GestionReportes() {
        this.listaReportes = new ArrayList<>();
    }

    // VALIDACIONES IMPLEMENTADAS Y LÓGICA DE NEGOCIO
    // Método de validación para verificar que los campos de un reporte no sean nulos ni vacíos
    public boolean esReporteValido(Reporte reporte) {
        // VALIDACIÓN: El objeto reporte no debe ser nulo
        if (reporte == null) {
            return false;
        }
        // VALIDACIÓN: Los campos obligatorios no deben ser nulos ni estar vacíos
        if (reporte.getUbicacion() == null || reporte.getUbicacion().trim().isEmpty()) {
            return false;
        }
        if (reporte.getEstadoSaludInicial() == null || reporte.getEstadoSaludInicial().trim().isEmpty()) {
            return false;
        }
        if (reporte.getDescripcionSalud() == null || reporte.getDescripcionSalud().trim().isEmpty()) {
            return false;
        }
        if (reporte.getCentroAdopcion() == null || reporte.getCentroAdopcion().trim().isEmpty()) {
            return false;
        }
        if (reporte.getCedulaRescatista() == null || reporte.getCedulaRescatista().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    // Método para registrar un nuevo reporte en la lista en memoria
    public boolean registrarReporte(Reporte nuevoReporte) {
        //Se valida que el reporte cumpla con todos los datos requeridos
        if (!esReporteValido(nuevoReporte)) {
            return false; //Si no es valido, no registra el reporte
        }
        return this.listaReportes.add(nuevoReporte);//Si es valido, agrega el nuevo reporte a la coleccion listaReportes
    }

    // Método para buscar un reporte en la lista por su ID autogenerado
    public Reporte buscarReportePorId(String idReporte) {
        // VALIDACIÓN: Si el id recibido es nulo, retorna null inmediatamente
        if (idReporte == null) {
            return null;
        }
        // Recorrido de la listaReportes para localizar el reporte
        for (Reporte reporte : listaReportes) {
            if (reporte.getIdReporte().equalsIgnoreCase(idReporte.trim())) {
                return reporte;
            }
        }
        return null;
    }

    // Método de consulta para obtener la lista completa de reportes almacenados
    public ArrayList<Reporte> getListaReportes() {
        return listaReportes;
    }
}