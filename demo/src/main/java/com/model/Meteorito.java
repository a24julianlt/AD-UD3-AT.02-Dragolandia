package com.model;

import java.util.List;

public class Meteorito implements Hechizo {

    @Override
    public void efecto(List<Monstruo> monstruos) {
        for (Monstruo mons : monstruos) {
            mons.setVida(0);
        }
    }
}
