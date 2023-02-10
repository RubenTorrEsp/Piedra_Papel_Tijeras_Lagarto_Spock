package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String victoriaPapel = "el papel ENVUELVE a la piedra";
        String victoriaPiedra = "la piedra ROMPE a las tijeras";
        String victoriaTijeras = "las tijeras CORTAN al papel";

        System.out.println("Bienvenido al juego Piedra, Papel, Tijeras!");
        System.out.println("Seleccione su opción con nombre o número:");
        System.out.println("1 - Piedra");
        System.out.println("2 - Papel");
        System.out.println("3 - Tijeras");
        System.out.println("-----------------------------------------------");

        List<String> elementos = List.of(new String[]{"piedra", "papel", "tijeras"});

        Scanner scanner = new Scanner(System.in);
        String entrada = scanner.nextLine();
        System.out.println("Ha seleccionado: \""+entrada+"\"");

        if(!elementos.contains(entrada.toLowerCase())) {
            System.out.println("No ha seleccionado un valor válido. Ahora moriré.");
        }
        else{
            int valorJuego = (int) (Math.random()*3);
            String seleccionJuego = elementos.get(valorJuego);

            System.out.println("El ordenador ha seleccionado \""+seleccionJuego+"\"");

            switch (entrada) {
                case "piedra":
                    if (seleccionJuego == "tijeras") {
                        System.out.println("¡¡¡Has ganado!!! Porque "+victoriaPiedra);
                    }
                    else if (seleccionJuego == "piedra") {
                        System.out.println("Empate!");
                    }
                    else {
                        System.out.println("Lo siento, has perdido, porque "+victoriaPapel);
                    }
                    break;
                case "papel":
                    if (seleccionJuego == "piedra") {
                        System.out.println("¡¡¡Has ganado!!! Porque "+victoriaPapel);
                    }
                    else if (seleccionJuego == "papel") {
                        System.out.println("Empate!");
                    }
                    else {
                        System.out.println("Lo siento, has perdido, porque "+victoriaTijeras);
                    }
                    break;
                case "tijeras":
                    if (seleccionJuego == "papel") {
                        System.out.println("¡¡¡Has ganado!!! Porque "+victoriaTijeras);
                    }
                    else if (seleccionJuego == "tijeras") {
                        System.out.println("Empate!");
                    }
                    else {
                        System.out.println("Lo siento, has perdido, porque "+victoriaPiedra);
                    }
                    break;
            }

        }



    }

}