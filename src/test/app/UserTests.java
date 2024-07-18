package app;

import static app.User.*;
import static resources.Textos.*;

import static tools.Texts.*;
import tools.CommonFunctions.*;

import java.io.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


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
        int puntuacionReal = comprobarPuntuacion();
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        reescribirPuntuacion(jugadorTestExistente, nuevaPuntuacion, archivoOriginalTests, archivoTemporalTests);
        puntuacionReal = comprobarPuntuacion();
        assertEquals(nuevaPuntuacion,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        reescribirPuntuacion(jugadorTestExistente, puntuacionInicial, archivoOriginalTests, archivoTemporalTests);
    }

    //TODO: El objeto User reescribe el archivo de usuarios. Pendiente de corregir
    @Test
    void ActualizarPuntuacion_Victoria_FuncionaCorrectamente() {
        User jugadorTest = new User(jugadorTestExistente);
        int puntuacionTrasVictoria = 51;
        int puntuacionReal = comprobarPuntuacion();
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest,true);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasVictoria,puntuacionReal);
    }

    //TODO: El objeto User reescribe el archivo de usuarios. Pendiente de corregir
    @Test
    void ActualizarPuntuacion_Derrota_FuncionaCorrectamente() {
        User jugadorTest = new User(jugadorTestExistente);
        int puntuacionTrasDerrota = 49;
        int puntuacionReal = comprobarPuntuacion();
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest,false);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasDerrota,puntuacionReal);
    }

}