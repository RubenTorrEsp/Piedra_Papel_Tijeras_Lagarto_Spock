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
        volverAlControlador();
    }

    // Método que crea un jugador y lo incorpora a la lista
    public static void createPlayer(Optional<String> playerName, File file) throws IOException {
        System.out.println(PLAYER_CREATE);
        String newPlayer = playerName.orElseGet(() -> scanner.nextLine());
        boolean playerExists = usuarioExiste(newPlayer, file);
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
        volverAlControlador();
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
        volverAlControlador();
    }

    // Método que elimina un jugador de la lista
    public static void deletePlayer(
            Optional<String> nombreJugador,
            File archivoReal,
            File archivoTemp) throws IOException {
        System.out.println(PLAYER_DELETE);
        String jugadorBorrado = nombreJugador.orElseGet(scanner::nextLine);
        boolean jugadorEliminado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoReal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemp))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(SEPARATOR);
                if (partes.length == 2 && partes[0].equals(jugadorBorrado)) {
                    indicarJugadorBorrado(jugadorBorrado);
                    jugadorEliminado = true;
                } else {
                    bw.write(linea);
                    bw.newLine();
                }
            }
        }
        if (!jugadorEliminado) {
            indicarJugadorNoEnBDD(jugadorBorrado);
            if(nombreJugador.isEmpty()) new PlayerControler();
        }
        reWriteFilePlayers(archivoReal, archivoTemp);
        volverAlControlador();
    }

    // Metodo que reinicia la puntuacion de un jugador
    public static void reStartPlayer(Optional<String> nombreJugador, File archivoReal, File archivoTemp) throws IOException {
        System.out.println(PLAYER_RESTART);
        String jugadorReiniciado = nombreJugador.orElseGet(() -> scanner.nextLine());
        boolean jugadorExistente = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoReal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemp))) {
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(SEPARATOR);
                if (!line.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(jugadorReiniciado)) {
                        line = partes[0]+SCORE_START;
                        indicarJugadorReiniciado(jugadorReiniciado);
                        jugadorExistente = true;
                    }
                    bw.write(line);
                    bw.newLine();
                }
            }
        }
        if(!jugadorExistente) {
            indicarJugadorNoEnBDD(jugadorReiniciado);
            new PlayerControler();
        }
        reWriteFilePlayers(archivoReal, archivoTemp);
        volverAlControlador();
    }

    public static void showPodium(File archivoReal) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoReal)))  {
            List<List<Object>> jugadores = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(SEPARATOR);
                if (!line.trim().isEmpty()) {
                    if (partes.length == 2) {
                        List<Object> jugador = new ArrayList<>();
                        jugador.add(partes[0]);
                        jugador.add(partes[1]);
                        jugadores.add(jugador);
                    }
                }
            }
            jugadores.sort((o1, o2) -> {
                int puntuacion1 = Integer.parseInt((String) o1.get(1));
                int puntuacion2 = Integer.parseInt((String) o2.get(1));
                return Integer.compare(puntuacion2, puntuacion1);
            });
            podium(jugadores);
        }
    }

    public static void volverAlControlador() throws IOException {
        System.out.println(BACK_TO_CONTROLLER);
        String eleccion = scanner.nextLine().toLowerCase();
        if (eleccion.equals(SELECT_YES)) new PlayerControler();
        else goOut();
    }

}
