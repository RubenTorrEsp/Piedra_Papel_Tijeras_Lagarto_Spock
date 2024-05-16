package main;

import static tools.Textos.*;

import java.io.IOException;
import java.util.Scanner;

public class _Main {
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        User jugador = new User(establecerUsuario());
        inicio(jugador);
    }

    public static void inicio(User jugador) throws IOException {
        System.out.println(bienvenida);
        String eleccion = scanner.nextLine();
        switch (eleccion) {
            case seleccionJuegoExtendido:
                while(Common.reJugar) new JuegoExtendido(jugador);
                break;
            case seleccionJuegoClasico:
                while(Common.reJugar) new JuegoClasico(jugador);
                break;
            case seleccionUsuarios:
                System.out.println(opcionNoDisponible);
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
        Integer indiceInicial = 0;
        Integer indiceResto = 1;
        String inicial = jugador.substring(indiceInicial, indiceResto).toUpperCase();
        String resto = jugador.substring(indiceResto).toLowerCase();
        return inicial + resto;
    }
    
}