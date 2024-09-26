package app;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

import static app.Common.*;
import static app.User.*;
import static resources.Texts.*;

public class ControladorUsuarios {

    static String linea;
    static Scanner scanner = new Scanner(System.in);
    static File archivoOriginal = new File(archivoUsuarios);
    static File archivoTemporal = new File(archivoUsuariosTemporal);

    public ControladorUsuarios() {
        System.out.println(OPTIONS_PLAYER);
        String eleccion = scanner.nextLine();
        switch (eleccion) {
            case NUMBER_1:
                mostrarJugadores();
                break;
            case NUMBER_2:
                crearJugador(nombreVacio, archivoOriginal);
                break;
            case NUMBER_3:
                modificarJugador(nombreVacio, nombreVacio, archivoOriginal, archivoTemporal);
                break;
            case NUMBER_4:
                eliminarJugador(nombreVacio, archivoOriginal, archivoTemporal);
                break;
            case NUMBER_5:
                reiniciarJugador(nombreVacio, archivoOriginal, archivoTemporal);
                break;
            case NUMBER_0:
                salir();
                break;
            default:
                System.out.println(seleccionInvalida);
                new ControladorUsuarios();
                break;
        }
    }

    // Método que muestra una lista de todos los jugadores disponibles
    public void mostrarJugadores(){
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            System.out.println(mostrarJugadores);
            while ((linea = br.readLine()) != null) {
                if(linea.length() > 1) {
                    String[] partes = linea.split(separadorUsuarios);
                    mostrarJugador(partes[0], partes[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método que crea un jugador y lo incorpora a la lista
    public static void crearJugador(Optional<String> nombreJugador, File archivo) {
        System.out.println(jugadorParaCrear);
        String jugadorNuevo = nombreJugador.orElseGet(() -> scanner.nextLine());
        boolean jugadorExistente = usuarioExiste(jugadorNuevo, archivo);
        if (!jugadorExistente) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
                bw.newLine();
                bw.write(jugadorNuevo + puntuacionInicial);
                indicarJugadorCreado(jugadorNuevo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            indicarJugadorYaEnBDD(jugadorNuevo);
            new ControladorUsuarios();
        }
    }

    // Método que modifica los valores de un usuario
    public static void modificarJugador(
            Optional<String> nombreJugador,
            Optional<String> nuevoNombre,
            File archivoReal,
            File archivoTemp) {
        System.out.println(modificarJugador);
        String jugadorModificado = nombreJugador.orElseGet(() -> scanner.nextLine());
        boolean jugadorExistente = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoReal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemp))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(jugadorModificado)) {
                        jugadorExistente = true;
                        System.out.println(nombreNuevo);
                        String nombre = nuevoNombre.orElseGet(() -> scanner.nextLine());
                        linea = nombre+separadorUsuarios+partes[1];
                    }
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!jugadorExistente) {
            System.out.println(jugadorNoExiste);
            new ControladorUsuarios();
        }
        else reescribirArchivoJugadores(archivoReal, archivoTemp);
    }

    // Método que elimina un jugador de la lista
    public static void eliminarJugador(
            Optional<String> nombreJugador,
            File archivoReal,
            File archivoTemp) {
        System.out.println(eliminarJugador);
        String jugadorBorrado = nombreJugador.orElseGet(scanner::nextLine);
        boolean jugadorEliminado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoReal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemp))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (partes.length == 2 && partes[0].equals(jugadorBorrado)) {
                    indicarJugadorBorrado(jugadorBorrado);
                    jugadorEliminado = true;
                } else {
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!jugadorEliminado) {
            indicarJugadorNoEnBDD(jugadorBorrado);
            new ControladorUsuarios();
        }
        reescribirArchivoJugadores(archivoReal, archivoTemp);
    }

    // Metodo que reinicia la puntuacion de un jugador
    public static void reiniciarJugador(Optional<String> nombreJugador, File archivoReal, File archivoTemp) {
        System.out.println(reiniciarJugador);
        String jugadorReiniciado = nombreJugador.orElseGet(() -> scanner.nextLine());
        boolean jugadorExistente = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoReal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemp))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(jugadorReiniciado)) {
                        linea = partes[0]+puntuacionInicial;
                        indicarJugadorReiniciado(jugadorReiniciado);
                        jugadorExistente = true;
                    }
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!jugadorExistente) {
            indicarJugadorNoEnBDD(jugadorReiniciado);
            new ControladorUsuarios();
        }
        reescribirArchivoJugadores(archivoReal, archivoTemp);
    }

}
