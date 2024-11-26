package app;

import org.junit.jupiter.api.Test;
import tools.Functions;

import static app._Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainTests extends Functions {

    @Test
    void Player_Format_Works_As_Expected() {
        String nombreEsperado = "User_test";
        String nombreTrasFormatear = formatPlayer(PLAYER_UNFORMATTED);

        assertEquals(nombreEsperado,nombreTrasFormatear);
    }

}
