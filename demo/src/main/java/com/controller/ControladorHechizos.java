package com.controller;

import java.util.List;

import com.model.BolaDeFuego;
import com.model.BolaDeNieve;
import com.model.Hechizo;
import com.model.Meteorito;
import com.model.Monstruo;
import com.model.Rayo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class ControladorHechizos {
    private final List<Hechizo> hechizos;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorHechizos() {
        this.hechizos = List.of(
                new BolaDeFuego(),
                new BolaDeNieve(),
                new Rayo(),
                new Meteorito());
    }

    public String getNombreHechizo(int indice) {
        return hechizos.get(indice).getNombre();
    }

    public void lanzar(int indice, List<Monstruo> monstruos) {
        hechizos.get(indice).efecto(monstruos);
    }

    public Hechizo getHechizo(int i) {
        return hechizos.get(i);
    }

    /*
     * BASE DE DATOS
     */
    public void gardarHechizos() {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();

            for (Hechizo h : hechizos) {
                Hechizo existente = em.createQuery(
                        "SELECT h FROM Hechizo h WHERE h.nombre = :nombre",
                        Hechizo.class).setParameter("nombre", h.getNombre())
                        .getResultStream()
                        .findFirst()
                        .orElse(null);

                if (existente != null) {
                    h.setId(existente.getId());
                }
            }
            
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL AÑADIR EL HECHIZO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    public void eliminarHechizo(Hechizo hechizo) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();

            em.remove(hechizo);
            
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL ELIMINAR EL HECHIZO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    public void actualizarHechizo(Hechizo hechizo) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();

            em.merge(hechizo);
            
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL ACTUALIZAR EL HECHIZO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    public void listarHechizos(Hechizo hechizo) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();

            em.createQuery("select * from Hechizos", Hechizo.class).getResultList();
            
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR LOS HECHIZOS: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    public void buscarHechizo(int id) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            Query query = em.createQuery("select * from Hechizo where id=:id");
            query.setParameter("id", id);
            query.getResultList();
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR EL HECHIZO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }
}
