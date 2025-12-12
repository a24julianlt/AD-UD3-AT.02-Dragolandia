package com.model;

import java.util.List;
import jakarta.persistence.Entity;

@Entity
public class Meteorito extends Hechizo {

    @Override
    public void efecto(List<Monstruo> monstruos) {
        for (Monstruo mons : monstruos) {
            mons.setVida(0);
        }
    }
}
