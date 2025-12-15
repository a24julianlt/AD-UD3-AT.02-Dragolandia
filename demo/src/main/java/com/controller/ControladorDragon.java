package com.controller;

import com.model.Bosque;
import com.model.Dragon;

public class ControladorDragon {
    private final Dragon dragon;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorDragon() {
        this.dragon = new Dragon();
    }

    public void crearDragon(String nombre, Bosque bosque) {
        dragon.setNombre(nombre);
        dragon.setIntensidadFuego((int) Math.random() * 20);
        dragon.setResistencia(30);
        dragon.setBosque(bosque);
    }
}
