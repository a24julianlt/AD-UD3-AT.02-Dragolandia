package com.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "bosques")
public class Bosque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int nivelPeligro;

    @OneToOne
    private Monstruo monstruoJefe;

    @OneToMany
    private List<Monstruo> mosntruos;

    public Bosque() {
    }

    public Bosque(String nombre, int nivelPeligro, Monstruo monstruoJefe) {
        this.nombre = nombre;
        this.nivelPeligro = nivelPeligro;
        this.monstruoJefe = monstruoJefe;
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

    public int getNivelPeligro() {
        return nivelPeligro;
    }

    public void setNivelPeligro(int nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }

    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

    
    public void cambiarJefe(Monstruo monstruoNuevo) {
        this.monstruoJefe = monstruoNuevo;
    }


    @Override
    public String toString() {
        return "Bosque [id=" + id + ", nombre=" + nombre + ", nivel de Peligro=" + nivelPeligro + ", monstruo Jefe=" + monstruoJefe + "]";
    }

}