package com.model;

import jakarta.persistence.*;

/**
 * Representa un monstruo en el juego Dragolandia.
 * Los monstruos tienen diferentes tipos y pueden atacar a los magos.
 */
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

    /**
     * Constructor vacío requerido por JPA.
     */
    public Monstruo() {
    }

    /**
     * Crea un nuevo monstruo con tipo aleatorio.
     * @param nombre El nombre del monstruo
     */
    public Monstruo(String nombre) {
        this.nombre = nombre;
        this.tipo = TipoMonstruo.values()[(int) (Math.random() * TipoMonstruo.values().length)];
        asignarEstadisticas();
    }

    /**
     * Asigna vida y fuerza según el tipo de monstruo.
     */
    private void asignarEstadisticas() {
        switch (tipo) {
            case OGRO:
                this.vida = 15;
                this.fuerza = 8;
                break;
            case TROLL:
                this.vida = 20;
                this.fuerza = 6;
                break;
            case ESPECTRO:
                this.vida = 10;
                this.fuerza = 10;
                break;
        }
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

    /**
     * Establece la vida del monstruo. No puede ser negativa.
     * @param vida Los puntos de vida (mínimo 0)
     */
    public void setVida(int vida) {
        if (vida < 0) {
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

    /**
     * Ataca a un mago reduciendo su vida según la fuerza del monstruo.
     * @param mago El mago objetivo
     */
    public void atacar(Mago mago) {
        System.out.println(nombre + " ataca a " + mago.getNombre() + " causando " + fuerza + " de daño");
        mago.setVida(mago.getVida() - fuerza);
    }

    @Override
    public String toString() {
        return "Monstruo [id=" + id + ", nombre=" + nombre + ", vida=" + vida + ", tipo=" + tipo + ", fuerza="
                + fuerza + "]";
    }
}