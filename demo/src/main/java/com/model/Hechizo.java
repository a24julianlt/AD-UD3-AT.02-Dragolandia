package com.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="hechizos")
public abstract class Hechizo {

    public void efecto(List<Monstruo> monstruos){}
    
}
