package com.controller;

import java.util.List;

import com.model.Bosque;
import com.model.Monstruo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class ControladorBosque {
    private final Bosque bosque;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorBosque() {
        this.bosque = new Bosque();
    }

    public void crearBosque(String nombre, Monstruo monstruo, List<Monstruo> monstruosBosque) {
        bosque.setNombre(nombre);
        bosque.setMonstruoJefe(monstruo);
        bosque.setNivelPeligro(5);
        bosque.setMonstruosBosque(monstruosBosque);
    }

    public Bosque getBosque() {
        return bosque;
    }

    public String getNombre() {
        return bosque.getNombre();
    }

    public int getNivelPeligro() {
        return bosque.getNivelPeligro();
    }

    public Monstruo getMontruoJefe() {
        return bosque.getMonstruoJefe();
    }

    public List<Monstruo> getMonstruos() {
        return bosque.getMonstruosBosque();
    }

    public void setMonstruoJefe(Monstruo mons) {
        bosque.setMonstruoJefe(mons);

        actualizarBosque(bosque);
    }

    public void añadirABosque(Monstruo mons) {
        bosque.addMonstruo(mons);
    }

    /*
     * BASE DE DATOS
     */
    public void gardarBosque(Bosque bosque) {
        EntityManager em = database.getEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.persist(bosque);
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL AÑADIR UN BOSQUE: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    public void eliminarBosque(Bosque bosque) {
        EntityManager em = database.getEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.remove(bosque);
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL ELIMINAR UN BOSQUE: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    public void actualizarBosque(Bosque bosque) {
        EntityManager em = database.getEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.merge(bosque);
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL ACTUALIZAR UN BOSQUE: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    public void listarBosques() {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.createQuery("select * from Bosque", Bosque.class).getResultList();
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR LOS BOSQUES: " + e.getMessage());
        } finally {
            if (em.isOpen()) em.close();
        }
    }

    public void buscarMonstruo(int id) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            Query query = em.createQuery("select * from Bosque where id=:id");
            query.setParameter("id", id);
            query.getResultList();
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR EL BOSQUE: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }
}
