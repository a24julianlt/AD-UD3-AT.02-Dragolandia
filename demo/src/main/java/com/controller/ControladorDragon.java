package com.controller;

import com.model.Bosque;
import com.model.Dragon;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class ControladorDragon {
    private final Dragon dragon;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorDragon() {
        this.dragon = new Dragon();
    }

    public void crearDragon(String nombre, Bosque bosque) {
        dragon.setNombre(nombre);
        dragon.setIntensidadFuego((int) Math.random() * 20);
        dragon.setResistencia(30);
        dragon.setBosque(bosque);
    }

    public Dragon getDragon() {
        return dragon;
    }

    /*
     * BASE DE DATOS
     */
    public void gardarDragon(Dragon dragon) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.persist(dragon);
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL AÑADIR UN DRAGON: " + e.getMessage());
        } finally {
            if (em.isOpen()) em.close();
        }
    }

    public void eliminarDragon(Dragon dragon) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.remove(dragon);
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL ELIMINAR UN DRAGON: " + e.getMessage());
        } finally {
            if (em.isOpen()) em.close();
        }
    }

    public void actualizarDragon(Dragon dragon) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.remove(dragon);
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL ACTUALIZAR UN DRAGON: " + e.getMessage());
        } finally {
            if (em.isOpen()) em.close();
        }
    }

    public void listarDragones() {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.createQuery("select * from Dragon", Dragon.class).getResultList();
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR LOS DRAGONES: " + e.getMessage());
        } finally {
            if (em.isOpen()) em.close();
        }
    }

    public void buscarMonstruo(int id) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            Query query = em.createQuery("select * from Dragon where id=:id");
            query.setParameter("id", id);
            query.getResultList();
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR EL DRAGON: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }
}
