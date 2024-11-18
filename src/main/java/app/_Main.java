package app;

import static app.Common.*;
import static resources.Texts.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class _Main {
    
    static Scanner scanner = new Scanner(System.in);
    static User player;
    static File filePlayers = new File(FILE_USERS);
    static File fileTemp = new File(FILE_TEMP);

    public static void main(String[] args) throws IOException {start();}

    public static void start() throws IOException {
        System.out.println(WELLCOME);
        String select = scanner.nextLine();
        switch (select) {
            case NUMBER_1:
                player = new User(activatePlayer(), filePlayers);
                while(Common.rePlay) new GameExtended(player);
                break;
            case NUMBER_2:
                player = new User(activatePlayer(), filePlayers);
                while(Common.rePlay) new GameClassic(player);
                break;
            case NUMBER_9:
                new PlayerControler();
                break;
            case NUMBER_0:
                goOut();
                break;
            default:
                System.out.println(SELECT_INVALID);
                start();
                break;
        }
    }

    // Método que establece el jugador
    public static String activatePlayer() throws IOException {
        System.out.println(CHECK_PLAYER);
        String unformatedAnswer = formatPlayer(scanner.nextLine());
        new User(formatPlayer(unformatedAnswer), filePlayers);
        definicionUser(User.name, User.score);
        return formatPlayer(unformatedAnswer);
    }

    // Método que capitaliza el formato del jugador
    public static String formatPlayer(String jugador) {
        int indiceInicial = 0;
        int indiceResto = 1;
        String inicial = jugador.substring(indiceInicial, indiceResto).toUpperCase();
        String resto = jugador.substring(indiceResto).toLowerCase();
        return inicial + resto;
    }
    
}