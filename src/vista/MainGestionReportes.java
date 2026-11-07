package vista;
import controlador.GestionReportes;

//Clase Main, crea el controlador y el menú del módulo de Gestión de Reportes de Avistamiento e inicia la ejecución de la prueba por consola.
public class MainGestionReportes {

    public static void main(String[] args) {
        // Creación del objeto controlador del módulo Gestion de Reportes de Avistamiento
        GestionReportes gestionReportes = new GestionReportes();
        
        // Creación del objeto menú inyectando la referencia del controlador
        MenuGestionReportes menuReportes = new MenuGestionReportes(gestionReportes);
        
        // Llamada al método que inicia la ejecución del menú interactivo por consola
        menuReportes.iniciar();
    }
}
