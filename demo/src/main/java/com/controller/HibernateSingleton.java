package com.controller;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSingleton {
    private static HibernateSingleton instance;
    private SessionFactory sessionFactory;

    private HibernateSingleton() {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Error al crear SessionFactory: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
