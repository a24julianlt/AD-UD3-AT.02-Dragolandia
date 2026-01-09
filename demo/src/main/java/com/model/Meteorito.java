package com.model;

import java.util.List;
import jakarta.persistence.Entity;

/**
 * Hechizo devastador que elimina instantáneamente a todos los monstruos.
 */
@Entity
public class Meteorito extends Hechizo {

    public Meteorito(){
        this.nombre = "Meteorito";
    }

    /**
     * Reduce la vida de todos los monstruos a 0.
     * @param monstruos Lista de monstruos objetivo
     */
    @Override
    public void efecto(List<Monstruo> monstruos) {
        System.out.println("El mago usa " + nombre);
        for (Monstruo mons : monstruos) {
            mons.setVida(0);
        }
    }
}