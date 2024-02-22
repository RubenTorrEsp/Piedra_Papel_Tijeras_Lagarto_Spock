package main;

import java.util.Scanner;

public class Main {

    public static void jugarDeNuevo(String entrada){
        new Programa();
        reJugar();
    }

    public static void main(String[] args) {
        Programa.darBienvenida();
        jugarDeNuevo("");
    }

    @SuppressWarnings("resource")
    private static void reJugar(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Desea volver a jugar? Escriba \"Si\" para jugar otra vez.");
        String elijeRejugar = scanner.nextLine().toLowerCase();
        switch (elijeRejugar){
            case "si":
                System.out.println("Has elegido volver a jugar. Pronto estará disponible");
            default:
                break;
        }

    }

}