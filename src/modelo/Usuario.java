package modelo;
import java.util.Objects; //importación de la Clase Objects para trabajar más adelante con Objects.equals()
public abstract class Usuario { //Creación de clase abstracta Usuario
    
    private String cedula; //Atributos privados de la clase abstracta Usuario
    private String nombreCompleto;
    private int edad;
    private String rol;
    
    public Usuario() { //Constructor por defecto de Usuario, para instanciar objetos sin necesidad de parametros iniciales
    }

    public Usuario(String cedula, String nombreCompleto, int edad, String rol) { //Constructor con parametros de Usuario
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.rol = rol;
    }

    public String getCedula() { //Getters y Setters de los atributos de Usuario
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    @Override //Sobreescritura del metodo equals() de la clase Objects para evitar registros duplicados (no pueden haber usuarios con la misma cedula)
    public boolean equals(Object obj) {  
        if (this == obj) return true; //Validación de si los dos objetos son iguales
        if (obj == null || getClass() != obj.getClass()) return false; //Validación para evitar objetos nulos o de diferentes Clases
        Usuario usuario = (Usuario) obj; //Conversión de obj de tipo Object a tipo Usuario para utilizar los atributos de Usuario
        return Objects.equals(cedula, usuario.cedula); //Comparación de cedula para ambos objetos, si tienen la misma cedula
    }
    
    @Override
    public String toString() { //Construcción de cadena de texto para mostrar los atributos de Ususario y utilizarlos despues con las subclases
        return "Cédula: " + cedula + " | Nombre: " + nombreCompleto + " | Edad: " + edad + " | Rol: " + rol; 
    }  
}

