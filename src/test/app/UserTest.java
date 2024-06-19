package app;

import org.junit.jupiter.api.*;

import static app.User.*;
import static org.junit.jupiter.api.Assertions.*;



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

    }

}