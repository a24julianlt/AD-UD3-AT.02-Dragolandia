package com.model;

import jakarta.persistence.Entity;

@Entity
public class Rayo extends Hechizo {

    public Rayo() {
        this.nombre = "Rayo";
    }

    @Override
    public void efecto(Monstruo monstruo) {
        System.out.println("El mago usa " + nombre);

        System.out.println("El rayo ataca al enemigo");

        monstruo.setVida(monstruo.getVida() - 5);
    }
}
