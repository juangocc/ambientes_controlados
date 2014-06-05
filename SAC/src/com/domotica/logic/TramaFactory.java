package com.domotica.logic;

import com.domotica.core.*;
import java.util.LinkedList;

public class TramaFactory {

//    LinkedList<Integer> temperaturas; //almacena 60 temperaturas para luego sacarles el promedio
//    LinkedList<Integer> humedades; //almacena 60 humedades para luego sacarles el promedio
    public int[] contError = {6, 6, 6, 6, 6, 6};

    public TramaFactory() {
    }

    public Habitacion decodificarTrama(LinkedList<Byte> DatoIn) {
        byte[] datostemp = linkedListByteToArrayByte(DatoIn);
        return decodificarTrama(datostemp);
    }
    //método que interpreta las tramas recibidas desde el sistema y realiza las
    //actualizaciones respectivas en el xml de los datos del sistema
    // actualiza y devuelve una habitación con condiciones nuevas

    public Habitacion decodificarTrama(byte[] tramaRecibida) {
        if (VerificarCabeceras(tramaRecibida) == -1) {
            System.out.println("Error en cabeceras");
            return null;
        }
        int longitudDatos = tramaRecibida[2] & 0x000000ff;
        int idOrigen = tramaRecibida[3] & 0x000000ff;
        int idDestino = tramaRecibida[4] & 0x000000ff;
        int estado = tramaRecibida[5]; // Que se hace con el estado de llegada ¿?
        System.out.println("Longitud de trama: " + longitudDatos);
        System.out.println("Direccion origen: " + idOrigen);
        Habitacion habitacion = new Habitacion(idOrigen, idDestino);
        contError[idOrigen - 102] = 0;
        //----------------------Datos-------------------------------------------
        //Antes habia una condicion de longitud de datos de 10, porque ¿?
        if (idOrigen >= 102 && idOrigen <= 104) {// si fue EEPROM 
            System.out.println("Trama EEPROM de: " + idOrigen);// como se sabe si la EEPROM siempre me va a responder de estos dispositivos, que pasa si recibe de mas o de menos¿?
            Dispositivo dispositivo = new Dispositivo("C1", tramaRecibida[6], "Calefactor");
            habitacion.addDispositivo(dispositivo);
            dispositivo = new Dispositivo("C2", tramaRecibida[7], "Calefactor");
            habitacion.addDispositivo(dispositivo);
            dispositivo = new Dispositivo("E1", tramaRecibida[8], "Extractpr");
            habitacion.addDispositivo(dispositivo);
            dispositivo = new Dispositivo("E2", tramaRecibida[9], "Extractor");
            habitacion.addDispositivo(dispositivo);
            dispositivo = new Dispositivo("P1", tramaRecibida[11], "Persiana");//que paso con el 10¿?
            habitacion.addDispositivo(dispositivo);
            dispositivo = new Dispositivo("BX", tramaRecibida[12], "Bombillo");
            habitacion.addDispositivo(dispositivo);
        }
        //recorre la longitud de los datos enviados
        int i = 0;
        int valor;
        int valorH;
        double temp;
        double RH;
        String nombreDispositivo;
        byte estadoDispositivo;
        byte TBajo = 0, TAlto = 0;
        byte HBajo = 0, HAlto = 0;
        /**
         * Trama de sensado // revisa el estado de cada dispositivo
         */
        if (idOrigen >= 105 && idOrigen <= 107) {// Si fue el sensor
            while (i + 8 < tramaRecibida.length) {// como se sabe de que dispositivo viene
                char[] nombreDisp = new char[2];
                nombreDisp[0] = (char) tramaRecibida[i + 6];
                nombreDisp[1] = (char) tramaRecibida[i + 7];
                estadoDispositivo = tramaRecibida[i + 8];

                nombreDispositivo = String.valueOf(nombreDisp);

                if (nombreDispositivo.equals("TL")) {//temperatura low
                    TBajo = estadoDispositivo;
                }
                if (nombreDispositivo.equals("TH")) {//temperatura high
                    TAlto = estadoDispositivo;
                }
                if (nombreDispositivo.equals("HL")) {//humedad low
                    HBajo = estadoDispositivo;
                }
                if (nombreDispositivo.equals("HH")) {//humedad high
                    HAlto = estadoDispositivo;
                }
                if (nombreDispositivo.equals("AA")) {
                    Dispositivo dispositivo = new Dispositivo("AA", estadoDispositivo, "AireAcondicicionado");
                    habitacion.addDispositivo(dispositivo);
//                    if (estadoDispositivo == 'e') { // esto quiere decir que esta habitación afecta a otra habitación ¿?
//                        ((sistema.getHabitacion(idOrigen - 105))).getDispositivo("AA").setEstado(1);
//                    } else {
//                        ((sistema.getHabitacion(idOrigen - 105))).getDispositivo("AA").setEstado(0);
//                    }
                }
                i += 3; //aumenta de 3 en 3 porque la info de cada dispositivo tiene 3 bytes
            } //cierra el while
            //------Evaluación de los datos obtenidos de todos los dispositivos
            valor = (TBajo & 0x00ff) | (TAlto & 0x00ff) * 256;
            temp = -40.1 + 0.01 * valor;
            valorH = (HBajo & 0x000ff) + (HAlto & 0x00ff) * 256;
            RH = -2.0468 + 0.0367 * valorH - 1.5955e-6 * Math.pow(valorH, 2);
            System.out.println("Llegó trama de sensado de la habitación " + idOrigen + " para la habitación " + idDestino + ", TL " + TBajo + "Th " + TAlto);
            System.out.println("Actualizar Estados de dispositivos en habitacionn " + idDestino);
            System.out.println("valor: " + valor);
            System.out.println("Habitación 1 decodifico temperatura en " + ("" + temp).substring(0, 5));
            System.out.println("Humedad: " + RH);
            habitacion.setTemperatura(temp);
            habitacion.setHumedad(valorH);
        }
        return habitacion;
    }

    public int VerificarCabeceras(byte[] DatoIn) {
        byte[] Datos;
        Datos = new byte[DatoIn.length - 4];
        int longitud = DatoIn.length;
        if (DatoIn[0] == "P".getBytes()[0]) {
            if (DatoIn[1] == "A".getBytes()[0]) {
                if (DatoIn[longitud - 1] == "R".getBytes()[0]) {
                    if (DatoIn[longitud - 2] == "M".getBytes()[0]) {
                        for (int i = 0; i < longitud - 4; i++) {
                            Datos[i] = DatoIn[i + 2];
                        }
                        return (longitud - 4);
                    }
                }
            }
        }
        return -1;
    }

    public byte[] solicitarTramaDatosDispositivo(String nombreDispositivo, int idHabitacion, int nuevoEstado) {
        char[] nombreDispcChar = nombreDispositivo.toCharArray();
        byte[] tramadato = new byte[3];
        tramadato[0] = (byte) nombreDispcChar[0]; //nombre dispositivo
        tramadato[1] = (byte) nombreDispcChar[1];
        //-------------Debido a restriccion de hardware----------------------
        if (nombreDispositivo.equals("C1") && idHabitacion == 104 && nuevoEstado == 1) {
            nuevoEstado = 2;
        }
        //-------------------------------------------------------------------
        tramadato[2] = (byte) nuevoEstado; //nuevo estado del dispositivo
        //---aumenta de 3 en 3 porque la info de cada dispositivo tiene 3 bytes------
        if (!(nombreDispcChar[0] == 'A' && nombreDispcChar[1] == 'A')) {
            tramadato[3] = (byte) 'A';
            tramadato[4] = (byte) 'A';
            tramadato[5] = (byte) 0;
        }
        return tramadato;
    }

    /**
     * solicitar Eeprom,Sensor,Variables
     *
     * @param
     * @param
     * @return byte[] trama solicitada
     */
    public byte[] solicitarTrama(int idOrigen, int idDestino, byte[] datos, int estado) {
        int longiDatos = 0;
        if (datos != null) {
            longiDatos = datos.length;
        }
        int longiTotal = 8 + longiDatos;
        byte[] trama;
        trama = new byte[longiTotal];
        int count = -1;
        trama[++count] = 'P';
        trama[++count] = 'A';
        trama[++count] = (byte) (longiDatos);             //Longitud
        trama[++count] = (byte) (idOrigen);               //origen
        trama[++count] = (byte) (idDestino & 0x000000FF); //destino
        trama[++count] = (byte) (estado & 0x000000FF);    //valor de estado que solicita los valores de la eeprom del micro. 
        //---------------Datos--------------
        if (longiDatos > 0 && datos != null) {
            for (int i = 0; i < datos.length; i++) {
                trama[++count] = datos[i];
            }
        }
        //----------------------------------
        trama[++count] = 'M';
        trama[++count] = 'R';
        return trama;
    }

    public byte[] linkedListByteToArrayByte(LinkedList<Byte> lista) {
        byte[] datostemp = new byte[lista.size()];
        for (int i = 0; i < datostemp.length; i++) {
            datostemp[i] = lista.get(i);
            System.out.print("" + datostemp[i] + "("+((char)datostemp[i])+") ");
        }
        return datostemp;
    }
//-----------------------Setters and Getters------------------------------------
}
