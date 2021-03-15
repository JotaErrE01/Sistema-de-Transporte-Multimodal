package control;

import BaseDeDatos.ConexionSqlDeb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Transporte;

public class AdmTransporte {

    //VARIABLES GLOBALES
    Connection cnx = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ArrayList<Transporte> transportes = null;
    Transporte transporte = null;
    private static AdmTransporte admTransporte = null;

    public AdmTransporte() {
        transportes = new ArrayList<>();
    }

    public ArrayList<Transporte> getTransportes() {
        return transportes;
    }

    public void setTransportes(ArrayList<Transporte> transportes) {
        this.transportes = transportes;
    }

    public static AdmTransporte getAdmTransporte() {
        if (admTransporte == null) {
            admTransporte = new AdmTransporte();
        }
        return admTransporte;
    }

    public static void setAdmTransporte(AdmTransporte admTransporte) {
        AdmTransporte.admTransporte = admTransporte;
    }

    public void buscar(JTable tblTransporte, JTextField txtIDTransporte, JComboBox txtTipoTransporte) {
        Transporte Trans = null;
        transportes.clear();
        try {
            cnx = ConexionSqlDeb.getConneccion();
            st = cnx.createStatement();
            rs = st.executeQuery("SELECT *"
                    + " FROM TRANSPORTE "
                    + " WHERE ID_TRANSPORTE='" + txtIDTransporte.getText() + "' OR TIPO_TRANSPORTE='"
                    + txtTipoTransporte.getSelectedItem().toString() + "'   ");
            while (rs.next()) {
                Trans = new Transporte();
                Trans.setId_Transporte(rs.getString(1));
                Trans.setTipo_Transporte(rs.getString(2));
                Trans.setId_Producto(rs.getString(3));
                transportes.add(Trans);
            }
            LimpiarTabla(tblTransporte);
            DefaultTableModel tb = (DefaultTableModel) tblTransporte.getModel();
            transportes.forEach(t -> {
                tb.addRow(new Object[]{t.getId_Transporte(), t.getTipo_Transporte(), t.getId_Producto()});
            });
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //LIMPIAR TABLA
    public void LimpiarTabla(JTable tblConductor) {
        DefaultTableModel tb = (DefaultTableModel) tblConductor.getModel();
        for (int i = tb.getRowCount() - 1; i >= 0; i--) {
            tb.removeRow(i);
        }
    }

    public void VerTrans(JTable tblTransporte) {
        LimpiarTabla(tblTransporte);

        transportes = new ArrayList<>();
        try {

            cnx = ConexionSqlDeb.getConneccion();
            st = cnx.createStatement();
            rs = st.executeQuery("SELECT *"
                    + "     FROM TRANSPORTE ");

            while (rs.next()) {

                transporte = new Transporte();
                transporte.setId_Transporte(rs.getString(1));
                transporte.setTipo_Transporte(rs.getString(2));
                transporte.setId_Producto(rs.getString(3));
                transportes.add(transporte);

            }

            DefaultTableModel tb = (DefaultTableModel) tblTransporte.getModel();
            transportes.forEach(t -> {
                tb.addRow(new Object[]{t.getId_Transporte(), t.getTipo_Transporte(), t.getId_Producto()});
            });

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void EliminarFila(JTable tblTransporte) {

    }

    void guardar(JTextField txtIDTransporte, JComboBox<String> cmbTipoTransporte, JTextField txtIdProducto) {

        transporte = new Transporte();
        transporte.setId_Transporte(txtIDTransporte.getText());
        transporte.setTipo_Transporte(cmbTipoTransporte.getSelectedItem().toString());
        transporte.setId_Producto(txtIdProducto.getText());
        try {
            cnx = ConexionSqlDeb.getConneccion();
            pst = cnx.prepareStatement("INSERT INTO TRANSPORTE("
                    + " ID_TRANSPORTE,TIPO_TRANSPORTE,ID_PRODUCTO) "
                    + "VALUES(?,?,?)");
            pst.setString(1, transporte.getId_Transporte());
            pst.setString(2, transporte.getTipo_Transporte());
            pst.setString(3, transporte.getId_Producto());
            pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AdmConductor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error en insert");
        }
    }

    public void Actualizar(JTable tblTransporte, JTextField txtIDTransporte, JComboBox<String> cmbTipoTransporte) {

        String transporte = cmbTipoTransporte.getSelectedItem().toString();
        try {
            int row = tblTransporte.getSelectedRow();
            cnx = ConexionSqlDeb.getConneccion();
            int msj = JOptionPane.showConfirmDialog(null, "ESTÁ SEGURO QUE DESEA ACTUALIZAR LA FILA SELECCIONADA");

            if (msj == JOptionPane.YES_OPTION) {
                pst = cnx.prepareStatement("UPDATE TRANSPORTE SET "
                        + "TIPO_TRANSPORTE=?"
                        + " WHERE ID_TRANSPORTE=?");
                //pst.setString(1, id);
                pst.setString(1, transporte);
                pst.setString(2, transportes.get(row).getId_Transporte());

                pst.executeQuery();
                VerTrans(tblTransporte);
            } else {
                JOptionPane.showMessageDialog(null, "LA TABLA NO SE HA ACTUALIZADO");
            }
        } catch (Exception ex) {
            System.out.println(ex.getCause());
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR, TRATE DE SELECCIONAR UNA FILA PRIMERO");
        }

    }

}
