package com.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Representa un bosque donde tiene lugar la batalla.
 * Contiene monstruos y un monstruo jefe.
 */
@Entity
@Table(name = "bosques")
public class Bosque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int nivelPeligro;

    @OneToOne
    private Monstruo monstruoJefe;

    @OneToMany
    @JoinTable(name = "bosques_monstruos", 
               joinColumns = @JoinColumn(name = "bosque_id"), 
               inverseJoinColumns = @JoinColumn(name = "monstruo_id"))
    private List<Monstruo> monstruos = new ArrayList<>();

    /**
     * Constructor vacío requerido por JPA.
     */
    public Bosque() {
    }

    /**
     * Crea un nuevo bosque con nivel de peligro aleatorio.
     * @param nombre El nombre del bosque
     */
    public Bosque(String nombre) {
        this.nombre = nombre;
        this.nivelPeligro = (int) (Math.random() * 10) + 1;
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

    public int getNivelPeligro() {
        return nivelPeligro;
    }

    public void setNivelPeligro(int nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }

    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    /**
     * Cambia el monstruo jefe del bosque.
     * @param monstruoJefe El nuevo monstruo jefe
     */
    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

    public List<Monstruo> getMonstruos() {
        return monstruos;
    }

    public void setMonstruos(List<Monstruo> monstruos) {
        this.monstruos = monstruos;
    }

    /**
     * Añade un monstruo a la lista del bosque.
     * @param monstruo El monstruo a añadir
     */
    public void addMonstruo(Monstruo monstruo) {
        this.monstruos.add(monstruo);
    }

    /**
     * Muestra la información del monstruo jefe.
     */
    public void mostrarJefe() {
        if (monstruoJefe != null) {
            System.out.println("El jefe del bosque es: " + monstruoJefe.getNombre());
        } else {
            System.out.println("No hay monstruo jefe en este bosque");
        }
    }

    /**
     * Cambia el monstruo jefe del bosque.
     * @param nuevoJefe El nuevo monstruo jefe
     */
    public void cambiarJefe(Monstruo nuevoJefe) {
        this.monstruoJefe = nuevoJefe;
        System.out.println("El nuevo jefe del bosque es: " + nuevoJefe.getNombre());
    }
}