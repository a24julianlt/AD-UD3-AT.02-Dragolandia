package com.controller;

import org.hibernate.Session;

import com.model.Monstruo;
import com.model.TipoMonstruo;

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
        try (Session s = database.getSessionFactory().openSession()) {

            s.getTransaction().begin();
            s.persist(monstruo);
            s.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERROR AL AÑADIR UN MONSTRUO: " + e.getMessage());
        }
    }

    public void eliminarMonstruo(Monstruo monstruo) {
        try (Session s = database.getSessionFactory().openSession()) {

            s.getTransaction().begin();
            s.remove(monstruo);
            s.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERROR AL ELIMINAR UN MONSTRUO: " + e.getMessage());
        }
    }
}
