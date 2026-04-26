/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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