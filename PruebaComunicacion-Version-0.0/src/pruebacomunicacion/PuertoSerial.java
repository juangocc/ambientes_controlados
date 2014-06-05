package pruebacomunicacion;

import gnu.io.*;
import java.util.*;
import java.io.*;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Nombre de clase: PuertoSerial
 */
public class PuertoSerial implements SerialPortEventListener {

    String nomPuerto;
    static CommPortIdentifier idPuerto;
    static SerialPort puertoSerie;
    OutputStream salida;
    InputStream entrada;
    String DatoRecibido;
    private int velocidad;
    private int bitsDatos;
    private int paridad;
    private int bitParada;
//    public int estado;
    private JTextArea CajaTexto;
    private JTextArea CajaTexto2;
    private JLabel labelVentana;
    private byte[] bufferLectura;
    LinkedList<Byte> vectDatoRecibido;

    public PuertoSerial(String str) {
        nomPuerto = str;

        DatoRecibido = "";
//        estado = 0;
        vectDatoRecibido = new LinkedList<>();
        inicializarPuerto();

    }
//
//    public void run() {
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//        }
//    }

    private void inicializarPuerto() {
        try {
            idPuerto = CommPortIdentifier.getPortIdentifier(nomPuerto);
            if (idPuerto.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                puertoSerie = (SerialPort) idPuerto.open("AplEscritura", 2000);
                ConfigurarPuerto(9600,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                // Se añade un receptor de eventos para estar informados de lo
                // que suceda en el puerto
                puertoSerie.addEventListener(this);
                // Hacemos que se nos notifique cuando haya datos disponibles 
                // para lectura en el buffer de la puerta
                puertoSerie.notifyOnDataAvailable(true);
//            Thread tLectura = new Thread(this);
//            tLectura.start();
            } else {
                System.out.println("No se pudo abrir el puerto:" + nomPuerto);
            }
        } catch (NoSuchPortException | PortInUseException | TooManyListenersException ex) {
            System.out.println("Error en : !!!" + ex);
        }
    }

    /**
     * invoca el método serSerialPortParams, el cual configura el puerto serial
     * con los parametros que recibe de ConfigurarPuerto.
     */
    public void ConfigurarPuerto(int Tx, int DataBits, int StopBits, int Parity) {
        try {
            salida = puertoSerie.getOutputStream();
            puertoSerie.setSerialPortParams(Tx,
                    DataBits,
                    StopBits,
                    Parity);
            setVelocidad(Tx);
            setBitsDatos(DataBits);
            setBitParada(StopBits);
            setParidad(Parity);
            entrada = puertoSerie.getInputStream();
        } catch (UnsupportedCommOperationException | IOException ex) {
            System.out.println("Error al configurar puerto !!!" + ex);
        }
    }

    @Override
    public void serialEvent(SerialPortEvent _ev) {
        String Cad_Binarios = "";
        try {
            int nBytes = 0;
            int a = 0;
            int cont = 0;
            int cont2 = 0;
            switch (_ev.getEventType()) {
                case SerialPortEvent.BI:
                case SerialPortEvent.OE:
                case SerialPortEvent.FE:
                case SerialPortEvent.PE:
                case SerialPortEvent.CD:
                case SerialPortEvent.CTS:
                case SerialPortEvent.DSR:
                case SerialPortEvent.RI:
                case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                    break;
                // Cuando haya datos disponibles se leen y luego se
                // imprime
                case SerialPortEvent.DATA_AVAILABLE:
                    double p = 0;
                    // Thread.sleep(100);
                    a = entrada.available();
                    cont = entrada.available();
                    System.out.println("a:" + Integer.toString(a));
                    setBufferLectura(new byte[a]);
                    int temp;
                    int tam;
                    int contTramasJuego = 0,
                     contTramasAdmon = 0;
                    Vector VectTemp;
                    Vector DatoVects = new Vector();

                    try {
                        CajaTexto.setText("");
                        CajaTexto2.setText("");
                        while (entrada.available() > 0) {

                            nBytes = entrada.read(getBufferLectura());

                            for (int i = 0; i < nBytes; i++) {
                                DatoRecibido = DatoRecibido + Byte.toString(getBufferLectura()[i]) + "\n";

                                temp = (Byte.valueOf(getBufferLectura()[i])).intValue();
                                Cad_Binarios = Cad_Binarios + Integer.toBinaryString(temp & 0xFF) + "\n";
                                CajaTexto.append(Integer.toBinaryString(temp & 0xFF));
                                CajaTexto2.append(String.valueOf((char) temp));
                                CajaTexto.append("\n");
                                CajaTexto2.append("\n");
                                vectDatoRecibido.add(getBufferLectura()[i]);
                                cont2++;

                            }
                            Thread.sleep(20);
                            cont = cont + entrada.available();
                        }//fibn de while(entrada.available()>0)


                        //a = entrada.available();                        

                        //EscribirDato("Longitud:" + Integer.toString(cont2)); 

                        /* byte[] Datos; 
                         Datos = new byte[28]; 
                         int longitud; 
                         longitud = VerificarCabeceras(vectDatoRecibido,Datos);
                         int SOt,SOh;
                         double T,H,H8; 
                            
                         SOt = (Datos[6] & 0x00FF) | ((Datos[9]&0x00FF) << 8);  
                         T = -40.1 + 0.01*SOt; 
                            
                         SOh = (Datos[12] & 0x00FF) | ((Datos[15]&0x00FF) << 8);
                         H = -2.0468+0.0367*SOh + (-1.5955e-6)*SOh*SOh; 
                         H8= -2.0468+0.5872*SOh + (-4.0845e-4)*SOh*SOh; 
                         System.out.println("Temperatura: "+ String.valueOf(T)); 
                         System.out.println("Humedad12: " + String.valueOf(H)); 
                         System.out.println("Humedad8: " + String.valueOf(H8)); 
                         */
                        DatoRecibido = "";
                        vectDatoRecibido.clear();
                        cont = 0;
                        cont2 = 0;
                        Cad_Binarios = "";
                    } catch (IOException ex) {
                        System.out.println("Error switch serial event en : !!!" + ex);
                    }
                    break;
            }
        } catch (InterruptedException | IOException ex) {
            System.out.println("Error serial event en : !!! " + ex);
        }
    }

    public void EscribirPuerto(String Dato) throws IOException, PortInUseException {
        salida = puertoSerie.getOutputStream();
        salida.write(Dato.getBytes());
        salida.close();
    }

    public void EscribirPuerto(byte[] Dato) throws IOException, PortInUseException {
        salida = puertoSerie.getOutputStream();
        salida.write(Dato);
        salida.close();
    }

    public void closeConnection() {
        try {
            puertoSerie.close();
            entrada.close();
            salida.close();
            System.out.println("Conección cerrada con éxito !!!");
        } catch (IOException ex) {
            System.out.println("Error al cerrar conección : !!! " + ex);
        }
    }
//-------------------------------------------------------------------------
    public int VerificarCabeceras(byte[] DatoIn, byte[] Datos) {
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

    public int VerificarCabeceras(Vector DatoIn, byte[] Datos) {
        byte[] datostemp = new byte[DatoIn.size()];
        int i = 0;
        for (Iterator it = DatoIn.iterator(); it.hasNext();) {
            datostemp[i] = (Byte) it.next();
            i++;
        }
        return (VerificarCabeceras(datostemp, Datos));
    }

    private Vector procesarVariasTramas(Vector Datos) {

        Vector vectVectores = new Vector();
        int bandD = 0;
        Byte temp, temp2;

        for (Iterator it = Datos.iterator(); it.hasNext();) {

            temp = ((Byte) it.next());

            if (temp == 'd') {
                temp2 = ((Byte) it.next());

                if (temp2 == 'v') {
                    vectVectores.add(new Vector());
                    ((Vector) vectVectores.lastElement()).add((byte) 'd');
                    ((Vector) vectVectores.lastElement()).add((byte) 'v');
                } else {
                    ((Vector) vectVectores.lastElement()).add(temp);
                    ((Vector) vectVectores.lastElement()).add(temp2);
                }
            } else {
                ((Vector) vectVectores.lastElement()).add(temp);
            }
        }
        return vectVectores;
    }
    //    public boolean VerificarCRC(byte[] DatoIn, byte[] Datos) {
//        clase_CRC calcCrc = new clase_CRC();
//        byte[] CRCin, CRCcal;
//
//        CRCin = new byte[2];
//        CRCcal = new byte[2];
//
//
//        int longitud = DatoIn.length;
//
//        for (int i = 0; i < longitud - 2; i++) {
//            Datos[i] = DatoIn[i];
//        }
//        CRCin[0] = DatoIn[longitud - 2];
//        CRCin[1] = DatoIn[longitud - 1];
//
//        CRCcal = calcCrc.CalcularCRC(Datos);
//        if (CRCcal[0] == CRCin[0] && CRCcal[1] == CRCin[1]) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    //-----------------------------Setters and Getters--------------------------

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getBitsDatos() {
        return bitsDatos;
    }

    public void setBitsDatos(int bitsDatos) {
        this.bitsDatos = bitsDatos;
    }

    public int getParidad() {
        return paridad;
    }

    public void setParidad(int paridad) {
        this.paridad = paridad;
    }

    public int getBitParada() {
        return bitParada;
    }

    public void setBitParada(int bitParada) {
        this.bitParada = bitParada;
    }

    public JTextArea getCajaTexto() {
        return CajaTexto;
    }

    public void setCajaTexto(JTextArea CajaTexto) {
        this.CajaTexto = CajaTexto;
    }

    public JLabel getLabelVentana() {
        return labelVentana;
    }

    public void setLabelVentana(JLabel labelVentana) {
        this.labelVentana = labelVentana;
    }

    public byte[] getBufferLectura() {
        return bufferLectura;
    }

    public void setBufferLectura(byte[] bufferLectura) {
        this.bufferLectura = bufferLectura;
    }

    public JTextArea getCajaTexto2() {
        return CajaTexto2;
    }

    public void setCajaTexto2(JTextArea CajaTexto2) {
        this.CajaTexto2 = CajaTexto2;
    }
}
