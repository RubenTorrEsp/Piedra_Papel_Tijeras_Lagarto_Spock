package app;

import org.junit.jupiter.api.Test;
import tools.Functions;

import static app._Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainTests extends Functions {

    @Test
    void FormatoJugador_FuncionaCorrectamente() {
        String nombreEsperado = "User_test";
        String nombreTrasFormatear = formatoJugador(PLAYER_UNFORMATTED);

        assertEquals(nombreEsperado,nombreTrasFormatear);
    }

}
