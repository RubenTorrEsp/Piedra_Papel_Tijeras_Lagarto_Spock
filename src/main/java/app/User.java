package app;

import java.io.*;
import java.util.Optional;

import static app.Common.*;
import static app.PlayerControler.createPlayer;
import static app._Main.*;
import static resources.Texts.*;

public class User {
   
    public static String name;
    public static Integer score;
    public static String line;

    // Constructor
    public User(String userName, File file) throws IOException {
        if(usuarioExiste(userName, file)) establishUser(userName, file);
        else createPlayer(Optional.ofNullable(userName), filePlayers);
    }
    
    // Método para establecer el usuario
    public void establishUser(String nombreUsuario, File archivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(SEPARATOR);
                if (partes[0].equals(nombreUsuario)) {
                    name = partes[0];
                    score = Integer.parseInt(partes[1]);
                }
            }
        }
    }

    // Método que comprueba si el usuario existe
    public static boolean usuarioExiste(String nombreUsuario, File archivo) {
        boolean usuarioExiste = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((line = br.readLine()) != null) {
                if(line.contains(nombreUsuario)) usuarioExiste = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarioExiste;
    }

    // Método que actualiza la puntuación del jugador
    public void actualizarPuntuacion(Boolean victoria) {
        if (victoria) score++;
        else score--;
        System.out.println(SCORE_NEW + score);
        if (score == 0) eliminarJugador(name, filePlayers, fileTemp);
    }

    // Método que actualiza la puntuación del jugador en el archivo
    public static void reescribirPuntuacion(
            String nombreUsuario,
            int nuevaPuntuacion,
            File archivoOriginal,
            File archivoTemporal) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(SEPARATOR);
                if (!line.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(nombreUsuario)) line = nombreUsuario+SEPARATOR+nuevaPuntuacion;
                    bw.write(line);
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
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(SEPARATOR);
                if (!line.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(nombreUsuario)) line = EMPTY_ROW;
                    bw.write(line);
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