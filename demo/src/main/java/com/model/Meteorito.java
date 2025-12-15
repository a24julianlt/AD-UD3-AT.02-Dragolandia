package com.model;

import java.util.List;
import jakarta.persistence.Entity;

@Entity
public class Meteorito extends Hechizo {

    private String nombre = "Meteorito";

    @Override
    public void efecto(List<Monstruo> monstruos) {
        System.out.println("El mago usa " + nombre);
        for (Monstruo mons : monstruos) {
            mons.setVida(0);
        }
    }
}
