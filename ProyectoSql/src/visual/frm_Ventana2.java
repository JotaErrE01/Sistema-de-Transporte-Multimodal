package visual;

import control.AdmCiudadOrigen;
import control.AdmConductor;
import control.AdmProducto;
import control.AdmValidacion;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.swing.JOptionPane;

public class frm_Ventana2 extends javax.swing.JFrame {

    //Globales
    AdmConductor adm_Conductor = AdmConductor.getAdmconductor();
    AdmProducto adm_Producto = AdmProducto.getamdproducto();
    AdmCiudadOrigen adm_origen = AdmCiudadOrigen.getAdmOrigen();
    AdmValidacion adm_Validacion = new AdmValidacion();

    //constructor
    public frm_Ventana2() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        dtcSalida.getDateEditor().setEnabled(false);
        dtcSalida.setMinSelectableDate(Date.valueOf(LocalDate.now()));
        dtcLlegada.getDateEditor().setEnabled(false);
        dtcLlegada.setMinSelectableDate(Date.valueOf(LocalDate.now()));
        TPSalida.getComponentTimeTextField().setEnabled(false);
        TPLlegada.getComponentTimeTextField().setEnabled(false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtIdConductor = new javax.swing.JTextField();
        txtNombreCiudadO = new javax.swing.JTextField();
        txtNombreConductor = new javax.swing.JTextField();
        txtIdCiudadO = new javax.swing.JTextField();
        txtNombreCiudadD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTipoLicencia = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        TPSalida = new com.github.lgooddatepicker.components.TimePicker();
        TPLlegada = new com.github.lgooddatepicker.components.TimePicker();
        jLabel11 = new javax.swing.JLabel();
        txtIdCiudadD = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCantidadProductos = new javax.swing.JTextField();
        cmbTipoTransporte = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtIDTransporte = new javax.swing.JTextField();
        dtcSalida = new com.toedter.calendar.JDateChooser();
        dtcLlegada = new com.toedter.calendar.JDateChooser();
        txtTiempoA = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        JLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cédula del Conductor:");
        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 180, 20));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombre del Conductor:");
        jLabel2.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 184, 200, 29));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Ciudad de Origen:");
        jLabel3.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 141, 20));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Id de la Ciudad de Origen:");
        jLabel4.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 220, 29));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ciudad de Destino:");
        jLabel5.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(836, 96, 190, 20));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Fecha y Hora de Salida:");
        jLabel6.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, 200, 30));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Fecha y Hora de LLegada Estimado:");
        jLabel7.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, 270, 20));

        txtIdConductor.setBorder(null);
        txtIdConductor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdConductorKeyTyped(evt);
            }
        });
        getContentPane().add(txtIdConductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 115, -1));

        txtNombreCiudadO.setBorder(null);
        getContentPane().add(txtNombreCiudadO, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 200, -1));

        txtNombreConductor.setBorder(null);
        txtNombreConductor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreConductorKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombreConductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 115, -1));

        txtIdCiudadO.setBorder(null);
        getContentPane().add(txtIdCiudadO, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 200, -1));

        txtNombreCiudadD.setBorder(null);
        getContentPane().add(txtNombreCiudadD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 100, 146, -1));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Nombre del Producto:");
        jLabel8.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 190, 20));

        txtIdProducto.setBorder(null);
        getContentPane().add(txtIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, 115, -1));

        txtNombreProducto.setBorder(null);
        getContentPane().add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 520, 115, -1));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Id del Producto:");
        jLabel9.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 443, 141, 29));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Tipo de licencia:");
        jLabel10.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 200, 20));

        txtTipoLicencia.setBorder(null);
        txtTipoLicencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoLicenciaKeyTyped(evt);
            }
        });
        getContentPane().add(txtTipoLicencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 115, -1));

        btnGuardar.setBackground(new java.awt.Color(255, 51, 0));
        btnGuardar.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Datos");
        btnGuardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 596, 194, 38));

        btnAtras.setBackground(new java.awt.Color(255, 51, 0));
        btnAtras.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setText("Atrás");
        btnAtras.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        getContentPane().add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(811, 596, 194, 38));
        getContentPane().add(TPSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(836, 269, -1, -1));
        getContentPane().add(TPLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(836, 332, -1, -1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Id de la Ciudad de Destino:");
        jLabel11.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 180, 220, 29));

        txtIdCiudadD.setBorder(null);
        getContentPane().add(txtIdCiudadD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1022, 187, 146, -1));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Cantidad de Productos:");
        jLabel12.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 605, 190, 29));

        txtCantidadProductos.setBorder(null);
        txtCantidadProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadProductosKeyTyped(evt);
            }
        });
        getContentPane().add(txtCantidadProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 610, 115, -1));

        cmbTipoTransporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TERRESTRE", "AEREO", "MARÍTIMO", "FERROVIARIO" }));
        cmbTipoTransporte.setBorder(null);
        cmbTipoTransporte.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        getContentPane().add(cmbTipoTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 498, 185, 30));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Tipo de Transporte:");
        jLabel13.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 505, 170, -1));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Id del Transporte:");
        jLabel14.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 500, -1, -1));

        txtIDTransporte.setBorder(null);
        getContentPane().add(txtIDTransporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 500, 155, -1));

        dtcSalida.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        dtcSalida.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(dtcSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, 201, -1));

        dtcLlegada.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        getContentPane().add(dtcLlegada, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 330, 201, -1));

        txtTiempoA.setBorder(null);
        getContentPane().add(txtTiempoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 115, -1));

        jLabel15.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("TIEMPO DE ACTIVIDAD:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 180, -1));

        JLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ventana2.jpg"))); // NOI18N
        getContentPane().add(JLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
        new frmVentana_1().setVisible(true);
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        adm_Validacion.validar(txtIdConductor, txtNombreConductor, txtTipoLicencia, txtTiempoA,
                txtIdProducto, txtNombreProducto, txtCantidadProductos, txtNombreCiudadO, txtIdCiudadO,
                dtcSalida, TPSalida, txtNombreCiudadD, txtIdCiudadD, dtcLlegada, TPLlegada, cmbTipoTransporte, txtIDTransporte);

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtIdConductorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdConductorKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || c == ' ') {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdConductorKeyTyped

    private void txtNombreConductorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreConductorKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) || c == ' ') {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreConductorKeyTyped

    private void txtTipoLicenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoLicenciaKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) || c == ' ') {
            evt.consume();
        }
    }//GEN-LAST:event_txtTipoLicenciaKeyTyped

    private void txtCantidadProductosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductosKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || c == ' ') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadProductosKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabelFondo;
    private com.github.lgooddatepicker.components.TimePicker TPLlegada;
    private com.github.lgooddatepicker.components.TimePicker TPSalida;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbTipoTransporte;
    private com.toedter.calendar.JDateChooser dtcLlegada;
    private com.toedter.calendar.JDateChooser dtcSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtCantidadProductos;
    private javax.swing.JTextField txtIDTransporte;
    private javax.swing.JTextField txtIdCiudadD;
    private javax.swing.JTextField txtIdCiudadO;
    private javax.swing.JTextField txtIdConductor;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtNombreCiudadD;
    private javax.swing.JTextField txtNombreCiudadO;
    private javax.swing.JTextField txtNombreConductor;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtTiempoA;
    private javax.swing.JTextField txtTipoLicencia;
    // End of variables declaration//GEN-END:variables
}
