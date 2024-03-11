package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class User {

    public String nombre;
    public Integer puntuacion;

    public User(){
        
    }
    
    public static void leerArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            System.out.println("Lista de usuarios existentes:");
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en palabras utilizando el espacio como delimitador
                String[] usuarios = linea.split(";");
                // Si hay al menos una palabra en la línea, mostrar la primera palabra
                if (usuarios.length > 0) {
                    System.out.println(usuarios[0]+", con "+usuarios[1]+" puntos.");
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
