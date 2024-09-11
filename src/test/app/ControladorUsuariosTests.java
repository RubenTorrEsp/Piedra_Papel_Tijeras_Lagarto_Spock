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

        ControladorUsuarios.crearJugador(Optional.of(jugadorTestParaCrear), archivoOriginalTests);
        int jugadoresTrasCreacion = contarJugadoresEnBDD(archivoOriginalTests);

        assertEquals(jugadoresEsperados, jugadoresTrasCreacion);
    }

    @Test
    void crearJugador_JugadorYaEnBDD() {
        int jugadoresEsperados = contarJugadoresEnBDD(archivoOriginalTests);

        ControladorUsuarios.crearJugador(Optional.of(jugadorTest), archivoOriginalTests);
        int jugadoresTrasCreacion = contarJugadoresEnBDD(archivoOriginalTests);

        assertEquals(jugadoresEsperados, jugadoresTrasCreacion);
    }

    @Test
    void modificarJugador_jugadorExistente() {
        assertTrue(usuarioExiste(jugadorTestParaModificar, archivoOriginalTests));
        assertFalse(usuarioExiste(jugadorNuevoNombre, archivoOriginalTests));

        modificarJugador(
                Optional.of(jugadorTestParaModificar),
                Optional.of(jugadorNuevoNombre),
                archivoOriginalTests,
                archivoTemporalTests);

        assertFalse(usuarioExiste(jugadorTestParaModificar, archivoOriginalTests));
        assertTrue(usuarioExiste(jugadorNuevoNombre, archivoOriginalTests));
    }

    @Test
    void eliminarJugador_jugadorExistente() {
        int jugadoresIniciales = contarJugadoresEnBDD(archivoOriginalTests);

        ControladorUsuarios.eliminarJugador(Optional.of(jugadorTest), archivoOriginalTests, archivoTemporalTests);

        assertEquals(jugadoresIniciales - 1, contarJugadoresEnBDD(archivoOriginalTests));
    }

    @Test
    void eliminarJugador_jugadorNoExistente() {
        int jugadoresIniciales = contarJugadoresEnBDD(archivoOriginalTests);

        ControladorUsuarios.eliminarJugador(Optional.of(jugadorTestNoExistente), archivoOriginalTests, archivoTemporalTests);
        int jugadoresTrasEliminacion = contarJugadoresEnBDD(archivoOriginalTests);

        assertEquals(jugadoresIniciales, jugadoresTrasEliminacion);
    }

    @Test
    void reiniciarJugador_jugadorExistente() {
        assertEquals(nuevaPuntuacion,comprobarPuntuacion(jugadorTestParaReiniciar, archivoOriginalTests));

        reiniciarJugador(Optional.of(jugadorTestParaReiniciar), archivoOriginalTests, archivoTemporalTests);

        assertEquals(puntuacionInicial ,comprobarPuntuacion(jugadorTestParaReiniciar, archivoOriginalTests));
    }

}
