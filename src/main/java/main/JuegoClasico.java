package main;

import java.util.Scanner;

import tools.Listas;
import tools.Textos;

public class JuegoClasico extends Common{

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    Scanner scanner = new Scanner(System.in);
    String seleccionJugador;

    // Constructor del programa y ciclo de vida
    public JuegoClasico(User jugador){
        jugarDeNuevo(jugador);
    }

    // Inicio del juego, recogida de seleccion del jugador y, si es una opcion correcta, comprobación de la victoria
    public void jugarDeNuevo(User jugador){
        System.out.println(Textos.cabeceraSeleccion);
        System.out.println(Textos.opcionesJuegoClasico);

        if(obtenerEntrada()){
            if (seleccionJugador == "salir") {
                System.out.println(Textos.seleccionSalir);
                System.out.println(Textos.rejugarNegativo);
                reJugar = false;
            }
            else {
                comprobarVictoria(seleccionJugador, obtenerSeleccionMaquina(3), jugador);
                reJugar();
            }
        }
        else{
            reJugar();
        }
    }

    // Obtener la elección del jugador
    public Boolean obtenerEntrada() {
        Boolean seleccionValida = false;
        seleccionJugador = scanner.nextLine().toLowerCase();
        if(Listas.elementosJuegoClasico.contains(seleccionJugador)) {
            seleccionValida = true;
            seleccionJugador = refactorizar(seleccionJugador);
        }
        else {
            System.out.println(Textos.seleccionInvalida);
            seleccionValida = false;
        }
        return seleccionValida;
    }

}