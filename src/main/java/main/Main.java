package main;

import tools.Textos;

public class Main {
    
    public static void main(String[] args) {
        inicio();
        while(ProgramaExtendido.reJugar) new ProgramaExtendido();
    }

    public static void inicio(){
        System.out.println(Textos.bienvenida);
    }

}