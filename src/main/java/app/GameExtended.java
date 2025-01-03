package app;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static resources.Lists.*;
import static resources.Texts.*;

public class GameExtended extends Common {

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    Scanner scanner = new Scanner(System.in);
    String selectionPlayer;

    // Constructor del programa y ciclo de vida
    public GameExtended(User player) throws IOException {
        playExtended(player);
    }

    // Inicio del juego, recogida de seleccion del jugador y, si es una opcion correcta, comprobación de la victoria
    public void playExtended(User player) throws IOException {
        System.out.println(OPTIONS_EXTENDED);
        if(getEntry(player)){
            if (Objects.equals(selectionPlayer, SELECT_OUT)) rePlay = false;
            else {
                checkMatch(selectionPlayer, getSelectionMachine(5), player);
                playAgain();
            }
        }
        else playAgain();
    }

    // Obtener la elección del jugador
    public boolean getEntry(User player) throws IOException {
        boolean selectValid = false;
        selectionPlayer = scanner.nextLine().toLowerCase();
        if(ELEMENTS_EXTENDED.contains(selectionPlayer)) {
            selectValid = true;
            selectionPlayer = refactor(selectionPlayer, player);
        }
        else {
            System.out.println(SELECT_INVALID);
        }
        return selectValid;
    }

}