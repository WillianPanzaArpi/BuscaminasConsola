/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import excepciones.GestorErrores;
import java.io.IOException;
import java.util.Scanner;
import model.Tablero;
import vista.VistaConsola;

/**
 *
 * @author Willian
 */
public class Controlador {
    private Tablero tablero;
    private final VistaConsola vista;

    public Controlador(int filas, int columnas, int minas) {
        this.tablero = new Tablero(filas, columnas, minas);
        this.vista = new VistaConsola();
    }

    public Controlador(String rutaArchivo) {
        this.tablero = Tablero.cargarDesdeArchivoBinario(rutaArchivo);
        this.vista = new VistaConsola();
    }

    /**
     * Método principal que ejecuta el ciclo del juego.
     */
    public void iniciarJuego() {
        Scanner scanner = new Scanner(System.in);
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            vista.mostrarTablero(tablero.getCeldas());
            vista.mostrarMensaje("Ingrese su acción (revelar/marcar/guardar/cargar) y posición (ejemplo: revelar A5):");
            String accion = scanner.next();

            if (accion.equals("guardar")) {
                vista.mostrarMensaje("Ingrese el nombre del archivo para guardar:");
                String rutaArchivo = scanner.next();
                tablero.guardarEnArchivoBinario(rutaArchivo);
                continue;
            }

            if (accion.equals("cargar")) {
                vista.mostrarMensaje("Ingrese el nombre del archivo para cargar:");
                String rutaArchivo = scanner.next();
                Tablero nuevoTablero = Tablero.cargarDesdeArchivoBinario(rutaArchivo);
                if (nuevoTablero != null) {
                    this.tablero = nuevoTablero;
                }
                continue;
            }

            String posicion = scanner.next();
            try {
                int fila = posicion.charAt(0) - 'A'; // Convertir 'A'-'H' a índices 0-7
                int columna = Integer.parseInt(posicion.substring(1)) - 1; // Convertir '1'-'10' a índices 0-9

                if (accion.equals("revelar")) {
                    if (tablero.getCeldas()[fila][columna].esMina()) {
                        vista.mostrarMensaje("¡BOOM! Has perdido.");
                        juegoTerminado = true;
                    } else {
                        tablero.getCeldas()[fila][columna].revelar();
                    }
                } else if (accion.equals("marcar")) {
                    tablero.getCeldas()[fila][columna].alternarMarca();
                }
            } catch (Exception e) {
                GestorErrores.manejarExcepcion(e);
            }
        }

        scanner.close();
    }
}
