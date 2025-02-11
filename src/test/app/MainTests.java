package app;

import org.junit.jupiter.api.Test;
import tools.Functions;

import static app._Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainTests extends Functions {

    @Test
    void player_Format_Works_As_Expected() {
        String expectedName = "User_test";
        String nameAfterFormat = formatPlayer(PLAYER_UNFORMATTED);

        assertEquals(expectedName,nameAfterFormat);
    }

}