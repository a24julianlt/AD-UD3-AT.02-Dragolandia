package com.controller;

import com.model.Mago;

import jakarta.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ControladorMago {
    private final Mago mago;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorMago() {
        this.mago = new Mago();
    }

    public void crearMago(String nombre) {
        mago.setNombre(nombre);
        mago.setVida(20);
        mago.setNivelMagia(5);
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

    /*
     * BASE DE DATOS
     */
    public void gardarMago() {
        try (SessionFactory sf = database.getSessionFactory();
                Session s = sf.openSession()) {

            s.getTransaction().begin();
            s.persist(getMago());
            s.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERROR AL AÑADIR UN MAGO: " + e.getMessage());
        }
    }
}
