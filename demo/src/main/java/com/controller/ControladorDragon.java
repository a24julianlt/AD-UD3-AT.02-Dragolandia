package com.controller;

import java.util.List;

import com.model.Bosque;
import com.model.Dragon;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/**
 * Controlador para gestionar las operaciones CRUD del Dragón.
 */
public class ControladorDragon {
    private final Dragon dragon;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorDragon() {
        this.dragon = new Dragon();
    }

    /**
     * Crea y configura un dragón.
     * @param nombre Nombre del dragón
     * @param bosque Bosque donde habita el dragón
     * @return El dragón creado
     */
    public Dragon crearDragon(String nombre, Bosque bosque) {
        dragon.setNombre(nombre);
        dragon.setIntensidadFuego((int) (Math.random() * 20));
        dragon.setResistencia(30);
        dragon.setBosque(bosque);

        gardarDragon(dragon);

        return dragon;
    }

    public Dragon getDragon() {
        return dragon;
    }

    /*
     * BASE DE DATOS
     */
    
    /**
     * Guarda un dragón en la base de datos.
     * @param dragon El dragón a guardar
     */
    public void gardarDragon(Dragon dragon) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(dragon);
            tx.commit();
            System.out.println("Dragón guardado correctamente");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL AÑADIR UN DRAGON: " + e.getMessage());
        } finally {
            if (em.isOpen()) 
                em.close();
        }
    }

    /**
     * Elimina un dragón de la base de datos.
     * @param dragon El dragón a eliminar
     */
    public void eliminarDragon(Dragon dragon) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            // Mergear para evitar detached entity
            Dragon dragonGestionado = em.merge(dragon);
            em.remove(dragonGestionado);
            
            tx.commit();
            System.out.println("Dragón eliminado correctamente");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL ELIMINAR UN DRAGON: " + e.getMessage());
        } finally {
            if (em.isOpen()) 
                em.close();
        }
    }

    /**
     * Actualiza un dragón en la base de datos.
     * @param dragon El dragón a actualizar
     */
    public void actualizarDragon(Dragon dragon) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(dragon);
            tx.commit();
            System.out.println("Dragón actualizado correctamente");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL ACTUALIZAR UN DRAGON: " + e.getMessage());
        } finally {
            if (em.isOpen()) 
                em.close();
        }
    }

    /**
     * Lista todos los dragones de la base de datos.
     * @return Lista de dragones
     */
    public List<Dragon> listarDragones() {
        EntityManager em = database.getEntityManager();
        List<Dragon> dragones = null;

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            dragones = em.createQuery("SELECT d FROM Dragon d", Dragon.class).getResultList();
            tx.commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL LISTAR LOS DRAGONES: " + e.getMessage());
        } finally {
            if (em.isOpen()) 
                em.close();
        }
        
        return dragones;
    }

    /**
     * Busca un dragón por su ID.
     * @param id El ID del dragón
     * @return El dragón encontrado o null
     */
    public Dragon buscarDragon(int id) {
        EntityManager em = database.getEntityManager();
        Dragon dragon = null;

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            dragon = em.find(Dragon.class, id);
            tx.commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL BUSCAR EL DRAGON: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
        
        return dragon;
    }
}