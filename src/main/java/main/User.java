package main;

import java.io.*;

import static tools.Textos.*;

public class User {

    private static String archivoUsuarios = "users.txt";
    
    public static String nombre;
    public static Integer puntuacion;
    public String linea;

    // Constructor
    public User(String nombreUsuario){
        if(usuarioExiste(nombreUsuario)) establecerUsuario(nombreUsuario);      
        else crearNuevoUsuario(nombreUsuario);
    }
    
    // Método para establecer el usuario
    public void establecerUsuario(String nombreUsuario) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios));
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
           while ((linea = br.readLine()) != null) {
               // Dividir la línea en usuario y puntuación utilizando el punto y coma como delimitador
               String[] partes = linea.split(";");
               // Comprobar si el usuario existe
               if (partes[0].equals(nombreUsuario)) {
                   // Establecer nombre y puntuacion del usuario
                   nombre = partes[0];
                   puntuacion = Integer.parseInt(partes[1]);
               }
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Método que comprueba si el usuario existe
    public Boolean usuarioExiste(String nombreUsuario){
        Boolean usuarioExiste = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios))) {
            while ((linea = br.readLine()) != null) {
                if(linea.contains(nombreUsuario)) usuarioExiste = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarioExiste;
    }

    // Método que crea un usuario nuevo
    public void crearNuevoUsuario(String usuario){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
            System.out.println(usuarioCreado);
            bw.newLine();
            bw.write(usuario+puntuacionInicial);
            nombre = usuario;
            puntuacion = 50;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método que actualiza la puntuación del jugador
    @SuppressWarnings("static-access")
    public void actualizarPuntuacion(User jugador, Boolean victoria){  
        if (victoria) jugador.puntuacion++;
        else jugador.puntuacion--;
        System.out.println("La nueva puntuacion es "+jugador.puntuacion);               
    }

    //Método que actualiza la puntuación del jugador en el archivo
    public static void reescribirPuntuacion(String nombreUsuario, int nuevaPuntuacion){

        File archivoOriginal = new File(archivoUsuarios);
        File archivoTemporal = new File("temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                // Dividir la línea en nombre y puntuación utilizando el punto y coma como delimitador
                String[] partes = linea.split(";");
                if (partes.length == 2 && partes[0].equals(nombreUsuario)) {
                    // Si se encuentra el usuario, actualizar la puntuación
                    linea = nombreUsuario + ";" + nuevaPuntuacion;
                }
                // Escribir la línea en el archivo temporal
                bw.write(linea);
                // Escribir un salto de línea después de cada línea
                bw.newLine();

                archivoOriginal.delete();
                archivoTemporal.renameTo(archivoOriginal);
            }

            // Renombrar el archivo temporal para reemplazar el archivo original
            if (!archivoOriginal.delete()) {
                throw new IOException("No se pudo eliminar el archivo original.");
            }
            if (!archivoTemporal.renameTo(archivoOriginal)) {
                throw new IOException("No se pudo renombrar el archivo temporal.");
            }
            System.out.println("La puntuación del usuario ha sido actualizada con éxito.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}