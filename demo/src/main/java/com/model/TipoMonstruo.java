package com.model;

/**
 * Enumeración de los tipos de monstruos disponibles en el juego.
 */
public enum TipoMonstruo {
    /** Monstruo tipo ogro */
    OGRO, 
    /** Monstruo tipo troll */
    TROLL, 
    /** Monstruo tipo espectro */
    ESPECTRO;

    /**
     * Genera un tipo de monstruo aleatorio.
     * @return Un tipo de monstruo aleatorio
     */
    public static TipoMonstruo random() {
        TipoMonstruo[] valores = values();
        return valores[(int)(Math.random() * valores.length)];
    }
}