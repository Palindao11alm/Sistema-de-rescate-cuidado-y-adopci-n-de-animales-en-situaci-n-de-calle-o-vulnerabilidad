package modelo;
public class Adoptante extends Usuario { //Subclase Adoptante hereda de Usuario
    
    public Adoptante() { //Constructor por defecto de Adoptante
        super();
        setRol("Adoptante"); //Asigna por defecto el rol de Adoptante al usuario
    }

    public Adoptante(String cedula, String nombreCompleto, int edad) { //Constructor con parametros de Adoptante
        super(cedula, nombreCompleto, edad, "Adoptante"); //Pide los parametros al padre y asigna por defecto el rol de Adoptante al usuario
 
    }
    
    public boolean esMayorDeEdad() { //Método para la validación de si el adoptante es mayor de edad
        return getEdad()>=18;
    }
    
    @Override
    public String toString() { //Agregar a la cadena de texto de Usuario la etiqueta de rol de Adoptante
        return "[Adoptante]" + super.toString();
    }
}
