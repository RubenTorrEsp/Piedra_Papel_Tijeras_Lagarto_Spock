package app;

import java.io.*;

import static app.Common.*;
import static resources.Textos.*;

public class User {
   
    public static String nombre;
    public static Integer puntuacion;
    public static String linea;
    static File archivoUsers = new File(archivoUsuarios);
    static File archivoTemp = new File(archivoUsuariosTemporal);

    // Constructor
    public User(String nombreUsuario, File archivo) {
        if(usuarioExiste(nombreUsuario, archivo)) establecerUsuario(nombreUsuario);
        else crearNuevoUsuario(nombreUsuario);
    }
    
    // Método para establecer el usuario
    public void establecerUsuario(String nombreUsuario) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (partes[0].equals(nombreUsuario)) {
                    nombre = partes[0];
                    puntuacion = Integer.parseInt(partes[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método que comprueba si el usuario existe
    public static boolean usuarioExiste(String nombreUsuario, File archivo) {
        boolean usuarioExiste = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((linea = br.readLine()) != null) {
                if(linea.contains(nombreUsuario)) usuarioExiste = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarioExiste;
    }

    // Método que crea un usuario nuevo
    public static void crearNuevoUsuario(String nombreJugadorNuevo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
            bw.newLine();
            bw.write(nombreJugadorNuevo + puntuacionInicial);
            nombre = nombreJugadorNuevo;
            puntuacion = 50;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método que actualiza la puntuación del jugador
    @SuppressWarnings(errorEstatico)
    public void actualizarPuntuacion(User jugador, Boolean victoria) {
        if (victoria) jugador.puntuacion++;
        else jugador.puntuacion--;
        System.out.println(nuevaPuntuacion+jugador.puntuacion);
        if (jugador.puntuacion == 0) eliminarJugador(jugador.nombre, archivoUsers, archivoTemp);
    }

    // Método que actualiza la puntuación del jugador en el archivo
    // TODO: Comprobar nombre de usuarios, mayusculas estricto
    public static void reescribirPuntuacion(
            String nombreUsuario,
            int nuevaPuntuacion,
            File archivoOriginal,
            File archivoTemporal) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(nombreUsuario)) linea = nombreUsuario+separadorUsuarios+nuevaPuntuacion;
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reescribirArchivoJugadores(archivoOriginal, archivoTemporal);
    }

    // Método que elimina el usuario si la puntuacion llega a 0
    public static void eliminarJugador(
            String nombreUsuario,
            File archivoOriginal,
            File archivoTemporal) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(nombreUsuario)) linea = lineaVacia;
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        reescribirArchivoJugadores(archivoOriginal, archivoTemporal, usuarioEliminado);
        System.exit(0);
    }
    
}