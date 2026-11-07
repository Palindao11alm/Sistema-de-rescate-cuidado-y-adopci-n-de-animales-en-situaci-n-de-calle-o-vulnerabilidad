package vista;

import controlador.GestionAdopciones;
import modelo.Solicitud;
import java.util.ArrayList;
import java.util.Scanner;

//Clase MenuGestionAdopciones, despliega el menú de opciones correspondiente exclusivamente al módulo de Gestión y Procesamiento de Adopciones, captura datos por teclado e invoca a los métodos del controlador.
public class MenuGestionAdopciones {

    // ATRIBUTOS DE LA CLASE
    // Referencia al controlador del módulo para delegar la lógica de negocio y almacenamiento en memoria
    private GestionAdopciones gestionAdopciones;
    private Scanner scanner;

    //CONSTRUCTOR
    public MenuGestionAdopciones(GestionAdopciones gestionAdopciones) {
        this.gestionAdopciones = gestionAdopciones;
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
                    registrarSolicitudAux();
                    break;
                case 2:
                    evaluarSolicitudAux();
                    break;
                case 3:
                    buscarSolicitudPorIdAux();
                    break;
                case 4:
                    listarSolicitudesAux();
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
        System.out.println("\n--- GESTION Y PROCESAMIENTO DE ADOPCIONES ---");
        System.out.println("1. Registrar nueva solicitud de adopcion");
        System.out.println("2. Evaluar / Procesar solicitud de adopcion");
        System.out.println("3. Buscar solicitud por ID");
        System.out.println("4. Listar todas las solicitudes de adopcion");
        System.out.println("5. Volver al menu principal");
        System.out.print("Seleccione una opcion: ");
    }

    // Lee de forma segura la opción numérica ingresada por teclado
    private int obtenerOpcion() {
        int opcion = -1;
        if (scanner.hasNextInt()) {
            opcion = scanner.nextInt();
        }
        scanner.nextLine();
        return opcion;
    }

    // MÉTODOS AUXILIARES PARA CADA OPCIÓN DEL MENÚ
    // Método auxiliar para la Opción 1: Captura la cédula del adoptante e ID de la mascota y solicita la registración al controlador
    private void registrarSolicitudAux() {
        System.out.println("\n-- Registrar Solicitud de Adopcion --");
        System.out.print("Ingrese la cedula del adoptante: ");
        String cedulaAdoptante = scanner.nextLine().trim();

        System.out.print("Ingrese el ID de la mascota a adoptar (ej. ANM-001): ");
        String idMascota = scanner.nextLine().trim();

        // Instanciación del objeto Solicitud (el ID se autogenera y el estado inicia en 'Pendiente')
        Solicitud nuevaSolicitud = new Solicitud(cedulaAdoptante, idMascota);

        // Invocación al método del controlador que aplica las validaciones de negocio
        boolean exito = gestionAdopciones.registrarSolicitud(nuevaSolicitud);

        if (exito) {
            System.out.println("Solicitud de adopcion registrada exitosamente con el ID autogenerado: " + nuevaSolicitud.getIdSolicitud());
        } else {
            System.out.println("Error al registrar la solicitud. Verifique que el usuario exista, sea Adoptante mayor de edad (>= 18 años) y que la mascota exista, esté 'Sana' y 'Disponible'.");
        }
    }

    // Método auxiliar para la Opción 2: Captura el ID de la solicitud y la resolución (Aprobar/Rechazar) para ser procesada por el controlador
    private void evaluarSolicitudAux() {
        System.out.println("\n-- Evaluar / Procesar Solicitud de Adopcion --");
        System.out.print("Ingrese el ID de la solicitud a evaluar (ej. SOL-001): ");
        String idSolicitud = scanner.nextLine().trim();

        System.out.println("Seleccione la decisión de evaluacion:");
        System.out.println("1. Aprobar solicitud");
        System.out.println("2. Rechazar solicitud");
        System.out.print("Opcion: ");
        int decision = obtenerOpcion();

        if (decision != 1 && decision != 2) {
            System.out.println("Opcion de decision no valida. Operacion cancelada.");
            return;
        }

        boolean aprobada = (decision == 1);

        // Invocación al controlador para procesar la evaluación y actualizar estados automáticamente
        boolean exito = gestionAdopciones.evaluarSolicitud(idSolicitud, aprobada);

        if (exito) {
            if (aprobada) {
                System.out.println("Solicitud aprobada exitosamente. La mascota ha pasado a estado 'Adoptado' y las demas solicitudes pendientes vinculadas fueron rechazadas automaticamente.");
            } else {
                System.out.println("Solicitud rechazada exitosamente.");
            }
        } else {
            System.out.println("Error al evaluar la solicitud. Verifique que el ID ingresado exista, se encuentre en estado 'Pendiente' y la mascota permanezca disponible.");
        }
    }

    // Método auxiliar para la Opción 3: Solicita un ID por consola y consulta la solicitud en el controlador
    private void buscarSolicitudPorIdAux() {
        System.out.println("\n-- Buscar Solicitud por ID --");
        System.out.print("Ingrese el ID de la solicitud a buscar (ej. SOL-001): ");
        String idSolicitud = scanner.nextLine().trim();

        Solicitud solicitudEncontrada = gestionAdopciones.buscarSolicitudPorId(idSolicitud);

        if (solicitudEncontrada != null) {
            System.out.println("Solicitud encontrada:");
            System.out.println(solicitudEncontrada.toString());
        } else {
            System.out.println("No se encontro ninguna solicitud registrada con el ID ingresado.");
        }
    }

    // Método auxiliar para la Opción 4: Obtiene la lista completa de solicitudes del controlador y la presenta en consola
    private void listarSolicitudesAux() {
        System.out.println("\n-- Listado General de Solicitudes de Adopcion --");
        ArrayList<Solicitud> solicitudes = gestionAdopciones.getListaSolicitudes();

        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes de adopción registradas en el sistema.");
        } else {
            for (Solicitud s : solicitudes) {
                System.out.println(s.toString());
            }
        }
    }
}