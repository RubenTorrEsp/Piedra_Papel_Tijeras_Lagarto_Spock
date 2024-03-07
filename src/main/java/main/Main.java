package main;

import tools.Textos;
import java.util.Scanner;

public class Main {
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicio();
        //while(ProgramaExtendido.reJugar) new ProgramaExtendido();
    }

    public static void inicio(){
        System.out.println(Textos.bienvenida);
        System.out.println(Textos.seleccionJuego);
        String eleccion = scanner.nextLine();
        switch (eleccion) {
            case "1":
                while(JuegoExtendido.reJugar) new JuegoExtendido();
                break;
            case "2":
                while(JuegoClasico.reJugar) new JuegoClasico();
                break;
            default:
                System.out.println(Textos.seleccionInvalida);
                break;
        }
    }

}