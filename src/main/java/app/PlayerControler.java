package app;

import java.io.*;
import java.util.*;

import static app.Common.*;
import static app.User.*;
import static app._Main.*;
import static resources.Texts.*;

public class PlayerControler {

    static String line;
    static Scanner scanner = new Scanner(System.in);

    public PlayerControler() throws IOException {
        System.out.println(OPTIONS_PLAYER);
        String election = scanner.nextLine();
        switch (election) {
            case NUMBER_1:
                showPlayers();
                break;
            case NUMBER_2:
                createPlayer(EMPTY_NAME, filePlayers);
                break;
            case NUMBER_3:
                modifyPlayer(EMPTY_NAME, EMPTY_NAME, filePlayers, fileTemp);
                break;
            case NUMBER_4:
                deletePlayer(EMPTY_NAME, filePlayers, fileTemp);
                break;
            case NUMBER_5:
                reStartPlayer(EMPTY_NAME, filePlayers, fileTemp);
                break;
            case NUMBER_6:
                showPodium(filePlayers);
                break;
            case NUMBER_0:
                goOut();
                break;
            default:
                System.out.println(SELECT_INVALID);
                new PlayerControler();
                break;
        }
    }

    // Método que muestra una lista de todos los jugadores disponibles
    public void showPlayers() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePlayers))) {
            System.out.println(SHOW_PLAYERS);
            while ((line = br.readLine()) != null) {
                if(line.length() > 1) {
                    String[] partes = line.split(SEPARATOR);
                    mostrarJugador(partes[0], partes[1]);
                }
            }
        }
        backToController();
    }

    // Método que crea un jugador y lo incorpora a la lista
    public static void createPlayer(Optional<String> playerName, File file) throws IOException {
        System.out.println(PLAYER_CREATE);
        String newPlayer = playerName.orElseGet(() -> scanner.nextLine());
        boolean playerExists = userExists(newPlayer, file);
        if (!playerExists) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                bw.newLine();
                bw.write(newPlayer + SCORE_START);
                indicarJugadorCreado(newPlayer);
            }
        } else {
            indicarJugadorYaEnBDD(newPlayer);
            if(playerName.isEmpty()) new PlayerControler();
        }
        backToController();
    }

    // Método que modifica los valores de un usuario
    public static void modifyPlayer(
            Optional<String> playerName,
            Optional<String> newName,
            File fileReal,
            File fileTemp) throws IOException {
        System.out.println(PLAYER_MODIFY);
        String playerModified = playerName.orElseGet(() -> scanner.nextLine());
        boolean playerExists = false;
        try (BufferedReader br = new BufferedReader(new FileReader(fileReal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemp))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (!line.trim().isEmpty()) {
                    if (parts.length == 2 && parts[0].equals(playerModified)) {
                        playerExists = true;
                        System.out.println(NEW_NAME);
                        String name = newName.orElseGet(() -> scanner.nextLine());
                        line = name+SEPARATOR+parts[1];
                    }
                    bw.write(line);
                    bw.newLine();
                }
            }
        }
        if(!playerExists) {
            System.out.println(PLAYER_NOT_EXIST);
            new PlayerControler();
        }
        else reWriteFilePlayers(fileReal, fileTemp);
        backToController();
    }

    // Método que elimina un jugador de la lista
    public static void deletePlayer(
            Optional<String> playerName,
            File fileReal,
            File fileTemp) throws IOException {
        System.out.println(PLAYER_DELETE);
        String playerDeleted = playerName.orElseGet(scanner::nextLine);
        boolean playerErased = false;
        try (BufferedReader br = new BufferedReader(new FileReader(fileReal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemp))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (parts.length == 2 && parts[0].equals(playerDeleted)) {
                    indicarJugadorBorrado(playerDeleted);
                    playerErased = true;
                } else {
                    bw.write(line);
                    bw.newLine();
                }
            }
        }
        if (!playerErased) {
            indicarJugadorNoEnBDD(playerDeleted);
            if(playerName.isEmpty()) new PlayerControler();
        }
        reWriteFilePlayers(fileReal, fileTemp);
        backToController();
    }

    // Metodo que reinicia la puntuacion de un jugador
    public static void reStartPlayer(
            Optional<String> namePlayer,
            File fileReal,
            File fileTemp) throws IOException {
        System.out.println(PLAYER_RESTART);
        String playerReStarted = namePlayer.orElseGet(() -> scanner.nextLine());
        boolean playerExists = false;
        try (BufferedReader br = new BufferedReader(new FileReader(fileReal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemp))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (!line.trim().isEmpty()) {
                    if (parts.length == 2 && parts[0].equals(playerReStarted)) {
                        line = parts[0]+SCORE_START;
                        indicarJugadorReiniciado(playerReStarted);
                        playerExists = true;
                    }
                    bw.write(line);
                    bw.newLine();
                }
            }
        }
        if(!playerExists) {
            indicarJugadorNoEnBDD(playerReStarted);
            new PlayerControler();
        }
        reWriteFilePlayers(fileReal, fileTemp);
        backToController();
    }

    public static void showPodium(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file)))  {
            List<List<Object>> players = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (!line.trim().isEmpty()) {
                    if (parts.length == 2) {
                        List<Object> player = new ArrayList<>();
                        player.add(parts[0]);
                        player.add(parts[1]);
                        players.add(player);
                    }
                }
            }
            players.sort((o1, o2) -> {
                int score1 = Integer.parseInt((String) o1.get(1));
                int score2 = Integer.parseInt((String) o2.get(1));
                return Integer.compare(score2, score1);
            });
            podium(players);
        }
    }

    public static void backToController() throws IOException {
        System.out.println(BACK_TO_CONTROLLER);
        String election = scanner.nextLine().toLowerCase();
        if (election.equals(SELECT_YES)) new PlayerControler();
        else goOut();
    }

}
