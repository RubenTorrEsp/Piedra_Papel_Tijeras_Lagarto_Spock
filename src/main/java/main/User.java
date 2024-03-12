package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class User {

    public String nombre;
    public Integer puntuacion;

    public User(){
        
    }
    
    public static void leerArchivo(String nombreArchivo, String nuevoArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
             BufferedWriter bw = new BufferedWriter(new FileWriter(nuevoArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en palabras utilizando el espacio como delimitador
                String[] palabras = linea.split(";");
                // Si hay al menos una palabra en la línea, modificar la primera palabra
                if (palabras.length > 0) {
                    palabras[0] = "NuevaPalabra"; // Modificar la primera palabra según sea necesario
                    // Reconstruir la línea con las palabras modificadas
                    StringBuilder nuevaLinea = new StringBuilder();
                    for (String palabra : palabras) {
                        nuevaLinea.append(palabra).append(" ");
                    }
                    // Escribir la línea modificada en el archivo de salida
                    bw.write(nuevaLinea.toString().trim());
                    bw.newLine();
                }
            }
            System.out.println("Archivo modificado creado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
