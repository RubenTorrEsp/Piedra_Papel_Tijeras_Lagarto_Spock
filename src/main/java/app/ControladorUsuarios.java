package app;

import java.io.*;
import java.util.*;

import static app.Common.*;
import static app.User.*;
import static resources.Texts.*;

public class ControladorUsuarios {

    static String linea;
    static Scanner scanner = new Scanner(System.in);
    static File archivoOriginal = new File(FILE_USERS);
    static File archivoTemporal = new File(FILE_TEMP);

    //TODO: Arreglo de la opcion podiuum cuando el comportamiento esté completado
    public ControladorUsuarios() throws IOException {
        System.out.println(OPTIONS_PLAYER);
        String eleccion = scanner.nextLine();
        switch (eleccion) {
            case NUMBER_1:
                mostrarJugadores();
                break;
            case NUMBER_2:
                crearJugador(EMPTY_NAME, archivoOriginal);
                break;
            case NUMBER_3:
                modificarJugador(EMPTY_NAME, EMPTY_NAME, archivoOriginal, archivoTemporal);
                break;
            case NUMBER_4:
                eliminarJugador(EMPTY_NAME, archivoOriginal, archivoTemporal);
                break;
            case NUMBER_5:
                reiniciarJugador(EMPTY_NAME, archivoOriginal, archivoTemporal);
                break;
            case NUMBER_6:
                mostrarPodium(archivoOriginal);
                break;
            case NUMBER_0:
                salir();
                break;
            default:
                System.out.println(SELECT_INVALID);
                new ControladorUsuarios();
                break;
        }
    }

    // Método que muestra una lista de todos los jugadores disponibles
    public void mostrarJugadores() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            System.out.println(SHOW_PLAYERS);
            while ((linea = br.readLine()) != null) {
                if(linea.length() > 1) {
                    String[] partes = linea.split(SEPARATOR);
                    mostrarJugador(partes[0], partes[1]);
                }
            }
        }
        volverAlControlador();
    }

    // Método que crea un jugador y lo incorpora a la lista
    public static void crearJugador(Optional<String> nombreJugador, File archivo) throws IOException {
        System.out.println(PLAYER_CREATE);
        String jugadorNuevo = nombreJugador.orElseGet(() -> scanner.nextLine());
        boolean jugadorExistente = usuarioExiste(jugadorNuevo, archivo);
        if (!jugadorExistente) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
                bw.newLine();
                bw.write(jugadorNuevo + SCORE_START);
                indicarJugadorCreado(jugadorNuevo);
            }
        } else {
            indicarJugadorYaEnBDD(jugadorNuevo);
            if(nombreJugador.isEmpty()) new ControladorUsuarios();
        }
        volverAlControlador();
    }

    // Método que modifica los valores de un usuario
    public static void modificarJugador(
            Optional<String> nombreJugador,
            Optional<String> nuevoNombre,
            File archivoReal,
            File archivoTemp) throws IOException {
        System.out.println(PLAYER_MODIFY);
        String jugadorModificado = nombreJugador.orElseGet(() -> scanner.nextLine());
        boolean jugadorExistente = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoReal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemp))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(SEPARATOR);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(jugadorModificado)) {
                        jugadorExistente = true;
                        System.out.println(NEW_NAME);
                        String nombre = nuevoNombre.orElseGet(() -> scanner.nextLine());
                        linea = nombre+SEPARATOR+partes[1];
                    }
                    bw.write(linea);
                    bw.newLine();
                }
            }
        }
        if(!jugadorExistente) {
            System.out.println(PLAYER_NOT_EXIST);
            new ControladorUsuarios();
        }
        else reescribirArchivoJugadores(archivoReal, archivoTemp);
        volverAlControlador();
    }

    // Método que elimina un jugador de la lista
    public static void eliminarJugador(
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
            if(nombreJugador.isEmpty()) new ControladorUsuarios();
        }
        reescribirArchivoJugadores(archivoReal, archivoTemp);
        volverAlControlador();
    }

    // Metodo que reinicia la puntuacion de un jugador
    public static void reiniciarJugador(Optional<String> nombreJugador, File archivoReal, File archivoTemp) throws IOException {
        System.out.println(PLAYER_RESTART);
        String jugadorReiniciado = nombreJugador.orElseGet(() -> scanner.nextLine());
        boolean jugadorExistente = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoReal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemp))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(SEPARATOR);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(jugadorReiniciado)) {
                        linea = partes[0]+SCORE_START;
                        indicarJugadorReiniciado(jugadorReiniciado);
                        jugadorExistente = true;
                    }
                    bw.write(linea);
                    bw.newLine();
                }
            }
        }
        if(!jugadorExistente) {
            indicarJugadorNoEnBDD(jugadorReiniciado);
            new ControladorUsuarios();
        }
        reescribirArchivoJugadores(archivoReal, archivoTemp);
        volverAlControlador();
    }

    // TODO: Completar comportamiento
    public static void  mostrarPodium(File archivoReal) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoReal)))  {
            List<List<Object>> jugadores = new ArrayList<>();
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(SEPARATOR);
                if (!linea.trim().isEmpty()) {
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
            showPodium(jugadores);
        }
    }

    public static void volverAlControlador() throws IOException {
        System.out.println(BACK_TO_CONTROLLER);
        String eleccion = scanner.nextLine().toLowerCase();
        if (eleccion.equals(SELECT_YES)) new ControladorUsuarios();
        else salir();
    }

}
