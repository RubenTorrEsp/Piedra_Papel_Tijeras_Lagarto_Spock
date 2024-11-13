package tools;

import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.nio.file.*;

import static app.User.linea;
import static resources.Texts.SEPARATOR;

public class Functions extends Texts {

    @BeforeEach
    void restartDB() throws IOException {
        Path sourcePath = Paths.get(USER_TEST_TEMPLATE);
        Path destinationPath = Paths.get(USER_TEST_FILE);

        Files.newBufferedWriter(destinationPath, StandardOpenOption.TRUNCATE_EXISTING).close();
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }

    public static int checkScore(String player, File file) throws IOException {
        int realScore = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((linea = br.readLine()) != null) {
                String[] parts = linea.split(SEPARATOR);
                if (parts.length == 2 && parts[0].equals(player)) {
                    realScore = Integer.parseInt(parts[1].trim());
                    break;
                }
            }
        }
        return realScore;
    }

    public static int countPlayersInDB(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return (int) br.lines().filter(linea -> linea.length() > 1).count();
        } catch (IOException e) {
            return 0;
        }
    }

}