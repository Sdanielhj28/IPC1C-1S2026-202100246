package sancarlistaacademy.modelo;

public class Instructor extends Usuario{
    private int cantidadSeccionesAsignadas;
    
    public Instructor(String codigo, String nombre, String fechaNacimiento, String genero, String contrasena){
        super(codigo, nombre, fechaNacimiento, genero, contrasena, "INSTRUCTOR");
        this.cantidadSeccionesAsignadas = 0;
    }
    
    public int getCantidadSeccionesAsignadas(){
        return cantidadSeccionesAsignadas;
    }
    
    public void aumentarSecciones(){
        cantidadSeccionesAsignadas++;
    }
    
    public void disminuirSecciones(){
        if (cantidadSeccionesAsignadas > 0){
            cantidadSeccionesAsignadas--;
        }
    }
}
/**
 *
 * @author danie
 */