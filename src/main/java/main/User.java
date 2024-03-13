package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class User {

    public String nombre;
    public Integer puntuacion;

    public User(){
        
    }
    
    public static void leerArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
             PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivo + ".temp")))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en usuario y puntuación utilizando el punto y coma como delimitador
                String[] partes = linea.split(";");
                if (partes.length == 2) {

                    // Modificar el usuario para que esté en mayúsculas
                    partes[0] = partes[0].toUpperCase();

                    // Actualizar puntuacion
                    Integer puntuacion = actualizarPuntuacion(Integer.parseInt(partes[1]));
                    partes[1] = String.valueOf(puntuacion);

                    // Reconstruir la línea con las partes modificadas y escribir en el archivo temporal
                    pw.println(partes[0] + ";" + partes[1]);
                }
            }
            System.out.println("Archivo de usuarios modificado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Renombrar el archivo temporal para reemplazar el archivo original
        try {
            java.nio.file.Files.move(
                java.nio.file.Paths.get(nombreArchivo + ".temp"),
                java.nio.file.Paths.get(nombreArchivo),
                java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer actualizarPuntuacion(Integer puntuacion){
        puntuacion++;
        return puntuacion;
    }

}
