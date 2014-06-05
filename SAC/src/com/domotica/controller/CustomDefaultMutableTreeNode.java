/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domotica.controller;

import com.domotica.core.Dispositivo;
import com.domotica.core.Habitacion;
import com.domotica.logic.Sistema;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author juangocc
 */
public class CustomDefaultMutableTreeNode extends DefaultMutableTreeNode {

    public CustomDefaultMutableTreeNode(Object userObject) {
        super(userObject);
    }

    public CustomDefaultMutableTreeNode() {
    }

    public CustomDefaultMutableTreeNode(Object userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);
    }

    @Override
    public void setUserObject(Object userObject) {
        if (userObject instanceof String) {
            jTreeContent obj = ((jTreeContent) this.getUserObject());
            System.out.println("Anterior " + obj.getNombre() + " , Nuevo : " + userObject);
            obj.setNombre(userObject.toString());
            if (obj.getDato() instanceof Sistema) {
                ((Sistema) obj.getDato()).setNombre(obj.getNombre());
            }
            if (obj.getDato() instanceof Habitacion) {
                //((Habitacion) obj.getDato()).setIdSensor(obj.getNombre());
            }
            if (obj.getDato() instanceof Dispositivo) {
                ((Dispositivo) obj.getDato()).setNombre(obj.getNombre());
            }
            userObject = obj;
        }
        super.setUserObject(userObject); //To change body of generated methods, choose Tools | Templates.
    }
}
