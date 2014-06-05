package com.domotica.core;

import com.domotica.logic.TareaTimerProgExtractores;
import java.util.*;

public class Habitacion {

    private String descripcion;
    private int id;
    private int humedad;
    private int TempRef;
    private String estado;  // NINGUNO,ALTAPOSITIVA,MEDIAPOSITIVA,IDEAL,ALTANEGATIVA,MEDIANEGATIVA
    private double temperatura;
    private int iluminacion;
    private int ventilacion;
    private int humedadIdeal;
    private boolean automatico;
    private int temperaturaIdeal;
    private int iluminacionIdeal;
    private int ventilacionIdeal;
    private LinkedList<Timer> tiemposextractores;
    private LinkedList<Dispositivo> dispositivos;
    private TareaTimerProgExtractores tareaextracotores;
    private int idSensor;

    public Habitacion(int id, int idSensor) {
        this.id = id;
        this.idSensor = idSensor;
        this.estado = "Ninguno";
        this.TempRef = 20;
        this.dispositivos = new LinkedList<>();
        this.tiemposextractores = new LinkedList<>();
    }

    public Dispositivo getDispositivo(String nombre) {
        for (Dispositivo disp : this.dispositivos) {
            if (disp.getNombre().equals(nombre)) {
                return disp;
            }
        }
        return null;
    }

    public void reducirTemperaturaRegionAlta() {
        System.out.println("*************   REDUCIENDO TEMPERATURA, REGION ALTA" + "Habitacion" + getIdSensor()+ "******************");
        System.out.print("Habitacion" + this.idSensor);
//        if(EstadoAutoHab[Integer.parseInt(nombre) - 102].equals("ALTAPOSITIVA"))
//                return;
        setEstado("ALTAPOSITIVA");
        int contadorExtractores = 0; //se deben encender 2  extractores
        try {
            //recorrer el arreglo de dispositivos
            for (int i = 0; i < this.dispositivos.size(); i++) {
                Dispositivo disp = this.dispositivos.get(i);
                //buscar los calefactores y apagarlos
                if (disp.getTipo().equals("Calefactor")) {
                    if (disp.getValor() != 0) {
                        disp.setValor(0);
                    }
                } else //buscar el aire acondicionado y encenderlo en "Alto"
                if (disp.getTipo().equals("AireAcondicionado") && disp.getValor() == 'a') {
                    disp.setValor(1);
                    Thread.sleep(3000);
                    disp.setValor(3); //Max Potencia

                }
                Thread.sleep(500);
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Error en reducir temperatura region alta !!!" + e.getMessage());
        }

    }

    public void reducirTemperaturaRegionMedia() {
        System.out.println("*************   REDUCIENDO TEMPERATURA, REGION MEDIA******************");
        System.out.println("Habitacion" + getIdSensor());
        //recorrer el arreglo de dispositivos
//        if(EstadoAutoHab[Integer.parseInt(nombre) - 102].equals("MEDIAPOSITIVA"))
//            return;
        setEstado("MEDIAPOSITIVA");
        try {
            for (int i = 0; i < this.dispositivos.size(); i++) {
                Dispositivo disp = this.dispositivos.get(i);
                //buscar los calefactores y apagarlos
                if (disp.getTipo().equals("Calefactor")) {
                    if (disp.getValor() != 0) {
                        disp.setValor(0);
                    }
                } else //buscar el aire acondicionado y encenderlo
                if (disp.getTipo().equals("AireAcondicionado") && disp.getValor() == 'a') {
                    disp.setValor(1);
                    Thread.sleep(2000);
                    disp.setValor(1);

                }
            }
            Thread.sleep(500);

        } catch (InterruptedException e) {
            System.out.println("Error en reducir temperatura region media !!!" + e.getMessage());
        }
    }

    public void aumentarTemperaturaRegionAlta() {
        System.out.println("*************   AUMENTANDO TEMPERATURA, REGION ALTA******************");
        System.out.println("Habitacion" + getIdSensor());
//        if(EstadoAutoHab[Integer.parseInt(nombre) - 102].equals("ALTANEGATIVA"))
//            return;
        setEstado("ALTANEGATIVA");
        try {
            //	recorrer el arreglo de dispositivos
            for (int i = 0; i < this.dispositivos.size(); i++) {
                Dispositivo disp = this.dispositivos.get(i);
                //buscar los calefactores y encenderlos en alto
                if (disp.getTipo().equals("Calefactor")) {
                    if (disp.getValor() == 0) {
                        disp.setValor(1);
                    }
                } else //buscar el aire acondicionado y apagarlo
                if (disp.getTipo().equals("AireAcondicionado") && disp.getValor() == 'e') {
                    System.out.println("Estado de AA:" + disp.getEstado());
                    disp.setValor(1);
                    disp.setValor(0);
                    Thread.sleep(2000);
                    disp.setValor(1);
                }
                Thread.sleep(700);
            }
        } catch (InterruptedException e) {
            System.out.println("Error en aumentar Temperatura region alta : !!!" + e.getMessage());
        }

    }

    public void aumentarTemperaturaRegionMedia() {
        System.out.println("*************   AUMENTANDO TEMPERATURA, REGION MEDIA******************");
        System.out.println("Habitacion" + getIdSensor());
        Random Rd;
        Rd = new Random();
        boolean temp = Rd.nextBoolean();
        if (estado.equals("MEDIANEGATIVA")) {
            return;
        }
        setEstado("MEDIANEGATIVA");
        //recorrer el arreglo de dispositivos
        int contadorCalefactores = 0;
        try {
            for (int i = 0; i < this.dispositivos.size(); i++) {
                Dispositivo disp = this.dispositivos.get(i);
                //buscar un calefactor lo enciende en alto
                if (disp.getTipo().equals("Calefactor") && contadorCalefactores < 2) {
                    if (temp) {
                        disp.setValor(1);
                        temp = !temp;
                    } else {
                        disp.setValor(0);
                        temp = !temp;
                    }
                    contadorCalefactores++;
                } else //buscar el aire acondicionado y apagarlo
                if (disp.getTipo().equals("AireAcondicionado") && disp.getValor() == 'e') {
                    disp.setValor(1);
                    disp.setValor(0);
                    Thread.sleep(2000);
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Error en aumentar temperatura en region media !!!" + e.getMessage());
        }

    }

    public void RegionIdeal() {
        System.out.println("*************   REGION IDEAL   ******************");
        System.out.println("Habitacion" + getIdSensor());
//        if(EstadoAutoHab[Integer.parseInt(nombre) - 102].equals("IDEAL"))
//            return;
        setEstado("IDEAL");
        int contadorCalefactores = 0;
        int contadorExtractores = 0;
        try {
            for (int i = 0; i < this.dispositivos.size(); i++) {
                Dispositivo disp = this.dispositivos.get(i);
                //buscar los calefactores y encenderlos en alto
                if (disp.getTipo().equals("Calefactor") && contadorCalefactores < 2) {
                    if (disp.getValor() != 0 /*|| getResumeneeprom().getCalefactor2() != 0*/) {
                        disp.setValor(0);
                    }
                    contadorCalefactores++;
                } else //buscar el aire acondicionado y apagarlo
                if (disp.getTipo().equals("AireAcondicionado") && disp.getValor() != 0) {
                    disp.setValor(1);
                    disp.setValor(0);

                } else if (disp.getTipo().equals("Extractor") && contadorExtractores < 2) {
                    if (disp.getValor() != 0) {
                        disp.setValor(2 + 1 + 8); //medio
                        Thread.sleep(1000);
                        disp.setValor(0); //medio
                    }
                    contadorExtractores++;
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException ex) {
            System.out.println("Error en region ideal !!!" + ex);
        }
    }

    //************ METODOS PARA EL CONTROL DE LA HUMEDAD *******************
    public void reducirHumedadRegionAlta() {
        //encender aire ac deshumidificador y 1 calefactor en medio
        //recorrer el arreglo de dispositivos
        int contadorCalefactores = 0;
        for (int i = 0; i < this.dispositivos.size(); i++) {
            Dispositivo disp = this.dispositivos.get(i);
            //buscar 1 calefactor y encenderlo en medio
            if (disp.getTipo().equals("Calefactor") && contadorCalefactores < 1) {
                disp.setValor(1);
                contadorCalefactores++;
            } else //buscar el aire acondicionado y encender deshumidificador
            if (disp.getTipo().equals("AireAcondicionado")) {
                disp.setValor(4);
            }
        }
    }

    public void reducirHumedadRegionMedia() {
        int contadorCalefactores = 0;
        for (int i = 0; i < this.dispositivos.size(); i++) {
            Dispositivo disp = this.dispositivos.get(i);
            //buscar el aire acondicionado y encender deshumidificador
            if (disp.getTipo().equals("AireAcondicionado")) {
                disp.setValor(4);
            }
        }
    }

    public void aumentarHumedadRegionAlta() {
        System.out.println("La humedad esta demasiado baja");
    }

    public void aumentarHumedadRegionMedia() {
        int contadorCalefactores = 0;
        for (int i = 0; i < this.dispositivos.size(); i++) {
            Dispositivo disp = this.dispositivos.get(i);
            //buscar los extractores y encenderlos en bajo
            if (disp.getTipo().equals("Extractor")) {
                disp.setValor(1);
            }
        }
    }

    public void addDispositivo(Dispositivo dispositivo_nuevo) {
        this.dispositivos.add(dispositivo_nuevo);
    }
    //-------------------Setters and Getters------------------------------------

    /**
     * @return the temperatura
     */
    public double getTemperatura() {
        return this.temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * @return the humedad
     */
    public int getHumedad() {
        return humedad;
    }

    /**
     * @param humedad the humedad to set
     */
    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    /**
     * @return the iluminacion
     */
    public int getIluminacion() {
        return iluminacion;
    }

    /**
     * @param iluminacion the iluminacion to set
     */
    public void setIluminacion(int iluminacion) {
        this.iluminacion = iluminacion;
    }

    /**
     * @return the ventilacion
     */
    public int getVentilacion() {
        return ventilacion;
    }

    /**
     * @param ventilacion the ventilacion to set
     */
    public void setVentilacion(int ventilacion) {
        this.ventilacion = ventilacion;
    }

    /**
     * @return the dispositivos
     */
    public LinkedList<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    /**
     * @param dispositivos the dispositivos to set
     */
    public void setDispositivos(LinkedList<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    /**
     * @return the temperaturaIdeal
     */
    public int getTemperaturaIdeal() {
        return temperaturaIdeal;
    }

    /**
     * @return the humedadIdeal
     */
    public int getHumedadIdeal() {
        return humedadIdeal;
    }

    /**
     * @return the iluminacionIdeal
     */
    public int getIluminacionIdeal() {
        return iluminacionIdeal;
    }

    /**
     * @return the ventilacionIdeal
     */
    public int getVentilacionIdeal() {
        return ventilacionIdeal;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setValor(String estado) {
        this.setEstado(estado);
    }

    /**
     * @return the automatico
     */
    public boolean getAutomatico() {
        return automatico;
    }

    /**
     * @param automatico the automatico to set
     */
    public void setAutomatico(boolean automatico) {
        this.automatico = automatico;
    }

    /**
     * @return the TempRef
     */
    public int getTempRef() {
        return TempRef;
    }

    /**
     * @param TempRef the TempRef to set
     */
    public void setTempRef(int TempRef) {
        this.TempRef = TempRef;
    }

    /**
     * @return the tareaextracotores
     */
    public TareaTimerProgExtractores getTareaextracotores() {
        return tareaextracotores;
    }

    /**
     * @param tareaextracotores the tareaextracotores to set
     */
    public void setTareaextracotores(TareaTimerProgExtractores tareaextracotores) {
        this.tareaextracotores = tareaextracotores;
    }

    /**
     * @return the tiemposextractores
     */
    public LinkedList<Timer> getTiemposextractores() {
        return tiemposextractores;
    }

    /**
     * @param tiemposextractores the tiemposextractores to set
     */
    public void setTiemposextractores(LinkedList<Timer> tiemposextractores) {
        this.tiemposextractores = tiemposextractores;
    }

    public void setHumedadIdeal(int humedadIdeal) {
        this.humedadIdeal = humedadIdeal;
    }

    public void setIluminacionIdeal(int iluminacionIdeal) {
        this.iluminacionIdeal = iluminacionIdeal;
    }

    public void setTemperaturaIdeal(int temperaturaIdeal) {
        this.temperaturaIdeal = temperaturaIdeal;
    }

    public void setVentilacionIdeal(int ventilacionIdeal) {
        this.ventilacionIdeal = ventilacionIdeal;
    }

    /**
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
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
     * @return the idSensor
     */
    public int getIdSensor() {
        return idSensor;
    }

    /**
     * @param idSensor the idSensor to set
     */
    public void setIdSensor(int idSensor) {
        this.idSensor = idSensor;
    }
}
