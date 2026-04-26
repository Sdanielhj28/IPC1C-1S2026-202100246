/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sancarlistaacademy.modelo;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Persistencia{
    private static final String RUTA = "Sistema_academy.ser";
    
    public static void guardarSistema(SistemaAcademy sistema) {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(RUTA));
            salida.writeObject(sistema);
            salida.close();
        } catch (Exception e){
            System.out.println("Error al guardar sistema: " + e.getMessage());
        }
    }
    
    public static SistemaAcademy cargarSistema() {
        try{
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(RUTA));
            SistemaAcademy sistema = (SistemaAcademy) entrada.readObject();
            entrada.close();
            return sistema;
        } catch (Exception e) {
            return null;
        }
    }
}
/**
 *
 * @author danie
 */