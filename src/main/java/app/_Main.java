package app;

import static app.Common.*;
import static resources.Textos.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class _Main {
    
    static Scanner scanner = new Scanner(System.in);
    static User jugador;
    static File archivoOriginal = new File(archivoUsuarios);

    public static void main(String[] args) throws IOException {
        inicio();
    }

    public static void inicio() throws IOException {
        System.out.println(bienvenida);
        String eleccion = scanner.nextLine();
        switch (eleccion) {
            case seleccionNumero1:
                jugador = new User(establecerUsuario(), archivoOriginal);
                while(Common.reJugar) new JuegoExtendido(jugador);
                break;
            case seleccionNumero2:
                jugador = new User(establecerUsuario(), archivoOriginal);
                while(Common.reJugar) new JuegoClasico(jugador);
                break;
            case seleccionNumero9:
                new ControladorUsuarios();
                break;
            case seleccionNumero0:
                salir();
                break;
            default:
                System.out.println(seleccionInvalida);
                break;
        }
    }

    // Método que establece el jugador
    public static String establecerUsuario(){
        System.out.println(comprobarUsuario);
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