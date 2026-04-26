package sancarlistaacademy.modelo;

import java.io.Serializable;

public class SistemaAcademy implements Serializable {
    
    private Usuario[] usuarios;
    private int cantidadUsuarios;
    
    private Curso[] cursos;
    private int cantidadCursos;
    
    private Seccion[] secciones;
    private int cantidadSecciones;
    
    private Nota[] notas;
    private int cantidadNotas;
    
    private BitacoraEvento[] bitacora;
    private int cantidadEventos;
    
    private int usuariosActivos;
    private int inscripcionesPendientes;
    
    public SistemaAcademy(){
        usuarios = new Usuario[300];
        cursos = new Curso[300];
        secciones = new Seccion[300];
        notas = new Nota[2000];
        bitacora = new BitacoraEvento[3000];
        
        cantidadUsuarios = 0;
        cantidadCursos = 0;
        cantidadSecciones = 0;
        cantidadNotas = 0;
        cantidadEventos = 0;
        
        usuariosActivos = 0;
        inscripcionesPendientes = 0;
    }
    
    public void inicializarAdminPorDefecto(String seccion) {
        Administrador admin = new Administrador("admin", "Administrador General", "2000-01-01", "N/A", "IPC1" + seccion);
        usuarios[cantidadUsuarios] = admin;
        cantidadUsuarios++;
        registrarEvento("ADMINISTRADOR", "admin", "INICIALIZAR_ADMIN", "EXITOSA", "Administrador por defecto creado");
    }
    
    public void registrarEvento(String tipoUsuario, String codigoUsuario, String operacion, String estado, String descripcion) {
        if (cantidadEventos < bitacora.length) {
            bitacora[cantidadEventos] = new BitacoraEvento(tipoUsuario, codigoUsuario, operacion, estado, descripcion);
            cantidadEventos++;
        }
    }
    
    public Usuario autenticar(String codigo, String pass) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getCodigo().equals(codigo) && usuarios[i].autenticar(pass)) {
                usuariosActivos++;
                registrarEvento(usuarios[i].getRol(), codigo, "LOGIN", "EXITOSA", "Inicio de sesión correcto");
                return usuarios[i];
            }
        }
        registrarEvento("DESCONOCIDO", codigo, "LOGIN", "FALLIDA", "Credenciales incorrectas");
        return null;
    }
    
    public void cerrarSesion(Usuario usuario) {
        if (usuariosActivos > 0) {
            usuariosActivos--;
        }
        registrarEvento(usuario.getRol(), usuario.getCodigo(), "LOGOUT", "EXITOSA", "Cierre de sesión correcto");
    }
    
    public boolean existeUsuario(String codigo) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }
    public Usuario buscarUsuario(String codigo) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getCodigo().equals(codigo)) {
                return usuarios[i];
            }
        }
        return null;
    }
    
    public boolean crearInstructor(String codigo, String nombre, String fechaNac, String genero, String contrasena) {
        if (cantidadUsuarios >= usuarios.length || existeUsuario(codigo)) {
            return false;
        }
        
        usuarios[cantidadUsuarios] = new Instructor(codigo, nombre, fechaNac, genero, contrasena);
        cantidadUsuarios++;
        registrarEvento("ADMINISTRADOR", "admin", "CREAR_INSTRUCTOR", "EXITOSA", "Instructor " + codigo + " creado");
        return true;
    }
    
    public boolean crearEstudiante(String codigo, String nombre, String fechaNac, String genero, String contrasena) {
        if (cantidadUsuarios >= usuarios.length || existeUsuario(codigo)) {
            return false;
        }
        
        usuarios[cantidadUsuarios] = new Estudiante(codigo, nombre, fechaNac, genero, contrasena);
        cantidadUsuarios++;
        registrarEvento("ADMINISTRADOR", "admin", "CREAR_ESTUDIANTE", "EXITOSA", "Estudiante " + codigo + " creado");
        return true;
    }
    
    public boolean crearCurso(String codigo, String nombre, String descripcion, int creditos, String seccion) {
        if (cantidadCursos >= cursos.length || buscarCurso(codigo) != null) {
            return false;
        }
        
        cursos[cantidadCursos] = new Curso(codigo, nombre, descripcion, creditos, seccion);
        cantidadCursos++;
        registrarEvento("ADMINISTRADOR", "admin", "CREAR_CURSO", "EXITOSA", "Curso " + codigo + " creado");
        return true;
    }
    
    public Curso buscarCurso(String codigo) {
        for (int i = 0; i < cantidadCursos; i++) {
           if (cursos[i].getCodigo().equals(codigo)) {
               return cursos[i];
           } 
        }
        
        return null;
    }
    
    public boolean crearSeccion(String codigoSeccion, String codigoCurso, String codigoInstructor, String semestre, String horario) {
        if (cantidadSecciones >= secciones.length || buscarSeccion(codigoSeccion) != null) {
            return false;
        }
        
        if (buscarCurso(codigoCurso) == null){
            return false;
        }
        
        Usuario instructor = buscarUsuario(codigoInstructor);
        if (instructor == null || !instructor.getRol().equals("INSTRUCTOR")) {
            return false;
        }
        
        secciones[cantidadSecciones] = new Seccion(codigoSeccion, codigoCurso, codigoInstructor, semestre, horario);
        cantidadSecciones++;
        
        ((Instructor) instructor).aumentarSecciones();
        
        registrarEvento("ADMINISTRADOR", "admin", "CREAR_SECCION", "EXITOSA", "Sección " + codigoSeccion + " creada");
        return true;
    }
    
    public Seccion buscarSeccion(String codigoSeccion) {
        for (int i = 0; i < cantidadSecciones; i++) {
            if (secciones[i].getCodigoSeccion().equals(codigoSeccion)) {
                return secciones[i];
            }
        }
        return null;
    }

public boolean inscribirEstudianteEnSeccion(String codigoEstudiante, String codigoSeccion) {
    Usuario usuario = buscarUsuario(codigoEstudiante);
    Seccion seccion = buscarSeccion(codigoSeccion);

    if (usuario == null) {
        System.out.println("ERROR: usuario no existe");
        return false;
    }

    if (!usuario.getRol().equals("ESTUDIANTE")) {
        System.out.println("ERROR: no es estudiante");
        return false;
    }

    if (seccion == null) {
        System.out.println("ERROR: sección no existe");
        return false;
    }

    if (seccion.yaInscrito(codigoEstudiante)) {
        System.out.println("ERROR: ya está inscrito");
        return false;
    }

    inscripcionesPendientes++;

    boolean resultado = seccion.inscribirEstudiante(codigoEstudiante);

    if (resultado) {
        ((Estudiante) usuario).aumentarCursos();
        registrarEvento("ESTUDIANTE", codigoEstudiante, "INSCRIBIR_SECCION", "EXITOSA", "Inscrito en " + codigoSeccion);
        System.out.println("Inscripción exitosa");
    } else {
        registrarEvento("ESTUDIANTE", codigoEstudiante, "INSCRIBIR_SECCION", "FALLIDA", "No se pudo inscribir en " + codigoSeccion);
        System.out.println("ERROR: fallo en inscribirEstudiante()");
    }

    if (inscripcionesPendientes > 0) {
        inscripcionesPendientes--;
    }

    return resultado;
}

    public boolean crearNota(String codigoCurso, String codigoSeccion, String codigoEstudiante, double ponderacion, double notaValor, String fecha, String etiqueta, String codigoInstructor) {
        if (cantidadNotas >= notas.length) {
            return false;
        }
        
        Seccion seccion = buscarSeccion(codigoSeccion);
        if (seccion == null) {
            return false;
        }
        
        if (!seccion.getCodigoInstructor().equals(codigoInstructor)) {
            return false;
        }
        
        if (!seccion.yaInscrito(codigoEstudiante)) {
            return false;
        }
        
        if (notaValor < 0 || notaValor > 100 || ponderacion <= 0) {
            return false;
        }
        
        for (int i = 0; i < cantidadNotas; i++) {
            if (notas[i].getCodigoSeccion().equals(codigoSeccion) && notas[i].getCodigoEstudiante().equals(codigoEstudiante) && notas[i].getEtiqueta().equalsIgnoreCase(etiqueta)) {
                return false;
            }
        }
        
        notas[cantidadNotas] = new Nota(codigoCurso, codigoSeccion, codigoEstudiante, ponderacion, notaValor, fecha, etiqueta);
        cantidadNotas++;
        
        registrarEvento("INSTRUCTOR", codigoInstructor, "CREAR_NOTA", "EXITOSA", "Nota creada para " + codigoEstudiante);
        return true;
    }
    
    public double calcularPromedioEstudianteSeccion(String codigoEstudiante, String codigoSeccion) {
        double suma = 0;
        double sumaPonderacion = 0;
        
        for (int i = 0; i < cantidadNotas; i++) {
            if (notas[i].getCodigoEstudiante().equals(codigoEstudiante) && notas[i].getCodigoSeccion().equals(codigoSeccion)) {
                suma += notas[i].getNota() * notas[i].getPonderacion();
                sumaPonderacion += notas[i].getPonderacion();
            }
        }
        
        if (sumaPonderacion == 0){
            return 0;
        }
        
        return suma / sumaPonderacion;
    }

    public String obtenerBitacoraTexto() {
    String texto = "";

    for (int i = 0; i < cantidadEventos; i++) {
        texto += bitacora[i].toString() + "\n";
    }

    return texto;
}
    
    public int getUsuariosActivos() {
        return usuariosActivos;
    }
    
    public int getInscripcionesPendientes() {
        return inscripcionesPendientes;
    }
    
    public int getCantidadCursos() {
        return cantidadCursos;
    }

    public int getCantidadUsuarios() {
        return cantidadUsuarios;
    }

    public int getCantidadNotas() {
        return cantidadNotas;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }
    
    public Curso[] getCursos() {
        return cursos;
    }

    public Seccion[] getSecciones() {
        return secciones;
    }

    public Nota[] getNotas() {
        return notas;
    }
}
/**
 *
 * @author danie
 */