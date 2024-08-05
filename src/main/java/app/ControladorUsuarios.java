package app;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

import static app.Common.*;
import static app.User.*;
import static resources.Textos.*;

public class ControladorUsuarios {

    static String linea;
    static Scanner scanner = new Scanner(System.in);
    static File archivoOriginal = new File(archivoUsuarios);
    static File archivoTemporal = new File(archivoUsuariosTemporal);

    public ControladorUsuarios() {
        System.out.println(opcionesGestorJugadores);
        String eleccion = scanner.nextLine();
        switch (eleccion) {
            case seleccionNumero1:
                mostrarJugadores();
                break;
            case seleccionNumero2:
                crearJugador(Optional.empty(), archivoOriginal);
                break;
            case seleccionNumero3:
                modificarJugador(Optional.empty(), Optional.empty(), archivoOriginal, archivoTemporal);
                break;
            case seleccionNumero4:
                eliminarJugador(Optional.empty(), archivoOriginal, archivoTemporal);
                break;
            case seleccionNumero5:
                reiniciarJugador(Optional.empty(), archivoOriginal, archivoTemporal);
                break;
            case seleccionNumero0:
                salir();
                break;
            default:
                System.out.println(seleccionInvalida);
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
    // TODO: Está repitiendo jugadores
    public static void crearJugador(Optional<String> nombreJugador, File archivo) {
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
                        System.out.println(nuevoNombre);
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
        if(!jugadorExistente) System.out.println(jugadorNoExiste);
        reescribirArchivoJugadores(archivoReal, archivoTemp);
    }

    // Método que elimina un jugador de la lista
    public static void eliminarJugador(Optional<String> nombreJugador, File archivoReal, File archivoTemp) {
        System.out.println(eliminarJugador);
        String jugadorBorrado = nombreJugador.orElseGet(() -> scanner.nextLine());
        boolean jugadorEliminado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoReal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemp))) {
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
        reescribirArchivoJugadores(archivoReal, archivoTemp);
    }

    // Metodo que reinicia la puntuacion de un jugador
    public void reiniciarJugador(Optional<String> nombreJugador, File archivoReal, File archivoTemp) {
        System.out.println(reiniciarJugador);
        String jugadorReiniciado = nombreJugador.orElseGet(() -> scanner.nextLine());
        boolean jugadorExistente = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
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
        if(!jugadorExistente) indicarJugadorNoEnBDD(jugadorReiniciado);
        reescribirArchivoJugadores(archivoReal, archivoTemp);
    }

}
