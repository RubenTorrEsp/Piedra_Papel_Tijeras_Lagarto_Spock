package app;

import org.junit.jupiter.api.Test;
import tools.Funciones;

import java.io.File;
import java.util.Optional;

import static app.User.*;
import static app.ControladorUsuarios.*;
import static org.junit.jupiter.api.Assertions.*;

public class ControladorUsuariosTests extends Funciones {

    static File archivoOriginalTests = new File(USER_TEST_FILE);
    static File archivoTemporalTests = new File(USER_TEST_TEMP);


    @Test
    void crearJugador_JugadorNoExistente() {
        int jugadoresEsperados = contarJugadoresEnBDD(archivoOriginalTests)+1;

        ControladorUsuarios.crearJugador(Optional.of(PLAYER_CREATE), archivoOriginalTests);
        int jugadoresTrasCreacion = contarJugadoresEnBDD(archivoOriginalTests);

        assertEquals(jugadoresEsperados, jugadoresTrasCreacion);
    }

//    TODO: Arreglar test. Escribe por consola y solicita respuesta
    @Test
    void crearJugador_JugadorYaEnBDD() {
        int jugadoresEsperados = contarJugadoresEnBDD(archivoOriginalTests);

        ControladorUsuarios.crearJugador(Optional.of(PLAYER_TEST), archivoOriginalTests);
        int jugadoresTrasCreacion = contarJugadoresEnBDD(archivoOriginalTests);

        assertEquals(jugadoresEsperados, jugadoresTrasCreacion);
    }

    @Test
    void modificarJugador_jugadorExistente() {
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
    void eliminarJugador_jugadorExistente() {
        int jugadoresIniciales = contarJugadoresEnBDD(archivoOriginalTests);

        ControladorUsuarios.eliminarJugador(Optional.of(PLAYER_TEST), archivoOriginalTests, archivoTemporalTests);

        assertEquals(jugadoresIniciales - 1, contarJugadoresEnBDD(archivoOriginalTests));
    }

    //TODO: Arreglar test. Escribe por consola y solicita respuesta
//    @Test
//    void eliminarJugador_jugadorNoExistente() {
//        int jugadoresIniciales = contarJugadoresEnBDD(archivoOriginalTests);
//
//        ControladorUsuarios.eliminarJugador(Optional.of(PLAYER_NON_EXISTED), archivoOriginalTests, archivoTemporalTests);
//        int jugadoresTrasEliminacion = contarJugadoresEnBDD(archivoOriginalTests);
//
//        assertEquals(jugadoresIniciales, jugadoresTrasEliminacion);
//    }

    @Test
    void reiniciarJugador_jugadorExistente() {
        assertEquals(SCORE_NEW,comprobarPuntuacion(PLAYER_RESTART, archivoOriginalTests));

        reiniciarJugador(Optional.of(PLAYER_RESTART), archivoOriginalTests, archivoTemporalTests);

        assertEquals(SCORE_INIT ,comprobarPuntuacion(PLAYER_RESTART, archivoOriginalTests));
    }

}
