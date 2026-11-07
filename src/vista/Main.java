package vista;

import controlador.GestionAdopciones;
import controlador.GestionAnimales;
import controlador.GestionConsultasYPrecarga;
import controlador.GestionReportes;
import controlador.GestionUsuarios;
import java.util.Scanner;

/*Clase Main, inicializa todos los controladores y menús del Sistema de rescate, cuidado y adopción de animales en situación de calle o vulnerabilidad 
compartiendo una única memoria en RAM, y desplegando el menú principal interactivo.*/

public class Main {

    public static void main(String[] args) {
        
        // 1. INICIALIZACIÓN DE CONTROLADORES (Se instancian una sola vez al iniciar el programa)
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        GestionReportes gestionReportes = new GestionReportes();
        GestionAnimales gestionAnimales = new GestionAnimales();
        
        // Controladores con dependencias cruzadas en memoria
        GestionAdopciones gestionAdopciones = new GestionAdopciones(gestionUsuarios, gestionAnimales);
        GestionConsultasYPrecarga gestionConsultas = new GestionConsultasYPrecarga(
                gestionUsuarios, 
                gestionReportes, 
                gestionAnimales, 
                gestionAdopciones
        );

        // 2. INICIALIZACIÓN DE LOS MENÚS DE CADA MÓDULO (Inyección de sus respectivos controladores)
        MenuGestionUsuarios menuUsuarios = new MenuGestionUsuarios(gestionUsuarios);
        MenuGestionReportes menuReportes = new MenuGestionReportes(gestionReportes);
        MenuGestionAnimales menuAnimales = new MenuGestionAnimales(gestionAnimales);
        MenuGestionAdopciones menuAdopciones = new MenuGestionAdopciones(gestionAdopciones);
        MenuGestionConsultasYPrecarga menuConsultas = new MenuGestionConsultasYPrecarga(gestionConsultas);

        // 3. RECURSOS DE LECTURA Y CONTROL DEL MENÚ PRINCIPAL
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        // 4. CICLO PRINCIPAL DEL SISTEMA (do-while)
        do {
            System.out.println("\n==================================================");
            System.out.println("      SISTEMA DE RESCATE, CUIDADO Y ADOPCION      ");
            System.out.println(" DE ANIMALES EN SITUACION DE CALLE O VULNERABILIDAD ");
            System.out.println("==================================================");
            System.out.println("1. Modulo de Gestion de Usuarios");
            System.out.println("2. Modulo de Gestion de Reportes de Avistamiento");
            System.out.println("3. Modulo de Gestion e Inventario de Animales");
            System.out.println("4. Modulo de Gestion y Procesamiento de Adopciones");
            System.out.println("5. Modulo de Consultas, Reportes y Precarga");
            System.out.println("6. Salir del Sistema");
            System.out.print("Seleccione un modulo para ingresar: ");

            int opcion = -1;
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
            }
            scanner.nextLine(); // Limpieza del búfer de lectura

            // 5. ESTRUCTURA DE SELECCIÓN DE MÓDULOS (switch)
            switch (opcion) {
                case 1:
                    // Transferencia de control al menú del Módulo 1
                    menuUsuarios.iniciar();
                    break;
                case 2:
                    // Transferencia de control al menú del Módulo 2
                    menuReportes.iniciar();
                    break;
                case 3:
                    // Transferencia de control al menú del Módulo 3
                    menuAnimales.iniciar();
                    break;
                case 4:
                    // Transferencia de control al menú del Módulo 4
                    menuAdopciones.iniciar();
                    break;
                case 5:
                    // Transferencia de control al menú del Módulo 5
                    menuConsultas.iniciar();
                    break;
                case 6:
                    // Finalización del ciclo del menú principal
                    salir = true;
                    System.out.println("\nGracias por utilizar el Sistema de rescate, cuidado y adopcion de animales en situacion de calle o vulnerabilidad. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nOpcion no valida. Por favor, seleccione un número entre 1 y 6.");
            }

        } while (!salir);

        scanner.close();
    }
}