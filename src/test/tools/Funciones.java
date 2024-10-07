package tools;

import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;

import static app.User.linea;
import static resources.Texts.SEPARATOR;

public class Funciones extends Texts {

    @BeforeEach
    void reiniciarBDD() throws IOException {
        Path sourcePath = Paths.get(USER_TEST_TEMPLATE);
        Path destinationPath = Paths.get(USER_TEST_FILE);

        Files.newBufferedWriter(destinationPath, StandardOpenOption.TRUNCATE_EXISTING).close();
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }

    public static int comprobarPuntuacion(String jugador, File file) {
        int puntuacionReal = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(SEPARATOR);
                if (partes.length == 2 && partes[0].equals(jugador)) {
                    puntuacionReal = Integer.parseInt(partes[1].trim());
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println(STR."Error al leer el archivo: \{e.getMessage()}");
        } catch (NumberFormatException e) {
            System.err.println(STR."Formato de puntuación inválido para el jugador \{jugador}: \{e.getMessage()}");
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

}
