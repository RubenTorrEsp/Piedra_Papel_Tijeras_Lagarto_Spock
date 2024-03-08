package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class User {

    public String nombre;
    public Integer puntuacion;

    public User(){
        leerArchivo("E:\\Proyectos\\Piedra_Papel_Tijeras_Lagarto_Spock\\src\\main\\java\\tools\\Users.txt");
    }
    
    public static void leerArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en palabras utilizando el espacio como delimitador
                String[] palabras = linea.split(" ");
                // Si hay al menos una palabra en la línea, mostrar la primera palabra
                if (palabras.length > 0) {
                    System.out.println(palabras[0].toUpperCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
