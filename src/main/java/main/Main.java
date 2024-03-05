package main;

import tools.Textos;

public class Main {
    
    public static void main(String[] args) {
        inicio();
        while(ProgramaExtendido.reJugar) new ProgramaExtendido();
    }

    public static void inicio(){
        System.out.println(Textos.bienvenida);
        //seleccion();
    }

    public static void seleccion(){
        System.out.println("Elija a qué quiere jugar");
        System.out.println("1- Juego extendido");
        System.out.println("2- Juego clásico (Próximamente)");
    }

}