package com.model;

import java.util.List;
import jakarta.persistence.Entity;

@Entity
public class BolaDeNieve {

    private String nombre = "Bola de nieve";

    public void efecto(List<Monstruo> monstruos) {
        monstruos.get(0).setVida(0);
    }
    
}
