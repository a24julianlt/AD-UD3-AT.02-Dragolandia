package com.model;

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

    public String getNombre() {
        return nombre;
    }
}
