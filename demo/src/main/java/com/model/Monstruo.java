package com.model;

import jakarta.persistence.*;

@Entity
@Table(name = "monstruos")
public class Monstruo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int vida;
    @Enumerated(EnumType.STRING)
    private TipoMonstruo tipo;
    private int fuerza;

    
    public Monstruo() {
    }

    public Monstruo(String nombre) {
        this.nombre = nombre;
        this.vida = 18;
        this.tipo = TipoMonstruo.random();
        this.fuerza = 4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 1) {
            this.vida = 0;
        } else {
            this.vida = vida;
        }
    }

    public TipoMonstruo getTipo() {
        return tipo;
    }

    public void setTipo(TipoMonstruo tipo) {
        this.tipo = tipo;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public void atacar(Mago mago) {
        int nuevaVida = mago.getVida() - this.getFuerza();

        mago.setVida(nuevaVida);
    }

    @Override
    public String toString() {
        return "Monstruo [id=" + id + ", nombre=" + nombre + ", vida=" + vida + ", fuerza=" + fuerza
                + ", tipo=" + tipo + "]";
    }
}
