import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Celda;
import model.Tablero;

public class TableroTest {

	private Tablero tablero;

    /**
     * Configuración inicial antes de cada prueba.
     * Se crea un tablero de 5x5 con 5 mi	nas para las pruebas.
     */
    @BeforeEach
    public void setUp() {
        tablero = new Tablero(5, 5, 5);
    }

    /**
     * Prueba para verificar que el tablero tiene las dimensiones correctas.
     */
    @Test
    public void testDimensionesTablero() {
        assertEquals(5, tablero.getFilas(), "El número de filas debería ser 5");
        assertEquals(5, tablero.getColumnas(), "El número de columnas debería ser 5");
    }

    /**
     * Prueba para verificar que el tablero contiene la cantidad correcta de minas.
     */
    @Test
    public void testCantidadDeMinas() {
        int minasContadas = contarMinas();
        assertEquals(5, minasContadas, "El tablero debería tener 5 minas");
    }

    /**
     * Prueba para verificar que las minas se colocan en posiciones válidas.
     */
    @Test
    public void testPosicionesValidasDeMinas() {
        Celda[][] celdas = tablero.getCeldas();
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                assertNotNull(celdas[i][j], "Cada celda debería estar inicializada");
            }
        }
    }

    /**
     * Prueba para verificar el cálculo correcto de las minas adyacentes.
     */
    @Test
    public void testCalculoMinasAdyacentes() {
        // Este test valida que el número de minas adyacentes sea correcto.
        Celda[][] celdas = tablero.getCeldas();
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if (!celdas[i][j].esMina()) {
                    int minasCalculadas = contarMinasAdyacentes(i, j);
                    assertEquals(minasCalculadas, celdas[i][j].getMinasAdyacentes(),
                            "El cálculo de minas adyacentes es incorrecto en (" + i + "," + j + ")");
                }
            }
        }
    }

    /**
     * Prueba para guardar y cargar el tablero desde un archivo binario.
     */
    @Test
    public void testGuardarYCargarTablero() {
        String rutaArchivo = "tablero_test.dat";
        tablero.guardarEnArchivoBinario(rutaArchivo);
        Tablero tableroCargado = Tablero.cargarDesdeArchivoBinario(rutaArchivo);

        assertNotNull(tableroCargado, "El tablero cargado no debería ser null");
        assertEquals(tablero.getFilas(), tableroCargado.getFilas(), "El número de filas debería coincidir");
        assertEquals(tablero.getColumnas(), tableroCargado.getColumnas(), "El número de columnas debería coincidir");
        assertEquals(contarMinas(), contarMinasEnTablero(tableroCargado), "La cantidad de minas debería coincidir");
    }

    // Métodos auxiliares para los tests

    private int contarMinas() {
        int conteo = 0;
        Celda[][] celdas = tablero.getCeldas();
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if (celdas[i][j].esMina()) {
                    conteo++;
                }
            }
        }
        return conteo;
    }

    /**
     * Cuenta el número de minas adyacentes a una celda específica.
     */
    private int contarMinasAdyacentes(int fila, int columna) {
        int conteo = 0;
        Celda[][] celdas = tablero.getCeldas();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;
                if (nuevaFila >= 0 && nuevaFila < tablero.getFilas() &&
                        nuevaColumna >= 0 && nuevaColumna < tablero.getColumnas() &&
                        celdas[nuevaFila][nuevaColumna].esMina()) {
                    conteo++;
                }
            }
        }
        return conteo;
    }

    /**
     * Cuenta el número total de minas en un tablero dado.
     */
    private int contarMinasEnTablero(Tablero tablero) {
        int conteo = 0;
        Celda[][] celdas = tablero.getCeldas();
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if (celdas[i][j].esMina()) {
                    conteo++;
                }
            }
        }
        return conteo;
    }
}
