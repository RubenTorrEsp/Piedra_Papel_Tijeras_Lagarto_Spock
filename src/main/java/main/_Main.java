package main;

import static tools.Textos.*;

import java.io.IOException;
import java.util.Scanner;

public class _Main {
    
    static Scanner scanner = new Scanner(System.in);
    static User jugador;

    public static void main(String[] args) throws IOException {
        inicio();
    }

    public static void inicio() throws IOException {
        System.out.println(bienvenida);
        String eleccion = scanner.nextLine();
        switch (eleccion) {
            case seleccionNumero1:
                jugador = new User(establecerUsuario());
                while(Common.reJugar) new JuegoExtendido(jugador);
                break;
            case seleccionNumero2:
                jugador = new User(establecerUsuario());    
                while(Common.reJugar) new JuegoClasico(jugador);
                break;
            case seleccionNumero9:
                new ControladorUsuarios();
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