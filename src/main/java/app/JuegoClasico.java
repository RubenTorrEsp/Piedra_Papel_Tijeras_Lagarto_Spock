package app;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static resources.Listas.*;
import static resources.Textos.*;

public class JuegoClasico extends Common{

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    Scanner scanner = new Scanner(System.in);
    String seleccionJugador;

    // Constructor del programa y ciclo de vida
    public JuegoClasico(User jugador) throws IOException{
        jugarDeNuevo(jugador);
    }

    // Inicio del juego, recogida de seleccion del jugador y, si es una opcion correcta, comprobación de la victoria
    public void jugarDeNuevo(User jugador) throws IOException{
        System.out.println(opcionesJuegoClasico);
        if(obtenerEntrada(jugador)){
            if (Objects.equals(seleccionJugador, seleccionSalir)) reJugar = false;
            else {
                comprobarVictoria(seleccionJugador, obtenerSeleccionMaquina(3), jugador);
                reJugar();
            }
        }
        else reJugar();
    }

    // Obtener la elección del jugador
    public boolean obtenerEntrada(User jugador) {
        boolean seleccionValida = false;
        seleccionJugador = scanner.nextLine().toLowerCase();
        if(elementosJuegoClasico.contains(seleccionJugador)) {
            seleccionValida = true;
            seleccionJugador = refactorizar(seleccionJugador, jugador);
        }
        else {
            System.out.println(seleccionInvalida);
        }
        return seleccionValida;
    }

}