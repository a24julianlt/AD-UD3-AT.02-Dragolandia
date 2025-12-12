package com.controller;

import com.model.Bosque;
import com.model.Monstruo;

public class ControladorBosque {
    private final Bosque bosque;


    public ControladorBosque() {
        this.bosque = new Bosque();
    }

    

    public void crearBosque(String nombre, Monstruo monstruo) {
        bosque.setNombre(nombre);
        bosque.setMonstruoJefe(monstruo);
        bosque.setNivelPeligro(5);
    }

    public String getNombre() {
        return bosque.getNombre();
    }

    public int getNivelPeligro() {
        return bosque.getNivelPeligro();
    }

    public Monstruo getMontruoJefe() {
        return bosque.getMonstruoJefe();
    }
}
