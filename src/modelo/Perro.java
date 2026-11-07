package modelo;

//Clase Perro hereda de AnimalRescatado, representa una mascota canina e implementa la regla polimórfica de cálculo de costo por tamaño.
public class Perro extends AnimalRescatado {

    // ATRIBUTOS DE LA CLASE
    private String tamano; // Valores válidos: "Pequeño", "Mediano", "Grande"

    //CONSTRUCTORES
    public Perro() {
        super();
        setEspecie("Perro");
    }

    public Perro(String nombre, int edad, double peso, String centroAdopcion, String estadoSalud, String descripcionMedica, String tamano) {
        super(nombre, "Perro", edad, peso, centroAdopcion, estadoSalud, descripcionMedica);
        this.tamano = tamano;
    }

    //GETTERS Y SETTERS
    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    // LÓGICA POLIMÓRFICA IMPLEMENTADA
    // Calcula el costo mensual multiplicando una tarifa base según la clasificación de tamaño
    @Override
    public double calcularCostoCuidado() {
        double tarifaBase = 30.0;
        if (tamano == null) {
            return tarifaBase;
        }
        switch (tamano.trim().toLowerCase()) {
            case "pequeño":
            case "pequeno":
                return tarifaBase * 1.0; // $30.00
            case "mediano":
                return tarifaBase * 1.5; // $45.00
            case "grande":
                return tarifaBase * 2.0; // $60.00
            default:
                return tarifaBase;
        }
    }

    @Override
    public String toString() {
        return "[Perro] " + super.toString() + " | Tamaño: " + tamano;
    }
}