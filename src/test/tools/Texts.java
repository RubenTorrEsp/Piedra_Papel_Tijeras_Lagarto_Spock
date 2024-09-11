package tools;

public class Texts {

    // Archivos
    public static final String archivoUsuariosTest = "src\\test\\resources\\userTest.txt";
    public final String archivoUsuariosTestTemplate = "src\\test\\resources\\userTestTemplate.txt";
    public static final String archivoUsuariosTemporalTest = "src\\test\\resources\\tempTest.txt";

    // Jugadores
    public final String jugadorTestExistente = "playerTest060782";
    public final String jugadorTest = "playerTest220694";
    public final String jugadorTestParaCrear = "playerTest200995";
    public final String jugadorTestParaModificar = "playerTest251289";
    public final String jugadorTestParaReiniciar = "playerTest090880";
    public final String jugadorNuevoNombre = "playerTestNewName";
    public final String jugadorTestNoExistente = "playerNoExistente";
    public final String jugadorSinFormato = "user_Test";

    // Puntuaciones
    public final int puntuacionInicial = 50;
    public final int puntuacionTrasVictoria = 51;
    public final int puntuacionTrasDerrota = 49;
    public final int nuevaPuntuacion = 30;

    // Comportamientos
    public final boolean VICTORY = true;
    public final boolean DEFEAT = false;

    // Mensajes de error
    public final String PLATER_NOT_IN_DB = "El jugador comprobado no se encuentra en la BDD.";
    public final String PLAYER_ALREADY_IN_DB = "El jugador comprobado sí aparece en la BDD.";
    public final String SCORE_DOES_NOT_MATCH = "La puntuación recibida no coincide con la esperada";

}