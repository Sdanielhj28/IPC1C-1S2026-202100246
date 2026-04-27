package sancarlistaacademy.modelo;

import java.io.Serializable;

public class Seccion implements Serializable{
    private String codigoSeccion;
    private String codigoCurso;
    private String codigoInstructor;
    private String semestre;
    private String horario;
    private boolean abierta;
    
    private String[] estudiantesInscritos;
    private int cantidadInscritos;
    
    public Seccion(String codigoSeccion, String codigoCurso, String codigoInstructor, String semestre, String horario){
        this.codigoSeccion = codigoSeccion;
        this.codigoCurso = codigoCurso;
        this.codigoInstructor = codigoInstructor;
        this.semestre = semestre;
        this.horario = horario;
        this.abierta = true;
        
        this.estudiantesInscritos = new String[200];
        this.cantidadInscritos = 0;
    }
    
    public String getCodigoSeccion(){
        return codigoSeccion;
    }
    
    public String getCodigoCurso(){
        return codigoCurso;
    }
    
    public String getCodigoInstructor(){
        return codigoInstructor;
    }
    
    public void setCodigoInstructor(String codigoInstructor){
        this.codigoInstructor = codigoInstructor;
    }
    
    public String getSemestre(){
        return semestre;
    }
    
    public String getHorario(){
        return horario;
    }
    
    public boolean isAbierta(){
        return abierta;
    }
    
    public void setAbierta(boolean abierta){
        this.abierta = abierta;
    }
    
    public int getCantidadInscritos(){
        return cantidadInscritos;
    }
    
    public boolean yaInscrito(String codigoEstudiante){
        for (int i = 0; i < cantidadInscritos; i++){
            if (estudiantesInscritos[i].equals(codigoEstudiante)){
                return true;
            }
        }
        return false;
    }
    
    public boolean inscribirEstudiante(String codigoEstudiante) {
        if (cantidadInscritos >= estudiantesInscritos.length || yaInscrito(codigoEstudiante)) {
            return false;
        }
        estudiantesInscritos[cantidadInscritos] = codigoEstudiante;
        cantidadInscritos++;
        return true;
    }
    
    public boolean desasignarEstudiante(String codigoEstudiante) {
        for (int i = 0; i < cantidadInscritos; i++) {
            if (estudiantesInscritos[i].equals(codigoEstudiante)) {
                for (int j = i; j < cantidadInscritos - 1; j++) {
                    estudiantesInscritos[j] = estudiantesInscritos[j + 1];
                }
                estudiantesInscritos[cantidadInscritos - 1] = null;
                cantidadInscritos--;
                return true;
            }
        }
        return false;
    }
    
    public String[] getEstudiantesInscritos(){
        return estudiantesInscritos;
    }
}
/**
 *
 * @author danie
 */
