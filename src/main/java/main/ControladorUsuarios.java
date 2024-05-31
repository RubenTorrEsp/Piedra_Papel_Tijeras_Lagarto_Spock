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
                mostrarJugadores();
                break;
            case "2":
                // Crear usuario
                System.out.println(opcionNoDisponible);
                break;
            case "3":
                // Modificar usuario
                System.out.println(opcionNoDisponible);
                break;
            case "4":
                eliminarJugador();
                break;
            case "5":
                // Reiniciar usuario
                System.out.println(opcionNoDisponible);
                break;
            case "0":
                System.out.println(seleccionSalir+"\n"+rejugarNegativo);
                break;
            default:
                System.out.println(seleccionInvalida);
                break;
        }
    }

    // Método que muestra una lista de todos los jugadores disponibles
    public void mostrarJugadores(){
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            System.out.println("Aquí se muestran los jugadores disponibles");
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

    // Método que crea un jugador y lo incorpora a la lista
    public void crearJugador() {

    }

    // Método que elimina un jugador de la lista
    public void eliminarJugador() {
        System.out.println(eliminarJugador);
        String jugadorBorrado = scanner.nextLine();
        Boolean jugadorEliminado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(jugadorBorrado)) {
                        linea = lineaVacia;
                        indicarJugadorBorrado(jugadorBorrado);
                        jugadorEliminado = true;
                    }
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!jugadorEliminado) indicarJugadorNoEnBDD(jugadorBorrado);
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
