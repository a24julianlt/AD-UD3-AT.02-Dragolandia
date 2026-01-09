package com.controller;

import com.model.Hechizo;
import com.model.Mago;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

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
        setConjuros(mago, hechizos);
    }

    public Mago newMago(String nombre, List<Integer> conjuros) {
        Mago newMago = new Mago(nombre);
        setConjuros(newMago, conjuros);

        gardarMago(newMago);

        return newMago;
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

    public void setConjuros(Mago mago, List<Integer> hechizos) {
        EntityManager em = database.getEntityManager();

        try {
            for (int h : hechizos) {
                Hechizo hechizoOriginal = contrHechizos.getHechizo(h);

                // Buscar el hechizo en BD por nombre para obtener la entidad gestionada
                Hechizo hechizoGestionado = em.createQuery(
                        "SELECT h FROM Hechizo h WHERE h.nombre = :nombre", Hechizo.class)
                        .setParameter("nombre", hechizoOriginal.getNombre())
                        .getSingleResult();

                mago.añadirConjuro(hechizoGestionado);
            }
        } catch (Exception e) {
            System.out.println("ERROR AL ASIGNAR HECHIZOS: " + e.getMessage());
            // Fallback: usar los hechizos originales
            for (int h : hechizos) {
                mago.añadirConjuro(contrHechizos.getHechizo(h));
            }
        } finally {
            if (em.isOpen())
                em.close();
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

    public void buscarMago(int id) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            Query query = em.createQuery("select * from Mago where id=:id");
            query.setParameter("id", id);
            query.getResultList();
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR EL MAGO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }
}
