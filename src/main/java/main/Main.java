package main;

import tools.Textos;

public class Main {
    
    public static void main(String[] args) {
        System.out.println(Textos.bienvenida);
        while(Programa.reJugar) new Programa();
    }

}