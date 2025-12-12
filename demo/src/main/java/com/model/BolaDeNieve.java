package com.model;

import java.util.List;

public class BolaDeNieve implements Hechizo {

    @Override
    public void efecto(List<Monstruo> monstruos) {
        monstruos.get(0).setVida(0);
    }
    
}
