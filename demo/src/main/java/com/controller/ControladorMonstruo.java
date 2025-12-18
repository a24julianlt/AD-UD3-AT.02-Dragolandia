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

    public Monstruo crearMonstruo(String nombre) {
        monstruo.setNombre(nombre);
        monstruo.setVida(18);
        monstruo.setFuerza(4);
        monstruo.setTipo(TipoMonstruo.random());
        
        return monstruo;
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
    public void gardarMonstruo() {
        try (Session s = database.getSessionFactory().openSession()) {

            s.getTransaction().begin();
            s.persist(getMonstruo());
            s.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERROR AL AÑADIR UN MONSTRUO: " + e.getMessage());
        }
    }
}
