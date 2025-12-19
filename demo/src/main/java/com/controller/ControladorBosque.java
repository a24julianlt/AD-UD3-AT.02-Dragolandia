package com.controller;

import java.util.List;

import org.hibernate.Session;

import com.model.Bosque;
import com.model.Monstruo;

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

    /*
     * BASE DE DATOS
     */
    public void gardarBosque(Bosque bosque) {
        try (Session s = database.getSessionFactory().openSession()) {

            s.getTransaction().begin();
            s.persist(bosque);
            s.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERROR AL AÑADIR UN BOSQUE: " + e.getMessage());
        }
    }

    public void eliminarBosque(Bosque bosque) {
        try (Session s = database.getSessionFactory().openSession()) {

            s.getTransaction().begin();
            s.remove(bosque);
            s.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERROR AL ELIMINAR UN BOSQUE: " + e.getMessage());
        }
    }
}
