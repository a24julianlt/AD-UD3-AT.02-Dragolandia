package com.controller;

import java.util.List;

import com.model.Bosque;
import com.model.Monstruo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/**
 * Controlador para gestionar las operaciones CRUD del Bosque.
 */
public class ControladorBosque {
    private final Bosque bosque;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorBosque() {
        this.bosque = new Bosque();
    }

    /**
     * Crea y configura un bosque.
     * @param nombre Nombre del bosque
     * @param monstruo Monstruo jefe
     * @param monstruosBosque Lista de monstruos del bosque
     */
    public void crearBosque(String nombre, Monstruo monstruo, List<Monstruo> monstruosBosque) {
        bosque.setNombre(nombre);
        bosque.setMonstruoJefe(monstruo);
        bosque.setNivelPeligro(5);
        bosque.setMonstruos(monstruosBosque);
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
        return bosque.getMonstruos();
    }

    /**
     * Establece el monstruo jefe y actualiza en BD.
     * @param mons El nuevo monstruo jefe
     */
    public void setMonstruoJefe(Monstruo mons) {
        bosque.setMonstruoJefe(mons);
        actualizarBosque(bosque);
    }

    public void añadirABosque(Monstruo mons) {
        bosque.addMonstruo(mons);
    }

    public void setMonstruosBosque(List<Monstruo> listaMons) {
        bosque.setMonstruos(listaMons);
    }

    /*
     * BASE DE DATOS
     */
    
    /**
     * Guarda un bosque en la base de datos.
     * @param bosque El bosque a guardar
     */
    public void gardarBosque(Bosque bosque) {
        EntityManager em = database.getEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(bosque);
            tx.commit();
            System.out.println("Bosque guardado correctamente");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL AÑADIR UN BOSQUE: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    /**
     * Elimina un bosque de la base de datos.
     * @param bosque El bosque a eliminar
     */
    public void eliminarBosque(Bosque bosque) {
        EntityManager em = database.getEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            // Mergear para evitar detached entity
            Bosque bosqueGestionado = em.merge(bosque);
            em.remove(bosqueGestionado);
            
            tx.commit();
            System.out.println("Bosque eliminado correctamente");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL ELIMINAR UN BOSQUE: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    /**
     * Actualiza un bosque en la base de datos.
     * @param bosque El bosque a actualizar
     */
    public void actualizarBosque(Bosque bosque) {
        EntityManager em = database.getEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(bosque);
            tx.commit();
            System.out.println("Bosque actualizado correctamente");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL ACTUALIZAR UN BOSQUE: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    /**
     * Lista todos los bosques de la base de datos.
     * @return Lista de bosques
     */
    public List<Bosque> listarBosques() {
        EntityManager em = database.getEntityManager();
        List<Bosque> bosques = null;

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            bosques = em.createQuery("SELECT b FROM Bosque b", Bosque.class).getResultList();
            tx.commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL LISTAR LOS BOSQUES: " + e.getMessage());
        } finally {
            if (em.isOpen()) 
                em.close();
        }
        
        return bosques;
    }

    /**
     * Busca un bosque por su ID.
     * @param id El ID del bosque
     * @return El bosque encontrado o null
     */
    public Bosque buscarBosque(int id) {
        EntityManager em = database.getEntityManager();
        Bosque bosque = null;

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            bosque = em.find(Bosque.class, id);
            tx.commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL BUSCAR EL BOSQUE: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
        
        return bosque;
    }
}