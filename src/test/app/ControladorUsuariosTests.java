package app;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Optional;

import static app.User.*;
import static app.ControladorUsuarios.*;
import static org.junit.jupiter.api.Assertions.*;
import static resources.Textos.*;
import static tools.Funciones.*;

public class ControladorUsuariosTests {

    String jugadorTest = "playerTest220694";
    String jugadorTestParaModificar = "playerTest251289";
    String jugadorTestParaReiniciar = "playerTest090880";
    String jugadorNuevoNombre = "playerTestNewName";
    String jugadorTestNoExistente = "playerNoExistente";
    static File archivoOriginalTests = new File(archivoUsuariosTest);
    static File archivoTemporalTests = new File(archivoUsuariosTemporalTest);


    @Test
    void crearJugador_JugadorNoExistente() {
        int jugadoresIniciales = contarJugadoresEnBDD(archivoOriginalTests);
        int jugadoresEsperados = jugadoresIniciales+1;

        ControladorUsuarios.crearJugador(Optional.ofNullable(jugadorTest), archivoOriginalTests);
        int jugadoresTrasCreacion = contarJugadoresEnBDD(archivoOriginalTests);

        assertEquals(jugadoresEsperados, jugadoresTrasCreacion);
    }

    @Test
    void crearJugador_JugadorYaEnBDD() {
        int jugadoresEsperados = contarJugadoresEnBDD(archivoOriginalTests);

        ControladorUsuarios.crearJugador(Optional.ofNullable(jugadorTest), archivoOriginalTests);
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

        modificarJugador(
                Optional.of(jugadorNuevoNombre),
                Optional.of(jugadorTestParaModificar),
                archivoOriginalTests,
                archivoTemporalTests);
    }

    @Test
    void eliminarJugador_jugadorExistente() {
        int jugadoresIniciales = contarJugadoresEnBDD(archivoOriginalTests);
        int jugadoresEsperados = jugadoresIniciales-1;

        ControladorUsuarios.eliminarJugador(Optional.ofNullable(jugadorTest), archivoOriginalTests, archivoTemporalTests);
        int jugadoresTrasEliminacion = contarJugadoresEnBDD(archivoOriginalTests);

        assertEquals(jugadoresEsperados, jugadoresTrasEliminacion);
    }

    @Test
    void eliminarJugador_jugadorNoExistente() {
        int jugadoresIniciales = contarJugadoresEnBDD(archivoOriginalTests);

        ControladorUsuarios.eliminarJugador(Optional.ofNullable(jugadorTestNoExistente), archivoOriginalTests, archivoTemporalTests);
        int jugadoresTrasEliminacion = contarJugadoresEnBDD(archivoOriginalTests);

        assertEquals(jugadoresIniciales, jugadoresTrasEliminacion);
    }

    @Test
    void reiniciarJugador_jugadorExistente() {
        int puntuacionAntes = 30;
        int puntuacionDespues = 50;
        int puntuacionReal = comprobarPuntuacion(jugadorTestParaReiniciar, archivoOriginalTests);

        assertEquals(puntuacionAntes,puntuacionReal);

        reiniciarJugador(Optional.ofNullable(jugadorTestParaReiniciar), archivoOriginalTests, archivoTemporalTests);
        puntuacionReal = comprobarPuntuacion(jugadorTestParaReiniciar, archivoOriginalTests);

        assertEquals(puntuacionDespues,puntuacionReal);

        reescribirPuntuacion(jugadorTestParaReiniciar, puntuacionAntes, archivoOriginalTests, archivoTemporalTests);
    }

}
