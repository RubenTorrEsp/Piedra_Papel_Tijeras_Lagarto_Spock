package org.example;

import java.util.List;
import java.util.Scanner;

public class Programa {

    String entrada = "";
    String seleccionJuego = "";
    String victoriaPapel = "el papel ENVUELVE a la piedra";
    String victoriaPiedra = "la piedra ROMPE a las tijeras";
    String victoriaTijeras = "las tijeras CORTAN al papel";

    List<String> elementos = List.of(new String[]{"piedra", "papel", "tijeras"});

    Scanner scanner = new Scanner(System.in);

    public Programa(){
        darBienvenida();
        obtenerEntrada();
        obtenerSeleccionMaquina();
        comprobarVictoria();
    }

    public void darBienvenida() {
        System.out.println("Bienvenido al juego Piedra, Papel, Tijeras!\nSeleccione su opción con nombre o número:");
        System.out.println("1 - Piedra\n2 - Papel\n3 - Tijeras");
        System.out.println("-----------------------------------------------");
    }

    public String obtenerEntrada() {
        entrada = scanner.nextLine().toLowerCase();
        System.out.println("Ha seleccionado: \""+entrada+"\"");
        return entrada;
    }

    public String obtenerSeleccionMaquina() {
        int valorJuego = (int) (Math.random() * 3);
        seleccionJuego = elementos.get(valorJuego);
        return seleccionJuego;
    }

    public void comprobarVictoria() {
        if(!elementos.contains(entrada)) System.out.println("El elemento seleccionado no es correcto. Ahora moriré.");
        else {
            System.out.println("El ordenador ha seleccionado \"" + seleccionJuego + "\"");
            if (seleccionJuego.contains(entrada)) System.out.println("Empate!!");
            else {
                switch (entrada) {
                    case "piedra":
                        if (seleccionJuego == "tijeras") System.out.println("¡¡¡Has ganado!!! Porque " + victoriaPiedra);
                        else System.out.println("Lo siento, has perdido, porque " + victoriaPapel);
                        break;
                    case "papel":
                        if (seleccionJuego == "piedra") System.out.println("¡¡¡Has ganado!!! Porque " + victoriaPapel);
                        else System.out.println("Lo siento, has perdido, porque " + victoriaTijeras);
                        break;
                    case "tijeras":
                        if (seleccionJuego == "papel") System.out.println("¡¡¡Has ganado!!! Porque " + victoriaTijeras);
                        else System.out.println("Lo siento, has perdido, porque " + victoriaPiedra);
                        break;
                }
            }
        }
    }

}
