package main;

import java.io.*;

import static tools.Textos.*;

public class User {
   
    public static String nombre;
    public static Integer puntuacion;
    public String linea;
    static File archivoOriginal = new File(archivoUsuarios);
    static File archivoTemporal = new File(archivoUsuariosTemporal);

    // Constructor
    public User(String nombreUsuario){
        if(usuarioExiste(nombreUsuario)) establecerUsuario(nombreUsuario);      
        else crearNuevoUsuario(nombreUsuario);
    }
    
    // Método para establecer el usuario
    public void establecerUsuario(String nombreUsuario) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes[0].equals(nombreUsuario)) {
                    nombre = partes[0];
                    puntuacion = Integer.parseInt(partes[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método que arregla el formato del user escrito para evitar repeticiones
    public String formatoJugador(String jugador) {
        String jugadorFormateado = jugador;
        jugadorFormateado.toLowerCase().substring(0, 1).toUpperCase();
        return jugadorFormateado;
    }


    // Método que comprueba si el usuario existe
    public Boolean usuarioExiste(String nombreUsuario) {
        Boolean usuarioExiste = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            while ((linea = br.readLine()) != null) {
                if(linea.contains(nombreUsuario)) usuarioExiste = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarioExiste;
    }

    // Método que crea un usuario nuevo
    public void crearNuevoUsuario(String nombreJugadorNuevo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
            // Ir al final del archivo
            bw.newLine();
            // Escribir la nueva línea
            bw.write(nombreJugadorNuevo + ";50");
            System.out.println("Se ha añadido una nueva línea al final del archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método que actualiza la puntuación del jugador
    @SuppressWarnings("static-access")
    public void actualizarPuntuacion(User jugador, Boolean victoria){  
        if (victoria) jugador.puntuacion++;
        else jugador.puntuacion--;
        System.out.println(nuevaPuntuacion+jugador.puntuacion);               
    }

    // Método que actualiza la puntuación del jugador en el archivo
    public static void reescribirPuntuacion(String nombreUsuario, int nuevaPuntuacion) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2 && partes[0].equals(nombreUsuario)) {
                    linea = nombreUsuario + ";" + nuevaPuntuacion;
                }
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        archivoOriginal.delete();
        archivoTemporal.renameTo(archivoOriginal);
    }
    
}