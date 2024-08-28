package app;

import org.junit.jupiter.api.Test;
import tools.Funciones;

import java.io.File;

import static app.User.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTests extends Funciones {

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
    void ReescribirPuntuacion_Test() {
        assertEquals(puntuacionInicial, comprobarPuntuacion(jugadorTestExistente, testUsersFile), mensajeErrorPuntuacionNoCoincide);

        reescribirPuntuacion(jugadorTestExistente, 30, testUsersFile, tempFile);
        assertEquals(30, comprobarPuntuacion(jugadorTestExistente, testUsersFile), mensajeErrorPuntuacionNoCoincide);
        reescribirPuntuacion(jugadorTestExistente, puntuacionInicial, testUsersFile, tempFile);
    }

    @Test
    void ActualizarPuntuacion_Victoria() {
        User jugadorTest = new User(jugadorTestExistente, testUsersFile);
        int puntuacionTrasVictoria = 51;
        int puntuacionReal = comprobarPuntuacion(jugadorTestExistente, testUsersFile);
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest,true);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasVictoria,puntuacionReal);
    }

    @Test
    void ActualizarPuntuacion_Derrota() {
        User jugadorTest = new User(jugadorTestExistente, testUsersFile);
        int puntuacionTrasDerrota = 49;
        int puntuacionReal = comprobarPuntuacion(jugadorTestExistente, testUsersFile);
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest,false);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasDerrota,puntuacionReal);
    }

}