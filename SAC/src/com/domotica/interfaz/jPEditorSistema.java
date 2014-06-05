/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domotica.interfaz;

import java.awt.event.ActionListener;

/**
 *
 * @author juangocc
 */
public class jPEditorSistema extends javax.swing.JPanel {

    /**
     * Creates new form jPEditorSistema
     */
    public jPEditorSistema() {
        initComponents();
    }

    public void setSubcripcionEventos(ActionListener al) {
        System.out.println("hreeaa");
        jBModificar.addActionListener(al);
        jBRestaurar.addActionListener(al);
    }

    public void setDatosSistema(String nombre, int id, String estado, int modoperacion, String descripcion) {
        jTFNombre.setText(nombre);
        jTFId.setText(id + "");
        jTFEstado.setText(estado);
        jTFModoOperacion.setText(modoperacion + "");
        jTFDescripcion.setText(descripcion);
    }

    public String[] getDatosSistema() {
        String[] datossistema = null;
        return datossistema;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLDescripcion = new javax.swing.JLabel();
        jLModoOperacion = new javax.swing.JLabel();
        jLEstado = new javax.swing.JLabel();
        jLId = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jTFNombre = new javax.swing.JTextField();
        jTFId = new javax.swing.JTextField();
        jLSistema = new javax.swing.JLabel();
        jTFEstado = new javax.swing.JTextField();
        jTFModoOperacion = new javax.swing.JTextField();
        jTFDescripcion = new javax.swing.JTextField();
        jBRestaurar = new javax.swing.JButton();
        jBModificar = new javax.swing.JButton();

        jLDescripcion.setText("Descripción :");

        jLModoOperacion.setText("Modo de Operación : ");

        jLEstado.setText("Estado :");

        jLId.setText("Id :");

        jLNombre.setText("Nombre :");

        jLSistema.setText("Sistema");

        jBRestaurar.setText("Restaurar");
        jBRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRestaurarActionPerformed(evt);
            }
        });

        jBModificar.setText("Modificar");
        jBModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLNombre)
                                    .addComponent(jLId)
                                    .addComponent(jLEstado))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLDescripcion)
                                        .addComponent(jLModoOperacion))))
                            .addGap(26, 26, 26)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTFEstado)
                                        .addComponent(jTFId)
                                        .addComponent(jTFNombre))
                                    .addGap(1, 1, 1))
                                .addComponent(jTFModoOperacion)
                                .addComponent(jTFDescripcion)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(87, 87, 87)
                            .addComponent(jBModificar)
                            .addGap(44, 44, 44)
                            .addComponent(jBRestaurar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLSistema)))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLSistema)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLId))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEstado))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFModoOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLModoOperacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDescripcion))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBModificar)
                    .addComponent(jBRestaurar))
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jBModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBModificarActionPerformed

    private void jBRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRestaurarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBRestaurarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBModificar;
    private javax.swing.JButton jBRestaurar;
    private javax.swing.JLabel jLDescripcion;
    private javax.swing.JLabel jLEstado;
    private javax.swing.JLabel jLId;
    private javax.swing.JLabel jLModoOperacion;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLSistema;
    private javax.swing.JTextField jTFDescripcion;
    private javax.swing.JTextField jTFEstado;
    private javax.swing.JTextField jTFId;
    private javax.swing.JTextField jTFModoOperacion;
    private javax.swing.JTextField jTFNombre;
    // End of variables declaration//GEN-END:variables
}
