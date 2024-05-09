package main;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

import static tools.Listas.*;
import static tools.Textos.*;

public class Common {

    // Creación del scanner
    static Scanner scanner = new Scanner(System.in);
    static Boolean reJugar = true;

    // Método que recoge la eleccion del jugador y, si es numerica, la convierte en texto
    @SuppressWarnings("static-access")
    public static String refactorizar(String seleccionJugador, User jugador) throws IOException{
        switch (seleccionJugador) {
            case seleccionPiedraNumero:
                seleccionJugador = seleccionPiedraTexto;
                break;
            case seleccionPapelNumero:
                seleccionJugador = seleccionPapelTexto;
                break;
            case seleccionTijerasNumero:
                seleccionJugador = seleccionTijerasTexto;
                break;
            case seleccionLagartoNumero:
                seleccionJugador = seleccionLagartoTexto;
                break;
            case seleccionSpockNumero:
                seleccionJugador = seleccionSpockTexto;
                break;
            case seleccionSalirNumero:
            case seleccionSalirTexto:
                seleccionJugador = seleccionSalirTexto;
                jugador.reescribirPuntuacion(jugador.nombre,jugador.puntuacion);
                break;
            default:
                break;
        }  
        return seleccionJugador;
    }

    // Comportamiento de comprobación del cruce entre la eleccion del jugador y la de la máquina
    public static void comprobarVictoria(String jugador, String maquina, User user) throws IOException {
        System.out.println(seleccionJugador+jugador);
        System.out.println(seleccionMaquina+maquina);

        if(jugador == maquina) System.out.println(empate);
        else if (obtenerVictoria(jugador, maquina)) {
            victoria(jugador, maquina);
            user.actualizarPuntuacion(user, true);
        }
        else {
            derrota(jugador, maquina);
            user.actualizarPuntuacion(user, false);
        }
    }

    // Selección de elemento por parte de la máquina
    public static String obtenerSeleccionMaquina(Integer elementos) {
        Random random = new Random();
        int valorJuego = random.nextInt(elementos)+1;
        String valorMaquina = elementosJuegoExtendido.get(valorJuego-1);
        return valorMaquina;
    }

    // Método por el que se pregunta al jugador si quiere jugar de nuevo
    @SuppressWarnings(errorEstatico)
    public static void reJugar(User jugador) throws IOException {
        System.out.println(seleccionRejugar);
        switch (scanner.nextLine().toLowerCase()){
            case seleccionSI:
                System.out.println(rejugarAfirmativo);
                break;
            default:
                reJugar = false;
                System.out.println(rejugarNegativo);
                jugador.reescribirPuntuacion(jugador.nombre,jugador.puntuacion);
                break;
        }
    }

    // Método que reescribe el archivo de jugadores
    public static void reescribirArchivoJugadores (File original, File temporal, String texto) {
        original.delete();
        temporal.renameTo(original);
        System.out.println(texto);
    }

}
