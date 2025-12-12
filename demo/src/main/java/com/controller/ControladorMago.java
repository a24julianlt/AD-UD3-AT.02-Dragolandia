package com.controller;

import com.model.Mago;

public class ControladorMago {
    private final Mago mago;

    public ControladorMago() {
        this.mago = new Mago();
    }

    public void crearMago(String nombre) {
        mago.setNombre(nombre);
        mago.setVida(20);
        mago.setNivelMagia(5);
    }

    
    public Mago getMago() {
        return mago;
    }

    public int getNivelMagia() {
        return mago.getNivelMagia();
    }

    public int getVida() {
        return mago.getVida();
    }
}
