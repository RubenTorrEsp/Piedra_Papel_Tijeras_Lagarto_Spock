package app;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static resources.Textos.*;

public class ControladorUsuariosTests {

    String jugadorTest = "player220694";
    static File archivoOriginal = new File(archivoUsuarios);

    @Test
    void crearJugador_funcionaCorrectamente() {

    }

    @Test
    void contarJugadoresEnBDD() {
        int jugadores = 0;

        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            System.out.println(mostrarJugadores);
            while ((linea = br.readLine()) != null) {
                if(linea.length() > 1) jugadores++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("El numero de jugadores es "+jugadores);
        //return jugadores;
    }

}
