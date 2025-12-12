package com.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="hechizos")
public class Hechizo {

    private BolaDeFuego bolaDeFuego;
    private Rayo rayo;
    private BolaDeNieve bolaDeNieve;
    private Meteorito meteorito;

    public Hechizo() {
        this.bolaDeFuego = new BolaDeFuego();
        this.rayo = new Rayo();
        this.bolaDeNieve = new BolaDeNieve();
        this.meteorito = new Meteorito();
    }

    public void usarBolaDeFuego(List<Monstruo> monstruos) {
        bolaDeFuego.efecto(monstruos);
    }

    public void usarRayo(List<Monstruo> monstruos) {
        rayo.efecto(monstruos);
    }
    
    public void usarBolaDeNieve(List<Monstruo> monstruos) {
        bolaDeNieve.efecto(monstruos);
    }

    public void usarMeteorito(List<Monstruo> monstruos) {
        meteorito.efecto(monstruos);
    }
}
