package main;

import tools.Textos;
import java.util.Scanner;

public class Main {
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicio();
        //while(ProgramaExtendido.reJugar) new ProgramaExtendido();
    }

    public static void inicio(){
        System.out.println(Textos.bienvenida);
        seleccion();
    }

    //Comportamiento de prueba para no interferir en el main. A eliminar
    public static void seleccion(){
        System.out.println("Elija a qué quiere jugar indicando el numero");
        System.out.println("1- Juego extendido");
        System.out.println("2- Juego clásico (Próximamente)");
        String eleccion = scanner.nextLine();
        switch (eleccion) {
            case "1":
                while(ProgramaExtendido.reJugar) new ProgramaExtendido();
                break;
            case "2":
                System.out.println("Opcion no disponible en este momento. Disculpe las molestias");
                break;
            default:
                System.out.println(Textos.seleccionInvalida);
                break;
        }
    }

}