package main;

import static tools.Textos.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    
    static Scanner scanner = new Scanner(System.in);
    static Integer indiceInicial = 0;
    static Integer indiceResto = 1;

    public static void main(String[] args) throws IOException {
        User jugador = new User(establecerUsuario());
        inicio(jugador);
    }

    public static void inicio(User jugador) throws IOException {
        System.out.println(bienvenida);
        System.out.println(seleccionJuego);
        String eleccion = scanner.nextLine();
        switch (eleccion) {
            case seleccionJuegoExtendido:
                while(Common.reJugar) new JuegoExtendido(jugador);
                break;
            case seleccionJuegoClasico:
                while(Common.reJugar) new JuegoClasico(jugador);
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
        new User(formatoJugador(respuestaSinFormato));
        definicionUser(User.nombre, User.puntuacion);
        return formatoJugador(respuestaSinFormato);
    }

    // Método que arregla el formato del user escrito para evitar repeticiones
    // estableciendo la primera letra en mayúuscula y el resto en minúscula
    public static String formatoJugador(String jugador) {
        String inicial = jugador.substring(indiceInicial, indiceResto).toUpperCase();
        String resto = jugador.substring(indiceResto).toLowerCase();
        return inicial + resto;
    }
    
}