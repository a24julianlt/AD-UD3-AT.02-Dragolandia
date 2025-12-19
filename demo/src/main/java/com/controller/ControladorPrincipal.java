package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.model.Monstruo;
import com.model.TipoMonstruo;

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

    public void crearPartida(String nombreMago, List<Integer> hechizos, String nombreMons, List<String> monstruos,
            String nombreBosque, String nombreDragon) {
        contrMago.crearMago(nombreMago, hechizos);
        contrMonstruo.crearMonstruo(nombreMons);
        List<Monstruo> monstruosBosque = new ArrayList<>();
        for (String m : monstruos) {
            monstruosBosque.add(contrMonstruo.newMonstruo(m));
        }
        contrBosque.crearBosque(nombreBosque, contrMonstruo.getMonstruo(), monstruosBosque);
        contrDragon.crearDragon(nombreDragon, contrBosque.getBosque());
        // Los hechizos son siempre los mismo, por eso ya se crean con el inicializador
        // entonces los guardamos automáticamente
        contrHechizos.gardarHechizos();
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

    /*
     * BASE DE DATOS
     */
    public void gardarMago() {
        contrMago.gardarMago(contrMago.getMago());
    }

    public void gardarMonstruo() {
        contrMonstruo.gardarMonstruo(contrMonstruo.getMonstruo());
    }

    public void gardarListaMonstruo(List<Monstruo> monstruos) {
        for (Monstruo m : monstruos) contrMonstruo.gardarMonstruo(m);
    }

    public void gardarBosque() {
        contrBosque.gardarBosque(contrBosque.getBosque());
    }

    public void gardarDragon() {
        contrDragon.gardarDragon(contrDragon.getDragon());
    }

    public void eliminarMago() {
        contrMago.eliminarMago(contrMago.getMago());
    }

    public void eliminarMonstruo() {
        contrMonstruo.eliminarMonstruo(contrMonstruo.getMonstruo());
    }

    public void eliminarListaMonstruo(List<Monstruo> monstruos) {
        for (Monstruo m : monstruos) contrMonstruo.eliminarMonstruo(m);
    }

    public void eliminarBosque() {
        contrBosque.eliminarBosque(contrBosque.getBosque());
    }

    public void eliminarDragon() {
        contrDragon.eliminarDragon(contrDragon.getDragon());
    }
}
