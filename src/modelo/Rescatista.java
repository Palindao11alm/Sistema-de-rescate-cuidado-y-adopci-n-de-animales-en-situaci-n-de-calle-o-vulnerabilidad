package modelo;
public class Rescatista extends Usuario { //Subclase Rescatista hereda de Usuario

    public Rescatista() { //Constructor por defecto de Rescatista
        super();
        setRol("Rescatista"); //Asigna por defecto el rol de Rescatista al usuario
    }

    public Rescatista(String cedula, String nombreCompleto, int edad) { //Constructor con parametros de Adoptante
        super(cedula, nombreCompleto, edad, "Rescatista"); //Pide los parametros al padre y asigna por defecto el rol de Rescatista al usuario
    }
    
    @Override
    public String toString() {
        return "[Rescatista]" + super.toString(); //Agregar a la cadena de texto de Usuario la etiqueta de rol de Rescatista
    }
    
}
