package main;

import java.io.*;
import java.nio.file.*;

import static tools.Textos.*;

public class User {

    static String archivoUsuarios = "users.txt";
    static String archivoUsuariosTemporal = "temp.txt";
    
    public static String nombre;
    public static Integer puntuacion;
    public String linea;

    // Constructor
    public User(String nombreUsuario){
        if(usuarioExiste(nombreUsuario)) establecerUsuario(nombreUsuario);      
        else crearNuevoUsuario(nombreUsuario);
    }
    
    // Método para establecer el usuario
    public void establecerUsuario(String nombreUsuario) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios))) {
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en usuario y puntuación utilizando el punto y coma como delimitador
                String[] partes = linea.split(";");
                // Comprobar si el usuario existe
                if (partes[0].equals(nombreUsuario)) {
                    // Establecer nombre y puntuacion del usuario
                    nombre = partes[0];
                    puntuacion = Integer.parseInt(partes[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void crearNuevoUsuario(String usuario){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
            System.out.println(usuarioCreado);
            bw.newLine();
            bw.write(usuario+puntuacionInicial);
            nombre = usuario;
            puntuacion = 50;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método que actualiza la puntuación del jugador
    @SuppressWarnings("static-access")
    public void actualizarPuntuacion(User jugador, Boolean victoria){  
        if (victoria) jugador.puntuacion++;
        else jugador.puntuacion--;
        System.out.println("La nueva puntuacion es "+jugador.puntuacion);               
    }

    //Método que actualiza la puntuación del jugador en el archivo
    public static void reescribirPuntuacion(String nombreUsuario, int nuevaPuntuacion){

        File archivoOriginal = new File(archivoUsuarios);
        File archivoTemporal = new File(archivoUsuariosTemporal);

        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                // Dividir la línea en nombre y puntuación utilizando el punto y coma como delimitador
                String[] partes = linea.split(";");
                if (partes.length == 2 && partes[0].equals(nombreUsuario)) {
                    // Si se encuentra el usuario, actualizar la puntuación
                    linea = nombreUsuario + ";" + nuevaPuntuacion;
                }
                // Escribir la línea en el archivo temporal
                bw.write(linea);
                // Escribir un salto de línea después de cada línea
                bw.newLine();
            }

            if (archivoOriginal.exists()) {
                System.out.println("El archivo existe");
                String rutaUsers = "users.txt";
                Path archivoPath = Paths.get(rutaUsers);
                try {
                    // Intentar eliminar el archivo
                    Files.delete(archivoPath);
                    System.out.println("El archivo ha sido eliminado con éxito.");
                } catch (IOException e) {
                    System.out.println("No se pudo eliminar el archivo: " + e.getMessage());
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}