package sancarlistaacademy;

import sancarlistaacademy.modelo.Persistencia;
import sancarlistaacademy.modelo.SistemaAcademy;
import sancarlistaacademy.vista.LoginFrame;

public class Main {
    public static void main(String[] args) {
        SistemaAcademy sistema = Persistencia.cargarSistema();

        if (sistema == null) {
            sistema = new SistemaAcademy();
            sistema.inicializarAdminPorDefecto("C");
            Persistencia.guardarSistema(sistema);
        }

        final SistemaAcademy sistemaFinal = sistema;

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame(sistemaFinal).setVisible(true);
            }
        });
    }
}