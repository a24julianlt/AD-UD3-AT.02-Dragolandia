package com.controller;

import com.model.Monstruo;
import com.model.TipoMonstruo;

public class ControladorMonstruo {
    private final Monstruo monstruo;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorMonstruo() {
        this.monstruo = new Monstruo();
    }

    public void crearMonstruo(String nombre) {
        monstruo.setNombre(nombre);
        monstruo.setVida(18);
        monstruo.setFuerza(4);
        monstruo.setTipo(TipoMonstruo.random());
    }
    

    public Monstruo getMonstruo() {
        return monstruo;
    }

    public int getFuerza() {
        return monstruo.getFuerza();
    }

    public int getVida() {
        return monstruo.getVida();
    }

    public TipoMonstruo getTipo() {
        return monstruo.getTipo();
    }

}
