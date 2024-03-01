package main;

import tools.Textos;

public class Main {
    
    public static void main(String[] args) {
        // Bienvenida
        System.out.println(Textos.bienvenida);

        while(Programa.reJugar) Programa.jugarDeNuevo();
    }

}