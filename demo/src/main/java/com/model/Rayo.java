package com.model;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Rayo extends Hechizo {

    private String nombre = "Rayo";

    @Override
    public void efecto(List<Monstruo> monstruos) {
        System.out.println("El mago usa " + nombre);
        if (monstruos.size() < 1) {
            System.out.println("El rayo ataca al enemigo");
        } else {
            System.out.println("El rayo solo ataca al primer enemigo");
        }

        monstruos.get(0).setVida(monstruos.get(0).getVida() - 5);
    }
}
