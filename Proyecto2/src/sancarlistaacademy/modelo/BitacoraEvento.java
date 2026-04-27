package sancarlistaacademy.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BitacoraEvento implements Serializable{
    private String fechaHora;
    private String tipoUsuario;
    private String codigoUsuario;
    private String operacion;
    private String estado;
    private String descripcion;
    
    public BitacoraEvento(String tipoUsuario, String codigoUsuario, String operacion, String estado, String descripcion) {
        this.fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        this.tipoUsuario = tipoUsuario;
        this.codigoUsuario = codigoUsuario;
        this.operacion = operacion;
        this.estado = estado;
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return "[" + fechaHora + "] | [" + tipoUsuario + "] | [" + codigoUsuario + "] | [" + operacion + "] | [" + estado + "] | [" + descripcion + "]";
    }
}

/**
 *
 * @author danie
 */
