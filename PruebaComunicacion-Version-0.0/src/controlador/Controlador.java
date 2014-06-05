/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import GUI.JFConnecciones;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextArea;
import tiposdeconecciones.PuertoSerialRXTX;

/**
 *
 * @author juangocc
 */
public class Controlador {

    JFConnecciones jfc;
    PuertoSerialRXTX psrxtx;

    public Controlador() {
        psrxtx = new PuertoSerialRXTX();
        jfc = new JFConnecciones();
        jfc.setSubscriptionEvents(al, kl, wl);
        jfc.setVisible(true);
    }

    public static void main(String[] args) {
        Controlador c = new Controlador();
    }
    ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Object obj = e.getSource();
            if (obj instanceof JButton) {
                JButton btn = (JButton) obj;

                if (btn.getName().equals("jBActualizar")) {
                    String[] portsrxtx = psrxtx.getPorts();
                    jfc.getjCBPortsRXTX2().removeAllItems();
                    for (String portpath : portsrxtx) {
                        jfc.getjCBPortsRXTX2().addItem(portpath);
                    }
                }
                if (btn.getName().equals("jBConectarRXTX2")) {
                    String port = jfc.getjCBPortsRXTX2().getSelectedItem().toString();
                    if (psrxtx.connect(port,sl)) {
                        jfc.getjBConectarRXTX2().setEnabled(false);
                        jfc.getjBDesconectarRXTX2().setEnabled(true);
                    }
                }
                if (btn.getName().equals("jBDesconectarRXTX2")) {
                    if (psrxtx.closeConnection()) {
                        jfc.getjBConectarRXTX2().setEnabled(true);
                        jfc.getjBDesconectarRXTX2().setEnabled(false);
                    }
                }
            }
        }
    };
    KeyListener kl = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Object obj = e.getSource();
            if (obj instanceof JTextArea) {
                JTextArea jta = (JTextArea) obj;
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    String msg = jta.getText();
                    System.out.println("Enviado : " + msg);
                    psrxtx.SerialWriter(msg);
                    llenarjTARXTX("Puerto local>" + msg);
                    jta.setText("");
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };
    WindowListener wl = new WindowListener() {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            psrxtx.closeConnection();
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    };
    private SerialPortEventListener sl = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent event) {
            String rawInput;
            switch (event.getEventType()) {
                case SerialPortEvent.BI:
                case SerialPortEvent.CD:
                case SerialPortEvent.CTS:
                case SerialPortEvent.DSR:
                case SerialPortEvent.FE:
                case SerialPortEvent.OE:
                case SerialPortEvent.PE:
                case SerialPortEvent.RI:
                case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                    break;
                case SerialPortEvent.DATA_AVAILABLE:
                    try {
                        BufferedReader inStream = psrxtx.getInStream();
                        while ((rawInput = inStream.readLine()) != null) {
                            llenarjTARXTX("Puerto remoto>" + rawInput);
                        }
                        inStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(PuertoSerialRXTX.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public void llenarjTARXTX(String msg) {
        psrxtx.getHistory().add(msg);
        String content = "";
        for (String txt : psrxtx.getHistory()) {
            content += txt + "\n";
        }
        jfc.getjTARXTX2Read().setText(content);
    }
}
