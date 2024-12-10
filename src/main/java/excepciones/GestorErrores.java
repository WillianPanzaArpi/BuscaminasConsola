/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Willian
 */
public class GestorErrores {
    /**
     * Muestra un mensaje de error estándar.
     */
    public static void mostrarError(String mensaje) {
        System.err.println("Error: " + mensaje);
    }

    /**
     * Maneja una excepción y muestra su información.
     */
    public static void manejarExcepcion(Exception e) {
        System.err.println("Ocurrió un error: " + e.getMessage());
        e.printStackTrace();
    }

    /**
     * Muestra una advertencia al usuario.
     */
    public static void advertencia(String mensaje) {
        System.out.println("Advertencia: " + mensaje);
    }
}
