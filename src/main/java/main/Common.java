package main;

import java.util.Random;
import java.util.Scanner;

import tools.Listas;
import tools.Textos;

public class Common {

    // Creación del scanner
    static Scanner scanner = new Scanner(System.in);
    static Boolean reJugar = true;

    // Método que recoge la eleccion del jugador y, si es numerica, la convierte en texto
    public static String refactorizar(String seleccionJugador){
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
            case "4":
                seleccionJugador = "lagarto";
                break;
            case "5":
                seleccionJugador = "spock";
                break;
            case "0":
            case "salir":
                seleccionJugador = "salir";
                break;
            default:
                break;
        }  
        return seleccionJugador;
    }

    // Comportamiento de comprobación del cruce entre la eleccion del jugador y la de la máquina
    public static void comprobarVictoria(String jugador, String maquina) {
        System.out.println(Textos.seleccionJugador+jugador);
        System.out.println(Textos.seleccionMaquina+maquina);

        if(jugador == maquina) System.out.println(Textos.empate);
        else if (Listas.obtenerVictoria(jugador, maquina)) {
            Textos.victoria(jugador, maquina);
            User.leerArchivo("E:\\Proyectos\\Piedra_Papel_Tijeras_Lagarto_Spock\\src\\main\\java\\tools\\Users.txt");
        }
        else {
            Textos.derrota(jugador, maquina);
        }
    }

    // Selección de elemento por parte de la máquina
    public static String obtenerSeleccionMaquina(Integer elementos) {
        Random random = new Random();
        int valorJuego = random.nextInt(elementos)+1;
        String valorMaquina = Listas.elementosJuegoExtendido.get(valorJuego-1);
        return valorMaquina;
    }

    // Método por el que se pregunta al jugador si quiere jugar de nuevo
    public static void reJugar(){
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
