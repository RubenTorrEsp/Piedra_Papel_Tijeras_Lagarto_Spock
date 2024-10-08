package tools;

public class Texts {

    // Archivos
    public static final String USER_TEST_FILE = "src\\test\\resources\\userTest.txt";
    public static final String USER_TEST_TEMPLATE = "src\\test\\resources\\userTestTemplate.txt";
    public static final String USER_TEST_TEMP = "src\\test\\resources\\tempTest.txt";

    // Jugadores
    public final String PLAYER_EXISTS = "playerTest060782";
    public final String PLAYER_TEST = "playerTest220694";
    public final String PLAYER_CREATE = "playerTest200995";
    public final String PLAYER_MODIFY = "playerTest251289";
    public final String PLAYER_RESTART = "playerTest090880";
    public final String PLAYER_NEW_NAME = "playerTestNewName";
    public final String PLAYER_NON_EXISTED = "playerNoExistente";
    public final String PLAYER_UNFORMATTED = "user_Test";

    // Puntuaciones
    public final int SCORE_INIT = 50;
    public final int SCORE_VICTORY = 51;
    public final int SCORE_DEFEAT = 49;
    public final int SCORE_NEW = 30;

    // Comportamientos
    public final boolean VICTORY = true;
    public final boolean DEFEAT = false;

    // Mensajes de error
    public final String PLATER_NOT_IN_DB = "El jugador comprobado no se encuentra en la BDD.";
    public final String PLAYER_ALREADY_IN_DB = "El jugador comprobado sí aparece en la BDD.";
    public final String SCORE_DOES_NOT_MATCH = "La puntuación recibida no coincide con la esperada";

}