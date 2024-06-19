package app;

import org.junit.jupiter.api.*;

import static app.User.*;
import static org.junit.jupiter.api.Assertions.*;



class UserTest {

    @Test
    void UsuarioExisteFuncionaCorrectamente() {
        String jugadorTest = "Hitler";

        boolean jugadorExiste = usuarioExiste(jugadorTest);

        assertTrue(jugadorExiste, "El jugador comprobado no se encuentra en la BDD.");
    }

}