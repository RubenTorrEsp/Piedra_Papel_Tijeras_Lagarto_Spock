package app;

import org.junit.jupiter.api.Test;
import tools.Funciones;

import java.io.File;

import static app.User.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTests extends Funciones {

    static File testFile = new File(USER_TEST_FILE);
    static File tempFile = new File(USER_TEST_TEMP);

    @Test
    void UsuarioExiste_True_FuncionaCorrectamente() {
        boolean jugadorExiste = usuarioExiste(jugadorTestExistente, testFile);
        assertTrue(jugadorExiste, PLATER_NOT_IN_DB);
    }

    @Test
    void UsuarioExiste_False_FuncionaCorrectamente() {
        boolean jugadorExiste = usuarioExiste(jugadorTestNoExistente, testFile);
        assertFalse(jugadorExiste, PLAYER_ALREADY_IN_DB);
    }

    @Test
    void ReescribirPuntuacion_Test() {
        assertEquals(puntuacionInicial, comprobarPuntuacion(jugadorTestExistente, testFile), SCORE_DOES_NOT_MATCH);

        reescribirPuntuacion(jugadorTestExistente, nuevaPuntuacion, testFile, tempFile);
        assertEquals(nuevaPuntuacion, comprobarPuntuacion(jugadorTestExistente, testFile), SCORE_DOES_NOT_MATCH);
    }

    @Test
    void ActualizarPuntuacion_Victoria() {
        User jugadorTest = new User(jugadorTestExistente, testFile);
        int puntuacionReal = comprobarPuntuacion(jugadorTestExistente, testFile);
        assertEquals(puntuacionInicial,puntuacionReal,SCORE_DOES_NOT_MATCH);

        jugadorTest.actualizarPuntuacion(VICTORY);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasVictoria,puntuacionReal);
    }

    @Test
    void ActualizarPuntuacion_Derrota() {
        User jugadorTest = new User(jugadorTestExistente, testFile);
        int puntuacionReal = comprobarPuntuacion(jugadorTestExistente, testFile);
        assertEquals(puntuacionInicial,puntuacionReal,SCORE_DOES_NOT_MATCH);

        jugadorTest.actualizarPuntuacion(DEFEAT);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasDerrota,puntuacionReal);
    }

}