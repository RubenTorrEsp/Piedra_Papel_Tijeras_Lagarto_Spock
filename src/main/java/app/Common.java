package app;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

import static resources.Listas.*;
import static resources.Textos.*;

public class Common {

    static Scanner scanner = new Scanner(System.in);
    static Boolean reJugar = true;
    static File archivoOriginal = new File(archivoUsuarios);
    static File archivoTemporal = new File(archivoUsuariosTemporal);

    // Método que recoge la eleccion del jugador y, si es numerica, la convierte en texto
    public static String refactorizar(String seleccionJugador, User jugador) {
        switch (seleccionJugador) {
            case NUMBER_1 -> seleccionJugador = seleccionPiedra;
            case NUMBER_2 -> seleccionJugador = seleccionPapel;
            case NUMBER_3 -> seleccionJugador = seleccionTijeras;
            case NUMBER_4 -> seleccionJugador = seleccionLagarto;
            case NUMBER_5 -> seleccionJugador = seleccionSpock;
            case NUMBER_0, seleccionSalir -> {
                seleccionJugador = seleccionSalir;
                salir(jugador);
            }
        }  
        return seleccionJugador;
    }

    // Métodos salir, que cortan el programa
    public static void salir() {
        System.out.println(seleccionadoSalir);
        System.out.println(rejugarNegativo);
    }

    @SuppressWarnings("static-access")
    public static void salir(User jugador) {
        System.out.println(seleccionadoSalir);
        System.out.println(rejugarNegativo);
        jugador.reescribirPuntuacion(jugador.nombre,jugador.puntuacion,archivoOriginal,archivoTemporal);
    }

    // Comportamiento de comprobación del cruce entre la eleccion del jugador y la de la máquina
    public static void comprobarVictoria(String jugador, String maquina, User user) {
        System.out.println(seleccionJugador+jugador);
        System.out.println(seleccionMaquina+maquina);
        if (jugador.equals(maquina)) {
            System.out.println(empate);
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
        return elementosJuegoExtendido.get(random.nextInt(elementos));
    }

    // Método por el que se pregunta al jugador si quiere jugar de nuevo
    public static void reJugar() {
        System.out.println(seleccionRejugar);
        if (scanner.nextLine().equalsIgnoreCase(seleccionSI)) {
            System.out.println(rejugarAfirmativo);
        } else {
            reJugar = false;
            System.out.println(rejugarNegativo);
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
