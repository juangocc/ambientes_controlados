package com.domotica.controller;

import com.domotica.core.Dispositivo;
import com.domotica.core.Habitacion;
import com.domotica.logic.Sistema;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Timer;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import com.domotica.interfaz.JFPrincipal;
import javax.swing.JButton;
import javax.swing.JTree;

/**
 *
 * @author juangocc
 */
public class Controlador {

    Sistema sistema;
    JFPrincipal jfprincipal;

    public Controlador() {
        sistema = new Sistema();
        sistema.setSubscripcionEventos(spel);
        this.ArranqueInicial();

//        new Thread(new TimerTemperaturas(1000 * 40, 5000)).start();//tramadelegate sensar habitaciones
        jfprincipal = new JFPrincipal();
        jfprincipal.setSubscripcionEventos(al);

        this.initTree();
    }
    ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton obj = (JButton) e.getSource();
            if (obj.getName().equals("jBModificar")) {
                System.out.println("hree");
            }

//            for (int i = 0; i < panelesresumen.size(); i++) {
//                if (evt.getSource() == panelesresumen.get(i)) {
//                    //Se encarga de programar una tarea o cancelar alguna existente para la habitación i
//                    //mostrarTareasActivas(i);
//                    //establecerNuevaTarea(i);
//                    //cancelarTarea(i);
//                    String tiempoencendido;
//                    String intervalo;
//                    String nivel;
//                    break;
//                }
//            }
        }
    };
    SerialPortEventListener spel = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent event) {
            try {
                int nBytes = 0;
                int a = 0;
                int cont = 0;
                String Cad_Binarios = "";
                String DatoRecibido;
                LinkedList<Byte> vectDatoRecibido = new LinkedList<>();
                switch (event.getEventType()) {
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
                        System.out.println("Hay datos disponibles");
                        int temp;
                        InputStream entrada = sistema.getSerialdelegate().getEntrada();
                        a = entrada.available();
                        byte[] bufferLectura = new byte[a];
                        DatoRecibido = "";
                        while (entrada.available() > 0) {
                            nBytes = entrada.read(bufferLectura);
                            for (int i = 0; i < nBytes; i++) {
                                DatoRecibido = DatoRecibido + Byte.toString(bufferLectura[i]);
                                temp = (Byte.valueOf(bufferLectura[i])).intValue();
                                Cad_Binarios = Cad_Binarios + Integer.toBinaryString(temp & 0xFF) + "\n";
                                vectDatoRecibido.add(bufferLectura[i]);
                            }
                            Thread.sleep(1000);
                            cont = cont + entrada.available();
                            Habitacion habact = sistema.getTramadelegate().decodificarTrama(vectDatoRecibido);
                            System.out.println("----");
                            sistema.getSistemaDAO().agregarHistoricoHabitacion(habact.getIdSensor(), habact.getTemperatura(), habact.getVentilacion(), habact.getIluminacion(), habact.getHumedad());
                        }

                        // ----EstadisticaDAO estadisticaDAO=new EstadisticaDAO(); //para setear los promedios le�dos
                        //------Archivar la temperatura en el archivo cvs
                        break;
                }
            } catch (IOException | InterruptedException ex) {
                System.out.println("Error en evento serial event !!!" + ex);
            }
        }
    };

    public void setjTree() {
    }
    private TreeSelectionListener tsl = new TreeSelectionListener() {
        @Override
        public void valueChanged(TreeSelectionEvent evt) {
            JTree jTree1 = (JTree) evt.getSource();
            if (jTree1.isSelectionEmpty()) {
                jTree1.setSelectionRow(0);
            }
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
            Object obj = ((jTreeContent) node.getUserObject()).getDato();
            if (obj instanceof Sistema) {
                jfprincipal.aderirEventos("<div style=\"background-color:rgba(100,100,100,0.7);\">Es el sistema</div>");
                Sistema sis = (Sistema) obj;
                jfprincipal.setDatosSistema(sis.getNombre(), sis.getId(), sis.getEstado(), sis.getModoOperacion(), sis.getDescripcion());
            }
            if (obj instanceof Habitacion) {
                System.out.print("Es una habitación");
                Habitacion hab = (Habitacion) obj;
                jfprincipal.setDatosHabitacion(hab.getId(), hab.getEstado(), hab.getDescripcion(), hab.getIdSensor(), hab.getHumedad(), hab.getTempRef(), (int) hab.getTemperatura(), hab.getIluminacion(), hab.getHumedadIdeal(), hab.getVentilacion(), hab.getIluminacionIdeal(), hab.getAutomatico(), hab.getTemperaturaIdeal(), hab.getVentilacionIdeal());
            }
            if (obj instanceof Dispositivo) {
                System.out.print("Es un dispositivo");
                Dispositivo disp = (Dispositivo) obj;
                jfprincipal.setDatosDispositivo(disp.getNombre(), disp.getId(), disp.getEstado(), disp.getValor(), disp.getTipo(), disp.getDescripcion());
            }
            System.out.println(" ,Se ha aderido a la selección " + evt.getNewLeadSelectionPath());
        }
    };

    private void ArranqueInicial() {
        Thread hilArranqueInicial = new Thread(new Runnable() {
            @Override
            public void run() {
                sistema.variablesHabitaciones();
                sistema.sensarHabitaciones();
            }
        });
        hilArranqueInicial.start();
    }

    private void initTree() {
        int desfase = 50;
        CustomDefaultMutableTreeNode dmtn_root = new CustomDefaultMutableTreeNode(new jTreeContent<>(sistema, "Sistema"));
        for (Habitacion habitacion : sistema.getListaHabitaciones()) {
            CustomDefaultMutableTreeNode dmtn_hab = new CustomDefaultMutableTreeNode(new jTreeContent<>(habitacion, habitacion.getIdSensor() + ""));
            for (Dispositivo dispositivo : habitacion.getDispositivos()) {
                CustomDefaultMutableTreeNode dmtn_disp = new CustomDefaultMutableTreeNode(new jTreeContent<>(dispositivo, dispositivo.getNombre()));
                dmtn_hab.add(dmtn_disp);
            }
            dmtn_root.add(dmtn_hab);
        }
        //    CustomDefaultMutableTreeNode root = (CustomDefaultMutableTreeNode) model.getRoot();
//        model.insertNodeInto(dmtn_nuevo, root, root.getChildCount());
        jfprincipal.setDatosArbol(new DefaultTreeModel(dmtn_root), tsl);
// le manda a trama delegate los paneles resumen para que los mantenga actualizados
    }

    public void mostrarTareasActivas(int idhabitacion) {
        LinkedList<Timer> tareastimers = sistema.getHabitacion(idhabitacion).getTiemposextractores();
        if (tareastimers.isEmpty()) {
            //se puede parar si hay mas tareas
            //no se puede programar tarea
            //mostrar alarma (imagen)
        } else {
            //no se puede parar porque no hay mas tareas
            //se puede programar tarea
            //no mostrar alarma (imagen)
            System.out.println("Error no hay tiempos establecidos en programación extractores !!!");
        }
    }

    public void establecerNuevaTarea(int idhabitacion) {
        int nivel;
        boolean habilitadonivelmedio = true;
        if (habilitadonivelmedio) {//nivel medio
            nivel = 2 + 1 + 8;
        } else {//nivel alto
            nivel = 4;
        }
        int tiempoencendido = 30;//30 minutos
        int intervalo = 5;//5 minutos
        sistema.crearTarea(idhabitacion, intervalo, tiempoencendido, nivel);
        //se muestra la alarma (imagen)
    }

    public void cancelarTarea(int idhabitacion, int idtask) {
        Timer timer = sistema.getHabitacion(idhabitacion).getTiemposextractores().get(idtask);
        timer.cancel();
        sistema.getHabitacion(idhabitacion).getTiemposextractores().remove(timer);
        //se puede parar si hay mas tareas
        //se puede programar tarea
        //no mostrar alarma (imagen)
    }

    public void actualizarDatosPanelResumen(int indexPanelResumen) {
    }

    //No olvidar poner colores para destacar el estado del dispositivo
    public void actualizarValores(int Habitacion) {
// revisar cuales son los estados de los dispositivos y mostrarlos
    }

    public void actualizarReferencia(int Habitacion) {
        int tempref = Integer.parseInt(JOptionPane.showInputDialog("Digite una temperatura de referencia : "));
        this.sistema.getHabitacion(Habitacion).setTempRef(tempref);
        this.sistema.getHabitacion(Habitacion).setAutomatico(true);
        if (sistema.getHabitacion(Habitacion).getTempRef() == -1) {
            System.out.println("Con temperatura de referencia igual a -1 la habitación " + Habitacion + "!!!");
        } else {
            System.out.println("La temperatura de referencia de la habitación " + Habitacion + " es " + sistema.getHabitacion(Habitacion).getTempRef());
        }
    }

    public void actualizar(int Habitacion) {
        sistema.sensarHabitacion(Habitacion);
    }
    // se manda a tramadelegate el label temperatura para que lo pueda actualizar cuando cambie

    public void setControl(int Habitacion, boolean modoAuto) {
        if (modoAuto) {// modo automatico
            actualizarReferencia(Habitacion);
        } else { //modo manual
            sistema.getHabitacion(Habitacion).setAutomatico(false);
        }
    }
    //revisar constantemente

    public void runRegiones(final int Habitacion) {
        actualizarValores(Habitacion);
        boolean mostrar = sistema.getHabitacion(Habitacion).getAutomatico();
        if (mostrar) {
            // mostra los niveles (panel con regiones)
            String EstadoAutoHab = sistema.getHabitacion(Habitacion).getEstado();
            //Alertar de alguna manera diferente cada estado de la habitación
            System.out.println("La habitación tiene un estado : " + EstadoAutoHab);
        } else {
            //no mostra los niveles (panel con regiones)
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Controlador c = new Controlador();
    }
}
