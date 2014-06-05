/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domotica.controller;

import com.domotica.core.Dispositivo;
import com.domotica.core.Habitacion;
import com.domotica.logic.Sistema;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author juangocc
 */
public class CustomDefaultTreeCellRender extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        String estado = "Disponible";
        Object obj = ((jTreeContent) ((DefaultMutableTreeNode) value).getUserObject()).getDato();
        if (obj instanceof Sistema) {
//           estado=((Sistema)obj).getEstado();
        }
        if (obj instanceof Habitacion) {
//           estado=((Habitacion)obj).getEstado();
        }
        if (obj instanceof Dispositivo) {
//           estado=((Dispositivo)obj).getEstado();
        }
        ImageIcon leafIconr = new ImageIcon("src/Imagenes/rejected.png");
        ImageIcon leafIcona = new ImageIcon("src/Imagenes/approved.png");
        ImageIcon folderopenr = new ImageIcon("src/Imagenes/forejected.png");
        ImageIcon folderopena = new ImageIcon("src/Imagenes/foapproved.png");
        ImageIcon foldercloser = new ImageIcon("src/Imagenes/fcrejected.png");
        ImageIcon folderclosea = new ImageIcon("src/Imagenes/fcapproved.png");
        if (leaf) {
            if (estado.equals("Disponible")) {
                setLeafIcon(leafIcona);
            } else {
                setLeafIcon(leafIconr);
            }
        } else if (expanded) {
            if (estado.equals("Disponible")) {
                setOpenIcon(folderopena);
            } else {
                setOpenIcon(folderopenr);
            }
        } else {
            if (estado.equals("Disponible")) {
                setClosedIcon(folderclosea);
            } else {
                setClosedIcon(foldercloser);
            }
        }
        return this;
    }
}
