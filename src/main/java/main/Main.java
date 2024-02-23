package main;

import java.util.Scanner;

import tools.Textos;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        darBienvenida();
        jugarDeNuevo();
    }

    public static void jugarDeNuevo(){
        new Programa();
        reJugar();
    }

    // Inicio del programa y presentación de opciones
    public static void darBienvenida() {
        System.out.println(Textos.bienvenida);
        System.out.println(Textos.opciones);
    }

    private static Boolean reJugar(){
        Boolean reJugar = false;
        System.out.println("¿Desea volver a jugar? Escriba \"Si\" para jugar otra vez.");
        switch (scanner.nextLine().toLowerCase()){
            case "si":
                reJugar = true;
                System.out.println("Has elegido volver a jugar. Pronto estará disponible");
            default:
                break;
        }
        return reJugar;
    }

}