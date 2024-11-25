package app;

import org.junit.jupiter.api.Test;
import tools.Functions;

import java.io.File;
import java.io.IOException;

import static app.User.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTests extends Functions {

    static File testFile = new File(USER_TEST_FILE);
    static File tempFile = new File(USER_TEST_TEMP);

    @Test
    void UsuarioExiste_True() {
        boolean jugadorExiste = userExists(PLAYER_EXISTS, testFile);
        assertTrue(jugadorExiste, PLATER_NOT_IN_DB);
    }

    @Test
    void UsuarioExiste_False() {
        boolean jugadorExiste = usuarioExiste(PLAYER_NOT_EXISTS, testFile);
        assertFalse(jugadorExiste, PLAYER_ALREADY_IN_DB);
    }

    @Test
    void ReescribirPuntuacion_Test() throws IOException {
        assertEquals(SCORE_INIT, checkScore(PLAYER_REWRITE_SCORE, testFile), SCORE_DOES_NOT_MATCH);

        reescribirPuntuacion(PLAYER_REWRITE_SCORE, SCORE_NEW, testFile, tempFile);
        assertEquals(SCORE_NEW, checkScore(PLAYER_REWRITE_SCORE, testFile), SCORE_DOES_NOT_MATCH);
    }

    @Test
    void ActualizarPuntuacion_Victoria() throws IOException {
        User jugadorTest = new User(PLAYER_EXISTS, testFile);
        int puntuacionReal = checkScore(PLAYER_EXISTS, testFile);
        assertEquals(SCORE_INIT,puntuacionReal,SCORE_DOES_NOT_MATCH);

        jugadorTest.actualizarPuntuacion(VICTORY);
        puntuacionReal = score;

        assertEquals(SCORE_VICTORY,puntuacionReal);
    }

    @Test
    void ActualizarPuntuacion_Derrota() throws IOException {
        User jugadorTest = new User(PLAYER_EXISTS, testFile);
        int puntuacionReal = checkScore(PLAYER_EXISTS, testFile);
        assertEquals(SCORE_INIT,puntuacionReal,SCORE_DOES_NOT_MATCH);

        jugadorTest.actualizarPuntuacion(DEFEAT);
        puntuacionReal = score;

        assertEquals(SCORE_DEFEAT,puntuacionReal);
    }

}