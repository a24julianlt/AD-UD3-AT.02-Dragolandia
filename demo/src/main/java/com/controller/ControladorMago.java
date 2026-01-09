package com.controller;

import com.model.Hechizo;
import com.model.Mago;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

/**
 * Controlador para gestionar las operaciones CRUD de los Magos.
 */
public class ControladorMago {
    private final Mago mago;
    private final ControladorHechizos contrHechizos;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorMago() {
        this.mago = new Mago();
        this.contrHechizos = new ControladorHechizos();
    }

    /**
     * Crea y configura un mago.
     * @param nombre Nombre del mago
     * @param hechizos Lista de índices de hechizos a asignar
     */
    public void crearMago(String nombre, List<Integer> hechizos) {
        mago.setNombre(nombre);
        mago.setVida(20);
        mago.setNivelMagia(5);
        setConjuros(mago, hechizos);
    }

    /**
     * Crea un nuevo mago, le asigna hechizos y lo guarda en BD.
     * @param nombre Nombre del mago
     * @param conjuros Lista de índices de hechizos
     * @return El mago creado
     */
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

    /**
     * Asigna hechizos a un mago desde la base de datos.
     * @param mago El mago al que asignar hechizos
     * @param hechizos Lista de índices de hechizos
     */
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
    
    /**
     * Guarda un mago en la base de datos.
     * @param mago El mago a guardar
     */
    public void gardarMago(Mago mago) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(mago);
            tx.commit();
            System.out.println("Mago guardado correctamente");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL AÑADIR UN MAGO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    /**
     * Elimina un mago de la base de datos.
     * @param mago El mago a eliminar
     */
    public void eliminarMago(Mago mago) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            // Mergear para evitar detached entity
            Mago magoGestionado = em.merge(mago);
            em.remove(magoGestionado);
            
            tx.commit();
            System.out.println("Mago eliminado correctamente");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL BORRAR UN MAGO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    /**
     * Actualiza un mago en la base de datos.
     * @param mago El mago a actualizar
     */
    public void actualizarMago(Mago mago) {
        EntityManager em = database.getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(mago);
            tx.commit();
            System.out.println("Mago actualizado correctamente");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL ACTUALIZAR UN MAGO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    /**
     * Lista todos los magos de la base de datos.
     * @return Lista de magos
     */
    public List<Mago> listarMagos() {
        EntityManager em = database.getEntityManager();
        List<Mago> magos = null;

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            magos = em.createQuery("SELECT m FROM Mago m", Mago.class).getResultList();
            tx.commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL LISTAR LOS MAGOS: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
        
        return magos;
    }

    /**
     * Busca un mago por su ID.
     * @param id El ID del mago
     * @return El mago encontrado o null
     */
    public Mago buscarMago(int id) {
        EntityManager em = database.getEntityManager();
        Mago mago = null;

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            mago = em.find(Mago.class, id);
            tx.commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL BUSCAR EL MAGO: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
        
        return mago;
    }
}
