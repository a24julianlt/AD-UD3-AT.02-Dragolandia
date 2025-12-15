package com.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "hechizos")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Hechizo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String nombre;

    public Hechizo() {

    }

    public void efecto(List<Monstruo> monstruos){
        
    }

    public String getNombre() {
        return nombre;
    }
}
