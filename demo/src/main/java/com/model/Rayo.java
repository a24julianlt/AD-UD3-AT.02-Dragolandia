package com.model;

import java.util.List;

public class Rayo {

    private String nombre = "Rayo";

    public void efecto(List<Monstruo> monstruos) {
        if (monstruos.size() < 1) {
            System.out.println("El rayo ataca al enemigo");
        } else {
            System.out.println("El rayo solo ataca al primer enemigo");
        }

        monstruos.get(0).setVida(monstruos.get(0).getVida() - 5);
    }
}
