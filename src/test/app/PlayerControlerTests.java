package app;

import org.junit.jupiter.api.Test;
import tools.Functions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static app.PlayerControler.*;
import static app.User.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerControlerTests extends Functions {

    static File originalFileTests = new File(USER_TEST_FILE);
    static File temporalFileTests = new File(USER_TEST_TEMP);

// TODO: Revision de tests. El comportamiento se queda esperando respuesta
    @Test
    void createPlayer_nonExistentPlayer() throws IOException {
        int expectedPlayers = countPlayersInDB(originalFileTests)+1;

        createPlayer(Optional.of(PLAYER_CREATE), originalFileTests);
        int playersAfterCreation = countPlayersInDB(originalFileTests);

        assertEquals(expectedPlayers, playersAfterCreation);
    }

// TODO: Revision de tests. El comportamiento se queda esperando respuesta
    @Test
    void createPlayer_playerAlreadyInDB() throws IOException {
        int expectedPlayers = countPlayersInDB(originalFileTests);

        PlayerControler.createPlayer(Optional.of(PLAYER_TEST), originalFileTests);
        int playersAfterCreation = countPlayersInDB(originalFileTests);

        assertEquals(expectedPlayers, playersAfterCreation);
    }

// TODO: Revision de tests. El comportamiento se queda esperando respuesta
    @Test
    void modifyPlayer_existentPlayer() throws IOException {
        assertTrue(userExists(PLAYER_MODIFY, originalFileTests));
        assertFalse(userExists(PLAYER_NEW_NAME, originalFileTests));

        modifyPlayer(
                Optional.of(PLAYER_MODIFY),
                Optional.of(PLAYER_NEW_NAME),
                originalFileTests,
                temporalFileTests);

        assertFalse(userExists(PLAYER_MODIFY, originalFileTests));
        assertTrue(userExists(PLAYER_NEW_NAME, originalFileTests));
    }

// TODO: Revision de tests. El comportamiento se queda esperando respuesta
    @Test
    void deletePlayer_playerExistent() throws IOException {
        int startingPlayers = countPlayersInDB(originalFileTests);

        PlayerControler.deletePlayer(Optional.of(PLAYER_DELETE), originalFileTests, temporalFileTests);

        assertEquals(startingPlayers - 1, countPlayersInDB(originalFileTests));
    }

// TODO: Revision de tests. El comportamiento se queda esperando respuesta
    @Test
    void deletePlayer_playerNonExistent() throws IOException {
        int startingPlayers = countPlayersInDB(originalFileTests);

        PlayerControler.deletePlayer(Optional.of(PLAYER_DELETE_FALSE), originalFileTests, temporalFileTests);
        int playersAfterDeleting = countPlayersInDB(originalFileTests);

        assertEquals(startingPlayers, playersAfterDeleting);
    }

// TODO: Revision de tests. El comportamiento se queda esperando respuesta
    @Test
    void reiniciarJugador_jugadorExistente() throws IOException {
        assertEquals(SCORE_NEW, checkScore(PLAYER_RESTART, originalFileTests));

        reStartPlayer(Optional.of(PLAYER_RESTART), originalFileTests, temporalFileTests);

        String simulatedInput = "no\n";



        assertEquals(SCORE_INIT , checkScore(PLAYER_RESTART, originalFileTests));
    }

}