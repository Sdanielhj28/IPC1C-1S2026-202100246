package sancarlistaacademy.controlador;

import sancarlistaacademy.modelo.SistemaAcademy;
import sancarlistaacademy.modelo.Usuario;

public class LoginControlador {
    
    private SistemaAcademy sistema;
    
    public LoginControlador(SistemaAcademy sistema) {
        this.sistema = sistema;
    }
    
    public Usuario iniciarSesion(String codigo, String contrasena){
        if (codigo == null || codigo.trim().isEmpty()){
            return null;
        }
        
        if (contrasena == null || contrasena.trim().isEmpty()){
            return null;
        }
        return sistema.autenticar(codigo.trim(), contrasena);
    }
}
/**
 *
 * @author danie
 */
