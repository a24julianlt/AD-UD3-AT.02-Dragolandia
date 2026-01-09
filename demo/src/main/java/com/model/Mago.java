package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;

@Entity
@Table(name = "magos")
public class Mago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int vida;
    private int nivelMagia;

    @OneToMany(mappedBy = "mago", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MagoHechizo> magoHechizos = new ArrayList<>();

    public Mago() {
    }

    public Mago(String nombre) {
        this.nombre = nombre;
        this.vida = 20;
        this.nivelMagia = 5;
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

    public int getNivelMagia() {
        return nivelMagia;
    }

    public void setNivelMagia(int nivelMagia) {
        this.nivelMagia = nivelMagia;
    }

    public void añadirConjuro(Hechizo hechizo) {
        if (magoHechizos.size() < 4) {
            MagoHechizo magoHechizo = new MagoHechizo(this, hechizo);
            magoHechizos.add(magoHechizo);
        } else {
            System.out.println("No puedes tener más de 4 hechizos");
        }
    }

    public List<Hechizo> getConjuros() {
        return magoHechizos.stream()
                .map(MagoHechizo::getHechizo)
                .collect(Collectors.toList());
    }

    public void lanzarHechizo(Monstruo monstruo) {
        int nuevaVida = monstruo.getVida() - this.getNivelMagia();
        monstruo.setVida(nuevaVida);
    }

    public void lanzarHechizo(Monstruo monstruo, Hechizo conjuro) {
        List<Hechizo> conjuros = getConjuros();
        if (conjuros.contains(conjuro)) {
            conjuro.efecto(monstruo);
        } else {
            setVida(vida - 1);
        }
    }

    @Override
    public String toString() {
        return "Mago [id=" + id + ", nombre=" + nombre + ", vida=" + vida + ", nivelMagia=" + nivelMagia + "]";
    }
}