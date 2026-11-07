package controlador;
import modelo.AnimalRescatado;
import java.util.ArrayList;

//Clase GestionAnimales, administra el inventario centralizado de animales rescatados en memoria y ejecuta las validaciones de negocio del módulo.
public class GestionAnimales {

    // ATRIBUTOS DE LA CLASE
    // Colección dinámica para almacenar las mascotas (Perros y Gatos)
    private ArrayList<AnimalRescatado> listaAnimales;

    //CONSTRUCTOR
    public GestionAnimales() {
        this.listaAnimales = new ArrayList<>();
    }

    // VALIDACIONES IMPLEMENTADAS Y LÓGICA DE NEGOCIO
    // Método para validar que la información del animal cumpla con las reglas del sistema
    public boolean esAnimalValido(AnimalRescatado animal) {
        // VALIDACIÓN: Objeto no nulo
        if (animal == null) {
            return false;
        }
        // VALIDACIÓN: Nombre no vacío
        if (animal.getNombre() == null || animal.getNombre().trim().isEmpty()) {
            return false;
        }
        // VALIDACIÓN: Edad no negativa (>= 0)
        if (animal.getEdad() < 0) {
            return false;
        }
        // VALIDACIÓN: Peso estrictamente positivo (> 0)
        if (animal.getPeso() <= 0) {
            return false;
        }
        // VALIDACIÓN: Centro de adopción especificado
        if (animal.getCentroAdopcion() == null || animal.getCentroAdopcion().trim().isEmpty()) {
            return false;
        }
        // VALIDACIÓN: Estado de salud dentro de los valores válidos aprobados
        if (animal.getEstadoSalud() == null) {
            return false;
        }
        String salud = animal.getEstadoSalud().trim();
        if (!salud.equalsIgnoreCase("Sano") && !salud.equalsIgnoreCase("En Tratamiento") && !salud.equalsIgnoreCase("Urgencia")) {
            return false;
        }
        // VALIDACIÓN: Descripción médica no vacía
        if (animal.getDescripcionMedica() == null || animal.getDescripcionMedica().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    // Método para registrar una nueva mascota en el inventario
    public boolean registrarAnimal(AnimalRescatado nuevoAnimal) {
        // VALIDACIÓN: Verifica que la información técnica y de negocio sea correcta
        if (!esAnimalValido(nuevoAnimal)) {
            return false;
        }
        // Incorpora el animal a la coleccion listaAnimales
        return this.listaAnimales.add(nuevoAnimal);
    }

    // Método para buscar una mascota por su identificador autogenerado
    public AnimalRescatado buscarAnimalPorId(String idMascota) {
        if (idMascota == null) {
            return null;
        }
        for (AnimalRescatado animal : listaAnimales) {
            if (animal.getIdMascota().equalsIgnoreCase(idMascota.trim())) {
                return animal;
            }
        }
        return null;
    }

    // Método de consulta para obtener el inventario completo
    public ArrayList<AnimalRescatado> getListaAnimales() {
        return listaAnimales;
    }

    // Método de consulta para obtener únicamente las mascotas disponibles para adopción
    public ArrayList<AnimalRescatado> obtenerAnimalesDisponibles() {
        ArrayList<AnimalRescatado> disponibles = new ArrayList<>();
        for (AnimalRescatado animal : listaAnimales) {
            if ("Disponible".equalsIgnoreCase(animal.getEstadoAdopcion())) {
                disponibles.add(animal);
            }
        }
        return disponibles;
    }
}