package app;

import java.util.Objects;
import java.util.Scanner;

import static resources.Listas.*;
import static resources.Texts.*;

public class JuegoExtendido extends Common {

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    Scanner scanner = new Scanner(System.in);
    String seleccionJugador;

    // Constructor del programa y ciclo de vida
    public JuegoExtendido(User jugador) {
        jugarDeNuevo(jugador);
    }

    // Inicio del juego, recogida de seleccion del jugador y, si es una opcion correcta, comprobación de la victoria
    public void jugarDeNuevo(User jugador) {
        System.out.println(OPTIONS_EXTENDED);
        if(obtenerEntrada(jugador)){
            if (Objects.equals(seleccionJugador, SELECT_OUT)) reJugar = false;
            else {
                comprobarVictoria(seleccionJugador, obtenerSeleccionMaquina(5), jugador);
                reJugar();
            }
        }
        else reJugar();
    }

    // Obtener la elección del jugador
    public boolean obtenerEntrada(User jugador) {
        boolean seleccionValida = false;
        seleccionJugador = scanner.nextLine().toLowerCase();
        if(ELEMENTS_EXTENDED.contains(seleccionJugador)) {
            seleccionValida = true;
            seleccionJugador = refactor(seleccionJugador, jugador);
        }
        else {
            System.out.println(SELECT_INVALID);
        }
        return seleccionValida;
    }

}