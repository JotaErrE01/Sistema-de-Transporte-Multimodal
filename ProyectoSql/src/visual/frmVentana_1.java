/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class frmVentana_1 extends javax.swing.JFrame {

    public frmVentana_1() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jlabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Transporte Multimodal");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTitulo.setText("Sistema de Tráfico Multimodal");
        jLabelTitulo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelTitulo.setName("JLabelTitulo"); // NOI18N
        getContentPane().add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(0, 102, 153));
        btnGuardar.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Envíos");
        btnGuardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 170, 38));

        btnVer.setBackground(new java.awt.Color(0, 102, 153));
        btnVer.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 14)); // NOI18N
        btnVer.setForeground(new java.awt.Color(255, 255, 255));
        btnVer.setText("Ver/Elimiar Envíos");
        btnVer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVer.setName("btnVer"); // NOI18N
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });
        getContentPane().add(btnVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 190, 38));

        btnSalir.setBackground(new java.awt.Color(255, 0, 0));
        btnSalir.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Cerrar");
        btnSalir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 130, 30));

        jlabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Portada.jpg"))); // NOI18N
        jlabelFondo.setPreferredSize(new java.awt.Dimension(653, 313));
        getContentPane().add(jlabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.dispose();
        new frm_Ventana2().setVisible(true);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        this.dispose();
        new frmPregunta().setVisible(true);
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVer;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jlabelFondo;
    // End of variables declaration//GEN-END:variables
}
