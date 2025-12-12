package com.controller;

import com.model.TipoMonstruo;

public class ControladorPrincipal {
    private final ControladorMago contrMago;
    private final ControladorMonstruo contrMonstruo;
    private final ControladorBosque contrBosque;

    public ControladorPrincipal() {
        this.contrBosque = new ControladorBosque();
        this.contrMago = new ControladorMago();
        this.contrMonstruo = new ControladorMonstruo();
    }

    public void crearPartida(String nombreMago, String nombreMons, String nombreBosque) {
        contrMago.crearMago(nombreMago);
        contrMonstruo.crearMonstruo(nombreMons);
        contrBosque.crearBosque(nombreBosque, contrMonstruo.getMonstruo());
    }

    public int getVidaMago() {
        return contrMago.getVida();
    }

    public int getNivelMagia() {
        return contrMago.getNivelMagia();
    }

    public int getVidaMons() {
        return contrMonstruo.getVida();
    }

    public TipoMonstruo getTipoMons() {
        return contrMonstruo.getTipo();
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
