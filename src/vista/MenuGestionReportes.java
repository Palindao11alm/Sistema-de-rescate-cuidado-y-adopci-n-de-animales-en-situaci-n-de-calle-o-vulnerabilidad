package vista;

import controlador.GestionReportes; //Importaciones de las Clases GestionReportes y Reporte
import modelo.Reporte;
import java.util.ArrayList;
import java.util.Scanner;

//Clase MenuGestionReportes(interfaz interactiva por consola),despliega el menú de opciones correspondiente al módulo de Gestión de Reportes, captura datos por teclado e invoca a los métodos del controlador.
public class MenuGestionReportes {

    // ATRIBUTOS DE LA CLASE
    // Referencia al controlador del módulo para delegar la lógica de negocio y persistencia en memoria
    private GestionReportes gestionReportes;
    private Scanner scanner;

    // MÉTODOS DE LA CLASE - CONSTRUCTOR
    public MenuGestionReportes(GestionReportes gestionReportes) {
        this.gestionReportes = gestionReportes;
        this.scanner = new Scanner(System.in);
    }

    // MÉTODOS DE LA CLASE - CICLO Y ESTRUCTURA DE CONTROL
    // Método principal que ejecuta el ciclo del menú e interactúa mediante el switch
    public void iniciar() {
        boolean volver = false;
        do {
            mostrarOpciones();
            int opcion = obtenerOpcion();
            switch (opcion) {
                case 1:
                    registrarReporteAux();
                    break;
                case 2:
                    buscarReportePorIdAux();
                    break;
                case 3:
                    listarReportesAux();
                    break;
                case 4:
                    volver = true;
                    System.out.println("Regresando al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        } while (!volver);
    }

    // MÉTODOS AUXILIARES DE PRESENTACIÓN Y LECTURA
    // Despliega el menú de opciones del módulo en la consola
    private void mostrarOpciones() {
        System.out.println("\n--- GESTION DE REPORTES DE ANIMALITOS ---");
        System.out.println("1. Registrar un nuevo reporte");
        System.out.println("2. Buscar un reporte por su ID");
        System.out.println("3. Listar todos los reportes de animalitos");
        System.out.println("4. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }

    // Lee de forma segura la opción numérica seleccionada por el usuario
    private int obtenerOpcion() {
        int opcion = -1;
        if (scanner.hasNextInt()) {
            opcion = scanner.nextInt();
        }
        scanner.nextLine();
        return opcion;
    }

    // MÉTODOS AUXILIARES PARA CADA OPCIÓN DEL MENÚ
    // Método auxiliar para la Opción 1: Captura los datos del reporte por teclado y llama al controlador
    private void registrarReporteAux() {
        System.out.println("\n-- Registrar Reporte --");
        
        System.out.print("Ingrese la ubicacion en donde encontro al animalito: ");
        String ubicacion = scanner.nextLine().trim();

        System.out.print("Ingrese el estado de salud del animalito (ej. Sano, En Tratamiento, Urgencia): ");
        String estadoSaludInicial = scanner.nextLine().trim();

        System.out.print("Ingrese una descripcion del estado de salud del animalito: ");
        String descripcionSalud = scanner.nextLine().trim();

        System.out.print("Ingrese el nombre del centro de adopcion al que se lo va a llevar: ");
        String centroAdopcion = scanner.nextLine().trim();

        System.out.print("Ingrese la cedula del rescatista responsable: ");
        String cedulaRescatista = scanner.nextLine().trim();

        // Instanciación del objeto Reporte (el identificador único se autogenera internamente en el constructor)
        Reporte nuevoReporte = new Reporte(ubicacion, estadoSaludInicial, descripcionSalud, centroAdopcion, cedulaRescatista);

        // Invocación al método del controlador para registrar y validar
        boolean exito = gestionReportes.registrarReporte(nuevoReporte);

        if (exito) {
            System.out.println("Reporte registrado exitosamente con el ID autogenerado: " + nuevoReporte.getIdReporte());
        } else {
            System.out.println("Error al registrar el reporte. Verifique que ninguno de los campos requeridos este vacio.");
        }
    }

    // Método auxiliar para la Opción 2: Solicita un ID por teclado y consulta su existencia en el controlador
    private void buscarReportePorIdAux() {
        System.out.println("\n-- Buscar Reporte por ID --");
        System.out.print("Ingrese el ID del reporte a buscar (ej. REP-001): ");
        String idReporte = scanner.nextLine().trim();

        Reporte reporteEncontrado = gestionReportes.buscarReportePorId(idReporte);

        if (reporteEncontrado != null) {
            System.out.println("Reporte encontrado:");
            System.out.println(reporteEncontrado.toString());
        } else {
            System.out.println("No se encontro ningun reporte con el ID ingresado.");
        }
    }

    // Método auxiliar para la Opción 3: Obtiene la lista completa de reportes del controlador y la imprime
    private void listarReportesAux() {
        System.out.println("\n-- Listado de Reportes de Animalitos --");
        ArrayList<Reporte> reportes = gestionReportes.getListaReportes();

        if (reportes.isEmpty()) {
            System.out.println("No hay reportes de animalitos registrados en el sistema.");
        } else {
            for (Reporte r : reportes) {
                System.out.println(r.toString());
            }
        }
    }
}