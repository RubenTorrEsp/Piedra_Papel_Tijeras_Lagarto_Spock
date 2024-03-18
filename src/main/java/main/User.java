package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class User {

    private static String archivoUsuarios = "E:\\Proyectos\\Piedra_Papel_Tijeras_Lagarto_Spock\\src\\main\\java\\tools\\Users.txt";
    
    public static String nombre;
    public static Integer puntuacion;
    public static String linea;
    public static Boolean usuarioNoExiste = true;

    // Constructor
    public User(){
        establecerUsuario("user6");
    }
    
    // Método que recibe el posible nombre de usuario y devuelve el objeto
    public static void establecerUsuario(String nombreUsuario) {

        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
            
                while ((linea = br.readLine()) != null) {

                    // Dividir la línea en usuario y puntuación utilizando el punto y coma como delimitador
                    String[] partes = linea.split(";");

                    
                    if (partes[0].equals(nombreUsuario)) {
                        
                        // Establecer nombre y puntuacion del usuario
                        nombre = partes[0];
                        puntuacion = Integer.parseInt(partes[1]);
                        
                        System.out.println("El usuario "+nombre+" tiene una puntuacion de "+puntuacion+" puntos.");

                        usuarioNoExiste = false;
                        
                        // Actualizar puntuacion
                        //partes[1] = String.valueOf(actualizarPuntuacion(puntuacion));
                        /* 
                        else {
                            System.out.println("Usuario creado");
                            bw.newLine();
                            bw.write("Esta es una nueva línea.");
                        }
                        */
                    }

                }
                if(usuarioNoExiste) System.out.println("El usuario no existe");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Integer actualizarPuntuacion(Integer puntuacion){
        puntuacion++;
        return puntuacion;
    }

}