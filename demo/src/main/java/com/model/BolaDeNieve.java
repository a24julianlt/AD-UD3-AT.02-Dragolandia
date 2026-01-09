package com.model;

import jakarta.persistence.Entity;

/**
 * Hechizo que congela completamente a un monstruo, dejándolo sin vida.
 */
@Entity
public class BolaDeNieve extends Hechizo {

    public BolaDeNieve(){
        this.nombre = "Bola de nieve";    
    }

    /**
     * Congela al monstruo, reduciendo su vida a 0.
     * @param monstruo El monstruo objetivo
     */
    @Override
    public void efecto(Monstruo monstruo) {
        System.out.println("El mago usa " + nombre);
        monstruo.setVida(0);
    }

}