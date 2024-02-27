package main;

public class Common {

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

}
