package com.domotica.core;

public class Dispositivo {

    private String nombre;  // C1,C2,E1,E2,P1,AA,BX,X1
    private byte valor;     // gracias a este valor se sabe el estado
    private String tipo;    // Calefactor,Extractor,Persianas,AireCondicionado,Bombillo,Puerta
    private String estado;  // Cerrado,Abiero,Apagado,Encendido,Bajo,Medio,Alto
    private String descripcion;
    private int id;

    public Dispositivo(String nombre, byte valor, String tipo) {
        this.tipo = tipo;
        this.valor = valor;
        this.nombre = nombre;
        this.setEstado(valor);
    }

    public Dispositivo(String nombre, String estado, String tipo) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.estado = estado;
    }

    private void setEstado(byte valor) {
        if (this.nombre.equals("P1")) {
            //Persianas
            if (this.valor == 0) {
                this.estado = "Cerrado";
            } else {
                this.estado = "Abierto";
            }
        } else {
            //Extractor y Calefactor y Airecondicionado
            if (this.valor == 0) {
                this.estado = "Apagado";
            } else {
                this.estado = "Encendido";
                if (this.valor == 2 + 8) {
                    this.estado = "Bajo";
                }
                if (this.valor == 2 + 8 + 1) {
                    this.estado = "Medio";
                }
                if (this.valor == 4) {
                    this.estado = "Alto";
                }
            }
        }
        if (this.nombre.equals("BX")) {
            int iluminacion = (this.valor & 0xff + 0) * 100 / 255;
            if (iluminacion > 50) {//en porcentaje
                this.estado = "Encendido";
            } else {
                this.estado = "Apagado";
            }
        }
        System.out.println("Dispositivo " + this.getTipo() + "-" + this.getNombre() + " , estado : " + this.estado);
        //    sistema.getTramadelegate().crearTrama(nombre, estado, habitacion, 0);
    }
//se tiene que configurar con el valor

    public void setValor(int valor) {
        this.setValor((byte) valor);
    }

    public void setValor(byte valor) {
        this.valor = valor;
        this.setEstado(valor);
    }
    //---------------------------------Setters and Getters----------------------

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return this.estado;
    }

    public byte getValor() {
        return valor;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
