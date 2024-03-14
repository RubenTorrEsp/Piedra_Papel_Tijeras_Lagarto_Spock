package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class User {

    public static String nombre;
    public static Integer puntuacion;
    public static String linea;

    public User(String user){
        leerArchivo(user);    
    }
    
    public static void establecerUsuario(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en usuario y puntuación utilizando el punto y coma como delimitador
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    // Establecer nombre y puntuacion del usuario
                    nombre = partes[0];
                    puntuacion = Integer.parseInt(partes[1]);

                    System.out.println("El usuario "+nombre+" tiene una puntuacion de "+puntuacion+" puntos.");

                    // Actualizar puntuacion
                    //partes[1] = String.valueOf(actualizarPuntuacion(puntuacion));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void leerArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
             PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivo + ".temp")))) {
            
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en usuario y puntuación utilizando el punto y coma como delimitador
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    // Establecer nombre y puntuacion del usuario
                    nombre = partes[0];
                    puntuacion = Integer.parseInt(partes[1]);

                    System.out.println("El usuario "+nombre+" tiene una puntuacion de "+puntuacion+" puntos.");

                    // Actualizar puntuacion
                    //partes[1] = String.valueOf(actualizarPuntuacion(puntuacion));
                    
                    // Reconstruir la línea con las partes modificadas y escribir en el archivo temporal
                    pw.println(partes[0] + ";" + partes[1]);
                }
            }
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
