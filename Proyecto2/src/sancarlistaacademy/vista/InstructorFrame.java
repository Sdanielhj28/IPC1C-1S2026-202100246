package sancarlistaacademy.vista;

import javax.swing.JOptionPane;
import sancarlistaacademy.modelo.Persistencia;
import sancarlistaacademy.modelo.SistemaAcademy;
import sancarlistaacademy.modelo.Usuario;

public class InstructorFrame extends javax.swing.JFrame {

    private SistemaAcademy sistema;
    private Usuario usuario;
    private LoginFrame loginFrame;

    public InstructorFrame(SistemaAcademy sistema, Usuario usuario, LoginFrame loginFrame) {
        this.sistema = sistema;
        this.usuario = usuario;
        this.loginFrame = loginFrame;
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        btnCrearNota = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel Instructor");

        btnCrearNota.setText("Registrar Nota");
        btnCrearNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearNota();
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
                    .addComponent(btnCrearNota, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnCrearNota)
                .addGap(12, 12, 12)
                .addComponent(btnCerrarSesion)
                .addContainerGap(40, Short.MAX_VALUE)
        );

        pack();
    }

    private void crearNota() {
        String curso = JOptionPane.showInputDialog(this, "Código curso:");
        String seccion = JOptionPane.showInputDialog(this, "Código sección:");
        String estudiante = JOptionPane.showInputDialog(this, "Código estudiante:");
        double ponderacion = Double.parseDouble(JOptionPane.showInputDialog(this, "Ponderación:"));
        double nota = Double.parseDouble(JOptionPane.showInputDialog(this, "Nota:"));
        String fecha = JOptionPane.showInputDialog(this, "Fecha:");
        String etiqueta = JOptionPane.showInputDialog(this, "Etiqueta:");

        boolean ok = sistema.crearNota(curso, seccion, estudiante, ponderacion, nota, fecha, etiqueta, usuario.getCodigo());
        JOptionPane.showMessageDialog(this, ok ? "Nota registrada" : "No se pudo registrar");
        Persistencia.guardarSistema(sistema);
    }

    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCrearNota;
}
/**
 *
 * @author danie
 */
