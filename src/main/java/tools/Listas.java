package tools;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

public class Listas {

    // Lista de elementos aceptados en el juego extendido
    public static List<String> elementosJuegoExtendido = List.of(new String[]{
        "piedra",
        "papel",
        "tijeras",
        "spock",
        "lagarto",
        "salir",
        "1",
        "2",
        "3",
        "4",
        "5",
        "0"
    });

    // Lista de elementos aceptados en el juego extendido
    public static List<String> elementosJuegoClasico = List.of(new String[]{
        "piedra",
        "papel",
        "tijeras",
        "salir",
        "1",
        "2",
        "3",
        "0"
    });

    // Listas de elementos a los que vence cada uno
    private static final Map<String, List<String>> listasVictoria = new HashMap<>();
    static {
        listasVictoria.put("piedra", Arrays.asList("tijeras", "lagarto"));
        listasVictoria.put("papel", Arrays.asList("piedra", "spock"));
        listasVictoria.put("tijeras", Arrays.asList("papel", "lagarto"));
        listasVictoria.put("lagarto", Arrays.asList("spock", "papel"));
        listasVictoria.put("spock", Arrays.asList("tijeras", "piedra"));
    }

    // Método que obtiene el elemento del jugador y el de la máquina y comprueba si el jugador gana o no.
    public static Boolean obtenerVictoria(String elemJugador, String elemMaquina) {
        Boolean victoria = false;
        if(listasVictoria.get(elemJugador).contains(elemMaquina)) victoria = true;
        return victoria;
    }

}
