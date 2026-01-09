package com.model;

import jakarta.persistence.*;

/**
 * Representa un dragón protector del bosque.
 * Los dragones pueden exhalar fuego contra los monstruos.
 */
@Entity
@Table(name = "dragones")
public class Dragon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int intensidadFuego;
    private int resistencia;

    @OneToOne
    private Bosque bosque;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Dragon() {
    }

    /**
     * Crea un nuevo dragón con intensidad de fuego aleatoria.
     * @param nombre El nombre del dragón
     */
    public Dragon(String nombre) {
        this.nombre = nombre;
        this.intensidadFuego = (int) (Math.random() * 20);
        this.resistencia = 30;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIntensidadFuego() {
        return intensidadFuego;
    }

    public void setIntensidadFuego(int intensidadFuego) {
        this.intensidadFuego = intensidadFuego;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    /**
     * Obtiene la vida del dragón (equivalente a resistencia).
     * @return Los puntos de resistencia
     */
    public int getVida() {
        return resistencia;
    }

    /**
     * Establece la vida del dragón. No puede ser negativa.
     * @param vida Los puntos de vida (mínimo 0)
     */
    public void setVida(int vida) {
        if (vida < 0) {
            this.resistencia = 0;
        } else {
            this.resistencia = vida;
        }
    }

    public Bosque getBosque() {
        return bosque;
    }

    public void setBosque(Bosque bosque) {
        this.bosque = bosque;
    }

    /**
     * Exhala fuego sobre un monstruo, reduciendo su vida.
     * @param monstruo El monstruo objetivo
     */
    public void exhalar(Monstruo monstruo) {
        System.out.println(nombre + " exhala fuego sobre " + monstruo.getNombre());
        monstruo.setVida(monstruo.getVida() - intensidadFuego);
    }

    /**
     * Ataca a un monstruo exhalando fuego.
     * @param monstruo El monstruo objetivo
     */
    public void atacar(Monstruo monstruo) {
        exhalar(monstruo);
    }
}