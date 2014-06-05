package com.domotica.delegat;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

/**
 * Clase que se encarga de establecer la comunicaci�n con el puerto serial y
 * enviar y recibir mensajes de este
 */
public class SerialDelegate {

    private OutputStream salida;
    private InputStream entrada;
    CommPort commPort;
    SerialPort serialPort;

    public SerialDelegate(SerialPortEventListener spel) {
        String nombrePuertoDefecto;
        switch (System.getProperty("os.name")) {
            case "Linux":
                //nombrePuertoDefecto = "/dev/ttyACM0";
                nombrePuertoDefecto = "/dev/pts/1";
                break;
            case "Windows XP":
                nombrePuertoDefecto = "COM1";//TODO revisar cual es el puerto
                break;
            default:
                nombrePuertoDefecto = "COM4";
                break;
        }
        inicializarPuerto(nombrePuertoDefecto, spel);
    }

    private void inicializarPuerto(String nombrePuerto, SerialPortEventListener spel) {
        try {
            System.out.println("-------------------" + nombrePuerto);
            System.setProperty("gnu.io.rxtx.SerialPorts", "" + nombrePuerto);
            Enumeration ports = CommPortIdentifier.getPortIdentifiers();
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(nombrePuerto);
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Error: Port is currently in use");
            } else {
                System.out.println("Conección estableciendo...");
                commPort = portIdentifier.open(this.getClass().getName(), 2000);
                if (commPort instanceof SerialPort) {
                    serialPort = (SerialPort) commPort;

                    serialPort.setSerialPortParams(9600,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);
                    salida = serialPort.getOutputStream();
                    setEntrada(serialPort.getInputStream());
                    serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                    serialPort.addEventListener(spel);
                    // Advise if data available to be read on the port
                    serialPort.notifyOnDataAvailable(true);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al inicializar puerto " + nombrePuerto + " en !!!" + ex);
        }
    }

//    public boolean EscribirPuerto(byte Dato) {
//        if (this.isConnected()) {
//            try {
//                this.salida.write(Dato);
//                this.salida.close();
//                return true;
//            } catch (IOException ex) {
//                System.out.println("Error al escribir en el Puerto !!!" + ex);
//            }
//        }
//        return false;
//    }

    public boolean EscribirPuerto(byte[] Dato) {
        String texto=new String(Dato,0,Dato.length);
        System.out.println("Enviando : "+texto);
        //mostrarArrayBytes(Dato);
        if (this.isConnected()) {
            try {
                this.salida.write(Dato);
                this.salida.close();
                return true;
            } catch (IOException ex) {
                System.out.println("Error al escribir puerto !!!" + ex);
            }
        }
        return false;
    }
    public void mostrarArrayBytes(byte[] Dato){
        String output="";
        for (byte b : Dato) {
            output+=b;
        }
        System.out.println("Bytes Array : "+output);
    }

    public boolean isConnected() {
        return (this.serialPort != null);
    }
    //----------------------------Setters and Getters---------------------------

    /**
     * @return the entrada
     */
    public InputStream getEntrada() {
        return entrada;
    }

    /**
     * @param entrada the entrada to set
     */
    public void setEntrada(InputStream entrada) {
        this.entrada = entrada;
    }
}
