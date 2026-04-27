package sancarlistaacademy.modelo;

public class Estudiante extends Usuario{
    private int cantidadCursosInscritos;
    
    public Estudiante(String codigo, String nombre, String fechaNacimiento, String genero, String contrasena){
       super(codigo, nombre, fechaNacimiento, genero, contrasena, "ESTUDIANTE");
       this.cantidadCursosInscritos = 0;
    }
    
    public int getCantidadCursosInscritos(){
        return cantidadCursosInscritos;
    }
    
    public void aumentarCursos(){
        cantidadCursosInscritos++;
    }
    public void disminuirCursos(){
        if (cantidadCursosInscritos > 0){
            cantidadCursosInscritos--;
        }
    }
}
/**
 *
 * @author danie
 */