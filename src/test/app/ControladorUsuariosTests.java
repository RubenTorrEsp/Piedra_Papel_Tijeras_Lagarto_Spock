package app;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static resources.Textos.*;
import app.ControladorUsuarios.*;

public class ControladorUsuariosTests {

    String jugadorTest = "player220694";
    static File archivoOriginal = new File(archivoUsuariosTest);

    @Test
    void crearJugador_funcionaCorrectamente() {
        int jugadoresIniciales = contarJugadoresEnBDD();
        int jugadoresEsperados = jugadoresIniciales++;

        assertEquals(jugadoresEsperados, jugadoresIniciales-1);

        ControladorUsuarios.crearJugador(Optional.ofNullable(jugadorTest));
    }

    int contarJugadoresEnBDD() {
        int jugadores = 0;

        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            while ((linea = br.readLine()) != null) {
                if(linea.length() > 1) jugadores++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jugadores;
    }

}
