package main;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Programa.darBienvenida();
        jugarDeNuevo("");
    }

    public static void jugarDeNuevo(String entrada){
        new Programa();
        reJugar();
    }

    private static Boolean reJugar(){
        Boolean reJugar = false;
        System.out.println("¿Desea volver a jugar? Escriba \"Si\" para jugar otra vez.");
        String elijeRejugar = scanner.nextLine().toLowerCase();
        switch (elijeRejugar){
            case "si":
                reJugar = true;
                System.out.println("Has elegido volver a jugar. Pronto estará disponible");
            default:
                break;
        }
        return reJugar;
    }

}