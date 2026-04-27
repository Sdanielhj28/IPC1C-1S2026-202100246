package sancarlistaacademy.vista;

import javax.swing.JOptionPane;
import sancarlistaacademy.modelo.Persistencia;
import sancarlistaacademy.modelo.SistemaAcademy;
import sancarlistaacademy.modelo.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;

public class AdminFrame extends javax.swing.JFrame {

    private SistemaAcademy sistema;
    private Usuario usuario;
    private LoginFrame loginFrame;

    public AdminFrame(SistemaAcademy sistema, Usuario usuario, LoginFrame loginFrame) {
        this.sistema = sistema;
        this.usuario = usuario;
        this.loginFrame = loginFrame;
        initComponents();
        setLocationRelativeTo(null);
        iniciarHilos();
    }

    private volatile boolean ejecutando = true;

    private void iniciarHilos() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (ejecutando) {
                    txtAreaMonitor.setText(
                        "[Thread-Sesiones] Usuarios Activos: " + sistema.getUsuariosActivos() + "\n" +
                        "[Thread-Inscripciones] Inscripciones Pendientes: " + sistema.getInscripcionesPendientes() + "\n" +
                        "[Thread-Estadísticas] Cursos Activos: " + sistema.getCantidadCursos() +
                        " | Usuarios: " + sistema.getCantidadUsuarios() +
                        " | Notas: " + sistema.getCantidadNotas()
                    );
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        t1.start();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        btnCrearInstructor = new javax.swing.JButton();
        btnCrearEstudiante = new javax.swing.JButton();
        btnCrearCurso = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnVerBitacora = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaMonitor = new javax.swing.JTextArea();
        btnCrearSeccion = new javax.swing.JButton();
        btnCargarInstructoresCSV = new javax.swing.JButton();
        btnCargarEstudiantesCSV = new javax.swing.JButton();
        btnCargarCursosCSV = new javax.swing.JButton();
        btnCargarCursosCSV.setText("Cargar Cursos CSV");
        btnCrearSeccion.setText("Crear Sección");
        btnCrearSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearSeccion();
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel Administrador");

        btnCrearInstructor.setText("Crear Instructor");
        btnCrearInstructor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearInstructor();
            }
        });

        btnCrearEstudiante.setText("Crear Estudiante");
        btnCrearEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearEstudiante();
            }
        });

        btnCrearCurso.setText("Crear Curso");
        btnCrearCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearCurso();
            }
        });

        btnVerBitacora.setText("Ver Bitácora");
        btnVerBitacora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(AdminFrame.this, sistema.obtenerBitacoraTexto());
            }
        });
        
        btnCargarInstructoresCSV.setText("Cargar Instructores CSV");
        btnCargarInstructoresCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarInstructoresCSV();
            }
        });
        
        btnCargarEstudiantesCSV.setText("Cargar Estudiantes CSV");
        btnCargarEstudiantesCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarEstudiantesCSV();
            }
        });
        
        btnCargarCursosCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarCursosCSV();
            }
        });
        
        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesion();
            }
        });
        
        txtAreaMonitor.setColumns(20);
        txtAreaMonitor.setRows(5);
        jScrollPane1.setViewportView(txtAreaMonitor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCrearInstructor, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(btnCrearEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(btnCrearCurso, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(btnCrearSeccion, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE).addComponent(btnCargarInstructoresCSV, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(btnCargarEstudiantesCSV, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(btnCargarCursosCSV, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(btnVerBitacora, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCrearInstructor)
                        .addGap(12, 12, 12)
                        .addComponent(btnCrearEstudiante)
                        .addGap(12, 12, 12)
                        .addComponent(btnCrearCurso)
                        .addGap(12, 12, 12)
                        .addComponent(btnCrearSeccion)
                        .addGap(12, 12, 12)
                        .addComponent(btnCargarInstructoresCSV)
                        .addGap(12, 12, 12)
                        .addComponent(btnCargarEstudiantesCSV)
                        .addGap(12, 12, 12)
                        .addComponent(btnCargarCursosCSV)
                        .addGap(12, 12, 12)
                        .addComponent(btnVerBitacora)
                        .addGap(12, 12, 12)
                        .addComponent(btnCerrarSesion)))
                .addContainerGap(30, Short.MAX_VALUE)
        );

        pack();
    }

    private void crearInstructor() {
    String codigo = JOptionPane.showInputDialog(this, "Código:");
    if (codigo == null) return; // canceló
    if (codigo.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "El código no puede estar vacío");
        return;
    }

    String nombre = JOptionPane.showInputDialog(this, "Nombre:");
    if (nombre == null) return;
    if (nombre.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío");
        return;
    }

    String fecha = JOptionPane.showInputDialog(this, "Fecha nacimiento:");
    if (fecha == null) return;

    String genero = JOptionPane.showInputDialog(this, "Género:");
    if (genero == null) return;

    String pass = JOptionPane.showInputDialog(this, "Contraseña:");
    if (pass == null) return;
    if (pass.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "La contraseña no puede estar vacía");
        return;
    }

    boolean ok = sistema.crearInstructor(codigo, nombre, fecha, genero, pass);

    JOptionPane.showMessageDialog(this, ok ? "Instructor creado" : "Código ya existe");
    Persistencia.guardarSistema(sistema);
}

    private void crearEstudiante() {
    String codigo = JOptionPane.showInputDialog(this, "Código:");
    if (codigo == null) return;

    String nombre = JOptionPane.showInputDialog(this, "Nombre:");
    if (nombre == null) return;

    String fecha = JOptionPane.showInputDialog(this, "Fecha nacimiento:");
    if (fecha == null) return;

    String genero = JOptionPane.showInputDialog(this, "Género:");
    if (genero == null) return;

    String pass = JOptionPane.showInputDialog(this, "Contraseña:");
    if (pass == null) return;

    boolean ok = sistema.crearEstudiante(codigo, nombre, fecha, genero, pass);
    JOptionPane.showMessageDialog(this, ok ? "Estudiante creado" : "No se pudo crear");
    Persistencia.guardarSistema(sistema);
}

    private void crearCurso() {
    String codigo = JOptionPane.showInputDialog(this, "Código curso:");
    if (codigo == null) return;

    String nombre = JOptionPane.showInputDialog(this, "Nombre curso:");
    if (nombre == null) return;

    String descripcion = JOptionPane.showInputDialog(this, "Descripción:");
    if (descripcion == null) return;

    String creditosTexto = JOptionPane.showInputDialog(this, "Créditos:");
    if (creditosTexto == null) return;

    String seccion = JOptionPane.showInputDialog(this, "Sección:");
    if (seccion == null) return;

    int creditos;
    try {
        creditos = Integer.parseInt(creditosTexto);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Los créditos deben ser numéricos");
        return;
    }

    boolean ok = sistema.crearCurso(codigo, nombre, descripcion, creditos, seccion);
    JOptionPane.showMessageDialog(this, ok ? "Curso creado" : "No se pudo crear");
    Persistencia.guardarSistema(sistema);
}

    private void crearSeccion() {
    String codigoSeccion = JOptionPane.showInputDialog(this, "Código de sección:");
    if (codigoSeccion == null) return;
    if (codigoSeccion.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "El código de sección no puede estar vacío");
        return;
    }

    String codigoCurso = JOptionPane.showInputDialog(this, "Código del curso:");
    if (codigoCurso == null) return;

    String codigoInstructor = JOptionPane.showInputDialog(this, "Código del instructor:");
    if (codigoInstructor == null) return;

    String semestre = JOptionPane.showInputDialog(this, "Semestre:");
    if (semestre == null) return;

    String horario = JOptionPane.showInputDialog(this, "Horario:");
    if (horario == null) return;

    boolean ok = sistema.crearSeccion(codigoSeccion.trim(), codigoCurso.trim(), codigoInstructor.trim(), semestre.trim(), horario.trim());

    JOptionPane.showMessageDialog(this, ok ? "Sección creada" : "No se pudo crear la sección");
    Persistencia.guardarSistema(sistema);
}
    private File seleccionarArchivoCSV() {
    JFileChooser chooser = new JFileChooser();
    int opcion = chooser.showOpenDialog(this);

    if (opcion == JFileChooser.APPROVE_OPTION) {
        return chooser.getSelectedFile();
    }

    return null;
}
    
    private void cargarInstructoresCSV() {
    File archivo = seleccionarArchivoCSV();
    if (archivo == null) return;

    int cargados = 0;
    int errores = 0;

    try {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");

            if (datos.length != 5) {
                errores++;
                continue;
            }

            boolean ok = sistema.crearInstructor(
                datos[0].trim(),
                datos[1].trim(),
                datos[2].trim(),
                datos[3].trim(),
                datos[4].trim()
            );

            if (ok) cargados++;
            else errores++;
        }

        br.close();
        Persistencia.guardarSistema(sistema);

        JOptionPane.showMessageDialog(this,
            "Carga de instructores finalizada\n"
            + "Cargados: " + cargados + "\n"
            + "Errores: " + errores
        );

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al leer CSV: " + e.getMessage());
    }
}
    
    private void cargarEstudiantesCSV() {
    File archivo = seleccionarArchivoCSV();
    if (archivo == null) return;

    int cargados = 0;
    int errores = 0;

    try {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");

            if (datos.length != 5) {
                errores++;
                continue;
            }

            boolean ok = sistema.crearEstudiante(
                datos[0].trim(),
                datos[1].trim(),
                datos[2].trim(),
                datos[3].trim(),
                datos[4].trim()
            );

            if (ok) cargados++;
            else errores++;
        }

        br.close();
        Persistencia.guardarSistema(sistema);

        JOptionPane.showMessageDialog(this,
            "Carga de estudiantes finalizada\n"
            + "Cargados: " + cargados + "\n"
            + "Errores: " + errores
        );

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al leer CSV: " + e.getMessage());
    }
}
    
    private void cargarCursosCSV() {
    File archivo = seleccionarArchivoCSV();
    if (archivo == null) return;

    int cargados = 0;
    int errores = 0;

    try {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");

            if (datos.length != 5) {
                errores++;
                continue;
            }

            int creditos;

            try {
                creditos = Integer.parseInt(datos[3].trim());
            } catch (NumberFormatException e) {
                errores++;
                continue;
            }

            boolean ok = sistema.crearCurso(
                datos[0].trim(),
                datos[1].trim(),
                datos[2].trim(),
                creditos,
                datos[4].trim()
            );

            if (ok) cargados++;
            else errores++;
        }

        br.close();
        Persistencia.guardarSistema(sistema);

        JOptionPane.showMessageDialog(this,
            "Carga de cursos finalizada\n"
            + "Cargados: " + cargados + "\n"
            + "Errores: " + errores
        );

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al leer CSV: " + e.getMessage());
    }
}
    
    private void cerrarSesion() {
        ejecutando = false;
        sistema.cerrarSesion(usuario);
        Persistencia.guardarSistema(sistema);
        loginFrame.setVisible(true);
        dispose();
    }

    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCrearCurso;
    private javax.swing.JButton btnCrearEstudiante;
    private javax.swing.JButton btnCrearInstructor;
    private javax.swing.JButton btnVerBitacora;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtAreaMonitor;
    private javax.swing.JButton btnCrearSeccion;
    private javax.swing.JButton btnCargarInstructoresCSV;
    private javax.swing.JButton btnCargarEstudiantesCSV;
    private javax.swing.JButton btnCargarCursosCSV;
}   
/**
 *
 * @author danie
 */
