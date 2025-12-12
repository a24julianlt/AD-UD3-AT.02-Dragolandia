package com.controller;

import com.model.Bosque;
import com.model.TipoMonstruo;

public class ControladorBosque {
    private final Bosque bosque;
    private final ControladorMago contrMago;
    private final ControladorMonstruo contrMonstruo;

    public ControladorBosque() {
        this.bosque = new Bosque();
        this.contrMago = new ControladorMago();
        this.contrMonstruo = new ControladorMonstruo();
    }

    public void crearPartida(String nombreMago, String nombreMons, String nombreBosque) {
        contrMago.crearMago(nombreMago);
        contrMonstruo.crearMonstruo(nombreMons);
        crearBosque(nombreBosque);
    }

    public void crearBosque(String nombre) {
        bosque.setNombre(nombre);
        bosque.setMonstruoJefe(contrMonstruo.getMonstruo());
        bosque.setNivelPeligro(5);
    }

    public TipoMonstruo getTipoMons() {
        return contrMonstruo.getTipo();
    }

    public int getNivelMagia() {
        return contrMago.getNivelMagia();
    }

    public int getVidaMons(){
        return contrMonstruo.getVida();
    }

    public int getVidaMago() {
        return contrMago.getVida();
    }

    public boolean finPartida() {
        if (contrMago.getVida() < 1 || contrMonstruo.getVida() < 1) {
            return false;
        }
        return true;
    }

    public void atacaMago() {
        contrMago.getMago().lanzarhechizo(contrMonstruo.getMonstruo());
    }

    public void atacaMons() {
        contrMonstruo.getMonstruo().atacar(contrMago.getMago());
    }

    public boolean ganaMago() {
        if (contrMago.getVida() < 1) {
            return false;
        }
        return true;
    }
}
