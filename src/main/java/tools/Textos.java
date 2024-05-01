package tools;

import java.util.HashMap;
import java.util.Map;

public class Textos {
    
    // Textos generales
    public static final String bienvenida = "Bienvenido al juego Piedra, Papel, Tijeras, Lagarto, Spock!";
    public static final String seleccionJuego = "Elija a qué quiere jugar indicando el numero:\n1- Juego extendido\n2- Juego clásico";
    public static final String cabeceraSeleccion = "Seleccione su opción con nombre o número, o elija salir:";
    public static final String opcionesJuegoExtendido = "1 - Piedra\n2 - Papel\n3 - Tijeras\n4 - Lagarto\n5 - Spock\n\n0 - Salir\n-----------------------------------------------";
    public static final String opcionesJuegoClasico = "1 - Piedra\n2 - Papel\n3 - Tijeras\n\n0 - Salir\n-----------------------------------------------";
    public static final String seleccionJugador = "Has elegido ";
    public static final String seleccionMaquina = "La máquina ha elegido ";
    public static final String seleccionInvalida = "Selección no válida.";
    public static final String seleccionSalir = "Ha seleccionado salir.";
    public static final String seleccionRejugar = "¿Desea volver a jugar? Escriba \"Si\" para jugar otra vez.";
    public static final String rejugarAfirmativo = "Has elegido volver a jugar. Elija su opción con nombre o número:";
    public static final String rejugarNegativo = "Gracias por jugar a Piedra, Papel, Tijera, Lagarto, Spock.\nEsperamos que haya disfrutado y que vuelva pronto";

    // Textos de seleccion
    public static final String seleccionPiedraTexto = "piedra";
    public static final String seleccionPiedraNumero = "1";
    public static final String seleccionPapelTexto = "papel";
    public static final String seleccionPapelNumero = "2";
    public static final String seleccionTijerasTexto = "tijeras";
    public static final String seleccionTijerasNumero = "3";
    public static final String seleccionLagartoTexto = "lagarto";
    public static final String seleccionLagartoNumero = "4";
    public static final String seleccionSpockTexto = "spock";
    public static final String seleccionSpockNumero = "5";
    public static final String seleccionSalirTexto = "salir";
    public static final String seleccionSalirNumero = "0";
    public static final String seleccionSI = "si";
    public static final String seleccionJuegoClasico = "2";
    public static final String seleccionJuegoExtendido = "1";

    // Textos de configuracion
    public static final String separadorUsuarios = ";";
    public static final String puntuacionInicial = ";50";
    public static final String nuevaPuntuacion = "La nueva puntuacion es ";
    public static final String archivoUsuarios = "users.txt";
    public static final String archivoUsuariosTemporal = "temp.txt";
    public static final String errorEstatico = "static-access";

    // Textos de usuario
    public static final String comprobarUsuario = "Escribe tu nombre de usuario y comprobaremos si ya has jugado antes.";
    public static final String usuarioEliminado = "Has llegado a 0 puntos y hemos eliminado al jugador. Eres mas malo que un dolor";
    public static final String usuarioCreado = "Usuario creado.";
    public static void definicionUser(String nombre, Integer puntuacion){
        System.out.println("El jugador "+nombre+" está ahora activo con "+puntuacion+" puntos.");
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
