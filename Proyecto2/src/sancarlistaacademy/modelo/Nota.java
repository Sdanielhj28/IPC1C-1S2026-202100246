/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sancarlistaacademy.modelo;

import java.io.Serializable;

public class Nota implements Serializable {
    private String codigoCurso;
    private String codigoSeccion;
    private String codigoEstudiante;
    private double ponderacion;
    private double nota;
    private String fechaRegistro;
    private String etiqueta;
    
    public Nota(String codigoCurso, String codigoSeccion, String codigoEstudiante,double ponderacion, double nota, String fechaRegistro, String etiqueta) {
       this.codigoCurso = codigoCurso;
       this.codigoSeccion = codigoSeccion;
       this.codigoEstudiante = codigoEstudiante;
       this.ponderacion = ponderacion;
       this.nota = nota;
       this.fechaRegistro = fechaRegistro;
       this.etiqueta = etiqueta;
    }
    
    public String getCodigoCurso(){
        return codigoCurso;
    }
    
    public String getCodigoSeccion(){
        return codigoSeccion;
    }
    
    public String getCodigoEstudiante(){
        return codigoEstudiante;
    }
    
    public double getPonderacion(){
        return ponderacion;
    }
    
    public void setPonderacion(double ponderacion){
        this.ponderacion = ponderacion;
    }
    
    public double getNota(){
        return nota;
    }
    
    public void setNota(double nota){
        this.nota = nota;
    }
    
    public String getFechaRegistro(){
        return fechaRegistro;
    }
    
    public String getEtiqueta(){
        return etiqueta;
    }
}
/**
 *
 * @author danie
 */
