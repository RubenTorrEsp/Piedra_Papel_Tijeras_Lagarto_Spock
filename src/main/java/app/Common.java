package app;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

import static app._Main.*;
import static resources.Lists.*;
import static resources.Texts.*;

public class Common {

    static Scanner scanner = new Scanner(System.in);
    static Boolean rePlay = true;

    // Método que recoge la eleccion del jugador y, si es numerica, la convierte en texto
    public static String refactor(String selectionPlayer, User player) throws IOException {
        switch (selectionPlayer) {
            case NUMBER_1 -> selectionPlayer = SELECT_ROCK;
            case NUMBER_2 -> selectionPlayer = SELECT_PAPER;
            case NUMBER_3 -> selectionPlayer = SELECT_SCISSORS;
            case NUMBER_4 -> selectionPlayer = SELECT_WIZARD;
            case NUMBER_5 -> selectionPlayer = SELECT_SPOCK;
            case NUMBER_0, SELECT_OUT -> {
                selectionPlayer = SELECT_OUT;
                goOut(player);
            }
        }  
        return selectionPlayer;
    }

    // Métodos salir, que cortan el programa
    public static void goOut() {
        System.out.println(SELECT_EXIT);
        System.out.println(REPLAY_NO);
    }

    @SuppressWarnings("static-access")
    public static void goOut(User player) throws IOException {
        System.out.println(SELECT_EXIT);
        System.out.println(REPLAY_NO);
        player.reWriteScore(player.name,player.score,filePlayers,fileTemp);
    }

    // Comportamiento de comprobación del cruce entre la eleccion del jugador y la de la máquina
    public static void checkMatch(String player, String machine, User user) throws IOException {
        System.out.println(SELECT_PLAYER+player);
        System.out.println(SELECT_MACHINE+machine);
        if (player.equals(machine)) {
            System.out.println(TIE);
        } else {
            boolean victoryPlayer = getVictory(player, machine);
            if (victoryPlayer) victoria(player, machine);
            else derrota(player, machine);
            user.updateScore(victoryPlayer);
        }
    }

    // Selección de elemento por parte de la máquina
    public static String getSelectionMachine(Integer elements) {
        Random random = new Random();
        return ELEMENTS_EXTENDED.get(random.nextInt(elements));
    }

    // Método por el que se pregunta al jugador si quiere jugar de nuevo
    public static void playAgain() throws IOException {
        System.out.println(SELECT_REPLAY);
        if (scanner.nextLine().equalsIgnoreCase(SELECT_YES)) {
            System.out.println(REPLAY_YES);
        } else {
            rePlay = false;
            System.out.println(REPLAY_NO);
            User.reWriteScore(User.name, User.score,filePlayers,fileTemp);
        }
    }

    // Método que reescribe el archivo de jugadores
    public static void reWriteFilePlayers (File original, File temp, String text) {
        original.delete();
        temp.renameTo(original);
        System.out.println(text);
    }

    // Método que reescribe el archivo de jugadores
    public static void reWriteFilePlayers (File original, File temp) {
        original.delete();
        temp.renameTo(original);
    }

}
