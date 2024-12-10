/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import excepciones.GestorErrores;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Willian
 */
public class Tablero implements Serializable{
    private static final long serialVersionUID = 1L; // Versión para serialización

    // Atributos del tablero
    private final int filas;        // Número de filas del tablero
    private final int columnas;    // Número de columnas del tablero
    private final int minas;       // Número total de minas en el tablero
    private Celda[][] celdas;      // Matriz de celdas que representan el tablero

    /**
     * Constructor para inicializar un nuevo tablero con las dimensiones y cantidad de minas especificadas.
     *
     * @param filas    Número de filas del tablero.
     * @param columnas Número de columnas del tablero.
     * @param minas    Número total de minas en el tablero.
     */
    public Tablero(int filas, int columnas, int minas) {
        this.filas = filas;
        this.columnas = columnas;
        this.minas = minas;
        inicializarTablero(); // Inicializar la matriz de celdas
        colocarMinas();       // Colocar minas aleatoriamente en el tablero
        calcularMinasAdyacentes(); // Calcular las minas adyacentes a cada celda
    }

    /**
     * Inicializa la matriz de celdas del tablero, creando una nueva Celda para cada posición.
     */
    private void inicializarTablero() {
        celdas = new Celda[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda(); // Crear una nueva celda vacía
            }
        }
    }

    /**
     * Coloca minas aleatoriamente en el tablero.
     * Se asegura de que no haya dos minas en la misma celda.
     */
    private void colocarMinas() {
        Random random = new Random();
        int minasColocadas = 0;
        while (minasColocadas < minas) {
            int fila = random.nextInt(filas);       // Generar índice aleatorio para fila
            int columna = random.nextInt(columnas); // Generar índice aleatorio para columna
            if (!celdas[fila][columna].esMina()) {  // Verificar que no haya una mina ya en la celda
                celdas[fila][columna].setEsMina(true); // Colocar mina
                minasColocadas++; // Incrementar el contador de minas colocadas
            }
        }
    }

    /**
     * Calcula el número de minas adyacentes para cada celda del tablero.
     */
    private void calcularMinasAdyacentes() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!celdas[i][j].esMina()) { // Si la celda no tiene mina
                    celdas[i][j].setMinasAdyacentes(contarMinasAdyacentes(i, j)); // Calcular minas adyacentes
                }
            }
        }
    }

    /**
     * Cuenta el número de minas adyacentes a una celda específica.
     */
    private int contarMinasAdyacentes(int fila, int columna) {
        int conteo = 0;
        // Recorrer todas las celdas adyacentes (incluyendo diagonales)
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaFila = fila + i;       // Índice de la fila adyacente
                int nuevaColumna = columna + j; // Índice de la columna adyacente
                // Verificar que la celda adyacente esté dentro de los límites del tablero
                if (nuevaFila >= 0 && nuevaFila < filas && nuevaColumna >= 0 && nuevaColumna < columnas) {
                    if (celdas[nuevaFila][nuevaColumna].esMina()) { // Si la celda adyacente tiene una mina
                        conteo++; // Incrementar el conteo
                    }
                }
            }
        }
        return conteo; // Retornar el número total de minas adyacentes
    }

    public void guardarEnArchivoBinario(String rutaArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(this); // Guardar el objeto actual (Tablero)
            System.out.println("Juego guardado exitosamente en " + rutaArchivo);
        } catch (IOException e) {
            GestorErrores.manejarExcepcion(e); // Manejar cualquier error de entrada/salida
        }
    }

    /**
     * Carga un tablero desde un archivo binario.
     */
    public static Tablero cargarDesdeArchivoBinario(String rutaArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (Tablero) ois.readObject(); // Leer y devolver el objeto guardado
        } catch (IOException | ClassNotFoundException e) {
            GestorErrores.manejarExcepcion(e); // Manejar errores al cargar el archivo
            return null;
        }
    }

    // Métodos Getters para acceder a las propiedades del tablero
    public Celda[][] getCeldas() {
        return celdas;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
