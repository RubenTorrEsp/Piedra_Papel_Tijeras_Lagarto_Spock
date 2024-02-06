package org.example;

import java.util.List;
import java.util.Scanner;

public class ProgramaFinal {

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    Scanner scanner = new Scanner(System.in);
    String seleccionJugador = "";
    String seleccionOrdenador = "";

    // Lista de elementos aceptados
    List<String> elementos = List.of(new String[]{"piedra", "papel", "tijeras", "lagarto", "Spock", "salir", "1", "2", "3", "4", "5", "0"});

    // Constructor del programa y ciclo de vida
    public ProgramaFinal(){
        darBienvenida();
        obtenerEntrada();
        obtenerSeleccionMaquina();
        comprobarVictoria();
    }

    public void darBienvenida() {
        System.out.println("Bienvenido al juego Piedra, Papel, Tijeras!\nSeleccione su opción con nombre o número, o elija salir:");
        System.out.println("1 - Piedra\n2 - Papel\n3 - Tijeras\n4 - Lagarto\n5 - Spock\n\n0 - Salir");
        System.out.println("-----------------------------------------------");
    }

    
    public void obtenerEntrada() {
        /*
        entrada = convertirNumeroEnElemento(scanner.nextLine().toLowerCase());
        System.out.println("Ha seleccionado: \""+entrada+"\"");
        return entrada;
        */
    }

    public void obtenerSeleccionMaquina() {
        /*
        int valorJuego = (int) (Math.random() * 3);
        seleccionJuego = elementos.get(valorJuego);
        return seleccionJuego;
        */
    }

    public void convertirNumeroEnElemento(String entrada) {
        /*
        String nuevaEntrada = entrada;
        if (entrada.equals("1")) nuevaEntrada = "piedra";
        if (entrada.equals("2")) nuevaEntrada = "papel";
        if (entrada.equals("3")) nuevaEntrada = "tijeras";
        if (entrada.equals("4")) nuevaEntrada = "lagarto";
        if (entrada.equals("5")) nuevaEntrada = "Spock";
        if (entrada.equals("0")) nuevaEntrada = "salir";
        return nuevaEntrada;
        */
    }

    public void comprobarVictoria() {
        /*
        if(entrada.equals("salir")) System.out.println("Gracias por jugar a Piedra, Papel, Tijeras.");
        else if(!elementos.contains(entrada)) System.out.println("El elemento seleccionado no es correcto. Ahora moriré.");
        else {
            System.out.println("El ordenador ha seleccionado \"" + seleccionJuego + "\"");
            if (seleccionJuego.contains(entrada)) System.out.println("Empate!!");
            else {
                switch (entrada) {
                    case "piedra","1":
                        if (seleccionJuego == "tijeras") System.out.println(Textos.victoria + Textos.PiedraTijeras);
                        else System.out.println(Textos.derrota + Textos.PapelPiedra);
                        break;
                    case "papel","2":
                        if (seleccionJuego == "piedra") System.out.println("¡¡¡Has ganado!!! Porque " + victoriaPapelvsPiedra);
                        else System.out.println("Lo siento, has perdido, porque " + victoriaTijeras);
                        break;
                    case "tijeras","3":
                        if (seleccionJuego == "papel") System.out.println("¡¡¡Has ganado!!! Porque " + victoriaTijeras);
                        else System.out.println("Lo siento, has perdido, porque " + victoriaPiedra);
                        break;
                    case "lagarto","4":
                        System.out.println("Todavía no disponible. Vuelva a intentarlo pronto");
                        break;
                    case "Spock","5":
                        System.out.println("Todavía no disponible. Vuelva a intentarlo pronto");
                        break;
                }
            }
        }
        */
    }

}
