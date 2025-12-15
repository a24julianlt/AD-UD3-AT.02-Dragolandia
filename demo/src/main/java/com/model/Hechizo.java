package com.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "hechizos")
public abstract class Hechizo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    public Hechizo() {

    }

    public void efecto(List<Monstruo> monstruos){
        
    }

    public String getNombre() {
        return nombre;
    }
}
