package com.model;

import java.util.List;
import jakarta.persistence.Entity;

@Entity
public class Meteorito {

    private String nombre = "Meteorito";

    public void efecto(List<Monstruo> monstruos) {
        System.out.println("El mago usa " + nombre);
        for (Monstruo mons : monstruos) {
            mons.setVida(0);
        }
    }
}
