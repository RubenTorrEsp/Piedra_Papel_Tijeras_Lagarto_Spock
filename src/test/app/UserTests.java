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
    static File testUsersFile = new File(archivoUsuariosTest);
    static File tempFile = new File(archivoUsuariosTemporalTest);

    @Test
    void UsuarioExiste_True_FuncionaCorrectamente() {
        boolean jugadorExiste = usuarioExiste(jugadorTestExistente, testUsersFile);
        assertTrue(jugadorExiste, mensajeErrorJugadorNoEnBDD);
    }

    @Test
    void UsuarioExiste_False_FuncionaCorrectamente() {
        boolean jugadorExiste = usuarioExiste(jugadorTestNoExistente, testUsersFile);
        assertFalse(jugadorExiste, mensajeErrorJugadorYaEnBDD);
    }

    @Test
    void ReescribirPuntuacion_FuncionaCorrectamente() {
        assertEquals(puntuacionInicial, comprobarPuntuacion(jugadorTestExistente, testUsersFile), mensajeErrorPuntuacionNoCoincide);

        reescribirPuntuacion(jugadorTestExistente, 30, testUsersFile, tempFile);
        assertEquals(nuevaPuntuacion, comprobarPuntuacion(jugadorTestExistente, testUsersFile), mensajeErrorPuntuacionNoCoincide);
        reescribirPuntuacion(jugadorTestExistente, puntuacionInicial, testUsersFile, tempFile);
    }

    @Test
    void ActualizarPuntuacion_Victoria() {
        User jugadorTest = new User(jugadorTestExistente, testUsersFile);
        assertEquals(puntuacionInicial, comprobarPuntuacion(jugadorTestExistente, testUsersFile), mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest, true);

        assertEquals(51, comprobarPuntuacion(jugadorTestExistente, testUsersFile));
    }

    @Test
    void ActualizarPuntuacion_Derrota_FuncionaCorrectamente() {
        User jugadorTest = new User(jugadorTestExistente, testUsersFile);
        int puntuacionTrasDerrota = 49;
        int puntuacionReal = comprobarPuntuacion(jugadorTestExistente, testUsersFile);
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest,false);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasDerrota,puntuacionReal);
    }

}