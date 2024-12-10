/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import model.Celda;

/**
 *
 * @author Willian
 */
public class VistaConsola {
   /**
     * Muestra el tablero con filas etiquetadas por letras y columnas por números.
     */
    public void mostrarTablero(Celda[][] celdas) {
        // Imprimir encabezado de números (1-10)
        System.out.print("  ");
        for (int i = 1; i <= celdas[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Imprimir filas etiquetadas con letras (A-H)
        char filaEtiqueta = 'A';
        for (int i = 0; i < celdas.length; i++) {
            System.out.print(filaEtiqueta + " "); // Etiqueta de la fila
            filaEtiqueta++;
            for (int j = 0; j < celdas[i].length; j++) {
                if (celdas[i][j].estaRevelada()) {
                    if (celdas[i][j].esMina()) {
                        System.out.print("X "); // Mostrar mina
                    } else {
                        System.out.print("V "); // Mostrar espacio vacío seleccionado
                    }
                } else {
                    System.out.print(". "); // Celda no revelada
                }
            }
            System.out.println();
        }
    }

    /**
     * Muestra un mensaje genérico al usuario.
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
