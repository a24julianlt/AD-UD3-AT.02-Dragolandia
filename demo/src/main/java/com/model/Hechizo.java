package com.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Clase abstracta que representa un hechizo en el juego.
 * Los hechizos pueden afectar a uno o varios monstruos.
 */
@Entity
@Table(name = "hechizos")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Hechizo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String nombre;

    @OneToMany(mappedBy = "hechizo", cascade = CascadeType.ALL)
    private List<MagoHechizo> magoHechizos = new ArrayList<>();

    /**
     * Constructor vacío requerido por JPA.
     */
    public Hechizo() {

    }

    /**
     * Aplica el efecto del hechizo a un monstruo individual.
     * @param monstruo El monstruo objetivo
     */
    public void efecto(Monstruo monstruo) {

    }

    /**
     * Aplica el efecto del hechizo a múltiples monstruos.
     * @param monstruos Lista de monstruos objetivo
     */
    public void efecto(List<Monstruo> monstruos) {

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
}