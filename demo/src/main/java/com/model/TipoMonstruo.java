package com.model;

import jakarta.persistence.Entity;

@Entity
public enum TipoMonstruo {
    ogro, troll, espectros;

    public static TipoMonstruo random() {
        TipoMonstruo[] valores = values();

        return valores[(int)(Math.random() * valores.length)];
    }
}
