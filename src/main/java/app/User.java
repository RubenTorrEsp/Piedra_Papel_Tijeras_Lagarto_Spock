package app;

import java.io.*;
import java.util.Optional;

import static app.Common.*;
import static app.ControladorUsuarios.crearJugador;
import static resources.Texts.*;

public class User {
   
    public static String nombre;
    public static Integer puntuacion;
    public static String linea;
    static File archivoUsers = new File(archivoUsuarios);
    static File archivoTemp = new File(archivoUsuariosTemporal);

    // Constructor
    public User(String nombreUsuario, File archivo) {
        if(usuarioExiste(nombreUsuario, archivo)) establecerUsuario(nombreUsuario, archivo);
        else crearJugador(Optional.ofNullable(nombreUsuario), archivoUsers);
    }
    
    // Método para establecer el usuario
    public void establecerUsuario(String nombreUsuario, File archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (partes[0].equals(nombreUsuario)) {
                    nombre = partes[0];
                    puntuacion = Integer.parseInt(partes[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        System.out.println(nuevaPuntuacion + puntuacion);
        if (puntuacion == 0) eliminarJugador(nombre, archivoUsers, archivoTemp);
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
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(nombreUsuario)) linea = nombreUsuario+separadorUsuarios+nuevaPuntuacion;
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reescribirArchivoJugadores(archivoOriginal, archivoTemporal);
    }

    // Método que elimina el usuario si la puntuacion llega a 0
    public static void eliminarJugador(
            String nombreUsuario,
            File archivoOriginal,
            File archivoTemporal) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(nombreUsuario)) linea = lineaVacia;
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reescribirArchivoJugadores(archivoOriginal, archivoTemporal, usuarioEliminado);
        System.exit(0);
    }
    
}