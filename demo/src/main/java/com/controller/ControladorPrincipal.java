package com.controller;

import com.model.TipoMonstruo;

public class ControladorPrincipal {
    private final ControladorMago contrMago;
    private final ControladorMonstruo contrMonstruo;
    private final ControladorBosque contrBosque;
    private final ControladorDragon contrDragon;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorPrincipal() {
        this.contrBosque = new ControladorBosque();
        this.contrMago = new ControladorMago();
        this.contrMonstruo = new ControladorMonstruo();
        this.contrDragon = new ControladorDragon();
    }

    public void crearPartida(String nombreMago, String nombreMons, String nombreBosque, String nombreDragon) {
        contrMago.crearMago(nombreMago);
        contrMonstruo.crearMonstruo(nombreMons);
        contrBosque.crearBosque(nombreBosque, contrMonstruo.getMonstruo());
        contrDragon.crearDragon(nombreDragon, contrBosque.getBosque());
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
