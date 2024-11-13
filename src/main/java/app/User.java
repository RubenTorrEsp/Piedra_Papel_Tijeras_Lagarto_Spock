package app;

import java.io.*;
import java.util.Optional;

import static app.Common.*;
import static app.PlayerControler.createPlayer;
import static app._Main.*;
import static resources.Texts.*;

public class User {
   
    public static String nombre;
    public static Integer puntuacion;
    public static String linea;

    // Constructor
    public User(String nombreUsuario, File archivo) throws IOException {
        if(usuarioExiste(nombreUsuario, archivo)) establecerUsuario(nombreUsuario, archivo);
        else createPlayer(Optional.ofNullable(nombreUsuario), filePlayers);
    }
    
    // Método para establecer el usuario
    public void establecerUsuario(String nombreUsuario, File archivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(SEPARATOR);
                if (partes[0].equals(nombreUsuario)) {
                    nombre = partes[0];
                    puntuacion = Integer.parseInt(partes[1]);
                }
            }
        }
    }

    // Método que comprueba si el usuario existe
    public static boolean usuarioExiste(String nombreUsuario, File archivo) {
        boolean usuarioExiste = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((linea = br.readLine()) != null) {
                if(linea.contains(nombreUsuario)) usuarioExiste = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarioExiste;
    }

    // Método que actualiza la puntuación del jugador
    public void actualizarPuntuacion(Boolean victoria) {
        if (victoria) puntuacion++;
        else puntuacion--;
        System.out.println(SCORE_NEW + puntuacion);
        if (puntuacion == 0) eliminarJugador(nombre, filePlayers, fileTemp);
    }

    // Método que actualiza la puntuación del jugador en el archivo
    public static void reescribirPuntuacion(
            String nombreUsuario,
            int nuevaPuntuacion,
            File archivoOriginal,
            File archivoTemporal) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(SEPARATOR);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(nombreUsuario)) linea = nombreUsuario+SEPARATOR+nuevaPuntuacion;
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reWriteFilePlayers(archivoOriginal, archivoTemporal);
    }

    // Método que elimina el usuario si la puntuacion llega a 0
    public static void eliminarJugador(
            String nombreUsuario,
            File archivoOriginal,
            File archivoTemporal) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(SEPARATOR);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(nombreUsuario)) linea = EMPTY_ROW;
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reWriteFilePlayers(archivoOriginal, archivoTemporal, PLAYER_ERASED);
        System.exit(0);
    }
    
}