package com.controller;

import org.hibernate.Session;

import com.model.Bosque;
import com.model.Dragon;

public class ControladorDragon {
    private final Dragon dragon;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorDragon() {
        this.dragon = new Dragon();
    }

    public void crearDragon(String nombre, Bosque bosque) {
        dragon.setNombre(nombre);
        dragon.setIntensidadFuego((int) Math.random() * 20);
        dragon.setResistencia(30);
        dragon.setBosque(bosque);
    }

    public Dragon getDragon() {
        return dragon;
    }

    /*
     * BASE DE DATOS
     */
    public void gardarDragon(Dragon dragon) {
        try (Session s = database.getSessionFactory().openSession()) {

            s.getTransaction().begin();
            s.persist(dragon);
            s.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERROR AL AÑADIR UN DRAGON: " + e.getMessage());
        }
    }

    public void eliminarDragon(Dragon dragon) {
        try (Session s = database.getSessionFactory().openSession()) {

            s.getTransaction().begin();
            s.remove(dragon);
            s.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERROR AL ELIMINAR UN DRAGON: " + e.getMessage());
        }
    }
}
