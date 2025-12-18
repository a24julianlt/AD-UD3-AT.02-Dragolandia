package com.controller;

import com.model.Mago;

import java.util.List;

import org.hibernate.Session;

public class ControladorMago {
    private final Mago mago;
    private final ControladorHechizos contrHechizos;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorMago() {
        this.mago = new Mago();
        this.contrHechizos = new ControladorHechizos();
    }

    public void crearMago(String nombre, List<Integer>hechizos) {
        mago.setNombre(nombre);
        mago.setVida(20);
        mago.setNivelMagia(5);
        setConjuros(hechizos);
    }

    public Mago getMago() {
        return mago;
    }

    public int getNivelMagia() {
        return mago.getNivelMagia();
    }

    public int getVida() {
        return mago.getVida();
    }

    public void setConjuros(List<Integer> hechizos) {
        for (int h : hechizos) {
            mago.añadirConjuro(contrHechizos.getHechizo(h));
        }
    }

    /*
     * BASE DE DATOS
     */
    public void gardarMago() {
        try (Session s = database.getSessionFactory().openSession()) {

            s.getTransaction().begin();
            s.persist(getMago());
            s.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERROR AL AÑADIR UN MAGO: " + e.getMessage());
        }
    }
}
