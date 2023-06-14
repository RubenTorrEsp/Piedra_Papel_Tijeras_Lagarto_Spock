package org.example;

import java.util.List;
import java.util.Scanner;

public class Programa {

    String entrada = "";
    String seleccionJuego = "";

    //Victorias papel
    String victoriaPapelvsPiedra = "el papel ENVUELVE a la piedra";
    String victoriaPapelvsSpock = "el papel DESAUTORIZA a Spock";

    //Victorias piedra
    String victoriaPiedra = "la piedra ROMPE a las tijeras";

    //Victorias tijeras
    String victoriaTijeras = "las tijeras CORTAN al papel";

    //Victorias lagarto

    //Victorias spock

    List<String> elementos = List.of(new String[]{"piedra", "papel", "tijeras", "salir", "1", "2", "3", "0"});

    Scanner scanner = new Scanner(System.in);

    public Programa(){
        darBienvenida();
        obtenerEntrada();
        obtenerSeleccionMaquina();
        comprobarVictoria();
    }

    public void darBienvenida() {
        System.out.println("Bienvenido al juego Piedra, Papel, Tijeras!\nSeleccione su opción con nombre o número, o elija salir:");
        System.out.println("1 - Piedra\n2 - Papel\n3 - Tijeras\n\n0 - Salir");
        System.out.println("-----------------------------------------------");
    }

    public String obtenerEntrada() {
        entrada = convertirNumeroEnElemento(scanner.nextLine().toLowerCase());
        System.out.println("Ha seleccionado: \""+entrada+"\"");
        return entrada;
    }

    public String obtenerSeleccionMaquina() {
        int valorJuego = (int) (Math.random() * 3);
        seleccionJuego = elementos.get(valorJuego);
        return seleccionJuego;
    }

    public String convertirNumeroEnElemento(String entrada) {
        String nuevaEntrada = entrada;
        if (entrada.equals("1")) nuevaEntrada = "piedra";
        if (entrada.equals("2")) nuevaEntrada = "papel";
        if (entrada.equals("3")) nuevaEntrada = "tijeras";
        if (entrada.equals("0")) nuevaEntrada = "salir";
        return nuevaEntrada;
    }

    public void comprobarVictoria() {
        if(entrada.equals("salir")) System.out.println("Gracias por jugar a Piedra, Papel, Tijeras.");
        else if(!elementos.contains(entrada)) System.out.println("El elemento seleccionado no es correcto. Ahora moriré.");
        else {
            System.out.println("El ordenador ha seleccionado \"" + seleccionJuego + "\"");
            if (seleccionJuego.contains(entrada)) System.out.println("Empate!!");
            else {
                switch (entrada) {
                    case "piedra","1":
                        if (seleccionJuego == "tijeras") System.out.println("¡¡¡Has ganado!!! Porque " + victoriaPiedra);
                        else System.out.println("Lo siento, has perdido, porque " + victoriaPapelvsPiedra);
                        break;
                    case "papel","2":
                        if (seleccionJuego == "piedra") System.out.println("¡¡¡Has ganado!!! Porque " + victoriaPapelvsPiedra);
                        else System.out.println("Lo siento, has perdido, porque " + victoriaTijeras);
                        break;
                    case "tijeras","3":
                        if (seleccionJuego == "papel") System.out.println("¡¡¡Has ganado!!! Porque " + victoriaTijeras);
                        else System.out.println("Lo siento, has perdido, porque " + victoriaPiedra);
                        break;
                }
            }
        }
    }

}
