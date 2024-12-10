/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.buscaminasconsola;

import controlador.Controlador;
import excepciones.GestorErrores;
import java.util.Scanner;

/**
 *
 * @author Willian
 */
public class BuscaminasConsola {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controlador controlador;

        System.out.println("¿Desea iniciar un nuevo juego o cargar uno existente? (nuevo/cargar):");
        String opcion = scanner.next();

        try {
            if (opcion.equals("nuevo")) {
                controlador = new Controlador(10, 10, 10); // Tablero de 10x10 con 10 minas
            } else {
                System.out.println("Ingrese el nombre del archivo a cargar:");
                String rutaArchivo = scanner.next();
                controlador = new Controlador(rutaArchivo);
            }

            controlador.iniciarJuego();
        } catch (Exception e) {
            GestorErrores.manejarExcepcion(e);
        }

        scanner.close();
    
    }
}
