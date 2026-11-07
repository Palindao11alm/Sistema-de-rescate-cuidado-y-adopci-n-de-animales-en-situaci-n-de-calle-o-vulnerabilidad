package vista;

import controlador.GestionAdopciones;
import controlador.GestionAnimales;
import controlador.GestionConsultasYPrecarga;
import controlador.GestionReportes;
import controlador.GestionUsuarios;

//Clase MainGestionConsultaYPrecarga, instancia los controladores de todos los módulos del sistema, crea el controlador de consultas y precarga e inicia la prueba interactiva por consola.
public class MainGestionConsultasYPrecarga {

    public static void main(String[] args) {
        // Instanciación de todos los controladores del sistema para compartir las colecciones en memoria
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        GestionReportes gestionReportes = new GestionReportes();
        GestionAnimales gestionAnimales = new GestionAnimales();
        GestionAdopciones gestionAdopciones = new GestionAdopciones(gestionUsuarios, gestionAnimales);

        // Instanciación del controlador del módulo de consultas y precarga inyectando los controladores del sistema
        GestionConsultasYPrecarga gestionConsultas = new GestionConsultasYPrecarga(gestionUsuarios, gestionReportes, gestionAnimales, gestionAdopciones);

        // Instanciación del menú del módulo inyectando el controlador de consultas
        MenuGestionConsultasYPrecarga menuConsultas = new MenuGestionConsultasYPrecarga(gestionConsultas);

        // Ejecución del método que inicia el submenú interactivo del módulo por consola
        menuConsultas.iniciar();
    }
}