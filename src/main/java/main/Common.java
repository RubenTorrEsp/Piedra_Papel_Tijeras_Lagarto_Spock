package main;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import static tools.Listas.*;
import static tools.Textos.*;

public class Common {

    // Creación del scanner
    static Scanner scanner = new Scanner(System.in);
    static Boolean reJugar = true;

    // Método que recoge la eleccion del jugador y, si es numerica, la convierte en texto
    public static String refactorizar(String seleccionJugador){
        switch (seleccionJugador) {
            case "1":
                seleccionJugador = "piedra";
                break;
            case "2":
                seleccionJugador = "papel";
                break;
            case "3":
                seleccionJugador = "tijeras";
                break;
            case "4":
                seleccionJugador = "lagarto";
                break;
            case "5":
                seleccionJugador = "spock";
                break;
            case "0":
            case "salir":
                seleccionJugador = "salir";
                break;
            default:
                break;
        }  
        return seleccionJugador;
    }

    // Comportamiento de comprobación del cruce entre la eleccion del jugador y la de la máquina
    public static void comprobarVictoria(String jugador, String maquina, User user) {
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
    @SuppressWarnings("static-access")
    public static void reJugar(User jugador) throws IOException {
        System.out.println(seleccionRejugar);
        switch (scanner.nextLine().toLowerCase()){
            case "si":
                System.out.println(rejugarAfirmativo);
                break;
            default:
                reJugar = false;
                System.out.println(rejugarNegativo);
                jugador.reescribirPuntuacion(jugador.nombre,jugador.puntuacion);
                break;
        }
    }

}
