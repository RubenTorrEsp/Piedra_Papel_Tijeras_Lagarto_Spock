package app;

import org.junit.jupiter.api.Test;
import tools.Funciones;

import static app._Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainTests extends Funciones {

    @Test
    void FormatoJugador_FuncionaCorrectamente() {
        String nombreEsperado = "User_test";
        String nombreTrasFormatear = formatoJugador(jugadorSinFormato);

        assertEquals(nombreEsperado,nombreTrasFormatear);
    }

}
