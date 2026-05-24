package main;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import packagee.LoginView;
import storage.Database;

public class Main {
    public static void main(String[] args) {
        // Inicializar la base de datos antes de lanzar la interfaz
        Database.getInstance();

        System.setProperty("flatlaf.useNativeLibrary", "false");
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }
}
