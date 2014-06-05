package com.domotica.logic;

import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.util.LinkedList;

public class Estadistica {

    public XYSeriesCollection calcularEstaditica(LinkedList<Integer> resultado, String nombreHab, String variable, String fechaInicio, String horaInicio, String minInicio, String fechaFin, String horaFin, String minFin) {
        //Los valores consultados los retorna en un TimeSeriesCollection
        XYSeriesCollection dataset = new XYSeriesCollection();
        GregorianCalendar dateInicio = new GregorianCalendar();
        GregorianCalendar dateFin = new GregorianCalendar();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        //convierte las cadenas de las fechas a GregorianCalendar
        try {
            dateInicio.setTime(df.parse(fechaInicio));
            dateFin.setTime(df.parse(fechaFin));
            //se le suma un dia al mes porque con date los meses empeizan en 0
            dateInicio.set(GregorianCalendar.MONTH, dateInicio.get(GregorianCalendar.MONTH) + 1);
            dateFin.set(GregorianCalendar.MONTH, dateFin.get(GregorianCalendar.MONTH) + 1);
        } catch (Exception ex) {
            System.out.println("Error en calcular estadistica !!!" + ex);
        }
        //valdar si las estadisticas consultadas son del mismo d�a o abarcan varios
        if (dateInicio.getTimeInMillis() == dateFin.getTimeInMillis()) {//son iguales, se consulta por hora
            XYSeries var = new XYSeries(dateInicio.get(GregorianCalendar.DAY_OF_MONTH) + "/" + dateInicio.get(GregorianCalendar.MONTH) + "/" + dateInicio.get(GregorianCalendar.YEAR));
            //LinkedList<Integer> resultado = estadisticaDAO.getEstadisticasPorHora(nombreHab, variable, dateInicio, Integer.parseInt(horaInicio), Integer.parseInt(horaFin));
            for (int i = 0; i < resultado.size(); i++) {
                var.add(i, resultado.get(i));
            }
            dataset.addSeries(var);
        } else { //se consulta por fecha
            //mirar cuantos d�as hay de diferencia entre fechas
            int dias = difDiasEntre2fechas(dateInicio.get(GregorianCalendar.YEAR), dateInicio.get(GregorianCalendar.MONTH), dateInicio.get(GregorianCalendar.DAY_OF_MONTH), dateFin.get(GregorianCalendar.YEAR), dateFin.get(GregorianCalendar.MONTH), dateFin.get(GregorianCalendar.DAY_OF_MONTH));
            XYSeries var;
            for (int i = 0; i <= dias; i++) {
                //va aumentando el d�a para buscar sus respectivas lecturas
                dateInicio.set(GregorianCalendar.DAY_OF_MONTH, dateInicio.get(GregorianCalendar.DAY_OF_MONTH) + i);
                //serie de tiempo para almacenar cada d�a
                var = new XYSeries(dateInicio.get(GregorianCalendar.DAY_OF_MONTH) + "/" + dateInicio.get(GregorianCalendar.MONTH) + "/" + dateInicio.get(GregorianCalendar.YEAR));
                //se obtiene un arrayList de cada una de las fechas y se almacena en una serie de tiempo
                // LinkedList<Integer> resultado = estadisticaDAO.getEstadisticasPorHora(nombreHab, variable, dateInicio, 0, 23);
                for (int j = 0; j < resultado.size(); j++) {
                    var.add(j, (Integer) resultado.get(j));
                }
                dataset.addSeries(var);
            }
        }
        return dataset;
    }

    public ArrayList obtenerTablaEstaditica(LinkedList<Integer> resultado, String nombreHab, String variable, String fechaInicio, String horaInicio, String minInicio, String fechaFin, String horaFin, String minFin) {
        //Array List a retornar
        ArrayList dataset = new ArrayList();
        GregorianCalendar dateInicio = new GregorianCalendar();
        GregorianCalendar dateFin = new GregorianCalendar();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        //convierte las cadenas de las fechas a GregorianCalendar
        try {
            dateInicio.setTime(df.parse(fechaInicio));
            dateFin.setTime(df.parse(fechaFin));
            //se le suma un dia al mes porque con date los meses empeizan en 0
            dateInicio.set(GregorianCalendar.MONTH, dateInicio.get(GregorianCalendar.MONTH) + 1);
            dateFin.set(GregorianCalendar.MONTH, dateFin.get(GregorianCalendar.MONTH) + 1);
        } catch (Exception ex) {
            System.out.println("Error en obtener estadistica!!!" + ex);
        }
        //valdar si las estadisticas consultadas son del mismo d�a o abarcan varios
        if (dateInicio.getTimeInMillis() == dateFin.getTimeInMillis()) {//son iguales, se consulta por hora
            ArrayList var;
            //LinkedList<Integer> resultado = estadisticaDAO.getEstadisticasPorHora(nombreHab, variable, dateInicio, Integer.parseInt(horaInicio), Integer.parseInt(horaFin));
            for (int i = 0; i < resultado.size(); i++) {
                var = new ArrayList();
                var.add(nombreHab); //habitacion
                var.add(dateInicio.get(GregorianCalendar.DAY_OF_MONTH) + "/" + dateInicio.get(GregorianCalendar.MONTH) + "/" + dateInicio.get(GregorianCalendar.YEAR)); //fecha
                var.add(i); // hora
                var.add((Integer) resultado.get(i)); //valor variable
                dataset.add(var);
            }
        } else { //se consulta por fecha
            //mirar cuantos d�as hay de diferencia entre fechas
            int dias = difDiasEntre2fechas(dateInicio.get(GregorianCalendar.YEAR), dateInicio.get(GregorianCalendar.MONTH), dateInicio.get(GregorianCalendar.DAY_OF_MONTH), dateFin.get(GregorianCalendar.YEAR), dateFin.get(GregorianCalendar.MONTH), dateFin.get(GregorianCalendar.DAY_OF_MONTH));
            ArrayList var;
            for (int i = 0; i <= dias; i++) {
                //va aumentando el d�a para buscar sus respectivas lecturas
                dateInicio.set(GregorianCalendar.DAY_OF_MONTH, dateInicio.get(GregorianCalendar.DAY_OF_MONTH) + i);
                //se obtiene un arrayList de cada una de las fechas y se almacena en una serie de tiempo
                // LinkedList<Integer> resultado = estadisticaDAO.getEstadisticasPorHora(nombreHab, variable, dateInicio, 0, 23);
                for (int j = 0; j < resultado.size(); j++) {
                    var = new ArrayList();
                    var.add(nombreHab); //habitacion
                    var.add(dateInicio.get(GregorianCalendar.DAY_OF_MONTH) + "/" + dateInicio.get(GregorianCalendar.MONTH) + "/" + dateInicio.get(GregorianCalendar.YEAR)); //fecha
                    var.add(j); // hora
                    var.add((Integer) resultado.get(j)); //valor variable
                    dataset.add(var);
                }
            }
        }
        return dataset;
    }

    private int calcularPromedio(LinkedList<Integer> arreglo) {
        int suma = 0;
        int promedio;
        for (int i = 0; i < arreglo.size(); i++) {
            suma += arreglo.get(i);
        }
        promedio = suma / arreglo.size();
        return promedio;
    }

    private static int difDiasEntre2fechas(int Y1, int M1, int D1, int Y2, int M2, int D2) {
        java.util.GregorianCalendar date1 = new java.util.GregorianCalendar(Y1, M1, D1);
        java.util.GregorianCalendar date2 = new java.util.GregorianCalendar(Y2, M2, D2);
        long difms = date2.getTimeInMillis() - date1.getTimeInMillis();
        long difd = difms / (1000 * 60 * 60 * 24);
        Long difdL = new Long(difd);
        return difdL.intValue();
    }
}
