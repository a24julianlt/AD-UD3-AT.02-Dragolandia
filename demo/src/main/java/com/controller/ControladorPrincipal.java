package com.controller;

import java.util.List;

import com.model.*;

public class ControladorPrincipal {
    private final ControladorMago contrMago;
    private final ControladorMonstruo contrMonstruo;
    private final ControladorBosque contrBosque;
    private final ControladorDragon contrDragon;
    private final ControladorHechizos contrHechizos;

    public ControladorPrincipal() {
        this.contrBosque = new ControladorBosque();
        this.contrMago = new ControladorMago();
        this.contrMonstruo = new ControladorMonstruo();
        this.contrDragon = new ControladorDragon();
        this.contrHechizos = new ControladorHechizos();
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

    public List<Monstruo> getListMonstruos() {
        return contrBosque.getMonstruos();
    }

    public String getHechizo(String hechizo) {
        String devolver = "";
        switch (hechizo.toLowerCase()) {
            case "bola de fuego":
                devolver = contrHechizos.getNombreHechizo(0);
                break;
            case "bola de nieve":
                devolver = contrHechizos.getNombreHechizo(1);
                break;
            case "rayo":
                devolver = contrHechizos.getNombreHechizo(2);
                break;
            case "meteorito":
                devolver = contrHechizos.getNombreHechizo(3);
                break;
            default:
                break;
        }

        return devolver;
    }

    public void usarHechizo(String hechizo, List<Monstruo> monstruos) {
        switch (hechizo.toLowerCase()) {
            case "bola de fuego":
                contrHechizos.lanzar(0, monstruos);
                break;
            case "bola de nieve":
                contrHechizos.lanzar(1, monstruos);
                break;
            case "rayo":
                contrHechizos.lanzar(2, monstruos);
                break;
            case "meteorito":
                contrHechizos.lanzar(3, monstruos);
                break;
            default:
                break;
        }
    }

    public boolean finPartida() {
        if (contrMago.getVida() < 1 || contrMonstruo.getVida() < 1) {
            return false;
        }
        return true;
    }

    public void atacaMago() {
        contrMago.getMago().lanzarHechizo(contrMonstruo.getMonstruo());
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

    public void setMontruoJefe(Monstruo mons) {
        contrBosque.setMonstruoJefe(mons);
    }

    public Monstruo crearMonstruo(String nombreMonstruo) {
        return contrMonstruo.newMonstruo(nombreMonstruo);
    }

    public Mago crearMago(String nombreMago, List<Integer> conjuros) {
        return contrMago.newMago(nombreMago, conjuros);
    }

    public Dragon crearDragon(String nombreDragon) {
        return contrDragon.crearDragon(nombreDragon, contrBosque.getBosque());
    }

    public void añadirABosque(Monstruo mons) {
        contrBosque.añadirABosque(mons);

        contrBosque.actualizarBosque(contrBosque.getBosque());
    }

    /*
     * BASE DE DATOS
     */
    public void gardarMago(String nombreMago, List<Integer> conjuros) {
        contrMago.gardarMago(contrMago.newMago(nombreMago, conjuros));
    }

    public void gardarMonstruo(String nombreMonstruo) {
        contrMonstruo.gardarMonstruo(contrMonstruo.newMonstruo(nombreMonstruo));
    }

    public void gardarListaMonstruo(List<Monstruo> monstruos) {
        for (Monstruo m : monstruos)
            contrMonstruo.gardarMonstruo(m);
    }

    public void gardarBosque(String nombreBosque) {
        contrBosque.crearBosque(nombreBosque, null, getListMonstruos());

        contrBosque.gardarBosque(contrBosque.getBosque());
    }

    public void gardarDragon() {
        contrDragon.gardarDragon(contrDragon.getDragon());
    }

    public void gardarHechizos() {
        contrHechizos.gardarHechizos();
    }

    public void eliminarMago() {
        contrMago.eliminarMago(contrMago.getMago());
    }

    public void eliminarMonstruo() {
        contrMonstruo.eliminarMonstruo(contrMonstruo.getMonstruo());
    }

    public void eliminarListaMonstruo(List<Monstruo> monstruos) {
        for (Monstruo m : monstruos)
            contrMonstruo.eliminarMonstruo(m);
    }

    public void eliminarBosque() {
        contrBosque.eliminarBosque(contrBosque.getBosque());
    }

    public void eliminarDragon() {
        contrDragon.eliminarDragon(contrDragon.getDragon());
    }
}
