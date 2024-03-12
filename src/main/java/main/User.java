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
            // Dividir la línea en dos partes: usuario y puntuación
            String[] partes = linea.split(";");
            if (partes.length == 2) { // Verificar que la línea tenga el formato correcto
                // Modificar el nombre de usuario a mayúsculas y mantener la puntuación
                String usuarioMayusculas = partes[0].toUpperCase();
                String puntuacion = partes[1];
                // Escribir la línea modificada en el archivo de salida
                bw.write(usuarioMayusculas + ";" + puntuacion);
                bw.newLine();
            } else {
                System.out.println("Error: Formato incorrecto en la línea - " + linea);
            }
       }
       System.out.println("Archivo de usuarios modificado creado con éxito.");
   } catch (IOException e) {
       e.printStackTrace();
   }
    }

}
