package main;

import java.util.Random;
import java.util.Scanner;

import tools.Listas;
import tools.Textos;

public class Programa {

    // Creacion del elemento scanner y strings en los que se guarda el texto captado
    static Scanner scanner = new Scanner(System.in);
    static String seleccionJugador;
    static Boolean reJugar = true;

    // Constructor del programa y ciclo de vida
    public Programa(){

    }

    public static void jugarDeNuevo(){
        System.out.println(Textos.cabeceraSeleccion);
        System.out.println(Textos.opciones);

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
        else{
            reJugar();
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
    
    // Selección de elemento por parte de la máquina
    public static String obtenerSeleccionMaquina() {
        Random random = new Random();
        int valorJuego = random.nextInt(5)+1;
        String valorMaquina = Listas.elementos.get(valorJuego-1);
        return valorMaquina;
    }

    // Comportamiento de comprobación del cruce entre la eleccion del jugador y la de la máquina
    public static void comprobarVictoria(String jugador, String maquina) {
        System.out.println(Textos.seleccionJugador+jugador);
        System.out.println(Textos.seleccionMaquina+maquina);

        if(jugador == maquina) System.out.println(Textos.empate);
        else if (Listas.obtenerVictoria(jugador, maquina)) Textos.victoria(jugador, maquina);
        else Textos.derrota(jugador, maquina);
    }

}
