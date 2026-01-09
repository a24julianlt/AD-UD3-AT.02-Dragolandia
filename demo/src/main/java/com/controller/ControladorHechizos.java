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

/**
 * Controlador para gestionar las operaciones CRUD de los Hechizos.
 */
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

    /**
     * Obtiene el nombre de un hechizo por su índice.
     * @param indice El índice del hechizo (0-3)
     * @return El nombre del hechizo
     */
    public String getNombreHechizo(int indice) {
        return hechizos.get(indice).getNombre();
    }

    /**
     * Lanza un hechizo sobre una lista de monstruos.
     * @param indice El índice del hechizo a lanzar
     * @param monstruos Lista de monstruos objetivo
     */
    public void lanzar(int indice, List<Monstruo> monstruos) {
        hechizos.get(indice).efecto(monstruos);
    }

    /**
     * Obtiene un hechizo por su índice.
     * @param i El índice del hechizo
     * @return El hechizo
     */
    public Hechizo getHechizo(int i) {
        return hechizos.get(i);
    }

    /*
     * BASE DE DATOS
     */
    
    /**
     * Guarda los 4 hechizos base en la base de datos si no existen.
     */
    public void gardarHechizos() {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            for (Hechizo h : hechizos) {
                // Buscar si ya existe en BD
                Hechizo existente = em.createQuery(
                        "SELECT h FROM Hechizo h WHERE h.nombre = :nombre",
                        Hechizo.class)
                        .setParameter("nombre", h.getNombre())
                        .getResultStream()
                        .findFirst()
                        .orElse(null);

                if (existente == null) {
                    // Solo persiste si no existe
                    em.persist(h);
                } else {
                    // Actualiza la referencia en memoria con el ID de BD
                    h.setId(existente.getId());
                }
            }

            tx.commit();
            System.out.println("Hechizos guardados correctamente");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL AÑADIR EL HECHIZO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    /**
     * Elimina un hechizo de la base de datos.
     * @param hechizo El hechizo a eliminar
     */
    public void eliminarHechizo(Hechizo hechizo) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            // Mergear para evitar detached entity
            Hechizo hechizoGestionado = em.merge(hechizo);
            em.remove(hechizoGestionado);
            
            tx.commit();
            System.out.println("Hechizo eliminado correctamente");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL ELIMINAR EL HECHIZO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    /**
     * Actualiza un hechizo en la base de datos.
     * @param hechizo El hechizo a actualizar
     */
    public void actualizarHechizo(Hechizo hechizo) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(hechizo);
            tx.commit();
            System.out.println("Hechizo actualizado correctamente");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL ACTUALIZAR EL HECHIZO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    /**
     * Lista todos los hechizos de la base de datos.
     * @return Lista de hechizos
     */
    public List<Hechizo> listarHechizos() {
        EntityManager em = database.getEntityManager();
        List<Hechizo> listaHechizos = null;

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            listaHechizos = em.createQuery("SELECT h FROM Hechizo h", Hechizo.class).getResultList();
            tx.commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL LISTAR LOS HECHIZOS: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
        
        return listaHechizos;
    }

    /**
     * Busca un hechizo por su ID.
     * @param id El ID del hechizo
     * @return El hechizo encontrado o null
     */
    public Hechizo buscarHechizo(int id) {
        EntityManager em = database.getEntityManager();
        Hechizo hechizo = null;

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            hechizo = em.find(Hechizo.class, id);
            tx.commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL BUSCAR EL HECHIZO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
        
        return hechizo;
    }
}
