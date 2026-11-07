package modelo;

import java.util.Objects;

//Clase Solicitud, representa la entidad de una solicitud de adopción ingresada por un adoptante para una mascota en particular.
public class Solicitud {

    // ATRIBUTOS DE LA CLASE
    // Atributo estático para autogenerar identificadores secuenciales únicos (ej. SOL-001)
    private static int contadorId = 1;

    private String idSolicitud;
    private String cedulaAdoptante;
    private String idMascota;
    private String estadoSolicitud; // Valores válidos: "Pendiente", "Aprobada", "Rechazada"

    // Constructor por defecto
    public Solicitud() {
        this.idSolicitud = "SOL-" + String.format("%03d", contadorId++);
        this.estadoSolicitud = "Pendiente";
    }

    // Constructor con parámetros que asigna datos y autogenera el identificador único
    public Solicitud(String cedulaAdoptante, String idMascota) {
        this.idSolicitud = "SOL-" + String.format("%03d", contadorId++);
        this.cedulaAdoptante = cedulaAdoptante;
        this.idMascota = idMascota;
        this.estadoSolicitud = "Pendiente";
    }

    //GETTERS Y SETTERS
    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getCedulaAdoptante() {
        return cedulaAdoptante;
    }

    public void setCedulaAdoptante(String cedulaAdoptante) {
        this.cedulaAdoptante = cedulaAdoptante;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    // MÉTODOS SOBREESCRITOS (EQUALS Y TOSTRING)
    // Sobrescritura de equals para comparar solicitudes por su identificador único (idSolicitud)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Solicitud solicitud = (Solicitud) obj;
        return Objects.equals(idSolicitud, solicitud.idSolicitud);
    }

    // Formato de presentación en texto de la solicitud
    @Override
    public String toString() {
        return "ID Solicitud: " + idSolicitud + 
               " | Cédula Adoptante: " + cedulaAdoptante + 
               " | ID Mascota: " + idMascota + 
               " | Estado Solicitud: " + estadoSolicitud;
    }
}