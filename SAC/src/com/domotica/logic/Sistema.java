package com.domotica.logic;

import com.domotica.core.Habitacion;
import com.domotica.delegat.SerialDelegate;
import com.domotica.delegat.SistemaDAO;
import gnu.io.SerialPortEventListener;
import java.util.LinkedList;
import java.util.Timer;

public class Sistema {

    private String nombre;
    private String descripcion;
    private String estado;
    private int id;
    int modoOperacion;
    boolean LimpiarAA = false;
    private SerialDelegate serialdelegate;
    private SistemaDAO sistemaDAO;
    private TramaFactory tramadelegate;
    LinkedList<Habitacion> habitaciones;

    public Sistema() {
        this.nombre = "Sistema de Ambientes Controlados - Domótica";
        this.id = 3;
        this.habitaciones = new LinkedList<>();
        this.tramadelegate = new TramaFactory();

        this.modoOperacion = 1;
        //---------------------cargar Habitaciones de archivo
        sistemaDAO = new SistemaDAO();
        habitaciones = sistemaDAO.getHabitaciones();
        //----------------------------------------------------------------------
        //cada  minuto actualiza el arreglo de habitaciones y las variables temperatura y humedad
        // llamar a metodo para solicitar las variables temp y humedad de toas las habitacioes
        //TODO hilo que cada mes actualiza el archivo csv de estadisticas
        //recuperar del XML los datos
        //la fecha para el nombre del archivo CVS mes - anio .csv
    }
    //-----------------------------------------------------------------

    public void setSubscripcionEventos(SerialPortEventListener spel) {
        this.serialdelegate = new SerialDelegate(spel);
    }
    //-----------------------------------------------------------------
    //método que realiza el control automático de las habitaciones

    public void controlarHabitaciones() {
        //System.out.println("YO TENGO EL CONTROL!!!");
        //TODO Poder cambiar los valores ideales en el XML
        //se hace un ciclo periódicamente por todas las habitaciones
        //verificar las condiciones ideales
        //verificar las condiciones actuales
        //si son diferentes y están por fuera del humbral
        //operar los dispositivos de acuerdo con los criterios establecidos
    }
    //------------------------------------------------------------------------

    public boolean controlAutomatico(double TActual, int idHab) {
        double error = 0;
        Habitacion habitacion = getHabitacion(idHab);
        if (habitacion != null) {
            error = habitacion.getTempRef() - TActual;
            System.out.println("Error para la Habitacion " + habitacion.getIdSensor() + " es:" + error);
        } else {
            return false;
        }

        if (error >= 0.3) {
            System.out.println("REGION ALTA POSITIVA");
            habitacion.setEstado("ALTAPOSITIVA");
            habitacion.aumentarTemperaturaRegionAlta();
        }

        if (error >= 0 && error < 0.3) //Region media Positiva
        {
            System.out.println("REGION MEDIA POSITIVA");
            habitacion.aumentarTemperaturaRegionMedia();
        }

        if (error <= -0.5 && error > -0.7) {
            System.out.println("REGION MEDIA NEGATIVA");
            habitacion.reducirTemperaturaRegionMedia();
        }
        if (error <= -0.7) {
            System.out.println("REGION ALTA NEGATIVA");
            habitacion.reducirTemperaturaRegionAlta();

        }
        if (error < 0 && error > -0.5) {
            System.out.println("REGION IDEAL");
            habitacion.RegionIdeal();
        }
        //-----------------controlar la humedad---------------------------------
        int humed = habitacion.getHumedad();
        int humedIdeal = habitacion.getHumedadIdeal();
        if (humed > humedIdeal) {
            //mirar en que parte del umbral se encuentra
            if (humed - humedIdeal > 5) { //est� en el rango del extremo
                habitacion.reducirHumedadRegionAlta();
            } else if ((humed - humedIdeal <= 5) && (humed - humedIdeal > 1)) { //esta en el rango cercano
                habitacion.reducirHumedadRegionMedia();
            }
        } else if (humed < humedIdeal) {
            //mirar en que parte del umbral se encuentra
            if ((humedIdeal - humed <= 5) && (humedIdeal - humed > 1)) { //esta en el rango cercano
                habitacion.aumentarHumedadRegionMedia();
            } else if (humedIdeal - humed > 5) { //est� en el rango del extremo
                habitacion.aumentarHumedadRegionAlta();
            }
        }
        byte[] bdatos = this.tramadelegate.solicitarTramaDatosDispositivo("AA", idHab, 0);
        this.tramadelegate.solicitarTrama(100, idHab, bdatos, 0);
        this.tramadelegate.solicitarTrama(100, idHab, bdatos, 2);//EEPROM
        return true;
    }

    public void crearTarea(int idhabitacion, int intervalo, int tiempoencendido, int nivel) {
        TareaTimerProgExtractores tarea = new TareaTimerProgExtractores(tiempoencendido, nivel, idhabitacion);
        tarea.tramas = tramasPorDefecto();
        Timer timer = new Timer("TimerEH" + idhabitacion);
        timer.schedule(tarea, 0, intervalo * 60 * 1000L);
        getHabitacion(idhabitacion).getTiemposextractores().add(timer);
    }
//Se puede hacer en interfaz de usuario

    public LinkedList<byte[]> tramasPorDefecto() {
        LinkedList<byte[]> listatramas;
        listatramas = new LinkedList<>();
        LinkedList<byte[]> listatramas_final;
        listatramas_final = new LinkedList<>();
        int nivel = 4;
        int idHabitacion = 102;
        int encendido = 100;
        try {
            byte[] baux;
            byte[] datos_aux;
            //secuencia de encendido
            System.out.println("ENCENDIENDO EXTRACTORES");
            System.out.println("E1  = medio");
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E1", idHabitacion, 2 + 1 + 8);
            listatramas.add(datos_aux);
            System.out.println("E2  = medio");
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E2", idHabitacion, 2 + 1 + 8);
            listatramas.add(datos_aux);
            System.out.println("E1  = medio/alto");
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E1", idHabitacion, 2 + 1 + 8 + 4);
            listatramas.add(datos_aux);
            System.out.println("E2  = medio/alto");
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E2", idHabitacion, 2 + 1 + 8 + 4);
            listatramas.add(datos_aux);
            System.out.println("E1  = alto");
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E1", idHabitacion, 4);
            listatramas.add(datos_aux);
            System.out.println("E2  = alto");
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E2", idHabitacion, 4);
            listatramas.add(datos_aux);
            System.out.println("APAGANDO EXTRACTORES");
            //SECUENCIA DE APAGADO.
            System.out.println("medio/alto");
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E1", idHabitacion, 2 + 1 + 8 + 4);
            listatramas.add(datos_aux);
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E2", idHabitacion, 2 + 1 + 8 + 4);
            listatramas.add(datos_aux);
            System.out.println("medio");
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E1", idHabitacion, 2 + 1 + 8);
            listatramas.add(datos_aux);
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E2", idHabitacion, 2 + 1 + 8);
            listatramas.add(datos_aux);
            System.out.println("bajo");
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E1", idHabitacion, 2 + 1 + 8);
            listatramas.add(datos_aux);
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E2", idHabitacion, 2 + 1 + 8);
            listatramas.add(datos_aux);
            System.out.println("apagado");
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E1", idHabitacion, 0);
            listatramas.add(datos_aux);
            datos_aux = tramadelegate.solicitarTramaDatosDispositivo("E2", idHabitacion, 0);
            listatramas.add(datos_aux);
            for (byte[] datos : listatramas) {
                baux = tramadelegate.solicitarTrama(100, idHabitacion, datos_aux, 0);
                listatramas_final.add(baux);
                Thread.sleep(2000);
            }
        } catch (InterruptedException ex) {
            System.out.println("Error en tareatimerprogextractores !!!" + ex);
        }
        return listatramas_final;
    }

    public LinkedList<Habitacion> getListaHabitaciones() {
        return habitaciones;
    }

//    public Habitacion getHabitacion(int idSensor) {
//        for (Habitacion habitacion : habitaciones) {
//            if (habitacion.getIdSensor()==idSensor) {
//                return habitacion;
//            }
//        }
//        return null;
//    }
    public Habitacion getHabitacion(int idHabitacion) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId() == idHabitacion) {
                return habitacion;
            }
        }
        return null;
    }

    public void sensarHabitacion(int idHabitacion) {
        Habitacion hab = getHabitacion(idHabitacion);
        byte[] tramasensado = tramadelegate.solicitarTrama(100, hab.getIdSensor(), null, 1);
        getSerialdelegate().EscribirPuerto(tramasensado);
    }

    public void variablesHabitaciones() {
        for (Habitacion habitacion : habitaciones) {
            byte[] tramasensado = tramadelegate.solicitarTrama(100, habitacion.getId(), null, 1);
            System.out.println("Enviando trama variables a Hab: " + habitacion.getIdSensor());
            getSerialdelegate().EscribirPuerto(tramasensado);
        }
    }

    public void sensarHabitaciones() {
        for (Habitacion habitacion : habitaciones) {
            byte[] tramasensado = tramadelegate.solicitarTrama(100, habitacion.getIdSensor(), null, 1);
            System.out.println("Enviando trama sensado a Hab: " + habitacion.getIdSensor());
            getSerialdelegate().EscribirPuerto(tramasensado);
        }
    }
    //----------------------Setters and Getters-------------------------------

    public void setHabitaciones(LinkedList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getModoOperacion() {
        return modoOperacion;
    }

    public void setModoOperacion(int modoOperacion) {
        this.modoOperacion = modoOperacion;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the serialdelegate
     */
    public SerialDelegate getSerialdelegate() {
        return serialdelegate;
    }

    /**
     * @param serialdelegate the serialdelegate to set
     */
    public void setSerialdelegate(SerialDelegate serialdelegate) {
        this.serialdelegate = serialdelegate;
    }

    /**
     * @return the tramadelegate
     */
    public TramaFactory getTramadelegate() {
        return tramadelegate;
    }

    /**
     * @return the sistemaDAO
     */
    public SistemaDAO getSistemaDAO() {
        return sistemaDAO;
    }

    /**
     * @param sistemaDAO the sistemaDAO to set
     */
    public void setSistemaDAO(SistemaDAO sistemaDAO) {
        this.sistemaDAO = sistemaDAO;
    }
}
