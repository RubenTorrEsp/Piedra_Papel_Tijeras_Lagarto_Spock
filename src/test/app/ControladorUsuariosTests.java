package app;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import static app.User.*;
import static app.ControladorUsuarios.*;
import static org.junit.jupiter.api.Assertions.*;
import static resources.Textos.*;

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
        int jugadoresIniciales = contarJugadoresEnBDD();
        int jugadoresEsperados = jugadoresIniciales+1;

        ControladorUsuarios.crearJugador(Optional.ofNullable(jugadorTest), archivoOriginalTests);
        int jugadoresTrasCreacion = contarJugadoresEnBDD();

        assertEquals(jugadoresEsperados, jugadoresTrasCreacion);
    }

    @Test
    void crearJugador_JugadorYaEnBDD() {
        int jugadoresEsperados = contarJugadoresEnBDD();

        ControladorUsuarios.crearJugador(Optional.ofNullable(jugadorTest), archivoOriginalTests);
        int jugadoresTrasCreacion = contarJugadoresEnBDD();

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
        int jugadoresIniciales = contarJugadoresEnBDD();
        int jugadoresEsperados = jugadoresIniciales-1;

        ControladorUsuarios.eliminarJugador(Optional.ofNullable(jugadorTest), archivoOriginalTests, archivoTemporalTests);
        int jugadoresTrasEliminacion = contarJugadoresEnBDD();

        assertEquals(jugadoresEsperados, jugadoresTrasEliminacion);
    }

    @Test
    void eliminarJugador_jugadorNoExistente() {
        int jugadoresIniciales = contarJugadoresEnBDD();

        ControladorUsuarios.eliminarJugador(Optional.ofNullable(jugadorTestNoExistente), archivoOriginalTests, archivoTemporalTests);
        int jugadoresTrasEliminacion = contarJugadoresEnBDD();

        assertEquals(jugadoresIniciales, jugadoresTrasEliminacion);
    }

    @Test
    void reiniciarJugador_jugadorExistente() {

    }

    @Test
    void reiniciarJugador_jugadorNoExistente() {

    }

    int contarJugadoresEnBDD() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginalTests))) {
            return (int) br.lines().filter(linea -> linea.length() > 1).count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //TODO: Completar comportamiento para eliminar previamente al jugador SI EXISTE antes de intentar crearlo
    void eliminarJugadorTests() {

    }

}
