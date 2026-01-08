package com.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateSingleton {
    private static HibernateSingleton instance;
    private EntityManagerFactory emf;

    private HibernateSingleton() {
        this.emf = Persistence.createEntityManagerFactory("dragolandiaServizo");
    }

    /*
     * Cambiar clase para usar EntityManager en vez de sessionFactory
     */

    public static HibernateSingleton getInstance() {
        if (instance == null) {
            synchronized (HibernateSingleton.class) {
                if (instance == null) {
                    instance = new HibernateSingleton();
                }
            }
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
