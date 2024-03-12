package main;

import tools.Textos;
import java.util.Scanner;

public class Main {
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //inicio();
        User.leerArchivo("E:\\Proyectos\\Piedra_Papel_Tijeras_Lagarto_Spock\\src\\main\\java\\tools\\Users.txt",
        "E:\\Proyectos\\Piedra_Papel_Tijeras_Lagarto_Spock\\src\\main\\java\\tools\\newUsers.txt");
    }

    public static void inicio(){
        System.out.println(Textos.bienvenida);
        System.out.println(Textos.seleccionJuego);
        String eleccion = scanner.nextLine();
        switch (eleccion) {
            case "1":
                while(Common.reJugar) new JuegoExtendido();
                break;
            case "2":
                while(Common.reJugar) new JuegoClasico();
                break;
            default:
                System.out.println(Textos.seleccionInvalida);
                break;
        }
    }

}