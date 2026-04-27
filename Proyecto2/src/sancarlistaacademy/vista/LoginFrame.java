package sancarlistaacademy.vista;

import javax.swing.JOptionPane;
import sancarlistaacademy.modelo.Persistencia;
import sancarlistaacademy.modelo.SistemaAcademy;
import sancarlistaacademy.modelo.Usuario;
import sancarlistaacademy.controlador.LoginControlador;

public class LoginFrame extends javax.swing.JFrame {

    private SistemaAcademy sistema;
    private LoginControlador controlador;

    public LoginFrame(SistemaAcademy sistema) {
        this.sistema = sistema;
        this.controlador = new LoginControlador(sistema);
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblCodigo = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sancarlista Academy - Login");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20));
        lblTitulo.setText("Sancarlista Academy");

        btnLogin.setText("Iniciar sesión");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblCodigo.setText("Código:");
        lblPassword.setText("Contraseña:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo)
                .addGap(20, 20, 20)
                .addComponent(lblCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogin)
                .addContainerGap(30, Short.MAX_VALUE)
        );

        pack();
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String codigo = txtCodigo.getText();
        String pass = new String(txtPassword.getPassword());

        Usuario usuario = controlador.iniciarSesion(codigo, pass);

        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
            Persistencia.guardarSistema(sistema);
            return;
        }

        JOptionPane.showMessageDialog(this, "Bienvenido " + usuario.getNombre());

        if (usuario.getRol().equals("ADMINISTRADOR")) {
            new AdminFrame(sistema, usuario, this).setVisible(true);
        } else if (usuario.getRol().equals("INSTRUCTOR")) {
            new InstructorFrame(sistema, usuario, this).setVisible(true);
        } else {
            new EstudianteFrame(sistema, usuario, this).setVisible(true);
        }

        this.setVisible(false);
        Persistencia.guardarSistema(sistema);
    }

    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JPasswordField txtPassword;
}
/**
 *
 * @author danie
 */