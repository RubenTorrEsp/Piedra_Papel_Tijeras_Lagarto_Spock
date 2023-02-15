package org.example;

import java.util.Scanner;

public class Main {

    public static boolean jugarDeNuevo(){
        boolean rejugar = false;
        System.out.println("Si quiere volver a jugar, escriba \"si\"");
        Scanner scanner = new Scanner(System.in);
        String respuesta = scanner.nextLine().toLowerCase();
        if (respuesta.contains("si")) rejugar = true;
        return rejugar;
    }

    public static void main(String[] args) {
        boolean otraPartida = true;
        while (otraPartida) {
            new Programa();
            otraPartida = jugarDeNuevo();
        }
    }

}