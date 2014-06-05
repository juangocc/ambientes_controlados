package com.domotica.delegat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class EstadisticaDAO {

    Document doc;
    private static String NOMBRE_ARCHIVO = "estadisticas.xml";

    public EstadisticaDAO() {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            //usar el parser Xerces y no queremos
            //que valide el documento
            doc = builder.build(NOMBRE_ARCHIVO);
        } catch (JDOMException | IOException ex) {
            System.out.println("Error en estadisticadao !!!" + ex);
        }
    }

    public LinkedList<Integer> getEstadisticasPorHora(String nombreHab, String variable, GregorianCalendar fecha, int horaInicio, int horaFin) {
        LinkedList<Integer> results = new LinkedList<>();
        Element raiz = doc.getRootElement();
        List habitaciones = raiz.getChildren("habitacion");
        Iterator iteradorHabitaciones = habitaciones.iterator();
        while (iteradorHabitaciones.hasNext()) {
            //recupera la habitacion y sus lecturas
            Element elementHabitacion = (Element) iteradorHabitaciones.next();
            //si el nombre es igual al que le pasan como parametro entonces procesar el resto
            if (elementHabitacion.getAttributeValue("nombre").equals(nombreHab)) {
                List fechas = elementHabitacion.getChildren("fecha");
                Iterator iteradorFechas = fechas.iterator();
                while (iteradorFechas.hasNext()) {
                    //las recupera como elemento
                    Element e = (Element) iteradorFechas.next();
                    //recuperar los atributos
                    int dia = Integer.parseInt(e.getAttributeValue("dia"));
                    int mes = Integer.parseInt(e.getAttributeValue("mes"));
                    int anio = Integer.parseInt(e.getAttributeValue("anio"));
                    Calendar laFecha = new GregorianCalendar(anio, mes, dia);
                    //comparar la fecha parametro con la fecha del XML
                    if (laFecha.getTimeInMillis() == fecha.getTimeInMillis()) {
                        //si son iguales obtener el rango de valores por hora dependiendo de la variable
                        List lecturas = e.getChildren("lectura");
                        Iterator iteradorLecturas = lecturas.iterator();
                        while (iteradorLecturas.hasNext()) {
                            Element lecturaHora = (Element) iteradorLecturas.next();
                            //si esta dentro del rango entonces adicionarla al arreglo
                            if (Integer.parseInt(lecturaHora.getAttributeValue("hora")) >= horaInicio && Integer.parseInt(lecturaHora.getAttributeValue("hora")) <= horaFin) {
                                results.add(Integer.parseInt(lecturaHora.getChildText(variable)));
                            }
                        }
                    }
                }
            }
        }
        return results;
    }

    public void setPromedioHora(int habitacion, int promedio, String variable) {
        //obtener la fecha actual
        Calendar fechaActual = new GregorianCalendar();
        //buscar la habitacion
        Element raiz = doc.getRootElement();
        List habitaciones = raiz.getChildren("habitacion");
        Iterator iteradorHab = habitaciones.iterator();
        //mientras hayan habitaciones en la lista
        while (iteradorHab.hasNext()) {
            //las recupera como elemento
            Element e = (Element) iteradorHab.next();
            //	recuperar el atributo nombre y lo compara con el nombre que le lleg
            if (e.getAttributeValue("nombre").equals(String.valueOf(habitacion))) {
                //buscar la feecha actual, si no est crearla
                List fechas = e.getChildren("fecha");
                boolean blnFechaEncontrada = false; //para ver al final del ciclo si encontr la fecha o si hay que crearla
                Iterator iteradorFechas = fechas.iterator();
                while (iteradorFechas.hasNext()) {
                    Element elementFecha = (Element) iteradorFechas.next();
                    String diaActual = String.valueOf(fechaActual.get(GregorianCalendar.DAY_OF_MONTH));
                    String mesActual = String.valueOf(fechaActual.get(GregorianCalendar.MONTH));
                    String anioActual = String.valueOf(fechaActual.get(GregorianCalendar.YEAR));
                    String diaXML = elementFecha.getAttributeValue("dia");
                    String mesXML = elementFecha.getAttributeValue("mes");
                    String anioXML = elementFecha.getAttributeValue("anio");
                    if (diaActual.equals(diaXML) && mesActual.equals(mesXML) && anioActual.equals(anioXML)) {
                        blnFechaEncontrada = true;
                        //mirar si ya hay un nodo con la lectura
                        List lecturas = elementFecha.getChildren("lectura");
                        Iterator iteradorLecturas = lecturas.iterator();
                        boolean blnHoraEncontrada = false;
                        while (iteradorLecturas.hasNext()) {
                            Element elementLectura = (Element) iteradorLecturas.next();
                            String horaActual = String.valueOf(fechaActual.get(GregorianCalendar.HOUR_OF_DAY));
                            if (elementLectura.getAttributeValue("hora").equals(horaActual)) {
                                blnHoraEncontrada = true;
                                //crear el tag de la variable con el respectivo valor
                                Element elementoVariable = new Element(variable);
                                elementoVariable.setText(String.valueOf(promedio));
                                elementLectura.addContent(elementoVariable);
                            }
                        }
                        if (!blnHoraEncontrada) {
                            //crear un tag con la variable, con la hora y agregarselo a la fecha que se tiene
                            Element elementoVariable = new Element(variable);
                            elementoVariable.addContent(String.valueOf(promedio));
                            Element elementLectura = new Element("lectura");
                            String horaActual = String.valueOf(fechaActual.get(GregorianCalendar.HOUR_OF_DAY));
                            elementLectura.setAttribute("hora", horaActual);
                            elementLectura.addContent(elementoVariable);
                            elementFecha.addContent(elementLectura);
                        }
                    }
                }
                //valida si encontro la fecha; si no, la crea y establece la lectura
                if (!blnFechaEncontrada) {
                    Element elementoVariable = new Element(variable);
                    elementoVariable.addContent(String.valueOf(promedio));
                    Element elementLectura = new Element("lectura");
                    String horaActual = String.valueOf(fechaActual.get(GregorianCalendar.HOUR_OF_DAY));
                    elementLectura.setAttribute("hora", horaActual);
                    elementLectura.addContent(elementoVariable);
                    Element elementFecha = new Element("fecha");
                    String dia = String.valueOf(fechaActual.get(GregorianCalendar.DAY_OF_MONTH));
                    String mes = String.valueOf(fechaActual.get(GregorianCalendar.MONTH));
                    String anio = String.valueOf(fechaActual.get(GregorianCalendar.YEAR));
                    elementFecha.setAttribute("dia", dia);
                    elementFecha.setAttribute("mes", mes);
                    elementFecha.setAttribute("anio", anio);
                    elementFecha.addContent(elementLectura);
                    e.addContent(elementFecha);
                }
            }
        }
        actualizarArchivoXML();
    }

    public LinkedList<String> obtenerCSV() {
        LinkedList<String> renglones = new LinkedList<>();
        Element raiz = doc.getRootElement();
        List habitaciones = raiz.getChildren("habitacion");
        Iterator iteradorHabitaciones = habitaciones.iterator();
        while (iteradorHabitaciones.hasNext()) {
            Element elementHabitacion = (Element) iteradorHabitaciones.next();
            List fechas = elementHabitacion.getChildren("fecha");
            Iterator iteradorFechas = fechas.iterator();
            while (iteradorFechas.hasNext()) {
                Element elementFechas = (Element) iteradorFechas.next();
                List lecturas = elementFechas.getChildren("lectura");
                Iterator iteradorLecturas = lecturas.iterator();
                while (iteradorLecturas.hasNext()) {
                    Element elementLectura = (Element) iteradorLecturas.next();
                    //obtener la fecha
                    String dia = elementFechas.getAttributeValue("dia");
                    String mes = elementFechas.getAttributeValue("mes");
                    String anio = elementFechas.getAttributeValue("anio");
                    //obtener la humedad y la temperatura
                    Element temp = (Element) elementLectura.getChild("temperatura");
                    Element humed = (Element) elementLectura.getChild("humedad");
                    String renglon = elementHabitacion.getAttributeValue("nombre") + ";"
                            + dia + "/" + mes + "/" + anio + ";"
                            + elementLectura.getAttributeValue("hora") + ";"
                            + temp.getText() + ";"
                            + humed.getText();
                    renglones.add(renglon);
                }
            }
        }
        return renglones;
    }

    private void actualizarArchivoXML() {
        try {
            XMLOutputter outputter = new XMLOutputter();
            PrintWriter escribir = new PrintWriter(new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO)));
            outputter.output(doc, escribir);
        } catch (Exception ex) {
            System.out.println("Error al actualizar Archivo XML !!!" + ex);
        }
    }
}
