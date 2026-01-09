package com.model;

import jakarta.persistence.Entity;

/**
 * Hechizo que lanza un rayo causando daño a un único monstruo.
 */
@Entity
public class Rayo extends Hechizo {

    public Rayo() {
        this.nombre = "Rayo";
    }

    /**
     * Reduce 5 puntos de vida al monstruo objetivo.
     * @param monstruo El monstruo objetivo
     */
    @Override
    public void efecto(Monstruo monstruo) {
        System.out.println("El mago usa " + nombre);
        System.out.println("El rayo ataca al enemigo");
        monstruo.setVida(monstruo.getVida() - 5);
    }
}