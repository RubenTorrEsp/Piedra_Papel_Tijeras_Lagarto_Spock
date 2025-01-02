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
    static File archivoTemporalTests = new File(USER_TEST_TEMP);


    @Test
    void createPlayer_JugadorNoExistente() throws IOException {
        int expectedPlayers = countPlayersInDB(originalFileTests)+1;

        createPlayer(Optional.of(PLAYER_CREATE), originalFileTests);
        int playersAfterCreation = countPlayersInDB(originalFileTests);

        assertEquals(expectedPlayers, playersAfterCreation);
    }

    @Test
    void crearJugador_JugadorYaEnBDD() throws IOException {
        int jugadoresEsperados = countPlayersInDB(originalFileTests);

        PlayerControler.createPlayer(Optional.of(PLAYER_TEST), originalFileTests);
        int jugadoresTrasCreacion = countPlayersInDB(originalFileTests);

        assertEquals(jugadoresEsperados, jugadoresTrasCreacion);
    }

    @Test
    void modificarJugador_jugadorExistente() throws IOException {
        assertTrue(userExists(PLAYER_MODIFY, archivoOriginalTests));
        assertFalse(userExists(PLAYER_NEW_NAME, archivoOriginalTests));

        modifyPlayer(
                Optional.of(PLAYER_MODIFY),
                Optional.of(PLAYER_NEW_NAME),
                archivoOriginalTests,
                archivoTemporalTests);

        assertFalse(userExists(PLAYER_MODIFY, archivoOriginalTests));
        assertTrue(userExists(PLAYER_NEW_NAME, archivoOriginalTests));
    }

    @Test
    void eliminarJugador_jugadorExistente() throws IOException {
        int jugadoresIniciales = countPlayersInDB(archivoOriginalTests);

        PlayerControler.deletePlayer(Optional.of(PLAYER_DELETE), archivoOriginalTests, archivoTemporalTests);

        assertEquals(jugadoresIniciales - 1, countPlayersInDB(archivoOriginalTests));
    }

    @Test
    void eliminarJugador_jugadorNoExistente() throws IOException {
        int jugadoresIniciales = countPlayersInDB(archivoOriginalTests);

        PlayerControler.deletePlayer(Optional.of(PLAYER_DELETE_FALSE), archivoOriginalTests, archivoTemporalTests);
        int jugadoresTrasEliminacion = countPlayersInDB(archivoOriginalTests);

        assertEquals(jugadoresIniciales, jugadoresTrasEliminacion);
    }

    @Test
    void reiniciarJugador_jugadorExistente() throws IOException {
        assertEquals(SCORE_NEW, checkScore(PLAYER_RESTART, archivoOriginalTests));

        reStartPlayer(Optional.of(PLAYER_RESTART), archivoOriginalTests, archivoTemporalTests);

        assertEquals(SCORE_INIT , checkScore(PLAYER_RESTART, archivoOriginalTests));
    }

}
