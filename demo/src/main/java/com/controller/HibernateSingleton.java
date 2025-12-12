package com.controller;

import org.hibernate.*;
import org.hibernate.cfg.*;

class HibernateSingleton {
    // Campo estático para almacenar la instancia única.
    private static HibernateSingleton instance;

    // El constructor es privado para evitar llamadas directas con `new`.
    private HibernateSingleton() {
        // Código de inicialización, como la conexión real a la base de datos.
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        try (Session session = factory.openSession();) {
            Transaction tx = session.beginTransaction();

            session.flush();

            tx.getStatus();
        } catch (Exception e) {
            System.out.println("Error al conectarse a la DB: " + e.getMessage());
        }
    }

    // Método estático que controla el acceso a la instancia del Singleton.
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

}
