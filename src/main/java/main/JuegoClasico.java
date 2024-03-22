package main;

import java.util.Scanner;

import tools.Listas;
import tools.Textos;

public class JuegoClasico {

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    static Scanner scanner = new Scanner(System.in);
    static String seleccionJugador;

    // Constructor del programa y ciclo de vida
    public JuegoClasico(User jugador){
        jugarDeNuevo();
    }

    // Inicio del juego, recogida de seleccion del jugador y, si es una opcion correcta, comprobación de la victoria
    public static void jugarDeNuevo(){
        System.out.println(Textos.cabeceraSeleccion);
        System.out.println(Textos.opcionesJuegoClasico);

        if(obtenerEntrada()){
            if (seleccionJugador == "salir") {
                System.out.println(Textos.seleccionSalir);
                System.out.println(Textos.rejugarNegativo);
                Common.reJugar = false;
            }
            else {
                Common.comprobarVictoria(seleccionJugador, Common.obtenerSeleccionMaquina(3));
                Common.reJugar();
            }
        }
        else{
            Common.reJugar();
        }
    }

    // Obtener la elección del jugador
    public static Boolean obtenerEntrada() {
        Boolean seleccionValida = false;
        seleccionJugador = scanner.nextLine().toLowerCase();
        if(Listas.elementosJuegoClasico.contains(seleccionJugador)) {
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

