package com.domotica.controller;

/**
 *
 * @author juangocc
 */
public class jTreeContent<E> {

    private E dato;
    private String nombre;

    public jTreeContent(E dato, String nombre) {
        this.dato = dato;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

    /**
     * @return the dato
     */
    public E getDato() {
        return dato;
    }

    /**
     * @param dato the dato to set
     */
    public void setDato(E dato) {
        this.dato = dato;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
