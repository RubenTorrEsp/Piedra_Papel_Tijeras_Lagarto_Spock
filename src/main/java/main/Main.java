package main;

import java.util.Scanner;

import tools.Listas;
import tools.Textos;

public class Main {

    static String seleccionJugador;
    static Scanner scanner = new Scanner(System.in);
    static Boolean reJugar = true;

    public static void main(String[] args) {
        // Bienvenida
        System.out.println(Textos.bienvenida);

        jugarDeNuevo();
    }

    public static void jugarDeNuevo(){
        //Seleccion del jugador
        System.out.println(Textos.cabeceraSeleccion);
        System.out.println(Textos.opciones);

        if(obtenerEntrada()){
            if (seleccionJugador == "salir") {
                System.out.println(Textos.seleccionSalir);
                System.out.println(Textos.rejugarNegativo);
            }
            else {
                Programa.comprobarVictoria(seleccionJugador, Programa.obtenerSeleccionMaquina());
                //reJugar();
                System.out.println(reJugar);
            }
        }
    }

        /*
        while(reJugar) {
            new Programa();
            reJugar();
        }
        */    

    // Obtener la elección del jugador
    public static Boolean obtenerEntrada() {
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

    private static void reJugar(){
        System.out.println(Textos.seleccionRejugar);
        switch (scanner.nextLine().toLowerCase()){
            case "si":
                System.out.println(Textos.rejugarAfirmativo);
                break;
            default:
                reJugar = false;
                System.out.println(Textos.rejugarNegativo);
                break;
        }
    }

}