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
        boolean jugadorExiste = usuarioExiste(PLAYER_EXISTS, testFile);
        assertTrue(jugadorExiste, PLATER_NOT_IN_DB);
    }

    @Test
    void UsuarioExiste_False_FuncionaCorrectamente() {
        boolean jugadorExiste = usuarioExiste(PLAYER_NON_EXISTED, testFile);
        assertFalse(jugadorExiste, PLAYER_ALREADY_IN_DB);
    }

    @Test
    void ReescribirPuntuacion_Test() {
        assertEquals(SCORE_INIT, comprobarPuntuacion(PLAYER_EXISTS, testFile), SCORE_DOES_NOT_MATCH);

        reescribirPuntuacion(PLAYER_EXISTS, SCORE_NEW, testFile, tempFile);
        assertEquals(SCORE_NEW, comprobarPuntuacion(PLAYER_EXISTS, testFile), SCORE_DOES_NOT_MATCH);
    }

    @Test
    void ActualizarPuntuacion_Victoria() {
        User jugadorTest = new User(PLAYER_EXISTS, testFile);
        int puntuacionReal = comprobarPuntuacion(PLAYER_EXISTS, testFile);
        assertEquals(SCORE_INIT,puntuacionReal,SCORE_DOES_NOT_MATCH);

        jugadorTest.actualizarPuntuacion(VICTORY);
        puntuacionReal = puntuacion;

        assertEquals(SCORE_VICTORY,puntuacionReal);
    }

    @Test
    void ActualizarPuntuacion_Derrota() {
        User jugadorTest = new User(PLAYER_EXISTS, testFile);
        int puntuacionReal = comprobarPuntuacion(PLAYER_EXISTS, testFile);
        assertEquals(SCORE_INIT,puntuacionReal,SCORE_DOES_NOT_MATCH);

        jugadorTest.actualizarPuntuacion(DEFEAT);
        puntuacionReal = puntuacion;

        assertEquals(SCORE_DEFEAT,puntuacionReal);
    }

}