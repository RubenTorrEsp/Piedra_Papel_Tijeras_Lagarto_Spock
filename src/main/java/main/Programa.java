package main;

import java.util.Random;
import java.util.Scanner;

import tools.Listas;
import tools.Textos;

public class Programa {

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    Scanner scanner = new Scanner(System.in);
    String seleccionJugador;

    // Constructor del programa y ciclo de vida
    public Programa(){
        if(obtenerEntrada()){
            if (seleccionJugador == "salir")  System.out.println(Textos.seleccionSalir);
            else comprobarVictoria(seleccionJugador, obtenerSeleccionMaquina());
        }
    }
    
    // Obtener la elección del jugador
    public Boolean obtenerEntrada() {
        Boolean seleccionValida = false;
        seleccionJugador = scanner.nextLine().toLowerCase();
        if(Listas.elementos.contains(seleccionJugador)) {
            seleccionValida = true;
            seleccionJugador = Common.refactorizar(seleccionJugador);
        }
        else {
            System.out.println(Textos.seleccionInvalida);
        }
        return seleccionValida;
    }

    // Selección de elemento por parte de la máquina
    public String obtenerSeleccionMaquina() {
        Random random = new Random();
        int valorJuego = random.nextInt(5)+1;
        String valorMaquina = Listas.elementos.get(valorJuego-1);
        return valorMaquina;
    }

    // Comportamiento de comprobación del cruce entre la eleccion del jugador y la de la máquina
    public void comprobarVictoria(String jugador, String maquina) {
        System.out.println(Textos.seleccionJugador+jugador);
        System.out.println(Textos.seleccionMaquina+maquina);

        if(jugador == maquina) System.out.println(Textos.empate);
        else if (Listas.obtenerVictoria(jugador, maquina)) Textos.victoria(jugador, maquina);
        else Textos.derrota(jugador, maquina);
    }

}
