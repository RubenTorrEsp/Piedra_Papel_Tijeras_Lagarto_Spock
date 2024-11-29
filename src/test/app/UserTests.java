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
    void PlayerExsits_True() {
        boolean playerExists = userExists(PLAYER_EXISTS, testFile);
        assertTrue(playerExists, PLATER_NOT_IN_DB);
    }

    @Test
    void PlayerExists_False() {
        boolean playerExists = userExists(PLAYER_NOT_EXISTS, testFile);
        assertFalse(playerExists, PLAYER_ALREADY_IN_DB);
    }

    @Test
    void ReWrite_Score() throws IOException {
        assertEquals(SCORE_INIT, checkScore(PLAYER_REWRITE_SCORE, testFile), SCORE_DOES_NOT_MATCH);

        reWriteScore(PLAYER_REWRITE_SCORE, SCORE_NEW, testFile, tempFile);
        assertEquals(SCORE_NEW, checkScore(PLAYER_REWRITE_SCORE, testFile), SCORE_DOES_NOT_MATCH);
    }

    @Test
    void Update_Score_Victory() throws IOException {
        User playerTest = new User(PLAYER_EXISTS, testFile);
        int realScore = checkScore(PLAYER_EXISTS, testFile);
        assertEquals(SCORE_INIT,realScore,SCORE_DOES_NOT_MATCH);

        playerTest.updateScore(VICTORY);
        realScore = score;

        assertEquals(SCORE_VICTORY,realScore);
    }

    @Test
    void ActualizarPuntuacion_Derrota() throws IOException {
        User jugadorTest = new User(PLAYER_EXISTS, testFile);
        int puntuacionReal = checkScore(PLAYER_EXISTS, testFile);
        assertEquals(SCORE_INIT,puntuacionReal,SCORE_DOES_NOT_MATCH);

        jugadorTest.updateScore(DEFEAT);
        puntuacionReal = score;

        assertEquals(SCORE_DEFEAT,puntuacionReal);
    }

}