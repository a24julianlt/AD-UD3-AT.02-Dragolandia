package com.controller;

import java.util.List;

import org.hibernate.Session;

import com.model.BolaDeFuego;
import com.model.BolaDeNieve;
import com.model.Hechizo;
import com.model.Meteorito;
import com.model.Monstruo;
import com.model.Rayo;

public class ControladorHechizos {
    private final List<Hechizo> hechizos;
    private final HibernateSingleton database = HibernateSingleton.getInstance();

    public ControladorHechizos() {
        this.hechizos = List.of(
                new BolaDeFuego(),
                new BolaDeNieve(),
                new Rayo(),
                new Meteorito());
    }

    public String getNombreHechizo(int indice) {
        return hechizos.get(indice).getNombre();
    }

    public void lanzar(int indice, List<Monstruo> monstruos) {
        hechizos.get(indice).efecto(monstruos);
    }

    public Hechizo getHechizo(int i) {
        return hechizos.get(i);
    }

    /*
     * BASE DE DATOS
     */
    public void gardarHechizos() {

        try (Session s = database.getSessionFactory().openSession()) {

            s.getTransaction().begin();

            for (Hechizo h : hechizos) {
                Hechizo existente = s.createQuery(
                        "SELECT h FROM Hechizo h WHERE h.nombre = :nombre",
                        Hechizo.class).setParameter("nombre", h.getNombre())
                        .getResultStream()
                        .findFirst()
                        .orElse(null);

                if (existente != null) {
                    h.setId(existente.getId());
                }
            }
            
            s.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERROR AL AÑADIR EL HECHIZO: " + e.getMessage());
        }
    }

}
