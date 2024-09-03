package app;

import org.junit.jupiter.api.Test;
import tools.Funciones;

import java.io.File;

import static app.User.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTests extends Funciones {

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

        reescribirPuntuacion(jugadorTestExistente, nuevaPuntuacion, testUsersFile, tempFile);
        assertEquals(nuevaPuntuacion, comprobarPuntuacion(jugadorTestExistente, testUsersFile), mensajeErrorPuntuacionNoCoincide);
    }

    @Test
    void ActualizarPuntuacion_Victoria() {
        User jugadorTest = new User(jugadorTestExistente, testUsersFile);
        int puntuacionReal = comprobarPuntuacion(jugadorTestExistente, testUsersFile);
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest,victoria);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasVictoria,puntuacionReal);
    }

    @Test
    void ActualizarPuntuacion_Derrota() {
        User jugadorTest = new User(jugadorTestExistente, testUsersFile);
        int puntuacionReal = comprobarPuntuacion(jugadorTestExistente, testUsersFile);
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest,derrota);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasDerrota,puntuacionReal);
    }

}