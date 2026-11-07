package vista;
import controlador.GestionUsuarios; //Importa las 4 clases del Modulo Gestion de Usuarios
import modelo.Adoptante;
import modelo.Rescatista;
import modelo.Usuario;

import java.util.ArrayList; //Importamos la Clase ArrayList
import java.util.Scanner; //Importamos libreria scanner para leer por teclado

public class MenuGestionUsuarios { //Clase MenuGestionUsuarios
    
    private GestionUsuarios gestionUsuarios; //Metodos privado gestionUsuarios de tipo GestionUsuarios
    private Scanner scanner;

    public MenuGestionUsuarios(GestionUsuarios gestionUsuarios) { //Constructor con parametros de MenuGestionUsuarios
        this.gestionUsuarios = gestionUsuarios;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() { //Metodo iniciar para el menu 
        boolean volver = false; //Variable bandera que determina si el menu debe seguir ejecutandose o regresa al menu inicial
        do { //Iniciamos el do while para que empiece por primera vez 
            mostrarOpciones();
            int opcion = obtenerOpcion();
            switch (opcion) {
                case 1:
                    registrarUsuarioAux();
                    break;
                case 2:
                    buscarUsuarioPorCedulaAux();
                    break;
                case 3:
                    listarUsuariosAux();
                    break;
                case 4:
                    volver = true;
                    System.out.println("Regresando al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        } while (!volver); //Al terminar el do, vuelve al menu principal (volver pasa a volver=true)
    }

    private void mostrarOpciones() { //Metodo para la impresion de opciones del menu 
        System.out.println("\n--- GESTION DE USUARIOS ---");
        System.out.println("1. Registrar nuevo usuario");
        System.out.println("2. Buscar usuario por cedula");
        System.out.println("3. Listar todos los usuarios");
        System.out.println("4. Volver al menu principal");
        System.out.print("Seleccione una opcion: ");
    }

    private int obtenerOpcion() { //Leer la opción que selecciona el usuario
        int opcion = -1;
        if (scanner.hasNextInt()) { //Valida si el usuario ingreso unicamente un numero entero valido
            opcion = scanner.nextInt(); //Si se cumple entonces la opción va a ser igual a lo que ingresa el usuario
        }
        scanner.nextLine();
        return opcion;
    }

    private void registrarUsuarioAux() { //Metodo de registro de usuario
        System.out.println("\n-- Registrar Usuario --");
        System.out.print("Ingrese su cedula (10 dígitos, debe iniciar con 0): ");
        String cedula = scanner.nextLine().trim();

        System.out.print("Ingrese su nombre completo: ");
        String nombreCompleto = scanner.nextLine().trim(); //Ingreso por teclado del nombre, .trim() para eliminar los espacios en blanco

        System.out.print("Ingrese su edad: ");
        int edad = -1;
        if (scanner.hasNextInt()) { //Valida si la edad es un numero entero
            edad = scanner.nextInt();
        }
        scanner.nextLine();

        System.out.println("Seleccione su rol:"); //Seleccion del rol a escoger
        System.out.println("1. Adoptante");
        System.out.println("2. Rescatista");
        System.out.print("Opcion: ");
        int opcionRol = -1;
        if (scanner.hasNextInt()) { //Valida si la opcion escogida por el usuario corresponde a un numero entero
            opcionRol = scanner.nextInt();
        }
        scanner.nextLine();

        Usuario nuevoUsuario = null;
        if (opcionRol == 1) {
            nuevoUsuario = new Adoptante(cedula, nombreCompleto, edad); //Instancia polimorficamente segun el rol que sea (Adoptante o Rescatista)
        } else if (opcionRol == 2) {
            nuevoUsuario = new Rescatista(cedula, nombreCompleto, edad);
        } else {
            System.out.println("Rol no valido. Operacion cancelada.");
            return;
        }

        boolean exito = gestionUsuarios.registrarUsuario(nuevoUsuario); //Uso del metodo registrarUsuario() para ver si cumple con las validaciones respectivas
        if (exito) { //Si cumple, usuario registrado
            System.out.println("Usuario registrado exitosamente."); 
        } else { //Si no cumple, vuelva a intentar
            System.out.println("Error al registrar el usuario. Verifique que su cedula sea valida (10 dígitos iniciando con 0) y que no se encuentre registrada previamente.");
        }
    }

    private void buscarUsuarioPorCedulaAux() {
        System.out.println("\n-- Buscar Usuario por Cedula --");
        System.out.print("Ingrese la cedula del usuario a buscar: ");
        String cedula = scanner.nextLine().trim();

        Usuario usuarioEncontrado = gestionUsuarios.buscarUsuarioPorCedula(cedula); //Uso del metodo heredado buscarUsuarioPorCedula de GestionUsuarios
        if (usuarioEncontrado != null) { //Si el valor de usuarioEncontrado no es nulo, entonces se encontro a un usuario con esa cedula
            System.out.println("Usuario encontrado:");
            System.out.println(usuarioEncontrado.toString()); //Imprime la información del usuario encontrado
        } else {
            System.out.println("No se encontró ningun usuario con la cedula ingresada.");
        }
    }

    private void listarUsuariosAux() {
        System.out.println("\n-- Listado de Usuarios Registrados --");
        ArrayList<Usuario> usuarios = gestionUsuarios.getListaUsuarios(); //variable usuarios donde su valor va a ser la listaUsuarios
        if (usuarios.isEmpty()) { //Validación de si la lista no esta vacia
            System.out.println("No hay usuarios registrados en el sistema."); //Si esta vacia, no hay usuarios
        } else {
            for (Usuario u : usuarios) { 
                System.out.println(u.toString()); //Imprime la información de cada uno de los usuarios de la listaUsuarios
            }
        }
    }  
}
