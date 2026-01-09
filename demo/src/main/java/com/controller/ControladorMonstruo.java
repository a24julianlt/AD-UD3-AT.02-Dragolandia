package com.controller;

import java.util.List;

import com.model.Monstruo;
import com.model.TipoMonstruo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/**
 * Controlador para gestionar las operaciones CRUD de los Monstruos.
 */
public class ControladorMonstruo {
    private final Monstruo monstruo;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorMonstruo() {
        this.monstruo = new Monstruo();
    }

    /**
     * Crea y configura un monstruo.
     * @param nombre Nombre del monstruo
     */
    public void crearMonstruo(String nombre) {
        monstruo.setNombre(nombre);
        monstruo.setVida(18);
        monstruo.setFuerza(4);
        monstruo.setTipo(TipoMonstruo.random());
    }
    
    /**
     * Crea un nuevo monstruo y lo guarda en BD.
     * @param nombre Nombre del monstruo
     * @return El monstruo creado
     */
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
    
    /**
     * Guarda un monstruo en la base de datos.
     * @param monstruo El monstruo a guardar
     */
    public void gardarMonstruo(Monstruo monstruo) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(monstruo);
            tx.commit();
            System.out.println("Monstruo guardado correctamente");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL AÑADIR UN MONSTRUO: " + e.getMessage());
        } finally {
            if (em.isOpen()) 
                em.close();
        }
    }

    /**
     * Elimina un monstruo de la base de datos.
     * @param monstruo El monstruo a eliminar
     */
    public void eliminarMonstruo(Monstruo monstruo) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            // Mergear para evitar detached entity
            Monstruo monstruoGestionado = em.merge(monstruo);
            em.remove(monstruoGestionado);
            
            tx.commit();
            System.out.println("Monstruo eliminado correctamente");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL ELIMINAR UN MONSTRUO: " + e.getMessage());
        } finally {
            if (em.isOpen()) 
                em.close();
        }
    }

    /**
     * Actualiza un monstruo en la base de datos.
     * @param monstruo El monstruo a actualizar
     */
    public void actualizarMonstruo(Monstruo monstruo) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(monstruo);
            tx.commit();
            System.out.println("Monstruo actualizado correctamente");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL ACTUALIZAR UN MONSTRUO: " + e.getMessage());
        } finally {
            if (em.isOpen()) 
                em.close();
        }
    }

    /**
     * Lista todos los monstruos de la base de datos.
     * @return Lista de monstruos
     */
    public List<Monstruo> listarMonstruos() {
        EntityManager em = database.getEntityManager();
        List<Monstruo> monstruos = null;

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            monstruos = em.createQuery("SELECT m FROM Monstruo m", Monstruo.class).getResultList();
            tx.commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL LISTAR LOS MONSTRUOS: " + e.getMessage());
        } finally {
            if (em.isOpen()) 
                em.close();
        }
        
        return monstruos;
    }

    /**
     * Busca un monstruo por su ID.
     * @param id El ID del monstruo
     * @return El monstruo encontrado o null
     */
    public Monstruo buscarMonstruo(int id) {
        EntityManager em = database.getEntityManager();
        Monstruo monstruo = null;

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            monstruo = em.find(Monstruo.class, id);
            tx.commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL BUSCAR EL MONSTRUO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
        
        return monstruo;
    }
}
