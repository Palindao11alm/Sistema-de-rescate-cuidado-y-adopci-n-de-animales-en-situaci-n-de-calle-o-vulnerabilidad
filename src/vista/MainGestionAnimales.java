package vista;
import controlador.GestionAnimales;

//Clase MainGestionAnimales, instancia el controlador y el menú del módulo de Gestión e Inventario de Animales Rescatados e inicia la prueba interactiva por consola.
public class MainGestionAnimales {
    
    public static void main(String[] args) {
        // Instanciación del objeto controlador que administrara la colección en memoria
        GestionAnimales gestionAnimales = new GestionAnimales();
        
        // Instanciación del objeto menú inyectando la referencia del controlador
        MenuGestionAnimales menuAnimales = new MenuGestionAnimales(gestionAnimales);
        
        // Ejecución del método que inicia el submenú interactivo del módulo
        menuAnimales.iniciar();
                
    }
}