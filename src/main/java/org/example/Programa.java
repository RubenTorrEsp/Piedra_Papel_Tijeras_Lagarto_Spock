package org.example;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Programa {

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    Scanner scanner = new Scanner(System.in);
    String seleccionJugador;
    String seleccionOrdenador;

    // Lista de elementos aceptados
    List<String> elementos = List.of(new String[]{"piedra", "papel", "tijeras", "spock", "lagarto", "salir", "1", "2", "3", "4", "5", "0"});

    // Constructor del programa y ciclo de vida
    public Programa(){
        darBienvenida();
        obtenerEntrada();
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
            switch (seleccionJugador) {
                case "1":
                    seleccionJugador = "piedra";
                    break;
                case "2":
                    seleccionJugador = "papel";
                    break;
                case "3":
                    seleccionJugador = "tijeras";
                    break;
                case "5":
                    seleccionJugador = "spock";
                    break;
                case "4":
                    seleccionJugador = "lagarto";
                    break;
                default:
                    break;
            }
            String valorMaquina = obtenerSeleccionMaquina();
            comprobarVictoria(seleccionJugador, valorMaquina);
        }
        else {
            System.out.println("Selección no válida");
        }
        return seleccionJugador;
    }

    // Selección de elemento por parte de la máquina
    public String obtenerSeleccionMaquina() {
        Random random = new Random();
        int valorJuego = random.nextInt(5) + 1;
        String valorMaquina = elementos.get(valorJuego-1);
        return valorMaquina;
    }

    // 
    public void comprobarVictoria(String jugador, String maquina) {
        System.out.println("Has elegido "+jugador);
        System.out.println("La máquina ha elegido "+maquina);

        if(jugador == maquina) System.out.println("Empate");
        else if (comprobarLista(jugador, maquina, ListasDeVictoria.piedra, ListasDeVictoria.papel, ListasDeVictoria.tijeras, ListasDeVictoria.lagarto, ListasDeVictoria.spock)){
            System.out.println("Victoria");
        }
        else {
            System.out.println("Derrota");
        }

    }

    // Método que toma un String como parámetro y accede a una de las listas en función de ese String
    public boolean comprobarLista(String jugador, String maquina, List<String> piedra, List<String> papel, List<String> tijeras, List<String> lagarto, List<String> spock) {
        boolean victoria = false;
        switch (jugador) {
            case "piedra":
                if(piedra.contains(maquina)) victoria = true;
                break;
            case "papel":
                if(papel.contains(maquina)) victoria = true;
                break;
            case "tijeras":
                if(tijeras.contains(maquina)) victoria = true;
                break;
            case "lagarto":
                if(lagarto.contains(maquina)) victoria = true;
                break;
            case "spock":
                if(spock.contains(maquina)) victoria = true;
                break;
            default:
                break;
        }
        return victoria;
    }

    public void crearTextoVictoria(String jugador, String maquina){

    }

}
