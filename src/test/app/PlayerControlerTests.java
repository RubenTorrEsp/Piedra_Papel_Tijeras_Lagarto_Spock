package app;

import org.junit.jupiter.api.Test;
import tools.Functions;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static app.User.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerControlerTests extends Functions {

    static File archivoOriginalTests = new File(USER_TEST_FILE);
    static File archivoTemporalTests = new File(USER_TEST_TEMP);


    @Test
    void crearJugador_JugadorNoExistente() throws IOException {
        int jugadoresEsperados = contarJugadoresEnBDD(archivoOriginalTests)+1;

        PlayerControler.createPlayer(Optional.of(PLAYER_CREATE), archivoOriginalTests);
        int jugadoresTrasCreacion = contarJugadoresEnBDD(archivoOriginalTests);

        assertEquals(jugadoresEsperados, jugadoresTrasCreacion);
    }

    @Test
    void crearJugador_JugadorYaEnBDD() throws IOException {
        int jugadoresEsperados = contarJugadoresEnBDD(archivoOriginalTests);

        PlayerControler.createPlayer(Optional.of(PLAYER_TEST), archivoOriginalTests);
        int jugadoresTrasCreacion = contarJugadoresEnBDD(archivoOriginalTests);

        assertEquals(jugadoresEsperados, jugadoresTrasCreacion);
    }

    @Test
    void modificarJugador_jugadorExistente() throws IOException {
        assertTrue(usuarioExiste(PLAYER_MODIFY, archivoOriginalTests));
        assertFalse(usuarioExiste(PLAYER_NEW_NAME, archivoOriginalTests));

        modificarJugador(
                Optional.of(PLAYER_MODIFY),
                Optional.of(PLAYER_NEW_NAME),
                archivoOriginalTests,
                archivoTemporalTests);

        assertFalse(usuarioExiste(PLAYER_MODIFY, archivoOriginalTests));
        assertTrue(usuarioExiste(PLAYER_NEW_NAME, archivoOriginalTests));
    }

    @Test
    void eliminarJugador_jugadorExistente() throws IOException {
        int jugadoresIniciales = contarJugadoresEnBDD(archivoOriginalTests);

        PlayerControler.eliminarJugador(Optional.of(PLAYER_DELETE), archivoOriginalTests, archivoTemporalTests);

        assertEquals(jugadoresIniciales - 1, contarJugadoresEnBDD(archivoOriginalTests));
    }

    @Test
    void eliminarJugador_jugadorNoExistente() throws IOException {
        int jugadoresIniciales = contarJugadoresEnBDD(archivoOriginalTests);

        PlayerControler.eliminarJugador(Optional.of(PLAYER_DELETE_FALSE), archivoOriginalTests, archivoTemporalTests);
        int jugadoresTrasEliminacion = contarJugadoresEnBDD(archivoOriginalTests);

        assertEquals(jugadoresIniciales, jugadoresTrasEliminacion);
    }

    @Test
    void reiniciarJugador_jugadorExistente() throws IOException {
        assertEquals(SCORE_NEW,comprobarPuntuacion(PLAYER_RESTART, archivoOriginalTests));

        reiniciarJugador(Optional.of(PLAYER_RESTART), archivoOriginalTests, archivoTemporalTests);

        assertEquals(SCORE_INIT ,comprobarPuntuacion(PLAYER_RESTART, archivoOriginalTests));
    }

}
