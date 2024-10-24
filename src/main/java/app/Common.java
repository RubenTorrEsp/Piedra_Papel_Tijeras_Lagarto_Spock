package app;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

import static resources.Listas.*;
import static resources.Texts.*;

public class Common {

    static Scanner scanner = new Scanner(System.in);
    static Boolean reJugar = true;
    static File archivoOriginal = new File(FILE_USERS);
    static File archivoTemporal = new File(FILE_TEMP);

    // Método que recoge la eleccion del jugador y, si es numerica, la convierte en texto
    public static String refactorizar(String seleccionJugador, User jugador) {
        switch (seleccionJugador) {
            case NUMBER_1 -> seleccionJugador = SELECT_ROCK;
            case NUMBER_2 -> seleccionJugador = SELECT_PAPER;
            case NUMBER_3 -> seleccionJugador = SELECT_SCISSORS;
            case NUMBER_4 -> seleccionJugador = SELECT_WIZARD;
            case NUMBER_5 -> seleccionJugador = SELECT_SPOCK;
            case NUMBER_0, SELECT_OUT -> {
                seleccionJugador = SELECT_OUT;
                salir(jugador);
            }
        }  
        return seleccionJugador;
    }

    // Métodos salir, que cortan el programa
    public static void salir() {
        System.out.println(SELECT_EXIT);
        System.out.println(REPLAY_NO);
    }

    @SuppressWarnings("static-access")
    public static void salir(User jugador) {
        System.out.println(SELECT_EXIT);
        System.out.println(REPLAY_NO);
        jugador.reescribirPuntuacion(jugador.nombre,jugador.puntuacion,archivoOriginal,archivoTemporal);
    }

    // Comportamiento de comprobación del cruce entre la eleccion del jugador y la de la máquina
    public static void comprobarVictoria(String jugador, String maquina, User user) {
        System.out.println(SELECT_PLAYER+jugador);
        System.out.println(SELECT_MACHINE+maquina);
        if (jugador.equals(maquina)) {
            System.out.println(TIE);
        } else {
            boolean victoriaJugador = obtenerVictoria(jugador, maquina);
            if (victoriaJugador) victoria(jugador, maquina);
            else derrota(jugador, maquina);
            user.actualizarPuntuacion(victoriaJugador);
        }
    }

    // Selección de elemento por parte de la máquina
    public static String obtenerSeleccionMaquina(Integer elementos) {
        Random random = new Random();
        return ELEMENTS_EXTENDED.get(random.nextInt(elementos));
    }

    // Método por el que se pregunta al jugador si quiere jugar de nuevo
    public static void reJugar() {
        System.out.println(SELECT_REPLAY);
        if (scanner.nextLine().equalsIgnoreCase(SELECT_YES)) {
            System.out.println(REPLAY_YES);
        } else {
            reJugar = false;
            System.out.println(REPLAY_NO);
            User.reescribirPuntuacion(User.nombre, User.puntuacion,archivoOriginal,archivoTemporal);
        }
    }

    // Método que reescribe el archivo de jugadores
    public static void reescribirArchivoJugadores (File original, File temporal, String texto) {
        original.delete();
        temporal.renameTo(original);
        System.out.println(texto);
    }

    // Método que reescribe el archivo de jugadores
    public static void reescribirArchivoJugadores (File original, File temporal) {
        original.delete();
        temporal.renameTo(original);
    }

}
