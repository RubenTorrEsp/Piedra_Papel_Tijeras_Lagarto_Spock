package tools;

public class Texts {

    // Archivos
    public static final String archivoUsuariosTest = "src\\test\\resources\\userTest.txt";
    public static final String archivoUsuariosTestTemplate = "src\\test\\resources\\userTestTemplate.txt";
    public static final String archivoUsuariosTemporalTest = "src\\test\\resources\\tempTest.txt";

    // Jugadores
    public static final String jugadorTestExistente = "playerTest060782";
    public static final String jugadorTest = "playerTest220694";
    public static final String jugadorTestParaCrear = "playerTest200995";
    public static final String jugadorTestParaModificar = "playerTest251289";
    public static final String jugadorTestParaReiniciar = "playerTest090880";
    public static final String jugadorNuevoNombre = "playerTestNewName";
    public static final String jugadorTestNoExistente = "playerNoExistente";
    public static final String jugadorSinFormato = "user_Test";

    // Puntuaciones
    public static final int puntuacionInicial = 50;
    public static final int puntuacionTrasVictoria = 51;
    public static final int puntuacionTrasDerrota = 49;
    public static final int nuevaPuntuacion = 30;

    // Comportamientos
    public static final boolean victoria = true;
    public static final boolean derrota = false;

    // Mensajes de error
    public static final String PLATER_NOT_IN_DB = "El jugador comprobado no se encuentra en la BDD.";
    public static final String PLAYER_ALREADY_IN_DB = "El jugador comprobado sí aparece en la BDD.";
    public static final String SCORE_NOT_MATCH = "La puntuación recibida no coincide con la esperada";

}