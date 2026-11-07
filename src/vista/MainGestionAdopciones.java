package vista;

import controlador.GestionAdopciones;
import controlador.GestionAnimales;
import controlador.GestionUsuarios;

//Clase MainGestionAdopciones, instancia los controladores de dependencias requeridos, el controlador de adopciones y el menú del módulo de Gestión y Procesamiento de Adopciones e inicia la prueba interactiva por consola.
public class MainGestionAdopciones {

    public static void main(String[] args) {
        // Instanciación de controladores dependientes para permitir validaciones cruzadas en memoria
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        GestionAnimales gestionAnimales = new GestionAnimales();

        // Instanciación del controlador del módulo de adopciones inyectando dependencias
        GestionAdopciones gestionAdopciones = new GestionAdopciones(gestionUsuarios, gestionAnimales);

        // Instanciación del menú del módulo inyectando el controlador de adopciones
        MenuGestionAdopciones menuAdopciones = new MenuGestionAdopciones(gestionAdopciones);

        // Ejecución del método que inicia el submenú interactivo del módulo por consola
        menuAdopciones.iniciar();
    }
}

