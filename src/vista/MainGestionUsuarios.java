package vista;
import controlador.GestionUsuarios;

public class MainGestionUsuarios {
    public static void main(String[] args) {
        GestionUsuarios gestionUsuarios = new GestionUsuarios(); //Instanciamos el objeto gestionUsuarios(controlador general de usuarios) de la clase GestionUsuarios
        MenuGestionUsuarios menuUsuarios = new MenuGestionUsuarios(gestionUsuarios); //Instanciamos el ojeto menuUsuarios(el menu de interaccion) de la clase MenuGestionUsuarios
        menuUsuarios.iniciar(); //Inicia el objeto menuUsuarios(el menu de interaccion) mediante el metodo iniciar()
    } 
}
