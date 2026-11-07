package modelo;

//Clase Gato hereda de AnimalRescatado, representa una mascota de especie felina e implementa la regla polimórfica de cálculo de costo mediante una tarifa fija de insumos.
public class Gato extends AnimalRescatado {

    //CONSTRUCTORES
    public Gato() {
        super();
        setEspecie("Gato");
    }

    public Gato(String nombre, int edad, double peso, String centroAdopcion, String estadoSalud, String descripcionMedica) {
        super(nombre, "Gato", edad, peso, centroAdopcion, estadoSalud, descripcionMedica);
    }

    // LÓGICA POLIMÓRFICA IMPLEMENTADA
    // Calcula el costo mensual retornando la tarifa fija de insumos básicos ($25.00)
    @Override
    public double calcularCostoCuidado() {
        double insumosBasicos = 25.0;
        return insumosBasicos;
    }

    @Override
    public String toString() {
        return "[Gato] " + super.toString();
    }
}