package controlador;

import modelo.Adoptante; //Importamos todas las clases necesarias
import modelo.AnimalRescatado;
import modelo.Gato;
import modelo.Perro;
import modelo.Reporte;
import modelo.Rescatista;
import modelo.Solicitud;
import modelo.Usuario;
import java.util.ArrayList;

//Clase GestionConsultasYPrecarga, administra la emisión de reportes generales del sistema y ejecuta la precarga inicial de datos de prueba en memoria al arrancar la aplicación.
public class GestionConsultasYPrecarga {

    // ATRIBUTOS DE LA CLASE
    // Referencias a los controladores de los módulos del sistema para consolidar reportes y realizar la precarga
    private GestionUsuarios gestionUsuarios;
    private GestionReportes gestionReportes;
    private GestionAnimales gestionAnimales;
    private GestionAdopciones gestionAdopciones;

    //CONSTRUCTOR
    public GestionConsultasYPrecarga(GestionUsuarios gestionUsuarios, GestionReportes gestionReportes, 
                                     GestionAnimales gestionAnimales, GestionAdopciones gestionAdopciones) {
        this.gestionUsuarios = gestionUsuarios;
        this.gestionReportes = gestionReportes;
        this.gestionAnimales = gestionAnimales;
        this.gestionAdopciones = gestionAdopciones;
    }

    // LÓGICA DE PRECARGA INICIAL DE DATOS
    // Método para inicializar los datos de prueba en memoria respetando las reglas aprobadas del sistema
    public void precargarDatosIniciales() {
        // 1. Precarga de 2 usuarios (1 Rescatista y 1 Adoptante mayor de edad)
        Rescatista rescatista1 = new Rescatista("0912345678", "Carlos Mendoza", 35);
        Adoptante adoptante1 = new Adoptante("0987654321", "Ana Gómez", 25);
        gestionUsuarios.registrarUsuario(rescatista1);
        gestionUsuarios.registrarUsuario(adoptante1);

        // 2. Precarga de 2 reportes de avistamiento independientes
        Reporte reporte1 = new Reporte("Av. 9 de Octubre y Chile", "En Tratamiento", "Herida leve en pata posterior", "Centro Norte", "0912345678");
        Reporte reporte2 = new Reporte("Parque Centenario", "Sano", "En buen estado general", "Centro Sur", "0912345678");
        gestionReportes.registrarReporte(reporte1);
        gestionReportes.registrarReporte(reporte2);

        // 3. Precarga de 4 mascotas (2 perros y 2 gatos) distribuidas en 2 centros de adopción
        Perro perro1 = new Perro("Max", 3, 12.5, "Centro Norte", "Sano", "Vacunado y desparasitado", "Mediano");
        Perro perro2 = new Perro("Rocky", 2, 18.0, "Centro Sur", "En Tratamiento", "Bajo tratamiento dermatológico", "Grande");
        Gato gato1 = new Gato("Michi", 1, 4.0, "Centro Norte", "Sano", "Esterilizado y sano");
        Gato gato2 = new Gato("Luna", 2, 3.5, "Centro Sur", "Sano", "Vacunada en buen estado");

        gestionAnimales.registrarAnimal(perro1);
        gestionAnimales.registrarAnimal(perro2);
        gestionAnimales.registrarAnimal(gato1);
        gestionAnimales.registrarAnimal(gato2);
    }

    // CONSULTAS Y REPORTES BÁSICOS IMPLEMENTADOS
    // REPORTE 1: Listado general de animales disponibles para adopción
    public ArrayList<AnimalRescatado> obtenerReporteAnimalesDisponibles() {
        ArrayList<AnimalRescatado> disponibles = new ArrayList<>();
        if (gestionAnimales != null) {
            for (AnimalRescatado animal : gestionAnimales.getListaAnimales()) {
                if ("Disponible".equalsIgnoreCase(animal.getEstadoAdopcion())) {
                    disponibles.add(animal);
                }
            }
        }
        return disponibles;
    }

    // REPORTE 2: Reporte de adopciones aprobadas (muestra nombre de la mascota y nombre del adoptante)
    public ArrayList<String> obtenerReporteAdopcionesAprobadas() {
        ArrayList<String> reporteAdopciones = new ArrayList<>();
        if (gestionAdopciones != null && gestionAnimales != null && gestionUsuarios != null) {
            for (Solicitud solicitud : gestionAdopciones.getListaSolicitudes()) {
                if ("Aprobada".equalsIgnoreCase(solicitud.getEstadoSolicitud())) {
                    AnimalRescatado animal = gestionAnimales.buscarAnimalPorId(solicitud.getIdMascota());
                    Usuario usuario = gestionUsuarios.buscarUsuarioPorCedula(solicitud.getCedulaAdoptante());

                    String nombreMascota = (animal != null) ? animal.getNombre() : "Desconocida";
                    String nombreAdoptante = (usuario != null) ? usuario.getNombreCompleto() : "Desconocido";

                    String registro = "Mascota: " + nombreMascota + " (ID: " + solicitud.getIdMascota() + ")" +
                                      " | Adoptante: " + nombreAdoptante + " (Cédula: " + solicitud.getCedulaAdoptante() + ")";
                    reporteAdopciones.add(registro);
                }
            }
        }
        return reporteAdopciones;
    }

    // REPORTE 3: Listado general de reportes de avistamiento
    public ArrayList<Reporte> obtenerReporteAvistamientos() {
        if (gestionReportes != null) {
            return gestionReportes.getListaReportes();
        }
        return new ArrayList<>();
    }
}