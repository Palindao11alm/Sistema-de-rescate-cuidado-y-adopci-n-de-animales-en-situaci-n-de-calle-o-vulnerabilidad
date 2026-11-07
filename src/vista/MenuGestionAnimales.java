package vista;

import controlador.GestionAnimales;
import modelo.AnimalRescatado;
import modelo.Gato;
import modelo.Perro;
import java.util.ArrayList;
import java.util.Scanner;

//Clase MenuGestionAnimales, despliega el menú de opciones correspondiente al módulo de Gestión e Inventario de Animales Rescatados, captura datos por teclado e invoca a los métodos del controlador.
public class MenuGestionAnimales {

    // ATRIBUTOS DE LA CLASE
    // Referencia al controlador del módulo para delegar la lógica de negocio e inventario en memoria
    private GestionAnimales gestionAnimales;
    private Scanner scanner;

    //CONSTRUCTOR
    public MenuGestionAnimales(GestionAnimales gestionAnimales) {
        this.gestionAnimales = gestionAnimales;
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
                    registrarAnimalAux();
                    break;
                case 2:
                    buscarAnimalPorIdAux();
                    break;
                case 3:
                    listarInventarioAnimalesAux();
                    break;
                case 4:
                    volver = true;
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (!volver);
    }

    // MÉTODOS AUXILIARES DE PRESENTACIÓN Y LECTURA
    // Despliega las opciones del módulo en la consola
    private void mostrarOpciones() {
        System.out.println("\n--- GESTION E INVENTARIO DE ANIMALES RESCATADOS ---");
        System.out.println("1. Registrar un nuevo animal en el inventario");
        System.out.println("2. Buscar un animal por su ID");
        System.out.println("3. Consultar el inventario completo de animales");
        System.out.println("4. Volver al menu principal");
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
    // Método auxiliar para la Opción 1: Captura los datos de la mascota (Perro o Gato) y delega la validación y registro al controlador
    private void registrarAnimalAux() {
        System.out.println("\n-- Registrar Animalito Rescatado --");
        System.out.println("Seleccione la especie del animal:");
        System.out.println("1. Perro");
        System.out.println("2. Gato");
        System.out.print("Opcion: ");
        int opcionEspecie = obtenerOpcion();

        if (opcionEspecie != 1 && opcionEspecie != 2) {
            System.out.println("Especie no valida. Operacion cancelada.");
            return;
        }

        System.out.print("Ingrese el nombre del animalito: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese edad (años): ");
        int edad = -1;
        if (scanner.hasNextInt()) {
            edad = scanner.nextInt();
        }
        scanner.nextLine();

        System.out.print("Ingrese peso (kg): ");
        double peso = -1.0;
        if (scanner.hasNextDouble()) {
            peso = scanner.nextDouble();
        }
        scanner.nextLine();

        System.out.print("Ingrese el nombre del centro de adopción de residencia: ");
        String centroAdopcion = scanner.nextLine().trim();

        System.out.print("Ingrese el estado de salud del animalito (Sano, En Tratamiento, Urgencia): ");
        String estadoSalud = scanner.nextLine().trim();

        System.out.print("Ingrese la descripcion medica del animalito: ");
        String descripcionMedica = scanner.nextLine().trim();

        AnimalRescatado nuevoAnimal = null;

        if (opcionEspecie == 1) {
            System.out.print("Ingrese el tamaño del perro (Pequeño, Mediano, Grande): ");
            String tamano = scanner.nextLine().trim();
            nuevoAnimal = new Perro(nombre, edad, peso, centroAdopcion, estadoSalud, descripcionMedica, tamano);
        } else if (opcionEspecie == 2) {
            nuevoAnimal = new Gato(nombre, edad, peso, centroAdopcion, estadoSalud, descripcionMedica);
        }

        // Delegación de validación y almacenamiento al controlador del módulo
        boolean exito = gestionAnimales.registrarAnimal(nuevoAnimal);

        if (exito) {
            System.out.println("Animalito registrado exitosamente en el inventario con el ID autogenerado: " + nuevoAnimal.getIdMascota());
        } else {
            System.out.println("Error al registrar al animalito. Verifique que no haya datos vacíos, que la edad sea >= 0, el peso > 0 y el estado de salud sea valido.");
        }
    }

    // Método auxiliar para la Opción 2: Solicita un ID por consola y consulta la mascota en el controlador
    private void buscarAnimalPorIdAux() {
        System.out.println("\n-- Buscar Animalito por su ID --");
        System.out.print("Ingrese el ID del animalito a buscar (ej. ANM-001): ");
        String idMascota = scanner.nextLine().trim();

        AnimalRescatado animalEncontrado = gestionAnimales.buscarAnimalPorId(idMascota);

        if (animalEncontrado != null) {
            System.out.println("Animalito encontrado:");
            System.out.println(animalEncontrado.toString());
        } else {
            System.out.println("No se encontro ningun animalito registrada con el ID ingresado.");
        }
    }

    // Método auxiliar para la Opción 3: Obtiene la lista completa de animales del inventario y la presenta en consola
    private void listarInventarioAnimalesAux() {
        System.out.println("\n-- Inventario Completo de Animalitos Rescatados --");
        ArrayList<AnimalRescatado> animales = gestionAnimales.getListaAnimales();

        if (animales.isEmpty()) {
            System.out.println("No hay animalitos albergados en el inventario.");
        } else {
            for (AnimalRescatado a : animales) {
                System.out.println(a.toString());
            }
        }
    }
}