package com.model;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class BolaDeFuego extends Hechizo {

    private String nombre = "Bola de fuego";

    public void efecto(List<Monstruo> monstruos) {
        System.out.println("El mago usa " + nombre);
        for (Monstruo mons : monstruos) {
            int daño = (int) Math.random() * 10;

            mons.setVida(mons.getVida() - daño);
        }
    }
}
