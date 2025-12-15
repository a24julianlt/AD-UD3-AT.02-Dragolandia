package com.model;

import java.util.List;
import jakarta.persistence.Entity;

@Entity
public class BolaDeNieve extends Hechizo {

    private String nombre = "Bola de nieve";

    @Override
    public void efecto(List<Monstruo> monstruos) {
        System.out.println("El mago usa " + nombre);
        monstruos.get(0).setVida(0);
    }

}
