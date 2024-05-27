package main;

import java.io.*;
import java.util.Scanner;

import static tools.Textos.*;
import static main.Common.*;


public class ControladorUsuarios {

    Scanner scanner = new Scanner(System.in);
    String linea;
    static File archivoOriginal = new File(archivoUsuarios);
    static File archivoTemporal = new File(archivoUsuariosTemporal);

    public ControladorUsuarios() {
        System.out.println(opcionesGestorJugadores);
        String eleccion = scanner.nextLine();
        switch (eleccion) {
            case "1":
                System.out.println("Aquí se muestran los jugadores disponibles");
                mostrarJugadores();
                break;
            case "2":
                System.out.println(opcionNoDisponible);
                break;
            case "3":
                System.out.println(opcionNoDisponible);
                break;
            case "4":
                eliminarUsuario();
                break;
            case "5":
                System.out.println(opcionNoDisponible);
                break;
            default:
                System.out.println("Selección no válida");
                break;
        }
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
    public Boolean eliminarUsuario() {
        System.out.println("Indique qué jugador desea eliminar:");
        String jugadorBorrado = scanner.nextLine();
        Boolean jugadorEliminado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(jugadorBorrado)) {
                        linea = lineaVacia;
                        System.out.println("El jugador "+jugadorBorrado+" ha sido eliminado de la base de datos");
                        jugadorEliminado = true;
                    }
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!jugadorEliminado) System.out.println("El jugador "+jugadorBorrado+" no se encuentra en la base de datos");
        reescribirArchivoJugadores(archivoOriginal, archivoTemporal);
        return jugadorEliminado;
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
