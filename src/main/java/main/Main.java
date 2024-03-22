package main;

import tools.Textos;
import java.util.Scanner;

public class Main {
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new User(establecerUsuario());
        inicio();
    }

    // Método que establece el jugador
    public static String establecerUsuario(){
        System.out.println(Textos.comprobarUsuario);
        String respuesta = scanner.nextLine();
        new User(respuesta);
        Textos.definicionUser(User.nombre, User.puntuacion);
        return respuesta;
    }

    public static void inicio(){
        System.out.println(Textos.bienvenida);
        System.out.println(Textos.seleccionJuego);
        String eleccion = scanner.nextLine();
        switch (eleccion) {
            case "1":
                while(Common.reJugar) new JuegoExtendido();
                break;
            case "2":
                while(Common.reJugar) new JuegoClasico();
                break;
            default:
                System.out.println(Textos.seleccionInvalida);
                break;
        }
    }

}