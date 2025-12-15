package com.controller;


import java.util.List;

import com.model.BolaDeFuego;
import com.model.BolaDeNieve;
import com.model.Hechizo;
import com.model.Meteorito;
import com.model.Monstruo;
import com.model.Rayo;

public class ControladorHechizos {
    private final List<Hechizo> hechizos;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorHechizos() {
        this.hechizos = List.of(
            new BolaDeFuego(),
            new BolaDeNieve(),
            new Rayo(),
            new Meteorito()
        );
    }

    public String getNombreHechizo(int indice) {
        return hechizos.get(indice).getNombre();
    }

    public void lanzar(int indice, List<Monstruo> monstruos) {
        hechizos.get(indice).efecto(monstruos);
    }
}
