package com.controller;

import com.model.Monstruo;
import com.model.TipoMonstruo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class ControladorMonstruo {
    private final Monstruo monstruo;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorMonstruo() {
        this.monstruo = new Monstruo();
    }

    public void crearMonstruo(String nombre) {
        monstruo.setNombre(nombre);
        monstruo.setVida(18);
        monstruo.setFuerza(4);
        monstruo.setTipo(TipoMonstruo.random());
    }
    
    public Monstruo newMonstruo(String nombre) {
        Monstruo newMons = new Monstruo(nombre);

        gardarMonstruo(newMons);

        return newMons;
    }

    public Monstruo getMonstruo() {
        return monstruo;
    }

    public int getFuerza() {
        return monstruo.getFuerza();
    }

    public int getVida() {
        return monstruo.getVida();
    }

    public TipoMonstruo getTipo() {
        return monstruo.getTipo();
    }

    /*
     * BASE DE DATOS
     */
    public void gardarMonstruo(Monstruo monstruo) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.persist(monstruo);
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL AÑADIR UN MONSTRUO: " + e.getMessage());
        } finally {
            if (em.isOpen()) em.close();
        }
    }

    public void eliminarMonstruo(Monstruo monstruo) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.remove(monstruo);
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL ELIMINAR UN MONSTRUO: " + e.getMessage());
        } finally {
            if (em.isOpen()) em.close();
        }
    }

    public void actualizarMonstruo(Monstruo monstruo) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.merge(monstruo);
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL ACTUALIZAR UN MONSTRUO: " + e.getMessage());
        } finally {
            if (em.isOpen()) em.close();
        }
    }

    public void listarMonstruos() {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            em.createQuery("select * from Monstruo", Monstruo.class).getResultList();
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR LOS MONSTRUOS: " + e.getMessage());
        } finally {
            if (em.isOpen()) em.close();
        }
    }

    public void buscarMonstruo(int id) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();

            tx.begin();
            Query query = em.createQuery("select * from Monstruo where id=:id");
            query.setParameter("id", id);
            query.getResultList();
            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR EL MONSTRUO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }
}
