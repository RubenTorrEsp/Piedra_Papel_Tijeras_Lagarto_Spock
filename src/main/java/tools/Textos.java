package tools;

import java.util.HashMap;
import java.util.Map;

public class Textos {
    
    // Textos generales
    public static String bienvenida = "Bienvenido al juego Piedra, Papel, Tijeras, Lagarto, Spock!";
    public static String seleccionJuego = "Elija a qué quiere jugar indicando el numero:\n1- Juego extendido\n2- Juego clásico";
    public static String cabeceraSeleccion = "Seleccione su opción con nombre o número, o elija salir:";
    public static String opcionesJuegoExtendido = "1 - Piedra\n2 - Papel\n3 - Tijeras\n4 - Lagarto\n5 - Spock\n\n0 - Salir\n-----------------------------------------------";
    public static String opcionesJuegoClasico = "1 - Piedra\n2 - Papel\n3 - Tijeras\n\n0 - Salir\n-----------------------------------------------";
    public static String seleccionJugador = "Has elegido ";
    public static String seleccionMaquina = "La máquina ha elegido ";
    public static String seleccionInvalida = "Selección no válida.";
    public static String seleccionSalir = "Ha seleccionado salir.";
    public static String seleccionRejugar = "¿Desea volver a jugar? Escriba \"Si\" para jugar otra vez.";
    public static String rejugarAfirmativo = "Has elegido volver a jugar. Elija su opción con nombre o número:";
    public static String rejugarNegativo = "Gracias por jugar a Piedra, Papel, Tijera, Lagarto, Spock.\nEsperamos que haya disfrutado y que vuelva pronto";

    //Textos de configuracion
    public static String puntuacionInicial = ";50";
    public static String nuevaPuntuacion = "La nueva puntuacion es ";
    public static String archivoUsuarios = "users.txt";
    public static String archivoUsuariosTemporal = "temp.txt";

    //Textos de usuario
    public static String comprobarUsuario = "Escribe tu nombre de usuario y comprobaremos si ya has jugado antes.";
    public static String usuarioCreado = "Usuario creado.";
    public static void definicionUser(String nombre, Integer puntuacion){
        System.out.println("El jugador "+nombre+" está ahora activo con "+puntuacion+" puntos.");
    }

    // Textos de resolución de enfrentamientos
    public static String victoria = "Enhorabuena!! Has ganado, porque ";
    public static String derrota = "Lo siento!! Has perdido, porque ";
    public static String empate = "Empate!! Aprovecha el momento o vuelve a por más.";

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
