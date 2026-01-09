package com.model;

import jakarta.persistence.Entity;

@Entity
public class BolaDeNieve extends Hechizo {

    public BolaDeNieve(){
        this.nombre = "Bola de nieve";    
    }

    @Override
    public void efecto(Monstruo monstruo) {
        System.out.println("El mago usa " + nombre);
        monstruo.setVida(0);
    }

}
