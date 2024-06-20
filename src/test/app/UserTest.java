package app;

import static app.User.*;
import static tools.Textos.*;

import java.io.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class UserTest {

    String jugadorTestExistente = "playerTest060782";
    String jugadorTestNoExistente = "playerTest220694";

    @Test
    void UsuarioExiste_True_FuncionaCorrectamente() {
        boolean jugadorExiste = usuarioExiste(jugadorTestExistente);
        assertTrue(jugadorExiste, "El jugador comprobado no se encuentra en la BDD.");
    }

    @Test
    void UsuarioExiste_False_FuncionaCorrectamente() {
        boolean jugadorExiste = usuarioExiste(jugadorTestNoExistente);
        assertFalse(jugadorExiste, "El jugador comprobado sí aparece en la BDD.");
    }

    @Test
    void ReescribirPuntuacion_FuncionaCorrectamente() {
        int antiguaPuntuacion = 50;
        int nuevaPuntuacion = 30;
        int puntuacionReal = comprobarPuntuacion();
        assertEquals(antiguaPuntuacion,puntuacionReal,"La puntuación recibida no coincide con la esperada");

        reescribirPuntuacion(jugadorTestExistente,nuevaPuntuacion);
        puntuacionReal = comprobarPuntuacion();
        assertEquals(nuevaPuntuacion,puntuacionReal,"La puntuación recibida no coincide con la esperada");

        reescribirPuntuacion(jugadorTestExistente, antiguaPuntuacion);
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