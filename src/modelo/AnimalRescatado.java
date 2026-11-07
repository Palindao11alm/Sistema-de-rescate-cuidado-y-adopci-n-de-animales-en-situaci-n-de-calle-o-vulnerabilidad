package modelo;
import java.util.Objects;

//Clase abstracta AnimalRescatado, define los atributos y comportamientos del animal albergado en el sistema, sirviendo de superclase para Perro y Gato.
public abstract class AnimalRescatado {

    // ATRIBUTOS DE LA CLASE
    // Atributo estático para autogenerar identificadores secuenciales únicos (ej. ANM-001)
    private static int contadorId = 1;

    private String idMascota;
    private String nombre;
    private String especie;
    private int edad;
    private double peso;
    private String centroAdopcion;
    private String estadoSalud;       // Valores válidos: "Sano", "En Tratamiento", "Urgencia"
    private String descripcionMedica;
    private String estadoAdopcion;    // Valores válidos: "Disponible", "Adoptado"

    // Constructor por defecto
    public AnimalRescatado() {
        this.idMascota = "ANM-" + String.format("%03d", contadorId++);
        this.estadoAdopcion = "Disponible";
    }

    // Constructor con parámetros
    public AnimalRescatado(String nombre, String especie, int edad, double peso, String centroAdopcion, String estadoSalud, String descripcionMedica) {
        this.idMascota = "ANM-" + String.format("%03d", contadorId++);
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.peso = peso;
        this.centroAdopcion = centroAdopcion;
        this.estadoSalud = estadoSalud;
        this.descripcionMedica = descripcionMedica;
        this.estadoAdopcion = "Disponible";
    }

    //GETTERS Y SETTERS de AnimalRescatado
    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCentroAdopcion() {
        return centroAdopcion;
    }

    public void setCentroAdopcion(String centroAdopcion) {
        this.centroAdopcion = centroAdopcion;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public String getDescripcionMedica() {
        return descripcionMedica;
    }

    public void setDescripcionMedica(String descripcionMedica) {
        this.descripcionMedica = descripcionMedica;
    }

    public String getEstadoAdopcion() {
        return estadoAdopcion;
    }

    public void setEstadoAdopcion(String estadoAdopcion) {
        this.estadoAdopcion = estadoAdopcion;
    }

    // MÉTODOS ABSTRACTOS Y POLIMÓRFICOS
    // Método abstracto obligatorio para calcular el costo mensual de cuidado según la especie
    public abstract double calcularCostoCuidado();

    // MÉTODOS SOBREESCRITOS (EQUALS Y TOSTRING)
    // Sobrescritura de equals para comparar animales por su identificador único (idMascota)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AnimalRescatado que = (AnimalRescatado) obj;
        return Objects.equals(idMascota, que.idMascota);
    }

    // Formato de presentación en texto del animal
    @Override
    public String toString() {
        return "ID: " + idMascota + 
               " | Nombre: " + nombre + 
               " | Especie: " + especie + 
               " | Edad: " + edad + " años" + 
               " | Peso: " + peso + " kg" + 
               " | Centro: " + centroAdopcion + 
               " | Salud: " + estadoSalud + 
               " | Desc. Médica: " + descripcionMedica + 
               " | Estado Adopción: " + estadoAdopcion + 
               " | Costo Cuidado: $" + String.format("%.2f", calcularCostoCuidado());
    }
}