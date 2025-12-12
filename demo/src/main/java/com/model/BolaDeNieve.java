package com.model;

import java.util.List;
import jakarta.persistence.Entity;

@Entity
public class BolaDeNieve extends Hechizo {

    @Override
    public void efecto(List<Monstruo> monstruos) {
        monstruos.get(0).setVida(0);
    }
    
}
