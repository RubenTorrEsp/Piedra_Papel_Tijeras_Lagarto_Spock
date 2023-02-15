package org.example;

import java.util.Scanner;

public class Main {

    public static boolean jugarDeNuevo(String entrada){
        boolean rejugar = false;
        if(!entrada.contains("salir")) {
            System.out.println("Si quiere volver a jugar, escriba \"si\"");
            Scanner scanner = new Scanner(System.in);
            String respuesta = scanner.nextLine().toLowerCase();
            if (respuesta.contains("si")) rejugar = true;
            else System.out.println("Gracias por jugar a Piedra, Papel, Tijeras.");
        }
        return rejugar;
    }

    public static void main(String[] args) {
        Programa programa;
        boolean otraPartida = true;
        while (otraPartida) {
            programa = new Programa();
            otraPartida = jugarDeNuevo(programa.entrada);
        }
    }

}