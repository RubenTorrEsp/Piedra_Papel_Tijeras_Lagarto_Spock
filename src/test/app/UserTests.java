package app;

import org.junit.jupiter.api.Test;

import java.io.File;

import static app.User.*;
import static org.junit.jupiter.api.Assertions.*;
import static resources.Textos.*;
import static tools.Texts.*;
import static tools.Funciones.*;

class UserTests {

    String jugadorTestExistente = "playerTest060782";
    String jugadorTestNoExistente = "playerTestNoExiste";
    int puntuacionInicial = 50;
    static File archivoOriginalTests = new File(archivoUsuariosTest);
    static File archivoTemporalTests = new File(archivoUsuariosTemporalTest);

    @Test
    void UsuarioExiste_True_FuncionaCorrectamente() {
        boolean jugadorExiste = usuarioExiste(jugadorTestExistente, archivoOriginalTests);
        assertTrue(jugadorExiste, mensajeErrorJugadorNoEnBDD);
    }

    @Test
    void UsuarioExiste_False_FuncionaCorrectamente() {
        boolean jugadorExiste = usuarioExiste(jugadorTestNoExistente, archivoOriginalTests);
        assertFalse(jugadorExiste, mensajeErrorJugadorYaEnBDD);
    }

    @Test
    void ReescribirPuntuacion_FuncionaCorrectamente() {
        int nuevaPuntuacion = 30;
        int puntuacionReal = comprobarPuntuacion(jugadorTestExistente, archivoOriginalTests);
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        reescribirPuntuacion(jugadorTestExistente, nuevaPuntuacion, archivoOriginalTests, archivoTemporalTests);
        puntuacionReal = comprobarPuntuacion(jugadorTestExistente, archivoOriginalTests);
        assertEquals(nuevaPuntuacion,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        reescribirPuntuacion(jugadorTestExistente, puntuacionInicial, archivoOriginalTests, archivoTemporalTests);
    }

    @Test
    void ActualizarPuntuacion_Victoria_FuncionaCorrectamente() {
        User jugadorTest = new User(jugadorTestExistente, archivoOriginalTests);
        int puntuacionTrasVictoria = 51;
        int puntuacionReal = comprobarPuntuacion(jugadorTestExistente, archivoOriginalTests);
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest,true);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasVictoria,puntuacionReal);
    }

    @Test
    void ActualizarPuntuacion_Derrota_FuncionaCorrectamente() {
        User jugadorTest = new User(jugadorTestExistente, archivoOriginalTests);
        int puntuacionTrasDerrota = 49;
        int puntuacionReal = comprobarPuntuacion(jugadorTestExistente, archivoOriginalTests);
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest,false);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasDerrota,puntuacionReal);
    }

}