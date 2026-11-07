package controlador;
import modelo.Usuario; //Importación de la clase Usuario
import java.util.ArrayList;

public class GestionUsuarios {
    
    private ArrayList<Usuario> listaUsuarios; //Atributo privado de tipo ArrayList<Usuario>, llamado listaUsuarios 

    public GestionUsuarios() { //Constructor de GestionUsuarios, instancia el ArrayList
        this.listaUsuarios = new ArrayList<>();
    }
    
    public boolean esCedulaValida(String cedula) { //Metodo para la validación de la cedula
        if (cedula == null) { //Evitar que cedula sea nulo
            return false;
        } 
        return cedula.matches("^0\\d{9}$"); //Validación de que cedula debe empezar estrictamente con 0 y luego seguido de 9 digitos (10 digitos en total)
    }
    
    public boolean existeUsuario(String cedula) { //Metodo para verificar la existencia de un usuario (verifica si hay duplicados o no)
        if (cedula == null) {
            return false; //si la cedula es nula, el usuario no existe
        }
        for (Usuario usuario : listaUsuarios ) { 
            if (usuario.getCedula().equals(cedula)) { 
                return true; //Si existe un usuario en listausuarios con la misma cedula, el usuario ya existe
            }
        }
        return false; //Si no es ninguna de las dos anteriores, el usuario es nuevo, el usuario no existe (previamente)
    }
    
    public boolean registrarUsuario(Usuario nuevoUsuario) { //Metodo para registrar usuarios, donde recibe un objeto de tipo Usuario
        if (nuevoUsuario == null) {
            return false; //Si el valor del usuario es nulo, no se puede registrar
        }
        if (!esCedulaValida(nuevoUsuario.getCedula())) {
            return false; //Si su cedula no es valida, no se puede registrar
        }
        if (existeUsuario(nuevoUsuario.getCedula())) {
            return false; //Si el usuario ya existe, no se puede registrar
        }
        return this.listaUsuarios.add(nuevoUsuario); //Si pasa las anteriores validaciones, el usuario se registra/agrega a listaUsuarios
    }
    
    public Usuario buscarUsuarioPorCedula(String cedula) { //Metodo para la busqueda de un usuario en listaUsuarios
        if (cedula == null) { //si la cedula es nula, returna null
            return null;
        }
        for (Usuario usuario : listaUsuarios) { 
            if (usuario.getCedula().equals(cedula)) { 
                return usuario; //Encuentra al usuario en listaUsuarios por su cedula y retorna al mismo
            }
        }
        return null; //Si no encuentra al usuario, retorna null
    }
    
    public ArrayList<Usuario> getListaUsuarios() { //Getter que retorna el ArrayList listaUsuarios
        return listaUsuarios;
    }   
}
