package com.model;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class BolaDeFuego extends Hechizo {

    @Override
    public void efecto(List<Monstruo> monstruos) {
        for (Monstruo mons : monstruos) {
            int daño = (int) Math.random() * 10;

            mons.setVida(mons.getVida() - daño);
        }
    }

}
