package main;

import static tools.Textos.*;
import java.util.Scanner;

public class Main {
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        User jugador = new User(establecerUsuario());
        inicio(jugador);
    }

    // Método que establece el jugador
    public static String establecerUsuario(){
        System.out.println(comprobarUsuario);
        String respuesta = scanner.nextLine();
        new User(respuesta);
        definicionUser(User.nombre, User.puntuacion);
        return respuesta;
    }

    public static void inicio(User jugador){
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

}