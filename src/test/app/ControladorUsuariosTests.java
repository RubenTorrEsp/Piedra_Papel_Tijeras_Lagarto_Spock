package app;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static resources.Textos.*;

public class ControladorUsuariosTests {

    String jugadorTest = "playerTest220694";
    static File archivoOriginal = new File(archivoUsuariosTest);

    @Test
    void crearJugador_JugadorNoExistente() {
        int jugadoresIniciales = contarJugadoresEnBDD();
        int jugadoresEsperados = jugadoresIniciales+1;

        ControladorUsuarios.crearJugador(Optional.ofNullable(jugadorTest), archivoOriginal);
        int jugadoresTrasCreacion = contarJugadoresEnBDD();

        assertEquals(jugadoresEsperados, jugadoresTrasCreacion);
    }

    @Test
    void crearJugador_JugadorYaEnBDD() {

    }

    @Test
    void eliminarJugador_jugadorExistente() {

    }

    @Test
    void eliminarJugador_jugadorNoExistente() {

    }

    int contarJugadoresEnBDD() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            return (int) br.lines().filter(linea -> linea.length() > 1).count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
