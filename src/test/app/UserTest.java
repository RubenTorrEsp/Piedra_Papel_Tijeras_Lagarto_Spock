package app;

import static app.User.*;
import static tools.Textos.*;

import java.io.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class UserTest {

    String jugadorTestExistente = "playerTest060782";
    String jugadorTestNoExistente = "playerTest220694";
    User jugadorTest = new User(jugadorTestExistente);

    String mensajeErrorJugadorNoEnBDD = "El jugador comprobado no se encuentra en la BDD.";
    String mensajeErrorJugadorYaEnBDD = "El jugador comprobado sí aparece en la BDD.";
    String mensajeErrorPuntuacionNoCoincide = "La puntuación recibida no coincide con la esperada";

    @Test
    void UsuarioExiste_True_FuncionaCorrectamente() {
        boolean jugadorExiste = usuarioExiste(jugadorTestExistente);
        assertTrue(jugadorExiste, mensajeErrorJugadorNoEnBDD);
    }

    @Test
    void UsuarioExiste_False_FuncionaCorrectamente() {
        boolean jugadorExiste = usuarioExiste(jugadorTestNoExistente);
        assertFalse(jugadorExiste, mensajeErrorJugadorYaEnBDD);
    }

    @Test
    void ReescribirPuntuacion_FuncionaCorrectamente() {
        int antiguaPuntuacion = 50;
        int nuevaPuntuacion = 30;
        int puntuacionReal = comprobarPuntuacion();
        assertEquals(antiguaPuntuacion,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        reescribirPuntuacion(jugadorTestExistente,nuevaPuntuacion);
        puntuacionReal = comprobarPuntuacion();
        assertEquals(nuevaPuntuacion,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        reescribirPuntuacion(jugadorTestExistente, antiguaPuntuacion);
    }

    @Test
    void ActualizarPuntuacion_Victoria_FuncionaCorrectamente() throws IOException {
        int puntuacionInicial = 50;
        int puntuacionTrasVictoria = 51;
        int puntuacionReal = comprobarPuntuacion();
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest,true);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasVictoria,puntuacionReal);
    }

    @Test
    void ActualizarPuntuacion_Derrota_FuncionaCorrectamente() throws IOException {
        int puntuacionInicial = 50;
        int puntuacionTrasVictoria = 49;
        int puntuacionReal = comprobarPuntuacion();
        assertEquals(puntuacionInicial,puntuacionReal,mensajeErrorPuntuacionNoCoincide);

        jugadorTest.actualizarPuntuacion(jugadorTest,false);
        puntuacionReal = puntuacion;

        assertEquals(puntuacionTrasVictoria,puntuacionReal);
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