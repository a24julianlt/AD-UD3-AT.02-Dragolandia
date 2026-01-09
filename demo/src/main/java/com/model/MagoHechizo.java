package com.model;

import jakarta.persistence.*;

/**
 * Tabla intermedia que relaciona magos con sus hechizos conocidos.
 * Implementa la relación Many-to-Many entre Mago y Hechizo.
 */
@Entity
@Table(name = "mago_hechizo")
public class MagoHechizo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "mago_id")
    private Mago mago;

    @ManyToOne
    @JoinColumn(name = "hechizo_id")
    private Hechizo hechizo;

    /**
     * Constructor vacío requerido por JPA.
     */
    public MagoHechizo() {
    }

    /**
     * Crea una nueva relación mago-hechizo.
     * @param mago El mago que aprende el hechizo
     * @param hechizo El hechizo aprendido
     */
    public MagoHechizo(Mago mago, Hechizo hechizo) {
        this.mago = mago;
        this.hechizo = hechizo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mago getMago() {
        return mago;
    }

    public void setMago(Mago mago) {
        this.mago = mago;
    }

    public Hechizo getHechizo() {
        return hechizo;
    }

    public void setHechizo(Hechizo hechizo) {
        this.hechizo = hechizo;
    }
}