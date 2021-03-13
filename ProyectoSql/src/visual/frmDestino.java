package visual;

import control.AdmCiudadDestino;

/**
 *
 * @author Jonthan Ruiz R. <sguergachi at gmail.com>
 */
public class frmDestino extends javax.swing.JFrame {

    AdmCiudadDestino admCiudadDestino = AdmCiudadDestino.getAdmDestino();

    public frmDestino() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDestinos = new javax.swing.JTable();
        btnDestinos = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblDestinos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_CIUDAD_DESTINO", "NOMBRE_CIUDAD_DESTINO", "FECHA_LLEGADA", "HORA_LLEGADA"
            }
        ));
        jScrollPane1.setViewportView(tblDestinos);

        btnDestinos.setText("Ver Destinos");
        btnDestinos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDestinosActionPerformed(evt);
            }
        });

        btnAtras.setText("Atrás");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(158, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
            .addGroup(layout.createSequentialGroup()
                .addGap(333, 333, 333)
                .addComponent(btnDestinos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDestinos, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDestinosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDestinosActionPerformed
        admCiudadDestino.VerDataBase(tblDestinos);
    }//GEN-LAST:event_btnDestinosActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
        new frmVentana_1().setVisible(true);
    }//GEN-LAST:event_btnAtrasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnDestinos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDestinos;
    // End of variables declaration//GEN-END:variables
}
