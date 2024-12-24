package resources;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

import static java.util.List.*;

public class Lists {

    // Lista de elementos aceptados en el juego extendido
    public static final List<String> ELEMENTS_EXTENDED = of(
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
        "0");

    // Lista de elementos aceptados en el juego extendido
    public static final List<String> ELEMENTS_CLASSIC = of(
        "piedra",
        "papel",
        "tijeras",
        "salir",
        "1",
        "2",
        "3",
        "0");

    // Listas de elementos a los que vence cada uno
    private static final Map<String, List<String>> LISTS_VICTORY = new HashMap<>();
    static {
        LISTS_VICTORY.put("piedra", Arrays.asList("tijeras", "lagarto"));
        LISTS_VICTORY.put("papel", Arrays.asList("piedra", "spock"));
        LISTS_VICTORY.put("tijeras", Arrays.asList("papel", "lagarto"));
        LISTS_VICTORY.put("lagarto", Arrays.asList("spock", "papel"));
        LISTS_VICTORY.put("spock", Arrays.asList("tijeras", "piedra"));
    }

    // Método que obtiene el elemento del jugador y el de la máquina y comprueba si el jugador gana o no.
    public static Boolean getVictory(String elemPlayer, String elemMaquina) {
        return LISTS_VICTORY.get(elemPlayer).contains(elemMaquina);
    }

}
