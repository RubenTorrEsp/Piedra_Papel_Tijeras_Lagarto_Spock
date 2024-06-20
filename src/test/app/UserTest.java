package app;

import org.junit.jupiter.api.*;

import java.io.*;

import static app.User.*;
import static org.junit.jupiter.api.Assertions.*;
import static tools.Textos.separadorUsuarios;


class UserTest {

    @Test
    void UsuarioExiste_True_FuncionaCorrectamente() {
        String jugadorTest = "playerTest060782";

        boolean jugadorExiste = usuarioExiste(jugadorTest);

        assertTrue(jugadorExiste, "El jugador comprobado no se encuentra en la BDD.");
    }

    @Test
    void UsuarioExiste_False_FuncionaCorrectamente() {
        String jugadorTest = "playerTest220694";

        boolean jugadorExiste = usuarioExiste(jugadorTest);

        assertFalse(jugadorExiste, "El jugador comprobado sí aparece en la BDD.");
    }

    @Test
    void ReescribirPuntuacion_FuncionaCorrectamente() {
        String jugadorTest = "playerTest060782";
        int antiguaPuntuacion = 50;
        int nuevaPuntuacion = 30;
        String puntuacionReal;

        reescribirPuntuacion(jugadorTest,nuevaPuntuacion);
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(jugadorTest)) puntuacionReal = partes[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}