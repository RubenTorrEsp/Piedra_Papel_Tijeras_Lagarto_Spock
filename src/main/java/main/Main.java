package main;

import java.util.Scanner;

import tools.Textos;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Boolean reJugar = true;

    public static void main(String[] args) {
        System.out.println(Textos.bienvenida);
        jugarDeNuevo();
    }

    public static void jugarDeNuevo(){
        while(reJugar) {
            new Programa();
            reJugar();
        }    
    }

    private static void reJugar(){
        System.out.println(Textos.seleccionRejugar);
        switch (scanner.nextLine().toLowerCase()){
            case "si":
                System.out.println(Textos.rejugarAfirmativo);
                break;
            default:
                reJugar = false;
                System.out.println(Textos.rejugarNegativo);
                break;
        }
    }

}