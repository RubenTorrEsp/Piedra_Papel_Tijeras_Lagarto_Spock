package app;

import java.util.Objects;
import java.util.Scanner;

import static resources.Listas.*;
import static resources.Texts.*;

public class GameClassic extends Common {

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    Scanner scanner = new Scanner(System.in);
    String selectionPlayer;

    // Constructor del programa y ciclo de vida
    public GameClassic(User player) {
        jugarDeNuevo(player);
    }

    // Inicio del juego, recogida de seleccion del jugador y, si es una opcion correcta, comprobación de la victoria
    public void jugarDeNuevo(User jugador) {
        System.out.println(OPTIONS_CLASSIC);
        if(obtenerEntrada(jugador)){
            if (Objects.equals(selectionPlayer, SELECT_OUT)) reJugar = false;
            else {
                checkMatch(selectionPlayer, getSelectionMachine(3), jugador);
                playAgain();
            }
        }
        else playAgain();
    }

    // Obtener la elección del jugador
    public boolean obtenerEntrada(User jugador) {
        boolean seleccionValida = false;
        selectionPlayer = scanner.nextLine().toLowerCase();
        if(ELEMENTS_CLASSIC.contains(selectionPlayer)) {
            seleccionValida = true;
            selectionPlayer = refactor(selectionPlayer, jugador);
        }
        else System.out.println(SELECT_INVALID);
        return seleccionValida;
    }

}