package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.model.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/**
 * Controlador principal que coordina las operaciones del juego.
 * Gestiona la interacción entre todos los controladores de las entidades.
 */
public class ControladorPrincipal {
    private final ControladorMago contrMago;
    private final ControladorMonstruo contrMonstruo;
    private final ControladorBosque contrBosque;
    private final ControladorDragon contrDragon;
    private final ControladorHechizos contrHechizos;

    public ControladorPrincipal() {
        this.contrBosque = new ControladorBosque();
        this.contrMago = new ControladorMago();
        this.contrMonstruo = new ControladorMonstruo();
        this.contrDragon = new ControladorDragon();
        this.contrHechizos = new ControladorHechizos();
    }

    /**
     * Obtiene la vida del mago actual.
     * 
     * @return Puntos de vida del mago
     */
    public int getVidaMago() {
        return contrMago.getVida();
    }

    /**
     * Obtiene el nivel de magia del mago actual.
     * 
     * @return Nivel de magia
     */
    public int getNivelMagia() {
        return contrMago.getNivelMagia();
    }

    /**
     * Obtiene la vida del monstruo actual.
     * 
     * @return Puntos de vida del monstruo
     */
    public int getVidaMons() {
        return contrMonstruo.getVida();
    }

    /**
     * Obtiene el tipo del monstruo actual.
     * 
     * @return Tipo de monstruo
     */
    public TipoMonstruo getTipoMons() {
        return contrMonstruo.getTipo();
    }

    /**
     * Obtiene la lista de monstruos del bosque.
     * 
     * @return Lista de monstruos
     */
    public List<Monstruo> getListMonstruos() {
        return contrBosque.getMonstruos();
    }

    /**
     * Obtiene el nombre de un hechizo.
     * 
     * @param hechizo Nombre del hechizo a buscar
     * @return Nombre del hechizo o cadena vacía
     */
    public String getHechizo(String hechizo) {
        String devolver = "";
        switch (hechizo.toLowerCase()) {
            case "bola de fuego":
                devolver = contrHechizos.getNombreHechizo(0);
                break;
            case "bola de nieve":
                devolver = contrHechizos.getNombreHechizo(1);
                break;
            case "rayo":
                devolver = contrHechizos.getNombreHechizo(2);
                break;
            case "meteorito":
                devolver = contrHechizos.getNombreHechizo(3);
                break;
            default:
                break;
        }

        return devolver;
    }

    /**
     * Lanza un hechizo sobre una lista de monstruos.
     * 
     * @param hechizo   Nombre del hechizo a lanzar
     * @param monstruos Lista de monstruos objetivo
     */
    public void usarHechizo(String hechizo, List<Monstruo> monstruos) {
        switch (hechizo.toLowerCase()) {
            case "bola de fuego":
                contrHechizos.lanzar(0, monstruos);
                break;
            case "bola de nieve":
                contrHechizos.lanzar(1, monstruos);
                break;
            case "rayo":
                contrHechizos.lanzar(2, monstruos);
                break;
            case "meteorito":
                contrHechizos.lanzar(3, monstruos);
                break;
            default:
                break;
        }
    }

    /**
     * Verifica si la partida ha terminado.
     * 
     * @return true si la partida continúa, false si ha terminado
     */
    public boolean finPartida() {
        if (contrMago.getVida() < 1 || contrMonstruo.getVida() < 1) {
            return false;
        }
        return true;
    }

    /**
     * El mago ataca al monstruo.
     */
    public void atacaMago() {
        contrMago.getMago().lanzarHechizo(contrMonstruo.getMonstruo());
    }

    /**
     * El monstruo ataca al mago.
     */
    public void atacaMons() {
        contrMonstruo.getMonstruo().atacar(contrMago.getMago());
    }

    /**
     * Verifica si el mago ha ganado.
     * 
     * @return true si el mago sigue vivo, false si ha muerto
     */
    public boolean ganaMago() {
        if (contrMago.getVida() < 1) {
            return false;
        }
        return true;
    }

    /**
     * Establece un monstruo como jefe del bosque.
     * 
     * @param mons El monstruo a designar como jefe
     */
    public void setMontruoJefe(Monstruo mons) {
        contrBosque.setMonstruoJefe(mons);
    }

    /**
     * Crea un nuevo monstruo.
     * 
     * @param nombreMonstruo Nombre del monstruo
     * @return El monstruo creado
     */
    public Monstruo crearMonstruo(String nombreMonstruo) {
        return contrMonstruo.newMonstruo(nombreMonstruo);
    }

    /**
     * Crea un nuevo mago con sus hechizos.
     * 
     * @param nombreMago Nombre del mago
     * @param conjuros   Lista de índices de hechizos
     * @return El mago creado
     */
    public Mago crearMago(String nombreMago, List<Integer> conjuros) {
        return contrMago.newMago(nombreMago, conjuros);
    }

    /**
     * Crea un nuevo dragón.
     * 
     * @param nombreDragon Nombre del dragón
     * @return El dragón creado
     */
    public Dragon crearDragon(String nombreDragon) {
        return contrDragon.crearDragon(nombreDragon, contrBosque.getBosque());
    }

    /**
     * Añade un monstruo al bosque.
     * 
     * @param mons El monstruo a añadir
     */
    public void añadirABosque(Monstruo mons) {
        contrBosque.añadirABosque(mons);

        contrBosque.actualizarBosque(contrBosque.getBosque());
    }

    /**
     * Establece la lista de monstruos del bosque.
     * 
     * @param listaMons Lista de monstruos
     */
    public void setMonstruosBosque(List<Monstruo> listaMons) {
        contrBosque.setMonstruosBosque(listaMons);

        contrBosque.actualizarBosque(contrBosque.getBosque());
    }

    /**
     * Obtiene todos los hechizos disponibles en el juego.
     * 
     * @return Lista de los 4 hechizos
     */
    public List<Hechizo> getTodosLosHechizos() {
        List<Hechizo> hechizos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            hechizos.add(contrHechizos.getHechizo(i));
        }
        return hechizos;
    }

    /**
     * Obtiene el bosque actual del juego.
     * 
     * @return El bosque
     */
    public Bosque obtenerBosque() {
        return contrBosque.getBosque();
    }

    /**
     * Obtiene el dragón actual del juego.
     * 
     * @return El dragón
     */
    public Dragon obtenerDragon() {
        return contrDragon.getDragon();
    }

    /*
     * BASE DE DATOS
     */

    /**
     * Guarda un mago en la base de datos.
     * 
     * @param nombreMago Nombre del mago
     * @param conjuros   Lista de índices de hechizos
     */
    public void gardarMago(String nombreMago, List<Integer> conjuros) {
        contrMago.gardarMago(contrMago.newMago(nombreMago, conjuros));
    }

    /**
     * Guarda un monstruo en la base de datos.
     * 
     * @param nombreMonstruo Nombre del monstruo
     */
    public void gardarMonstruo(String nombreMonstruo) {
        contrMonstruo.gardarMonstruo(contrMonstruo.newMonstruo(nombreMonstruo));
    }

    /**
     * Guarda una lista de monstruos en la base de datos.
     * 
     * @param monstruos Lista de monstruos a guardar
     */
    public void gardarListaMonstruo(List<Monstruo> monstruos) {
        for (Monstruo m : monstruos)
            contrMonstruo.gardarMonstruo(m);
    }

    /**
     * Guarda el bosque en la base de datos.
     * 
     * @param nombreBosque Nombre del bosque
     */
    public void gardarBosque(String nombreBosque) {
        contrBosque.crearBosque(nombreBosque, null, getListMonstruos());

        contrBosque.gardarBosque(contrBosque.getBosque());
    }

    /**
     * Guarda el dragón en la base de datos.
     */
    public void gardarDragon() {
        contrDragon.gardarDragon(contrDragon.getDragon());
    }

    /**
     * Guarda los 4 hechizos base en la base de datos.
     */
    public void gardarHechizos() {
        contrHechizos.gardarHechizos();
    }

    /**
     * Elimina el mago actual de la base de datos.
     */
    public void eliminarMago() {
        contrMago.eliminarMago(contrMago.getMago());
    }

    /**
     * Elimina el monstruo actual de la base de datos.
     */
    public void eliminarMonstruo() {
        contrMonstruo.eliminarMonstruo(contrMonstruo.getMonstruo());
    }

    /**
     * Elimina una lista de monstruos de la base de datos.
     * 
     * @param monstruos Lista de monstruos a eliminar
     */
    public void eliminarListaMonstruo(List<Monstruo> monstruos) {
        for (Monstruo m : monstruos)
            contrMonstruo.eliminarMonstruo(m);
    }

    /**
     * Elimina el bosque actual de la base de datos.
     */
    public void eliminarBosque() {
        contrBosque.eliminarBosque(contrBosque.getBosque());
    }

    /**
     * Elimina el dragón actual de la base de datos.
     */
    public void eliminarDragon() {
        contrDragon.eliminarDragon(contrDragon.getDragon());
    }

    /**
     * Limpia completamente la base de datos eliminando todas las entidades.
     * Respeta el orden de dependencias para evitar errores de integridad
     * referencial.
     */
    public void limpiarBaseDatos() {
        EntityManager em = HibernateSingleton.getInstance().getEntityManager();

        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // Eliminar por queries directas respetando orden de dependencias
            em.createQuery("DELETE FROM MagoHechizo").executeUpdate();
            em.createQuery("DELETE FROM Mago").executeUpdate();
            em.createQuery("DELETE FROM Hechizo").executeUpdate();
            em.createQuery("DELETE FROM Dragon").executeUpdate();
            em.createQuery("DELETE FROM Bosque").executeUpdate();
            em.createQuery("DELETE FROM Monstruo").executeUpdate();

            tx.commit();
            System.out.println("Base de datos limpiada correctamente");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("ERROR AL LIMPIAR LA BASE DE DATOS: " + e.getMessage());
        } finally {
            if (em.isOpen())
                em.close();
        }
    }

    public void actualizarBD(Bosque bosque, Dragon dragon, List<Mago> magos, List<Monstruo> monstruos) {
        contrBosque.actualizarBosque(bosque);
        contrDragon.actualizarDragon(dragon);
        for (Mago m : magos) {
            contrMago.actualizarMago(m);
        }
        for (Monstruo m : monstruos) {
            contrMonstruo.actualizarMonstruo(m);
        }
    }
}
