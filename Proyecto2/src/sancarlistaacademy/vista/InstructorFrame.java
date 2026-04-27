package sancarlistaacademy.vista;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import sancarlistaacademy.modelo.Persistencia;
import sancarlistaacademy.modelo.SistemaAcademy;
import sancarlistaacademy.modelo.Usuario;
import sancarlistaacademy.modelo.ReportePDF;

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
        btnVerPromedio = new javax.swing.JButton();
        btnReportePDF = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel Instructor");

        btnCrearNota.setText("Registrar Nota");
        btnCrearNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearNota();
            }
        });
        
        btnVerPromedio.setText("Ver Promedio");
        btnVerPromedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verPromedio();
            }
        });
        
        btnReportePDF.setText("Reporte PDF");
        btnReportePDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarReportePDF();
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
        .addComponent(btnVerPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(btnReportePDF, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(40, Short.MAX_VALUE)
);
        layout.setVerticalGroup(
    layout.createSequentialGroup()
        .addGap(40, 40, 40)
        .addComponent(btnCrearNota)
        .addGap(12, 12, 12)
        .addComponent(btnVerPromedio)
        .addGap(12, 12, 12)
        .addComponent(btnReportePDF)
        .addGap(12, 12, 12)
        .addComponent(btnCerrarSesion)
        .addContainerGap(40, Short.MAX_VALUE)
);

        pack();
    }
    
    private void crearNota() {

    String curso = JOptionPane.showInputDialog(this, "Código curso:");
    if (curso == null || curso.trim().isEmpty()) return;

    String seccion = JOptionPane.showInputDialog(this, "Código sección:");
    if (seccion == null || seccion.trim().isEmpty()) return;

    String estudiante = JOptionPane.showInputDialog(this, "Código estudiante:");
    if (estudiante == null || estudiante.trim().isEmpty()) return;

    String ponderacionTxt = JOptionPane.showInputDialog(this, "Ponderación:");
    if (ponderacionTxt == null || ponderacionTxt.trim().isEmpty()) return;

    String notaTxt = JOptionPane.showInputDialog(this, "Nota:");
    if (notaTxt == null || notaTxt.trim().isEmpty()) return;

    String fecha = JOptionPane.showInputDialog(this, "Fecha:");
    if (fecha == null || fecha.trim().isEmpty()) return;

    String etiqueta = JOptionPane.showInputDialog(this, "Etiqueta:");
    if (etiqueta == null || etiqueta.trim().isEmpty()) return;

    double ponderacion;
    double nota;

    try {
        ponderacion = Double.parseDouble(ponderacionTxt);
        nota = Double.parseDouble(notaTxt);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Ponderación y nota deben ser numéricas");
        return;
    }
    
    if (ponderacion <= 0) {
        JOptionPane.showMessageDialog(this, "La ponderación debe ser mayor a 0");
        return;
    }

    if (nota < 0 || nota > 100) {
        JOptionPane.showMessageDialog(this, "La nota debe estar entre 0 y 100");
        return;
    }

    boolean ok = sistema.crearNota(
        curso.trim(),
        seccion.trim(),
        estudiante.trim(),
        ponderacion,
        nota,
        fecha.trim(),
        etiqueta.trim(),
        usuario.getCodigo()
    );

    JOptionPane.showMessageDialog(this,
        ok ? "Nota registrada correctamente" : "No se pudo registrar la nota"
    );

    Persistencia.guardarSistema(sistema);
}
    
    private void verPromedio() {
    String codigoEstudiante = JOptionPane.showInputDialog(this, "Código del estudiante:");
    if (codigoEstudiante == null || codigoEstudiante.trim().isEmpty()) return;

    String codigoSeccion = JOptionPane.showInputDialog(this, "Código de sección:");
    if (codigoSeccion == null || codigoSeccion.trim().isEmpty()) return;

    double promedio = sistema.calcularPromedioEstudianteSeccion(
        codigoEstudiante.trim(),
        codigoSeccion.trim()
    );

    String estado = promedio >= 61 ? "Aprobado" : "Reprobado";

    JOptionPane.showMessageDialog(this,
        "Promedio: " + promedio + "\nEstado: " + estado
    );
}
    
    private void generarReportePDF() {
        String codigoSeccion = JOptionPane.showInputDialog(this, "Código de sección:");
        if (codigoSeccion == null || codigoSeccion.trim().isEmpty()) return;

        String contenido = sistema.generarReporteNotasSeccion(codigoSeccion.trim());

        ReportePDF reporte = new ReportePDF("Reporte de Notas - " + codigoSeccion, contenido);
        reporte.generar();

        Persistencia.guardarSistema(sistema);
}
    
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCrearNota;
    private javax.swing.JButton btnVerPromedio;
    private javax.swing.JButton btnReportePDF;
}
/**
 *
 * @author danie
 */