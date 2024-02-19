

//import java.util.Scanner;

public class Main {

    public static void jugarDeNuevo(String entrada){
        /*
        boolean rejugar = false;
        if(!entrada.contains("salir")) {
            System.out.println("Si quiere volver a jugar, escriba \"si\"");
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            String respuesta = scanner.nextLine().toLowerCase();
            if (respuesta.contains("si")) rejugar = true;
            else System.out.println("Gracias por jugar a Piedra, Papel, Tijeras.");
        }
        return rejugar;
        */
        new Programa();
    }

    public static void main(String[] args) {
        jugarDeNuevo("");
    }

}