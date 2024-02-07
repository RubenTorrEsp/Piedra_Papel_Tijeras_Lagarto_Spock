package org.example;

import java.util.List;
import java.util.Scanner;

public class ProgramaFinal {

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    Scanner scanner = new Scanner(System.in);
    String seleccionJugador;
    String seleccionOrdenador;

    // Lista de elementos aceptados
    List<String> elementos = List.of(new String[]{"piedra", "papel", "tijeras", "spock", "lagarto", "salir", "1", "2", "3", "4", "5", "0"});

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
            obtenerSeleccionMaquina();
            convertirElementoEnNumero(seleccionJugador);
        }
        else {
            System.out.println("Selección no válida");
        }
        return seleccionJugador;
    }

    // Selección de elemento por parte de la máquina
    public int obtenerSeleccionMaquina() {
        int valorJuego = (int) (Math.random() * 5);
        System.out.println("La máquina ha elegido "+elementos.get(valorJuego));
        System.out.println("El valor de la eleccion de la máquina es "+Integer.toString(valorJuego));
        return valorJuego;
    }

    // Comportamiento para convertir la eleccion del jugador en un valor numérico para realizar el juego
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
            case "spock":
            case "5":
                nuevaEntrada = 4;
                break;
            case "lagarto":
            case "4":
                nuevaEntrada = 5;
                break;
            default:
                break;
        }

        System.out.println("El nuevo valor de tu eleccion es " + Integer.toString(nuevaEntrada));

        return nuevaEntrada;
    }

    public void comprobarVictoria() {

    }

}
