package com.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "magos")
public class Mago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int vida;
    private int nivelMagia;
    private List<Hechizo> conjuros;

    public Mago() {
    }

    public Mago(String nombre) {
        this.nombre = nombre;
        this.vida = 20;
        this.nivelMagia = 5;
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

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 1) {
            this.vida = 0;
        } else {
            this.vida = vida;
        }
    }

    public int getNivelMagia() {
        return nivelMagia;
    }

    public void setNivelMagia(int nivelMagia) {
        this.nivelMagia = nivelMagia;
    }

    public void lanzarhechizo(Monstruo monstruo) {
        int nuevaVida = monstruo.getVida() - this.getNivelMagia();

        monstruo.setVida(nuevaVida);
    }

    @Override
    public String toString() {
        return "Mago [id=" + id + ", nombre=" + nombre + ", vida=" + vida + ", nivelMagia=" + nivelMagia + "]";
    }
}
