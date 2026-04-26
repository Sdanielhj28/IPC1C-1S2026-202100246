/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sancarlistaacademy.modelo;

import java.io.Serializable;

public class Curso implements Serializable{
    private String codigo;
    private String nombre;
    private String descripcion;
    private int creditos;
    private String seccion;
    
    public Curso(String codigo, String nombre, String descripcion, int creditos, String seccion){
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.seccion = seccion;
    }
    
    public String getCodigo(){
        return codigo;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public int getCreditos(){
        return creditos;
    }
    
    public void setCreditos(int creditos){
        this.creditos = creditos;
    }
    
    public String getSeccion(){
        return seccion;
    }
    
    public void setSeccion(String seccion){
        this.seccion = seccion;
    }
}
/**
 *
 * @author danie
 */