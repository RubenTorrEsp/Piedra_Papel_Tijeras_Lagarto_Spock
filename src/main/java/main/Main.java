package main;

import tools.Textos;

public class Main {


    public static void main(String[] args) {
        // Bienvenida
        System.out.println(Textos.bienvenida);

        while(Programa.reJugar) Programa.jugarDeNuevo();
    }

    /*
    public static void jugarDeNuevo(){
        System.out.println(Textos.cabeceraSeleccion);
        System.out.println(Textos.opciones);wisel

        if(obtenerEntrada()){
            if (seleccionJugador == "salir") {
                System.out.println(Textos.seleccionSalir);
                System.out.println(Textos.rejugarNegativo);
                reJugar = false;
            }
            else {
                Programa.comprobarVictoria(seleccionJugador, Programa.obtenerSeleccionMaquina());
                reJugar();
            }
        }
    }

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
            seleccionValida = false;
        }
        System.out.println(seleccionValida);
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
    */

}