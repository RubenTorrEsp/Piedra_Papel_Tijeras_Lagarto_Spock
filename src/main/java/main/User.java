package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class User {

    private static String archivoUsuarios = "..\\tools\\Users.txt";
    
    public String nombre;
    public Integer puntuacion;
    public static String linea;
    public static Boolean usuarioNoExiste = true;

    // Constructor
    public User(String nombreUsuario){

        /*
        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
                while ((linea = br.readLine()) != null) {
                    // Dividir la línea en usuario y puntuación utilizando el punto y coma como delimitador
                    String[] partes = linea.split(";");
                    // Comprobar si el usuario existe
                    if (partes[0].equals(nombreUsuario)) {
                        // Establecer nombre y puntuacion del usuario
                        nombre = partes[0];
                        puntuacion = Integer.parseInt(partes[1]);
                        usuarioNoExiste = false;
                    }
                }
                // Si no existe, crearlo
                if(usuarioNoExiste) crearNuevoUsuario(nombreUsuario);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        
        if(usuarioExiste(nombreUsuario)) System.out.println("El usuario existe");
        else System.out.println("El usuario no existe");

    }
    

    // Método que comprueba si el usuario existe
    public Boolean usuarioExiste(String nombreUsuario){
        Boolean usuarioExiste = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios))) {
            while ((linea = br.readLine()) != null) {
                if(linea.contains(nombreUsuario)) usuarioExiste = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarioExiste;
    }

    // Método que crea un usuario nuevo
    public static void crearNuevoUsuario(String usuario){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
            System.out.println("Usuario creado con 50 puntos.");
            bw.newLine();
            bw.write(usuario+";50");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer actualizarPuntuacion(Integer puntuacion){
        puntuacion++;
        return puntuacion;
    }

}