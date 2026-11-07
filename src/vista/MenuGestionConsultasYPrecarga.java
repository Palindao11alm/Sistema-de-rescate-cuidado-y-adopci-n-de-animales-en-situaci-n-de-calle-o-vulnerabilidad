package vista;

import controlador.GestionConsultasYPrecarga;
import modelo.AnimalRescatado;
import modelo.Reporte;
import java.util.ArrayList;
import java.util.Scanner;

//Clase MenuGestionConsultasYPrecarga, despliega el menú de opciones correspondiente al módulo de Consultas, Reportes y Precarga del Sistema, procesa la navegación del usuario e invoca al controlador correspondiente.
public class MenuGestionConsultasYPrecarga {

    // ATRIBUTOS DE LA CLASE
    // Referencia al controlador del módulo de consultas y precarga en memoria
    private GestionConsultasYPrecarga gestionConsultas;
    private Scanner scanner;

    //CONSTRUCTOR
    public MenuGestionConsultasYPrecarga(GestionConsultasYPrecarga gestionConsultas) {
        this.gestionConsultas = gestionConsultas;
        this.scanner = new Scanner(System.in);
    }

    // MÉTODOS DE LA CLASE - CICLO Y ESTRUCTURA DE CONTROL
    // Método principal que ejecuta el ciclo repetitivo del menú e interactúa mediante el switch
    public void iniciar() {
        boolean volver = false;
        do {
            mostrarOpciones();
            int opcion = obtenerOpcion();
            switch (opcion) {
                case 1:
                    precargarDatosAux();
                    break;
                case 2:
                    consultarAnimalesDisponiblesAux();
                    break;
                case 3:
                    consultarAdopcionesAprobadasAux();
                    break;
                case 4:
                    consultarAvistamientosAux();
                    break;
                case 5:
                    volver = true;
                    System.out.println("Regresando al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        } while (!volver);
    }

    // MÉTODOS AUXILIARES DE PRESENTACIÓN Y LECTURA
    // Despliega las opciones del módulo en la consola
    private void mostrarOpciones() {
        System.out.println("\n--- CONSULTAS, REPORTES Y PRECARGA DEL SISTEMA ---");
        System.out.println("1. Precargar datos iniciales del sistema");
        System.out.println("2. Consultar animales disponibles para adopcion");
        System.out.println("3. Consultar reporte de adopciones aprobadas");
        System.out.println("4. Consultar reporte de avistamientos");
        System.out.println("5. Volver al menu principal");
        System.out.print("Seleccione una opcion: ");
    }

    private int obtenerOpcion() {
        int opcion = -1;
        if (scanner.hasNextInt()) {
            opcion = scanner.nextInt();
        }
        scanner.nextLine();
        return opcion;
    }

    // MÉTODOS AUXILIARES PARA CADA OPCIÓN DEL MENÚ
    // Método auxiliar para la Opción 1: Ejecuta la precarga de datos de prueba en memoria a través del controlador
    private void precargarDatosAux() {
        System.out.println("\n-- Precarga de Datos Iniciales --");
        gestionConsultas.precargarDatosIniciales();
        System.out.println("Datos iniciales de prueba (usuarios, reportes y mascotas) cargados exitosamente en memoria.");
    }

    // Método auxiliar para la Opción 2: Obtiene la lista de mascotas disponibles del controlador y las presenta en consola
    private void consultarAnimalesDisponiblesAux() {
        System.out.println("\n-- Reporte de Animales Disponibles para Adopcion --");
        ArrayList<AnimalRescatado> disponibles = gestionConsultas.obtenerReporteAnimalesDisponibles();

        if (disponibles.isEmpty()) {
            System.out.println("No hay animales disponibles para adopcion en este momento.");
        } else {
            for (AnimalRescatado a : disponibles) {
                System.out.println(a.toString());
            }
        }
    }

    // Método auxiliar para la Opción 3: Obtiene la lista consolidada de adopciones aprobadas y las muestra en consola
    private void consultarAdopcionesAprobadasAux() {
        System.out.println("\n-- Reporte de Adopciones Aprobadas --");
        ArrayList<String> adopcionesAprobadas = gestionConsultas.obtenerReporteAdopcionesAprobadas();

        if (adopcionesAprobadas.isEmpty()) {
            System.out.println("No hay adopciones aprobadas registradas en el sistema.");
        } else {
            for (String registro : adopcionesAprobadas) {
                System.out.println(registro);
            }
        }
    }

    // Método auxiliar para la Opción 4: Obtiene el listado completo de reportes de avistamiento y los despliega
    private void consultarAvistamientosAux() {
        System.out.println("\n-- Reporte General de Avistamientos --");
        ArrayList<Reporte> reportes = gestionConsultas.obtenerReporteAvistamientos();

        if (reportes.isEmpty()) {
            System.out.println("No existen reportes de avistamiento registrados en el sistema.");
        } else {
            for (Reporte r : reportes) {
                System.out.println(r.toString());
            }
        }
    }
}