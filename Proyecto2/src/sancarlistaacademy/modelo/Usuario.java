package sancarlistaacademy.modelo;

import java.io.Serializable;

public abstract class Usuario implements Serializable{
    protected String codigo;
    protected String nombre;
    protected String fechaNacimiento;
    protected String genero;
    protected String contrasena;
    protected String rol;
    
    public Usuario(String codigo, String nombre, String fechaNacimiento, String genero, String contrasena, String rol){
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.contrasena = contrasena;
        this.rol = rol;
    }
    
    public String getCodigo(){
        return codigo;
    }
    
    public String getNombre (){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getFechaNacimiento(){
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento(String fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getGenero(){
        return genero;
    }
    
    public void setGenero(String genero){
        this.genero = genero;
    }
    
    public String getContrasena(){
        return contrasena;
    }
    
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
    
    public String getRol(){
        return rol;
    }
    
    public boolean autenticar(String pass){
        return contrasena.equals(pass);
    }
}
