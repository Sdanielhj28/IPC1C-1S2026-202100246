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
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel Estudiante");

        btnInscribirse.setText("Inscribirse en Sección");
        btnInscribirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inscribirse();
            }
        });

        btnVerPromedio.setText("Ver Promedio por Sección");
        btnVerPromedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verPromedio();
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
    private void verPromedio() {
        String codigoSeccion = JOptionPane.showInputDialog(this, "Código de sección:");
        double promedio = sistema.calcularPromedioEstudianteSeccion(usuario.getCodigo(), codigoSeccion);
        JOptionPane.showMessageDialog(this, "Promedio: " + promedio);
    }

    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnInscribirse;
    private javax.swing.JButton btnVerPromedio;
}
/**
 *
 * @author danie
 */
