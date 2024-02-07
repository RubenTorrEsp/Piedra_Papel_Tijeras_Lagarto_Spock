package org.example;

import java.util.List;
import java.util.Scanner;

public class ProgramaFinal {

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    Scanner scanner = new Scanner(System.in);
    String seleccionJugador;
    String seleccionOrdenador;

    // Lista de elementos aceptados
    List<String> elementos = List.of(new String[]{"piedra", "papel", "tijeras", "lagarto", "Spock", "salir", "1", "2", "3", "4", "5", "0"});

    // Constructor del programa y ciclo de vida
    public ProgramaFinal(){
        darBienvenida();
        obtenerEntrada();
        comprobarVictoria();
    }

    // Inicio del programa y presentación de opciones
    public void darBienvenida() {
        System.out.println(Textos.bienvenida);
        System.out.println(Textos.opciones);
    }
    
    // Obtener la elección del jugador
    public String obtenerEntrada() {
        seleccionJugador = scanner.nextLine().toLowerCase();
        if(elementos.contains(seleccionJugador)) {
            System.out.println("Has elegido "+seleccionJugador);
            convertirElementoEnNumero(seleccionJugador);
            System.out.println("La máquina ha elegido "+obtenerSeleccionMaquina());
        }
        else {
            System.out.println("Selección no válida");
        }
        return seleccionJugador;
    }

    // Selección de elemento por parte de la máquina
    public String obtenerSeleccionMaquina() {
        int valorJuego = (int) (Math.random() * 5);
        seleccionOrdenador = elementos.get(valorJuego);
        return seleccionOrdenador;
    }

    public int convertirElementoEnNumero(String entrada) {
        int nuevaEntrada = 0;

        switch (entrada) {
            case "piedra":
            case "1":
                nuevaEntrada = 1;
                break;
            case "papel":
            case "2":
                nuevaEntrada = 2;
                break;
            case "tijeras":
            case "3":
                nuevaEntrada = 3;
                break;
            case "lagarto":
            case "4":
                nuevaEntrada = 5;
                break;
            case "spock":
            case "5":
                nuevaEntrada = 4;
                break;
            default:
                break;
        }

        System.out.println("El nuevo valor de tu eleccion es " + Integer.toString(nuevaEntrada));

        return nuevaEntrada;
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
