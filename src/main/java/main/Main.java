package main;

import java.util.Scanner;

import tools.Textos;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Boolean reJugar = true;

    public static void main(String[] args) {
        darBienvenida();
        jugarDeNuevo();
    }

    public static void jugarDeNuevo(){
        while(reJugar) {
            new Programa();
            reJugar();
        }    
    }

    // Inicio del programa y presentación de opciones
    public static void darBienvenida() {
        System.out.println(Textos.bienvenida);
    }

    private static void reJugar(){
        System.out.println("¿Desea volver a jugar? Escriba \"Si\" para jugar otra vez.");
        switch (scanner.nextLine().toLowerCase()){
            case "si":
                reJugar = true;
                System.out.println("Has elegido volver a jugar. Elija su opción con nombre o número");
                break;
            default:
                reJugar = false;
                break;
        }
    }

}