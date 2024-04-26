package main;

import java.io.*;

import static tools.Textos.*;

public class User {
   
    public static String nombre;
    public static Integer puntuacion;
    public String linea;
    static File archivoOriginal = new File(archivoUsuarios);
    static File archivoTemporal = new File(archivoUsuariosTemporal);

    // Constructor
    public User(String nombreUsuario){
        if(usuarioExiste(nombreUsuario)) establecerUsuario(nombreUsuario);      
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
    public Boolean usuarioExiste(String nombreUsuario) {
        Boolean usuarioExiste = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            while ((linea = br.readLine()) != null) {
                if(linea.contains(nombreUsuario)) usuarioExiste = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarioExiste;
    }

    // Método que crea un usuario nuevo
    public void crearNuevoUsuario(String nombreJugadorNuevo) {
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
    public void actualizarPuntuacion(User jugador, Boolean victoria){  
        if (victoria) jugador.puntuacion++;
        else jugador.puntuacion--;
        System.out.println(nuevaPuntuacion+jugador.puntuacion);               
    }

    // Método que actualiza la puntuación del jugador en el archivo
    public static void reescribirPuntuacion(String nombreUsuario, int nuevaPuntuacion) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(nombreUsuario) && !linea.trim().isEmpty()) {
                        linea = nombreUsuario + separadorUsuarios + nuevaPuntuacion;
                    }
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        archivoOriginal.delete();
        archivoTemporal.renameTo(archivoOriginal);
    }

    // Método que elimina el usuario si la puntuacion llega a 0
    public static void eliminarJugador(String nombreUsuario) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(separadorUsuarios);
                if (!linea.trim().isEmpty()) {
                    if (partes.length == 2 && partes[0].equals(nombreUsuario) && !linea.trim().isEmpty()) {
                        linea = "";
                    }
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        archivoOriginal.delete();
        archivoTemporal.renameTo(archivoOriginal);
    }
    
}