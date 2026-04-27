package sancarlistaacademy.vista;

import javax.swing.JOptionPane;
import sancarlistaacademy.modelo.Persistencia;
import sancarlistaacademy.modelo.SistemaAcademy;
import sancarlistaacademy.modelo.Usuario;

public class EstudianteFrame extends javax.swing.JFrame {

    private SistemaAcademy sistema;
    private Usuario usuario;
    private LoginFrame loginFrame;

    public EstudianteFrame(SistemaAcademy sistema, Usuario usuario, LoginFrame loginFrame) {
        this.sistema = sistema;
        this.usuario = usuario;
        this.loginFrame = loginFrame;
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        btnInscribirse = new javax.swing.JButton();
        btnVerPromedio = new javax.swing.JButton();
        btnVerPerfil = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnDesasignar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel Estudiante");

        btnInscribirse.setText("Inscribirse en Sección");
        btnInscribirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inscribirse();
            }
        });
        
        btnDesasignar.setText("Desasignar Curso");
        btnDesasignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desasignarCurso();
            }
        });

        btnVerPromedio.setText("Ver Promedio por Sección");
        btnVerPromedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verPromedio();
            }
        });
        
        btnVerPerfil.setText("Ver Perfil");
        btnVerPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verPerfil();
            }
        });

        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sistema.cerrarSesion(usuario);
                Persistencia.guardarSistema(sistema);
                loginFrame.setVisible(true);
                dispose();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup()
                    .addComponent(btnInscribirse, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnInscribirse)
                .addGap(12, 12, 12)
                .addComponent(btnVerPromedio)
                .addGap(12, 12, 12)
                .addComponent(btnVerPerfil)
                .addGap(12, 12, 12)  
                .addComponent(btnCerrarSesion)
                .addContainerGap(40, Short.MAX_VALUE)
        );

        pack();
    }
    
    private void inscribirse() {
    String codigoSeccion = JOptionPane.showInputDialog(this, "Código de sección:");
    if (codigoSeccion == null) return;

    if (codigoSeccion.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "El código de sección no puede estar vacío");
        return;
    }

    boolean ok = sistema.inscribirEstudianteEnSeccion(usuario.getCodigo(), codigoSeccion.trim());

    if (ok) {
        JOptionPane.showMessageDialog(this, "Inscripción exitosa");
    } else {
        JOptionPane.showMessageDialog(this, 
            "No se pudo inscribir.\n\nRevisa:\n"
            + "- Que la sección exista\n"
            + "- Que no estés ya inscrito\n"
            + "- Que el código esté escrito igual"
        );
    }

    Persistencia.guardarSistema(sistema);
}

       private void desasignarCurso() {
    String codigoSeccion = JOptionPane.showInputDialog(this, "Código de sección a desasignar:");
    if (codigoSeccion == null || codigoSeccion.trim().isEmpty()) return;

    int confirmacion = JOptionPane.showConfirmDialog(
        this,
        "¿Seguro que deseas desasignarte de esta sección?",
        "Confirmar desasignación",
        JOptionPane.YES_NO_OPTION
    );

    if (confirmacion != JOptionPane.YES_OPTION) return;

    boolean ok = sistema.desasignarEstudianteDeSeccion(usuario.getCodigo(), codigoSeccion.trim());

    JOptionPane.showMessageDialog(this,
        ok ? "Estudiante desasignado de la sección correctamente."
           : "No se pudo desasignar. Puede que no estés inscrito o que ya tengas notas."
    );

    Persistencia.guardarSistema(sistema);
}
       
    private void verPromedio() {
        String codigoSeccion = JOptionPane.showInputDialog(this, "Código de sección:");
        double promedio = sistema.calcularPromedioEstudianteSeccion(usuario.getCodigo(), codigoSeccion);
        JOptionPane.showMessageDialog(this, "Promedio: " + promedio);
    }
    
    private void verPerfil() {
    JOptionPane.showMessageDialog(this,
        "Perfil del Estudiante\n\n"
        + "Código: " + usuario.getCodigo() + "\n"
        + "Nombre: " + usuario.getNombre() + "\n"
        + "Fecha de nacimiento: " + usuario.getFechaNacimiento() + "\n"
        + "Género: " + usuario.getGenero() + "\n"
        + "Rol: " + usuario.getRol()
    );
}

    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnInscribirse;
    private javax.swing.JButton btnVerPromedio;
    private javax.swing.JButton btnVerPerfil;
    private javax.swing.JButton btnDesasignar;
}
/**
 *
 * @author danie
 */
