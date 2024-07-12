package app;

import static app.User.*;
import static resources.Textos.*;

import static tools.Texts.*;

import java.io.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class UserTests {

    String jugadorTestExistente = "playerTest060782";
    String jugadorTestNoExistente = "playerTestNoExiste";
    User jugadorTest = new User(jugadorTestExistente);
    int puntuacionInicial = 50;
    static File archivoOriginalTests = new File(archivoUsuariosTest);

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

        reescribirPuntuacion(jugadorTestExistente, nuevaPuntuacion, archivoOriginalTests, archivoTemporal);
        puntuacionReal = comprobarPuntuacion();
        assertEquals(nuevaPuntuacion,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        reescribirPuntuacion(jugadorTestExistente, puntuacionInicial, archivoOriginalTests, archivoTemporal);
    }

    @Test
    void ActualizarPuntuacion_Victoria_FuncionaCorrectamente() {
        int puntuacionTrasVictoria = 51;
        int puntuacionReal = comprobarPuntuacion();
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest,true);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasVictoria,puntuacionReal);
    }

    @Test
    void ActualizarPuntuacion_Derrota_FuncionaCorrectamente() {
        int puntuacionTrasDerrota = 49;
        int puntuacionReal = comprobarPuntuacion();
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest,false);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasDerrota,puntuacionReal);
    }

    int comprobarPuntuacion() {
        int puntuacionReal = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(jugadorTestExistente)) puntuacionReal = Integer.parseInt(partes[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return puntuacionReal;
    }

}