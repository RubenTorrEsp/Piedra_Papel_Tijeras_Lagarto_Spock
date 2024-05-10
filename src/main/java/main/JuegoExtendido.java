package main;

import java.io.IOException;
import java.util.Scanner;

import static tools.Listas.*;
import static tools.Textos.*;

public class JuegoExtendido extends Common {

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    Scanner scanner = new Scanner(System.in);
    String seleccionJugador;

    // Constructor del programa y ciclo de vida
    public JuegoExtendido(User jugador) throws IOException {
        jugarDeNuevo(jugador);
    }

    // Inicio del juego, recogida de seleccion del jugador y, si es una opcion correcta, comprobación de la victoria
    public void jugarDeNuevo(User jugador) throws IOException {
        System.out.println(cabeceraSeleccion);
        System.out.println(opcionesJuegoExtendido);

        if(obtenerEntrada(jugador)){
            if (seleccionJugador == seleccionSalirTexto) {
                System.out.println(seleccionSalir);
                System.out.println(rejugarNegativo);
                reJugar = false;
            }
            else {
                comprobarVictoria(seleccionJugador, obtenerSeleccionMaquina(5), jugador);
                reJugar(jugador);
            }
        }
        else reJugar(jugador);
    }

    // Obtener la elección del jugador
    public Boolean obtenerEntrada(User jugador) throws IOException {
        Boolean seleccionValida = false;
        seleccionJugador = scanner.nextLine().toLowerCase();
        if(elementosJuegoExtendido.contains(seleccionJugador)) {
            seleccionValida = true;
            seleccionJugador = refactorizar(seleccionJugador, jugador);
        }
        else {
            System.out.println(seleccionInvalida);
            seleccionValida = false;
        }
        return seleccionValida;
    }

}