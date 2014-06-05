/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiposdeconecciones;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juangocc
 */
public class PuertoSerialRXTX {

    private BufferedReader inStream;
    OutputStream out;
    CommPort commPort;
    private LinkedList<String> history;

    public PuertoSerialRXTX() {
        history = new LinkedList<>();
        String nombrePuertoDefecto;
        switch (System.getProperty("os.name")) {
            case "Linux":
                nombrePuertoDefecto = "/dev/pts/1";
                break;
            case "Windows XP":
                nombrePuertoDefecto = "COM1";//TODO revisar cual es el puerto
                break;
            default:
                nombrePuertoDefecto = "COM4";
                break;
        }
        //psrxtx.connect(nombrePuertoDefecto);
    }

    public boolean connect(String portPath, SerialPortEventListener sl) {
        try {
            System.setProperty("gnu.io.rxtx.SerialPorts", portPath);
            Enumeration ports = CommPortIdentifier.getPortIdentifiers();
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portPath);
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Error: Port is currently in use");
            } else {
                System.out.println("Conección estableciendo...");
                commPort = portIdentifier.open(this.getClass().getName(), 2000);
                if (commPort instanceof SerialPort) {
                    SerialPort serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(57600, 
                                    SerialPort.DATABITS_8, 
                                    SerialPort.STOPBITS_1, 
                                    SerialPort.PARITY_NONE);
                    setInStream(new BufferedReader(new InputStreamReader(serialPort.getInputStream())));
                    out = serialPort.getOutputStream();
                    serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                    serialPort.addEventListener(sl);
                    // Advise if data available to be read on the port
                    serialPort.notifyOnDataAvailable(true);
                    return true;
                } else {
                    System.out.println("Error: Only serial ports are handled by this example.");
                }
            }
        } catch (NoSuchPortException | PortInUseException | UnsupportedCommOperationException | IOException | TooManyListenersException ex) {
            System.out.println("No se ha podido conectar al puerto " + portPath + ": !!!" + ex);
        }
        return false;
    }

    /**
     *
     */
    public void SerialWriter(String data) {
        try {
            out.write(data.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(PuertoSerialRXTX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean closeConnection() {
        try {
            if (out == null) {
                return false;
            }
            System.out.println("Conección cerrando...");
            out.close();

            out = null;
            commPort.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(PuertoSerialRXTX.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String[] getPorts() {
        boolean encuentra = true;
        int count = 0;
        LinkedList<String> listapuertos = new LinkedList<>();
        while (encuentra) {
            System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/pts/" + count); //add this line ttyACMx [0,1,2,..]
            Enumeration ports = CommPortIdentifier.getPortIdentifiers();
            if (!ports.hasMoreElements()) {
                encuentra = false;
            }
            while (ports.hasMoreElements()) {
                CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
                if (!listapuertos.contains(port.getName())) {
                    listapuertos.add(port.getName());
                }
            }
            count++;
        }
        String[] puertos = new String[listapuertos.size()];
        for (int i = 0; i < listapuertos.size(); i++) {
            puertos[i] = listapuertos.get(i);
        }
        return puertos;
    }

    /**
     * @return the history
     */
    public LinkedList<String> getHistory() {
        return history;
    }

    /**
     * @param history the history to set
     */
    public void setHistory(LinkedList<String> history) {
        this.history = history;
    }

    /**
     * @return the inStream
     */
    public BufferedReader getInStream() {
        return inStream;
    }

    /**
     * @param inStream the inStream to set
     */
    public void setInStream(BufferedReader inStream) {
        this.inStream = inStream;
    }
}