package app;

import static app.Common.*;
import static resources.Texts.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class _Main {
    
    static Scanner scanner = new Scanner(System.in);
    static User jugador;
    static File archivoOriginal = new File(FILE_USERS);

    public static void main(String[] args) throws IOException {start();}

    public static void start() throws IOException {
        System.out.println(WELLCOME);
        String select = scanner.nextLine();
        switch (select) {
            case NUMBER_1:
                jugador = new User(establecerUsuario(), archivoOriginal);
                while(Common.reJugar) new JuegoExtendido(jugador);
                break;
            case NUMBER_2:
                jugador = new User(establecerUsuario(), archivoOriginal);
                while(Common.reJugar) new JuegoClasico(jugador);
                break;
            case NUMBER_9:
                new ControladorUsuarios();
                break;
            case NUMBER_0:
                salir();
                break;
            default:
                System.out.println(SELECT_INVALID);
                start();
                break;
        }
    }

    // Método que establece el jugador
    public static String establecerUsuario() throws IOException {
        System.out.println(CHECK_PLAYER);
        String respuestaSinFormato = formatoJugador(scanner.nextLine());
        new User(formatoJugador(respuestaSinFormato), archivoOriginal);
        definicionUser(User.nombre, User.puntuacion);
        return formatoJugador(respuestaSinFormato);
    }

    // Método que capitaliza el formato del jugador
    public static String formatoJugador(String jugador) {
        int indiceInicial = 0;
        int indiceResto = 1;
        String inicial = jugador.substring(indiceInicial, indiceResto).toUpperCase();
        String resto = jugador.substring(indiceResto).toLowerCase();
        return inicial + resto;
    }
    
}