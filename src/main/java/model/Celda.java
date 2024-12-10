/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Willian
 */
public class Celda implements Serializable{
    private static final long serialVersionUID = 1L; // Versión para serialización
    private boolean esMina;
    private boolean estaRevelada;
    private boolean estaMarcada;
    private int minasAdyacentes;

    public Celda() {
        this.esMina = false;
        this.estaRevelada = false;
        this.estaMarcada = false;
        this.minasAdyacentes = 0;
    }

    // Getters y Setters
    public boolean esMina() {
        return esMina;
    }

    public void setEsMina(boolean esMina) {
        this.esMina = esMina;
    }

    public boolean estaRevelada() {
        return estaRevelada;
    }

    public void revelar() {
        this.estaRevelada = true;
    }

    public boolean estaMarcada() {
        return estaMarcada;
    }

    public void alternarMarca() {
        this.estaMarcada = !this.estaMarcada;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }
}
