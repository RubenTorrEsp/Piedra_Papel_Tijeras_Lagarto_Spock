package main;

import static tools.Textos.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        User jugador = new User(establecerUsuario());
        inicio(jugador);
    }

    // Método que establece el jugador
    public static String establecerUsuario(){
        System.out.println(comprobarUsuario);
        String respuestaSinFormato = formatoJugador(scanner.nextLine());
        String respuesta = formatoJugador(respuestaSinFormato);
        new User(respuesta);
        definicionUser(User.nombre, User.puntuacion);
        return respuesta;
    }

    public static void inicio(User jugador) throws IOException {
        System.out.println(bienvenida);
        System.out.println(seleccionJuego);
        String eleccion = scanner.nextLine();
        switch (eleccion) {
            case "1":
                while(Common.reJugar) new JuegoExtendido(jugador);
                break;
            case "2":
                while(Common.reJugar) new JuegoClasico(jugador);
                break;
            default:
                System.out.println(seleccionInvalida);
                break;
        }
    }

    // Método que arregla el formato del user escrito para evitar repeticiones
    public static String formatoJugador(String jugador) {
        String inicial = jugador.substring(0, 1).toUpperCase();
        String resto = jugador.substring(1).toLowerCase();
        return inicial + resto;
    }
    
}