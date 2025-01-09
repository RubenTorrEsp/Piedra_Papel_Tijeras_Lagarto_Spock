package app;

import org.junit.jupiter.api.Test;
import tools.Functions;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static app.PlayerControler.*;
import static app.User.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerControlerTests extends Functions {

    static File originalFileTests = new File(USER_TEST_FILE);
    static File temporalFileTests = new File(USER_TEST_TEMP);


    @Test
    void createPlayer_JugadorNoExistente() throws IOException {
        int expectedPlayers = countPlayersInDB(originalFileTests)+1;

        createPlayer(Optional.of(PLAYER_CREATE), originalFileTests);
        int playersAfterCreation = countPlayersInDB(originalFileTests);

        assertEquals(expectedPlayers, playersAfterCreation);
    }

    @Test
    void createPlayer_playerAlreadyInDB() throws IOException {
        int jugadoresEsperados = countPlayersInDB(originalFileTests);

        PlayerControler.createPlayer(Optional.of(PLAYER_TEST), originalFileTests);
        int jugadoresTrasCreacion = countPlayersInDB(originalFileTests);

        assertEquals(jugadoresEsperados, jugadoresTrasCreacion);
    }

    @Test
    void modificarJugador_jugadorExistente() throws IOException {
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

    @Test
    void eliminarJugador_jugadorExistente() throws IOException {
        int jugadoresIniciales = countPlayersInDB(originalFileTests);

        PlayerControler.deletePlayer(Optional.of(PLAYER_DELETE), originalFileTests, temporalFileTests);

        assertEquals(jugadoresIniciales - 1, countPlayersInDB(originalFileTests));
    }

    @Test
    void eliminarJugador_jugadorNoExistente() throws IOException {
        int jugadoresIniciales = countPlayersInDB(originalFileTests);

        PlayerControler.deletePlayer(Optional.of(PLAYER_DELETE_FALSE), originalFileTests, temporalFileTests);
        int jugadoresTrasEliminacion = countPlayersInDB(originalFileTests);

        assertEquals(jugadoresIniciales, jugadoresTrasEliminacion);
    }

    @Test
    void reiniciarJugador_jugadorExistente() throws IOException {
        assertEquals(SCORE_NEW, checkScore(PLAYER_RESTART, originalFileTests));

        reStartPlayer(Optional.of(PLAYER_RESTART), originalFileTests, temporalFileTests);

        assertEquals(SCORE_INIT , checkScore(PLAYER_RESTART, originalFileTests));
    }

}
