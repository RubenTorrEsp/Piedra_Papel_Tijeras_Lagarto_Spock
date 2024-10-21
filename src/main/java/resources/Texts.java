package resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Texts {
    
    // Textos de opciones
    public static final String WELLCOME = """
            Bienvenido al juego Piedra, Papel, Tijeras, Lagarto, Spock!
            Elija qué desea indicando el numero:
            1- Juego extendido
            2- Juego clásico
            9- Gestionar usuarios

            0- Salir""";

    public static final String OPTIONS_EXTENDED = """
            Seleccione su opción con nombre o número, o elija salir:
            1 - Piedra
            2 - Papel
            3 - Tijeras
            4 - Lagarto
            5 - Spock

            0 - Salir
            -----------------------------------------------""";

    public static final String OPTIONS_CLASSIC = """
            Seleccione su opción con nombre o número, o elija salir:
            1 - Piedra
            2 - Papel
            3 - Tijeras

            0 - Salir
            -----------------------------------------------""";
    
    public static final String OPTIONS_PLAYER = """
            Indique qué desea realizar:
            1 - Consultar la lista de usuarios
            2 - Crear usuario
            3 - Modificar usuario
            4 - Eliminar usuario
            5 - Reiniciar usuario
            6 - Consultar podium

            0 - Salir""";

    // Textos generales
    public static final String SELECT_PLAYER = "Has elegido ";
    public static final String SELECT_MACHINE = "La máquina ha elegido ";
    public static final String SELECT_INVALID = "Selección no válida.";
    public static final String SELECT_EXIT = "Ha seleccionado salir.";
    public static final String SELECT_REPLAY = "¿Desea volver a jugar? Escriba \"Si\" para jugar otra vez.";
    public static final String REPLAY_YES = "Has elegido volver a jugar. Elija su opción con nombre o número:";
    public static final String REPLAY_NO = "Gracias por jugar a Piedra, Papel, Tijera, Lagarto, Spock.\nEsperamos que haya disfrutado y que vuelva pronto";
    public static final String EMPTY_ROW = "";
    public static final Optional<String> EMPTY_NAME = Optional.empty();


    // Textos de seleccion
    public static final String NUMBER_1 = "1";
    public static final String NUMBER_2 = "2";
    public static final String NUMBER_3 = "3";
    public static final String NUMBER_4 = "4";
    public static final String NUMBER_5 = "5";
    public static final String NUMBER_9 = "9";
    public static final String NUMBER_0 = "0";
    public static final String SELECT_ROCK = "piedra";
    public static final String SELECT_PAPER = "papel";
    public static final String SELECT_SCISSORS = "tijeras";
    public static final String SELECT_WIZARD = "lagarto";
    public static final String SELECT_SPOCK = "spock";
    public static final String SELECT_OUT = "salir";
    public static final String SELECT_YES = "si";
    public static final String OPTION_UNAVAILABLE = "Opcion no disponible en estos momentos";

    // Textos de configuracion
    public static final String SEPARATOR = ";";
    public static final String SCORE_START = ";50";
    public static final String SCORE_NEW = "La nueva puntuacion es ";
    public static final String FILE_USERS = "users.txt";
    public static final String FILE_TEMP = "temp.txt";

    // Textos de gestión de jugadores
    public static final String CHECK_PLAYER = "Escribe tu nombre de usuario y comprobaremos si ya has jugado antes.";
    public static final String PLAYER_ERASED = "Has llegado a 0 puntos y hemos eliminado al jugador. Eres mas malo que un dolor";
    public static void definicionUser(String nombre, Integer puntuacion){
        System.out.println(STR."El jugador \{nombre} está ahora activo con \{puntuacion} puntos.");
    }
    public static void indicarJugadorNoEnBDD(String jugador) {
        System.out.println(STR."El jugador \{jugador} no se encuentra en la base de datos");
    }

        // Clase ControladorUsuarios
        public static final String BACK_TO_CONTROLLER = "¿Desea realizar otra acción? Escriba \"Si\" para volver al controlador";

        // Mostrar jugadores
        public static final String SHOW_PLAYERS = "Aquí se muestran los jugadores disponibles";
        public static void mostrarJugador(String nombre, String puntuacion) {
         System.out.println(STR."Nombre: \{nombre}. Puntuación: \{puntuacion}");
        }
        // Crear jugadores
        public static final String PLAYER_CREATE = "Indique el jugador que desea crear";
        public static void indicarJugadorCreado(String jugadorCreado) {
            System.out.println(STR."El jugador \{jugadorCreado} ha sido añadido a la base de datos");
        }
        public static void indicarJugadorYaEnBDD(String jugadorCreado) {
            System.out.println(STR."El jugador \{jugadorCreado} ya se encuentra en la base de datos");
        }
        // Modificar jugadores
        public static final String PLAYER_MODIFY = "Indique qué jugador quiere modificar";
        public static final String NEW_NAME = "Indique el nuevo nombre para el jugador";
        public static final String PLAYER_NOT_EXIST = "El jugador indicado no existe";
        // Eliminar jugadores
        public static final String PLAYER_DELETE = "Indique qué jugador desea eliminar:";
        public static void indicarJugadorBorrado(String jugadorBorrado) {
            System.out.println(STR."El jugador \{jugadorBorrado} ha sido eliminado de la base de datos");
        }
        // Reiniciar jugadores
        public static final String PLAYER_RESTART = "Indique el jugador que desea reiniciar";
        public static void indicarJugadorReiniciado(String jugadorReiniciado) {
            System.out.println(STR."El jugador \{jugadorReiniciado} vuelve a tener ahora 50 puntos.");
        }

    // Textos de resolución de enfrentamientos
    public static final String VICTORY = "Enhorabuena!! Has ganado, porque ";
    public static final String DEFEAT = "Lo siento!! Has perdido, porque ";
    public static final String TIE = "Empate!! Aprovecha el momento o vuelve a por más.";

    // Mapa de textos de victoria en enfrentamientos
    public static final Map<String, String> STRINGS = new HashMap<>();
    static {
        STRINGS.put("piedra_tijeras", "la piedra ROMPE las tijeras.");
        STRINGS.put("piedra_lagarto", "la piedra APLASTA al lagarto.");
        STRINGS.put("tijeras_papel", "las tijeras CORTAN el papel.");
        STRINGS.put("tijeras_lagarto", "las tijeras DECAPITAN al lagarto.");
        STRINGS.put("papel_piedra", "el papel ENVUELVE la piedra.");
        STRINGS.put("papel_spock", "el papel DESAUTORIZA a Spock.");
        STRINGS.put("lagarto_papel", "el lagarto SE COME el papel.");
        STRINGS.put("lagarto_spock", "el lagarto ENVENENA a Spock.");
        STRINGS.put("spock_piedra", "Spock DESINTEGRA la piedra.");
        STRINGS.put("spock_tijeras", "Spock DESMONTA las tijeras.");
    }

    // Comportamiento para, pasado el nombre del enfrentamiento, obtener el texto
    public static String obtenerTexto(String nombre) {
        return STRINGS.get(nombre);
    }

    // Comportamiento que genera el texto que resuelve el enfrentamiento como victoria
    public static void victoria(String jugador, String maquina){
        System.out.println(VICTORY+obtenerTexto(jugador.concat("_").concat(maquina)));
    }

    // Comportamiento que genera el texto que resuelve el enfrentamiento como derrota
    public static void derrota(String jugador, String maquina){
        System.out.println(DEFEAT+obtenerTexto(maquina.concat("_").concat(jugador)));
    }
}
