package controlador;

import modelo.Adoptante;
import modelo.AnimalRescatado;
import modelo.Solicitud;
import modelo.Usuario;
import java.util.ArrayList;

//Clase GestionAdopciones, administra las solicitudes de adopción en memoria y ejecuta la lógica y reglas de negocio para su registro, validación y procesamiento.
public class GestionAdopciones {

    // ATRIBUTOS DE LA CLASE
    // Colección dinámica para almacenar objetos de tipo Solicitud
    private ArrayList<Solicitud> listaSolicitudes;
    // Referencias a los controladores de los módulos de usuarios y animales para validaciones cruzadas
    private GestionUsuarios gestionUsuarios;
    private GestionAnimales gestionAnimales;

    // ==========================================
    //CONSTRUCTOR
    public GestionAdopciones(GestionUsuarios gestionUsuarios, GestionAnimales gestionAnimales) {
        this.listaSolicitudes = new ArrayList<>();
        this.gestionUsuarios = gestionUsuarios;
        this.gestionAnimales = gestionAnimales;
    }

    // VALIDACIONES IMPLEMENTADAS Y LÓGICA DE NEGOCIO
    // Método para registrar y validar una nueva solicitud de adopción
    public boolean registrarSolicitud(Solicitud nuevaSolicitud) {
        // VALIDACIÓN: El objeto solicitud no debe ser nulo
        if (nuevaSolicitud == null) {
            return false;
        }

        // VALIDACIÓN: Existencia del usuario en el sistema
        Usuario usuario = gestionUsuarios.buscarUsuarioPorCedula(nuevaSolicitud.getCedulaAdoptante());
        if (usuario == null) {
            return false;
        }

        // VALIDACIÓN: El usuario debe ser de rol Adoptante y mayor de edad (>= 18 años)
        if (!(usuario instanceof Adoptante)) {
            return false;
        }
        Adoptante adoptante = (Adoptante) usuario;
        if (!adoptante.esMayorDeEdad()) {
            return false;
        }

        // VALIDACIÓN: Existencia de la mascota en el inventario
        AnimalRescatado animal = gestionAnimales.buscarAnimalPorId(nuevaSolicitud.getIdMascota());
        if (animal == null) {
            return false;
        }

        // VALIDACIÓN: La mascota debe estar registrada con estado de salud "Sano"
        if (!"Sano".equalsIgnoreCase(animal.getEstadoSalud())) {
            return false;
        }

        // VALIDACIÓN: La mascota debe encontrarse disponible para adopción
        if (!"Disponible".equalsIgnoreCase(animal.getEstadoAdopcion())) {
            return false;
        }

        // Incorpora la solicitud validada a la lista en memoria
        return this.listaSolicitudes.add(nuevaSolicitud);
    }

    // Método para evaluar y procesar una solicitud de adopción (Aprobar o Rechazar)
    public boolean evaluarSolicitud(String idSolicitud, boolean aprobada) {
        Solicitud solicitud = buscarSolicitudPorId(idSolicitud);

        // VALIDACIÓN: La solicitud debe existir y su estado debe ser "Pendiente"
        if (solicitud == null || !"Pendiente".equalsIgnoreCase(solicitud.getEstadoSolicitud())) {
            return false;
        }

        if (aprobada) {
            AnimalRescatado animal = gestionAnimales.buscarAnimalPorId(solicitud.getIdMascota());
            if (animal == null || !"Disponible".equalsIgnoreCase(animal.getEstadoAdopcion())) {
                return false;
            }

            // Cambia el estado de la solicitud a "Aprobada"
            solicitud.setEstadoSolicitud("Aprobada");

            // Cambia el estado de la mascota a "Adoptado"
            animal.setEstadoAdopcion("Adoptado");

            // Regla de negocio: Rechaza automáticamente todas las demás solicitudes pendientes para esta misma mascota
            for (Solicitud s : listaSolicitudes) {
                if (!s.equals(solicitud) && s.getIdMascota().equalsIgnoreCase(solicitud.getIdMascota()) && "Pendiente".equalsIgnoreCase(s.getEstadoSolicitud())) {
                    s.setEstadoSolicitud("Rechazada");
                }
            }
        } else {
            // Si la solicitud no es aprobada, cambia su estado a "Rechazada"
            solicitud.setEstadoSolicitud("Rechazada");
        }

        return true;
    }

    // Método para buscar una solicitud por su ID autogenerado
    public Solicitud buscarSolicitudPorId(String idSolicitud) {
        if (idSolicitud == null) {
            return null;
        }
        for (Solicitud solicitud : listaSolicitudes) {
            if (solicitud.getIdSolicitud().equalsIgnoreCase(idSolicitud.trim())) {
                return solicitud;
            }
        }
        return null;
    }

    // Método de consulta para obtener la lista completa de solicitudes
    public ArrayList<Solicitud> getListaSolicitudes() {
        return listaSolicitudes;
    }
}