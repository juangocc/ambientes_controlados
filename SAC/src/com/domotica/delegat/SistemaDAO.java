package com.domotica.delegat;

import com.chat.controlador.clsConn;
import java.util.*;
import org.jdom2.*;
import com.domotica.core.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.input.SAXBuilder;

public class SistemaDAO {

    Document doc;// el documento XML
    private String NOMBRE_ARCHIVO = "sistema.xml";
    clsConn conn;

    public SistemaDAO() {
        conn = new clsConn();
        try {
            SAXBuilder builder = new SAXBuilder(false);
            //usar el parser Xerces y no queremos
            //que valide el documento
            doc = builder.build(NOMBRE_ARCHIVO);
        } catch (JDOMException | IOException ex) {
            System.out.println("Error en SistemaDAO !!!" + ex);
        }
        //agregarHabitacion("habitacion 1", 105, 25, 100, 10, 10);
        //agregarHabitacion("habitacion 2", 106, 25, 100, 10, 10);
        //agregarHabitacion("habitacion 3", 107, 25, 100, 10, 10);

//        agregarDispositivo(105, "C1", "apagado", "calentador", "dispositivo 1");
//        agregarDispositivo(105, "P1", "apagado", "persiana", "dispositivo 2");
//        agregarDispositivo(106, "B1", "apagado", "bombillo", "dispositivo 3");
//        agregarDispositivo(106, "C2", "apagado", "calentador", "dispositivo 4");
//        agregarDispositivo(107, "P2", "apagado", "persiana", "dispositivo 5");
//        agregarDispositivo(107, "A1", "apagado", "aire acondicionado", "dispositivo 6");
        
//        agregarHorario("C1", 1626, "apagar");
//        agregarHorario("C1", 1826, "prender");
//        agregarHorario("P1", 1626, "subir");
//        agregarHorario("P1", 0526, "bajar");
    }

    public LinkedList<Habitacion> getHabitaciones() {


        String sql = "SELECT * FROM habitacion;";

        ResultSet rs = conn.consultar(sql);
        LinkedList<Habitacion> arrayHabitaciones = new LinkedList<>();
        try {
            while (rs.next()) {
                int idhab = Integer.parseInt(rs.getString(1));
                String descripcion = rs.getString(2);
                int idSensor = Integer.parseInt(rs.getString(3));
                int temp_Ideal = Integer.parseInt(rs.getString(4));
                int ilum_Ideal = Integer.parseInt(rs.getString(5));
                int ventil_Ideal = Integer.parseInt(rs.getString(6));
                int humedad_Ideal = Integer.parseInt(rs.getString(7));

                Habitacion h = new Habitacion(idhab, idSensor);
                h.setTemperaturaIdeal(temp_Ideal);
                h.setHumedadIdeal(humedad_Ideal);
                h.setVentilacionIdeal(ventil_Ideal);
                h.setIluminacionIdeal(ilum_Ideal);

                arrayHabitaciones.add(h);
            }
            // System.out.println("Longitud de habitaciones :" + arrayHabitaciones.size());
        } catch (SQLException ex) {
            Logger.getLogger(SistemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Habitacion habitacion : arrayHabitaciones) {
            LinkedList<Dispositivo> arrayDispositivos = new LinkedList<>();
            sql = "SELECT * FROM dispositivo WHERE id_hab='" + habitacion.getIdSensor() + "';";

            rs = conn.consultar(sql);
            try {
                while (rs.next()) {
                    String nombre = rs.getString(2);
                    String estado = rs.getString(3);
                    String tipo = rs.getString(4);
                    Dispositivo d = new Dispositivo(nombre, estado, tipo);
                    arrayDispositivos.add(d);
                }
                System.out.println("Para la habitacion " + habitacion.getId() + " hay " + arrayDispositivos.size() + " dispositivos ");
                habitacion.setDispositivos(arrayDispositivos);
            } catch (SQLException ex) {
                Logger.getLogger(SistemaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        //retornar las habitaciones
        System.out.println("Habitaciones cargadas con éxito !!!!");
        return arrayHabitaciones;
    }

    public void agregarHabitacion(String descripcion, int idSensor, int temp_Ideal, int ilum_Ideal, int ventil_Ideal, int humedad_Ideal) {
        String sql = "INSERT INTO habitacion (id,descripcion,idsensor ,temp_ideal ,ilum_ideal,ventil_ideal,humedad_ideal) values (nextval('seq_id_habitacion'),'" + descripcion + "','" + idSensor + "','" + temp_Ideal + "','" + ilum_Ideal + "','" + ventil_Ideal + "','" + humedad_Ideal + "');";
        System.out.println("" + sql);
        if (conn.insertar(sql) == null) {
            System.out.println("Exito al agregar habitación");
        } else {
            System.out.println("Error al agregar habitación !!!");
        }
    }

    public void agregarDispositivo(int id_hab, String nombre, String estado, String tipo, String descripcion) {
        String sql = "INSERT INTO dispositivo (id,id_hab,nombre,estado ,tipo,descripcion) values (nextval('seq_id_dispositivo'),'" + id_hab + "','" + nombre + "','" + estado + "','" + tipo + "','" + descripcion + "');";
        System.out.println("" + sql);
        if (conn.insertar(sql) == null) {
            System.out.println("Exito al agregar dispositivo.");
        } else {
            System.out.println("Error al agregar dispositivo !!!");
        }
    }

    public void agregarHorario(String id_disp, int hora, String tipo) {
        String sql = "INSERT INTO horario (id, id_nombre_disp, hora, tipo) values (nextval('seq_id_horario'),'" + id_disp + "','" + hora + "','" + tipo + "');";
        System.out.println("" + sql);
        if (conn.insertar(sql) == null) {
            System.out.println("Exito al agregar horario.");
        } else {
            System.out.println("Error al agregar horario !!!");
        }
    }

    public void agregarHistoricoHabitacion(int id_hab, double temperatura, int ventilacion, int iluminacion, double humedad) {
        String sql = "INSERT INTO historico_habitacion (id, id_hab, temperatura, ventilacion, iluminacion, humedad,fecha) values (nextval('seq_id_historico_habitacion'),'" + id_hab + "','" + temperatura + "','" + ventilacion + "','" + iluminacion + "','" + humedad + "',now());";
        System.out.println("" + sql);
        if (conn.insertar(sql) == null) {
            System.out.println("Exito al agregar historico habitación.");
        } else {
            System.out.println("Error al agregar historico habitación !!!");
        }
    }
}
