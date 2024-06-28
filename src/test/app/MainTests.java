package app;

import org.junit.jupiter.api.Test;

import static app._Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainTests {

    String nombreSinFormato = "user_Test";

    @Test
    void FormatoJugador_FuncionaCorrectamente() {
        String nombreEsperado = "User_test";
        String nombreTrasFormatear = formatoJugador(nombreSinFormato);

        assertEquals(nombreEsperado,nombreTrasFormatear);
    }

}
