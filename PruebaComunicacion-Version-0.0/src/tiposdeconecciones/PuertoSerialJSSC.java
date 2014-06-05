package tiposdeconecciones;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEventListener;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author juangocc
 */
public class PuertoSerialJSSC {

    SerialPort serialPort;
    LinkedList<String> historyread;
    LinkedList<String> historywrite;

    public PuertoSerialJSSC() {
          historyread = new LinkedList<>();
        historywrite = new LinkedList<>();
        String[] pts= getPorts();
        for (int pi = 0; pi < pts.length; pi++) {
            System.out.println(">"+pts[pi]);   
        }
    }

    public static void main(String[] args) {
        PuertoSerialJSSC psjssc = new PuertoSerialJSSC();
    }

    public String[] getPorts() {
        //Method getPortNames() returns an array of strings. Elements of the array is already sorted.
        String[] portNames = SerialPortList.getPortNames();

        System.out.println("" + portNames.length);
        for (int i = 0; i < portNames.length; i++) {
            System.out.println(portNames[i]);
        }
        return portNames;
    }

    public boolean connect(String portPath) {
        try {
            //In the constructor pass the name of the port with which we work
            serialPort = new SerialPort(portPath);
            //Open port
            serialPort.openPort();
            //We expose the settings. You can also use this line - serialPort.setParams(9600, 8, 1, 0);
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Preparing a mask. In a mask, we need to specify the types of events that we want to track.
            //Well, for example, we need to know what came some data, thus in the mask must have the
            //following value: MASK_RXCHAR. If we, for example, still need to know about changes in states 
            //of lines CTS and DSR, the mask has to look like this: SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR
            int mask = SerialPort.MASK_RXCHAR;
            //Set the prepared mask
            serialPort.setEventsMask(mask);
            //Add an interface through which we will receive information about events
            serialPort.addEventListener(spel);
            return true;
        } catch (SerialPortException ex) {
            System.out.println("No se ha podido conectar al puerto " + portPath + ": !!!" + ex);
        }
        return false;
    }

    public void write(String data) {
        try {
            //Writes data to port
            byte[] arraybuffer = data.getBytes();
            serialPort.writeBytes(arraybuffer);
            historywrite.add(data);
        } catch (SerialPortException ex) {
            System.out.println("Error al escribir en : !!!" + ex);
        }
    }

    public boolean closeConnection() {
        try {
            //Closing the port
            serialPort.closePort();
            return true;
        } catch (SerialPortException ex) {
            Logger.getLogger(PuertoSerialJSSC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    SerialPortEventListener spel = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent event) {
            //Object type SerialPortEvent carries information about which event occurred and a value.
            //For example, if the data came a method event.getEventValue() returns us the number of bytes in the input buffer.
            if (event.isRXCHAR()) {
                if (event.getEventValue() == 10) {
                    try {
                        //Read the data of 10 bytes. Be careful with the method readBytes(), if the number of bytes in the input buffer
                        //is less than you need, then the method will wait for the right amount. Better to use it in conjunction with the
                        //interface SerialPortEventListener.
                        byte buffer[] = serialPort.readBytes();
                        historyread.add(new String(buffer));
                    } catch (SerialPortException ex) {
                        System.out.println("Error en el listener del puerto serie en : !!!" + ex);
                    }
                }
            } //If the CTS line status has changed, then the method event.getEventValue() returns 1 if the line is ON and 0 if it is OFF.
            else if (event.isCTS()) {
                if (event.getEventValue() == 1) {
                    System.out.println("CTS - ON");
                } else {
                    System.out.println("CTS - OFF");
                }
            } else if (event.isDSR()) {
                if (event.getEventValue() == 1) {
                    System.out.println("DSR - ON");
                } else {
                    System.out.println("DSR - OFF");
                }
            }
        }
    };
}
