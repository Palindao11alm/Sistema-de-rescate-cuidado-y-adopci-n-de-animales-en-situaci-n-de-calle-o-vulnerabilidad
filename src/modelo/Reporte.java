package modelo;
import java.util.Objects;

public class Reporte {
    
    // ATRIBUTOS DE LA CLASE
    // Atributo estático para autogenerar identificadores secuenciales únicos (ej. REP-001)
    private static int contadorId = 1;

    private String idReporte;
    private String ubicacion;
    private String estadoSaludInicial;
    private String descripcionSalud;
    private String centroAdopcion;
    private String cedulaRescatista;

    // Constructor por defecto
    public Reporte() {
        this.idReporte = "REP-" + String.format("%03d", contadorId++);
    }

    // Constructor con parámetros que asigna los atributos y autogenera el identificador único para el animal
    public Reporte(String ubicacion, String estadoSaludInicial, String descripcionSalud, String centroAdopcion, String cedulaRescatista) {
        this.idReporte = "REP-" + String.format("%03d", contadorId++);
        this.ubicacion = ubicacion;
        this.estadoSaludInicial = estadoSaludInicial;
        this.descripcionSalud = descripcionSalud;
        this.centroAdopcion = centroAdopcion;
        this.cedulaRescatista = cedulaRescatista;
    }

    //GETTERS Y SETTERS de Reporte
    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstadoSaludInicial() {
        return estadoSaludInicial;
    }

    public void setEstadoSaludInicial(String estadoSaludInicial) {
        this.estadoSaludInicial = estadoSaludInicial;
    }

    public String getDescripcionSalud() {
        return descripcionSalud;
    }

    public void setDescripcionSalud(String descripcionSalud) {
        this.descripcionSalud = descripcionSalud;
    }

    public String getCentroAdopcion() {
        return centroAdopcion;
    }

    public void setCentroAdopcion(String centroAdopcion) {
        this.centroAdopcion = centroAdopcion;
    }

    public String getCedulaRescatista() {
        return cedulaRescatista;
    }

    public void setCedulaRescatista(String cedulaRescatista) {
        this.cedulaRescatista = cedulaRescatista;
    }

    // MÉTODOS SOBREESCRITOS (EQUALS Y TOSTRING)
    // Sobrescritura del método equals para comparar reportes por su identificador único (idReporte)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Reporte reporte = (Reporte) obj;
        return Objects.equals(idReporte, reporte.idReporte);
    }

    //Construcción de cadena de texto para imprimirlo en consola
    @Override
    public String toString() {
        return "ID Reporte: " + idReporte + 
               " | Ubicación: " + ubicacion + 
               " | Salud Inicial: " + estadoSaludInicial + 
               " | Descripción: " + descripcionSalud + 
               " | Centro Derivado: " + centroAdopcion + 
               " | Rescatista (Cédula): " + cedulaRescatista;
    }
}