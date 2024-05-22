package main;

import java.io.*;
import static tools.Textos.*;
import static main.Common.*;


public class ControladorUsuarios {

    public static String linea;
    static File archivoOriginal = new File(archivoUsuarios);
    static File archivoTemporal = new File(archivoUsuariosTemporal);

    public ControladorUsuarios() {
        System.out.println(opcionesGestorJugadores);
        System.out.println("Método en construccion. Le mostramos una lista de los jugadores disponibles.");
        mostrarJugadores();
    }

    // Método que muestra una lista de todos los jugadores disponibles
    public void mostrarJugadores(){
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            while ((linea = br.readLine()) != null) {
                if(linea.length() > 1) {
                    String[] partes = linea.split(separadorUsuarios);
                    System.out.println("Nombre: "+partes[0]+". Puntuación: "+partes[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método que elimina un jugador de la lista
    public void eliminarUsuario(String nombreUsuario) {
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
        reescribirArchivoJugadores(archivoOriginal, archivoTemporal);
    }

    // Método que modifica el nombre de un usuario
    public void editarUsuario(String nombreUsuario, String nuevoNombre) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(nombreUsuario)) {
                        linea = nuevoNombre+separadorUsuarios+nuevaPuntuacion;
                    }
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reescribirArchivoJugadores(archivoOriginal, archivoTemporal);
    }
    
}
