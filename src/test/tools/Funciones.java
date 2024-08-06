package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static app.User.linea;
import static resources.Textos.separadorUsuarios;

public class Funciones {
    public int comprobarPuntuacion(String jugador, File file) {
        int puntuacionReal = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(jugador)) puntuacionReal = Integer.parseInt(partes[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return puntuacionReal;
    }

}
