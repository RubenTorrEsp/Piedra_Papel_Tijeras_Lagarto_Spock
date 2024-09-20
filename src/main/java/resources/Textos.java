package resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Textos {
    
    // Textos de opciones
    public static final String WELLCOME = """
            Bienvenido al juego Piedra, Papel, Tijeras, Lagarto, Spock!
            Elija qué desea indicando el numero:
            1- Juego extendido
            2- Juego clásico
            9- Gestionar usuarios

            0- Salir""";

    public static final String opcionesJuegoExtendido = """
            Seleccione su opción con nombre o número, o elija salir:
            1 - Piedra
            2 - Papel
            3 - Tijeras
            4 - Lagarto
            5 - Spock

            0 - Salir
            -----------------------------------------------""";

    public static final String opcionesJuegoClasico = """
            Seleccione su opción con nombre o número, o elija salir:
            1 - Piedra
            2 - Papel
            3 - Tijeras

            0 - Salir
            -----------------------------------------------""";
    
    public static final String opcionesGestorJugadores = """
            Indique qué desea realizar:
            1 - Consultar la lista de usuarios
            2 - Crear usuario
            3 - Modificar usuario
            4 - Eliminar usuario
            5 - Reiniciar usuario

            0 - Salir""";

    // Textos generales
    public static final String seleccionJugador = "Has elegido ";
    public static final String seleccionMaquina = "La máquina ha elegido ";
    public static final String seleccionInvalida = "Selección no válida.";
    public static final String seleccionadoSalir = "Ha seleccionado salir.";
    public static final String seleccionRejugar = "¿Desea volver a jugar? Escriba \"Si\" para jugar otra vez.";
    public static final String rejugarAfirmativo = "Has elegido volver a jugar. Elija su opción con nombre o número:";
    public static final String rejugarNegativo = "Gracias por jugar a Piedra, Papel, Tijera, Lagarto, Spock.\nEsperamos que haya disfrutado y que vuelva pronto";
    public static final String lineaVacia = "";
    public static final Optional<String> nombreVacio = Optional.empty();


    // Textos de seleccion
    public static final String NUUMBER_1 = "1";
    public static final String NUUMBER_2 = "2";
    public static final String NUUMBER_3 = "3";
    public static final String NUUMBER_4 = "4";
    public static final String NUUMBER_5 = "5";
    public static final String NUUMBER_9 = "9";
    public static final String NUUMBER_0 = "0";
    public static final String seleccionPiedra = "piedra";
    public static final String seleccionPapel = "papel";
    public static final String seleccionTijeras = "tijeras";
    public static final String seleccionLagarto = "lagarto";
    public static final String seleccionSpock = "spock";
    public static final String seleccionSalir = "salir";
    public static final String seleccionSI = "si";
    public static final String opcionNoDisponible = "Opcion no disponible en estos momentos";

    // Textos de configuracion
    public static final String separadorUsuarios = ";";
    public static final String puntuacionInicial = ";50";
    public static final String nuevaPuntuacion = "La nueva puntuacion es ";
    public static final String archivoUsuarios = "users.txt";
    public static final String archivoUsuariosTemporal = "temp.txt";

    // Textos de gestión de jugadores
    public static final String CHECK_PLAYER = "Escribe tu nombre de usuario y comprobaremos si ya has jugado antes.";
    public static final String usuarioEliminado = "Has llegado a 0 puntos y hemos eliminado al jugador. Eres mas malo que un dolor";
    public static void definicionUser(String nombre, Integer puntuacion){
        System.out.println(STR."El jugador \{nombre} está ahora activo con \{puntuacion} puntos.");
    }
    public static void indicarJugadorNoEnBDD(String jugador) {
        System.out.println(STR."El jugador \{jugador} no se encuentra en la base de datos");
    }

        // Clase ControladorUsuarios
        // Mostrar jugadores
        public static final String mostrarJugadores = "Aquí se muestran los jugadores disponibles";
        public static void mostrarJugador(String nombre, String puntuacion) {
         System.out.println(STR."Nombre: \{nombre}. Puntuación: \{puntuacion}");
        }
        // Crear jugadores
        public static final String jugadorParaCrear = "Indique el jugador que desea crear";
        public static void indicarJugadorCreado(String jugadorCreado) {
            System.out.println(STR."El jugador \{jugadorCreado} ha sido añadido a la base de datos");
        }
        public static void indicarJugadorYaEnBDD(String jugadorCreado) {
            System.out.println(STR."El jugador \{jugadorCreado} ya se encuentra en la base de datos");
        }
        // Modificar jugadores
        public static final String modificarJugador = "Indique qué jugador quiere modificar";
        public static final String nombreNuevo = "Indique el nuevo nombre para el jugador";
        public static final String jugadorNoExiste = "El jugador indicado no existe";
        // Eliminar jugadores
        public static final String eliminarJugador = "Indique qué jugador desea eliminar:";
        public static void indicarJugadorBorrado(String jugadorBorrado) {
            System.out.println(STR."El jugador \{jugadorBorrado} ha sido eliminado de la base de datos");
        }
        // Reiniciar jugadores
        public static final String reiniciarJugador = "Indique el jugador que desea reiniciar";
        public static void indicarJugadorReiniciado(String jugadorReiniciado) {
            System.out.println(STR."El jugador \{jugadorReiniciado} vuelve a tener ahora 50 puntos.");
        }

    // Textos de resolución de enfrentamientos
    public static final String victoria = "Enhorabuena!! Has ganado, porque ";
    public static final String derrota = "Lo siento!! Has perdido, porque ";
    public static final String empate = "Empate!! Aprovecha el momento o vuelve a por más.";

    // Mapa de textos de victoria en enfrentamientos
    public static final Map<String, String> strings = new HashMap<>();
    static {
        strings.put("piedra_tijeras", "la piedra ROMPE las tijeras.");
        strings.put("piedra_lagarto", "la piedra APLASTA al lagarto.");
        strings.put("tijeras_papel", "las tijeras CORTAN el papel.");
        strings.put("tijeras_lagarto", "las tijeras DECAPITAN al lagarto.");
        strings.put("papel_piedra", "el papel ENVUELVE la piedra.");
        strings.put("papel_spock", "el papel DESAUTORIZA a Spock.");
        strings.put("lagarto_papel", "el lagarto SE COME el papel.");
        strings.put("lagarto_spock", "el lagarto ENVENENA a Spock.");
        strings.put("spock_piedra", "Spock DESINTEGRA la piedra.");
        strings.put("spock_tijeras", "Spock DESMONTA las tijeras.");
    }

    // Comportamiento para, pasado el nombre del enfrentamiento, obtener el texto
    public static String obtenerTexto(String nombre) {
        return strings.get(nombre);
    }

    // Comportamiento que genera el texto que resuelve el enfrentamiento como victoria
    public static void victoria(String jugador, String maquina){
        System.out.println(victoria+obtenerTexto(jugador.concat("_").concat(maquina)));
    }

    // Comportamiento que genera el texto que resuelve el enfrentamiento como derrota
    public static void derrota(String jugador, String maquina){
        System.out.println(derrota+obtenerTexto(maquina.concat("_").concat(jugador)));
    }
}
