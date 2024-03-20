package main;

import tools.Textos;
import java.util.Scanner;

public class Main {
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //inicio();
        new User(establecerUsuario());
    }


    public static String establecerUsuario(){
        /*
        System.out.println("Escribe tu nombre de usuario y comprobaremos si ya has jugado antes.");
        String user = scanner.nextLine();
        User usuario = new User(user);
        System.out.println("El usuario "+usuario.nombre+" tiene una puntuacion de "+usuario.puntuacion+" puntos.");
        return usuario.nombre;
        */
        
        System.out.println("Escribe tu nombre de usuario y comprobaremos si ya has jugado antes.");
        String respuesta = scanner.nextLine();
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