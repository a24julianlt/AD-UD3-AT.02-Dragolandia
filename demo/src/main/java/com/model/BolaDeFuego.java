package com.model;

import java.util.List;

import jakarta.persistence.Entity;

/**
 * Hechizo que lanza una bola de fuego causando daño aleatorio a todos los monstruos.
 */
@Entity
public class BolaDeFuego extends Hechizo {

    public BolaDeFuego() {
        this.nombre = "Bola de fuego";
    }

    /**
     * Aplica daño aleatorio (0-9 puntos) a todos los monstruos.
     * @param monstruos Lista de monstruos objetivo
     */
    @Override
    public void efecto(List<Monstruo> monstruos) {
        System.out.println("El mago usa " + nombre);
        for (Monstruo mons : monstruos) {
            int daño = (int) (Math.random() * 10);
            mons.setVida(mons.getVida() - daño);
        }
    }
}