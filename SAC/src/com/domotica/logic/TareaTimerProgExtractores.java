package com.domotica.logic;

import java.util.LinkedList;
import java.util.TimerTask;

/**
 * @author AmbientesControlados
 */
public class TareaTimerProgExtractores extends TimerTask {

    int encendido;// = 20;
    int intervalo;
    int nivel;
    int Habitacion;
    boolean continuar;
    long tiempotranscurrido;
    LinkedList<byte[]> tramas;
    Sistema sistema;
    /**
     * @param Encendido: tiempo en el cual permanecen encendido el extractor en
     * segundos.
     * @param Extractor: Numero de extractor 1 o 2
     * @param nivel: medio o alto.
     * @param Hab Numero de habitacion 1,2,3
     */
    public TareaTimerProgExtractores(int encendido, int nivel, int Hab) {
        this.encendido = encendido * 1000;
        this.intervalo = 60 * 1000;
        this.intervalo = intervalo * 1000;
        this.nivel = nivel;
        this.Habitacion = Hab;
        this.continuar = true;
        this.tiempotranscurrido = 0;
        this.tramas=new LinkedList<>();
    }

    @Override
    public void run() {            
        for (byte[] bytes : tramas) {
             sistema.getSerialdelegate().EscribirPuerto(bytes);
            }
    }

    public void detener() {
        continuar = false;
    }
}
