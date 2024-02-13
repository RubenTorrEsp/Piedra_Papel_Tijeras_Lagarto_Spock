package org.example;

import java.util.HashMap;
import java.util.Map;

public class Textos {

    // Textos de victoria en enfrentamientos
    private static final Map<String, String> strings = new HashMap<>();
    static {
        strings.put("piedra_tijeras", "la piedra ROMPE las tijeras.");
        strings.put("piedra_lagarto", "El papel le gana a la piedra");
        strings.put("tijeras_papel", "La piedra le gana a las tijeras");
        strings.put("tijeras_lagarto", "La piedra le gana a las tijeras");
        strings.put("papel_piedra", "La piedra le gana a las tijeras");
        strings.put("papel_spock", "La piedra le gana a las tijeras");
        strings.put("lagarto_papel", "La piedra le gana a las tijeras");
        strings.put("lagarto_spock", "La piedra le gana a las tijeras");
        strings.put("spock_piedra", "La piedra le gana a las tijeras");
        strings.put("spock_tijeras", "La piedra le gana a las tijeras");
    }
    public static String obtenerTexto(String nombre) {
        return strings.get(nombre);
    }
    

    static String bienvenida = "Bienvenido al juego Piedra, Papel, Tijeras, Lagarto, Spock!\nSeleccione su opción con nombre o número, o elija salir:";
    static String opciones = "1 - Piedra\n2 - Papel\n3 - Tijeras\n4 - Lagarto\n5 - Spock\n\n0 - Salir\n-----------------------------------------------";

    static String victoria = "Enhorabuena!! Has ganado, porque ";
    static String derrota = "Lo siento!! Has perdido, porque ";
    static String empate = "Empate!! Aprovecha el momento o vuelve a por más.";
}
