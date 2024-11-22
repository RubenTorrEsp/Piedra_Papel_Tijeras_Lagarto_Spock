package app;

import java.io.*;
import java.util.Optional;

import static app.Common.*;
import static app.PlayerControler.createPlayer;
import static app._Main.*;
import static resources.Texts.*;

public class User {
   
    public static String name;
    public static Integer score;
    public static String line;

    // Constructor
    public User(String userName, File file) throws IOException {
        if(userExists(userName, file)) establishUser(userName, file);
        else createPlayer(Optional.ofNullable(userName), filePlayers);
    }
    
    // Método para establecer el usuario
    public void establishUser(String userName, File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (parts[0].equals(userName)) {
                    name = parts[0];
                    score = Integer.parseInt(parts[1]);
                }
            }
        }
    }

    // Método que comprueba si el usuario existe
    public static boolean userExists(String userName, File file) {
        boolean userExists = false;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                if(line.contains(userName)) userExists = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userExists;
    }

    // Método que actualiza la puntuación del jugador
    public void updateScore(Boolean victory) throws IOException {
        if (victory) score++;
        else score--;
        System.out.println(SCORE_NEW + score);
        if (score == 0) deletePlayer(name, filePlayers, fileTemp);
    }

    // Método que actualiza la puntuación del jugador en el archivo
    public static void reWriteScore(String userName, int newScore, File fileReal, File fileTemp) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileReal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemp))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (!line.trim().isEmpty()) {
                    if (parts.length == 2 && parts[0].equals(userName)) line = userName+SEPARATOR+newScore;
                    bw.write(line);
                    bw.newLine();
                }
            }
        }
        reWriteFilePlayers(fileReal, fileTemp);
    }

    // Método que elimina el usuario si la puntuacion llega a 0
    public static void deletePlayer(String userName, File fileReal, File fileTemp) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileReal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemp))) {
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                if (!line.trim().isEmpty()) {
                    if (parts.length == 2 && parts[0].equals(userName)) line = EMPTY_ROW;
                    bw.write(line);
                    bw.newLine();
                }
            }
        }
        reWriteFilePlayers(fileReal, fileTemp, PLAYER_ERASED);
        System.exit(0);
    }
    
}