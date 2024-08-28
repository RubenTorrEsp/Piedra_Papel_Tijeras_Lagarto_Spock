package tools;

import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static app.User.linea;
import static resources.Textos.separadorUsuarios;

public class Funciones extends Texts {

    public static int comprobarPuntuacion(String jugador, File file) {
        int puntuacionReal = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);

                if (partes.length == 2 && partes[0].equals(jugador)) {
                    puntuacionReal = Integer.parseInt(partes[1].trim());
                    break; // Salimos del bucle una vez encontrada la puntuación del jugador
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Formato de puntuación inválido para el jugador " + jugador + ": " + e.getMessage());
        }
        return puntuacionReal;
    }

    public static int contarJugadoresEnBDD(File archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            return (int) br.lines().filter(linea -> linea.length() > 1).count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //TODO: Completar comportamiento para reiniciar la base de datos
    @BeforeEach
    void reiniciarBDD() {
        System.out.println("Se va a realizar 1 test");
    }

}
