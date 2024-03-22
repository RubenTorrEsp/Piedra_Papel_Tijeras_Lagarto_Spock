package main;

import java.util.Scanner;

import tools.Listas;
import tools.Textos;

public class JuegoExtendido {

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    Scanner scanner = new Scanner(System.in);
    String seleccionJugador;

    // Constructor del programa y ciclo de vida
    public JuegoExtendido(User jugador){
        jugarDeNuevo(jugador);
    }

    // Inicio del juego, recogida de seleccion del jugador y, si es una opcion correcta, comprobación de la victoria
    public void jugarDeNuevo(User jugador){
        System.out.println(Textos.cabeceraSeleccion);
        System.out.println(Textos.opcionesJuegoExtendido);

        if(obtenerEntrada()){
            if (seleccionJugador == "salir") {
                System.out.println(Textos.seleccionSalir);
                System.out.println(Textos.rejugarNegativo);
                Common.reJugar = false;
            }
            else {
                Common.comprobarVictoria(seleccionJugador, Common.obtenerSeleccionMaquina(5), jugador);
                Common.reJugar();
            }
        }
        else{
            Common.reJugar();
        }
    }

    // Obtener la elección del jugador
    public Boolean obtenerEntrada() {
        Boolean seleccionValida = false;
        seleccionJugador = scanner.nextLine().toLowerCase();
        if(Listas.elementosJuegoExtendido.contains(seleccionJugador)) {
            seleccionValida = true;
            seleccionJugador = Common.refactorizar(seleccionJugador);
        }
        else {
            System.out.println(Textos.seleccionInvalida);
            seleccionValida = false;
        }
        return seleccionValida;
    }

}
