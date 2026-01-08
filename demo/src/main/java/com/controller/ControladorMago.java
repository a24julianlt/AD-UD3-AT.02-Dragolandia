package com.controller;

import com.model.Mago;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ControladorMago {
    private final Mago mago;
    private final ControladorHechizos contrHechizos;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorMago() {
        this.mago = new Mago();
        this.contrHechizos = new ControladorHechizos();
    }

    public void crearMago(String nombre, List<Integer> hechizos) {
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
    public void gardarMago(Mago mago) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.persist(mago);
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL AÑADIR UN MAGO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    public void eliminarMago(Mago mago) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.remove(mago);
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL BORRAR UN MAGO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    public void actualizarMago(Mago mago) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.merge(mago);
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL ACTUALIZAR UN MAGO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    public void ListarMagos() {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.createQuery("select * from Mago", Mago.class).getResultList();
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR LOS MAGOS: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }
}
