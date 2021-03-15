package control;

import BaseDeDatos.ConexionSqlDeb;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Conductor;

public class AdmConductor {

    Connection cnx = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ArrayList<Conductor> conductores = null;
    Conductor conductor = null;
    private static AdmConductor admconductor = null;

    private AdmConductor() {
        conductores = new ArrayList<>();
    }

    public ArrayList<Conductor> getConductores() {
        return conductores;
    }

    public void setConductores(ArrayList<Conductor> conductores) {
        this.conductores = conductores;
    }

    public static AdmConductor getAdmconductor() {
        if (admconductor == null) {
            admconductor = new AdmConductor();
        }

        return admconductor;
    }

    public static void setAdmconductor(AdmConductor admconductor) {
        AdmConductor.admconductor = admconductor;
    }

    //GUARDAR DATOS EN LA TABLA SQL
    public void guardar(JTextField txtIdConductor, JTextField txtNombreConductor, JTextField txtTipoLicencia,
            JTextField txtTiempoA, JTextField txtIDTransporte, JTextField txtIDOrigen, JTextField txtIdDestino) {

        conductor = new Conductor();
        conductor.setId_conductor(txtIdConductor.getText());
        conductor.setNombre_Conductor(txtNombreConductor.getText().toUpperCase());
        conductor.setTipoLicencia(txtTipoLicencia.getText().toUpperCase());
        conductor.setTiempoA(txtTiempoA.getText() + " HORAS");
        conductor.setTipo_transporte(txtIDTransporte.getText());
        conductor.setIDCiudadO(txtIDOrigen.getText());
        conductor.setIDCiudadD(txtIdDestino.getText());

        try {
            cnx = ConexionSqlDeb.getConneccion();
            pst = cnx.prepareStatement("INSERT INTO CONDUCTOR("
                    + " ID_CONDUCTOR,NOMBRE_CONDUCTOR,TIPO_LICENCIA,DURACION_ACTIVIDAD,ID_TRANSPORTE,ID_CIUDAD_ORIGEN,ID_CIUDAD_DESTINO) "
                    + "VALUES(?,?,?,?,?,?,?)");
            pst.setString(1, conductor.getId_conductor());
            pst.setString(2, conductor.getNombre_Conductor());
            pst.setString(3, conductor.getTipoLicencia());
            pst.setString(4, conductor.getTiempoA());
            pst.setString(5, conductor.getTipo_transporte());
            pst.setString(6, conductor.getIDCiudadO());
            pst.setString(7, conductor.getIDCiudadD());
            pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AdmConductor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error en insert");
        }
    }

    //LIMPIAR TABLA
    public void LimpiarTabla(JTable tblConductor) {
        DefaultTableModel tb = (DefaultTableModel) tblConductor.getModel();
        for (int i = tb.getRowCount() - 1; i >= 0; i--) {
            tb.removeRow(i);
        }
    }

    //CREANDO EL ARRAY PARA LA LISTARLOS EN LA TABLA
    public void VerDataBase(JTable tblConductor) {

        LimpiarTabla(tblConductor);

        conductores = new ArrayList<>();
        try {

            cnx = ConexionSqlDeb.getConneccion();
            st = cnx.createStatement();
            rs = st.executeQuery("SELECT *"
                    + "     FROM CONDUCTOR ");

            while (rs.next()) {

                conductor = new Conductor();
                conductor.setId_conductor(rs.getString(1));
                conductor.setNombre_Conductor(rs.getString(2));
                conductor.setTipoLicencia(rs.getString(3));
                conductor.setTiempoA(rs.getString(4));
                conductor.setTipo_transporte(rs.getString(5));
                conductor.setIDCiudadO(rs.getString(6));
                conductor.setIDCiudadD(rs.getString(7));
                conductores.add(conductor);

            }

            DefaultTableModel tb = (DefaultTableModel) tblConductor.getModel();
            conductores.forEach(c -> {
                tb.addRow(new Object[]{c.getId_conductor(), c.getNombre_Conductor(), c.getTipoLicencia(),
                    c.getTiempoA(), c.getTipo_transporte(), c.getIDCiudadO(), c.getIDCiudadD()});
            });

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //ELIMINAR FILA SELECCIONADA
    public void DeleteRow(JTable tblConductor) {

        DefaultTableModel modelo = (DefaultTableModel) tblConductor.getModel();
        int row = tblConductor.getSelectedRow();

        if (row >= 0) {

            int msj = JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO QUE DESEA ELIMINAR LA FILA SELECCIONADA");
            if (msj == JOptionPane.YES_OPTION) {

                //ELIMINAR DE LA BASE DE DATOS
                try {

                    cnx = ConexionSqlDeb.getConneccion();
                    pst = cnx.prepareStatement("DELETE FROM CONDUCTOR "
                            + " WHERE ID_CONDUCTOR=?");
                    pst.setString(1, conductores.get(row).getId_conductor());
                    pst.executeQuery();

                    //ELIMINAR DEL ARRAY
                    conductores.remove(row);
                    modelo.removeRow(row);
                    JOptionPane.showMessageDialog(null, "SE HAN ELIMINADO LOS DATOS SATISFACTORIAMENTE");
                } catch (Exception e) {
                    System.out.println(e.getCause());
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "NO HA SELECCIONADO FILA PARA ELIMINAR", "WARNING", JOptionPane.WARNING_MESSAGE);
        }

    }

    //ACTUALIZAR LOS DATOS
    public void Actualizar(JTable tblConductor, JTextField txtID, JTextField txtNombreC, JTextField txtTipoLicencia) {
        String id = txtID.getText();
        String nombre = txtNombreC.getText().toUpperCase();
        String licencia = txtTipoLicencia.getText().toUpperCase();
        try {
            int row = tblConductor.getSelectedRow();
            cnx = ConexionSqlDeb.getConneccion();
            int msj = JOptionPane.showConfirmDialog(null, "ESTÁ SEGURO QUE DESEA ACTUALIZAR LA FILA SELECCIONADA");

            if (txtID.getText().isEmpty()) {
                id = conductores.get(row).getId_conductor();
            }
            if (txtNombreC.getText().isEmpty()) {
                nombre = conductores.get(row).getNombre_Conductor();
            }
            if (txtTipoLicencia.getText().isEmpty()) {
                licencia = conductores.get(row).getTiempoA();
            }

            if (msj == JOptionPane.YES_OPTION) {
                pst = cnx.prepareStatement("UPDATE CONDUCTOR SET "
                        + "NOMBRE_CONDUCTOR=?,TIPO_LICENCIA=?"
                        + " WHERE ID_CONDUCTOR=?");
                //pst.setString(1, id);
                pst.setString(1, nombre);
                pst.setString(2, licencia);
                pst.setString(3, conductores.get(row).getId_conductor());
                /*pst.setString(5, conductores.get(row).getNombre_Conductor());
                pst.setString(6, conductores.get(row).getTipoLicencia());*/
                //pst.setString(7, conductores.get(row).getTiempoA());
                pst.executeQuery();
                VerDataBase(tblConductor);
            } else {
                JOptionPane.showMessageDialog(null, "LA TABLA NO SE HA ACTUALIZADO");
            }
        } catch (Exception ex) {
            System.out.println(ex.getCause());
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR, TRATE DE SELECCIONAR UNA FILA PRIMERO");
        }

    }

    //BUSCAR DATOS EN LA BASE DE DATOS
    public void Buscar(JTable tblConductor, JTextField txtID, JTextField txtNombreC, JTextField txtLicencia) {

        Conductor con = null;
        conductores.clear();
        try {
            cnx = ConexionSqlDeb.getConneccion();
            st = cnx.createStatement();
            rs = st.executeQuery("SELECT *"
                    + " FROM CONDUCTOR "
                    + " WHERE ID_CONDUCTOR='" + txtID.getText() + "' OR NOMBRE_CONDUCTOR='" + txtNombreC.getText().toUpperCase()
                    + "' OR TIPO_LICENCIA= '" + txtLicencia.getText().toUpperCase() + "'  ");
            while (rs.next()) {
                con = new Conductor();
                con.setId_conductor(rs.getString(1));
                con.setNombre_Conductor(rs.getString(2));
                con.setTipoLicencia(rs.getString(3));
                con.setTiempoA(rs.getString(4));
                con.setTipo_transporte(rs.getString(5));
                con.setIDCiudadO(rs.getString(6));
                con.setIDCiudadD(rs.getString(7));
                conductores.add(con);
            }
            LimpiarTabla(tblConductor);
            DefaultTableModel tb = (DefaultTableModel) tblConductor.getModel();
            conductores.forEach(c -> {
                tb.addRow(new Object[]{c.getId_conductor(), c.getNombre_Conductor(), c.getTipoLicencia(),
                    c.getTiempoA(), c.getTipo_transporte(), c.getIDCiudadO(), c.getIDCiudadD()});
            });
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    // ELIMINAR TODOS LOS REGISTROS
    public void DeleteAll(JTable tblConductor) {
        int msj = JOptionPane.showConfirmDialog(null, "ESTÁ SEGURO QUE DESEA ELIMINAR TODOS LOS REGISTROS\nNO PODRÁ RECUPERARLOS DESPUÉS");
        if (msj == JOptionPane.YES_OPTION) {
            conductores.clear();

            try {
                cnx = ConexionSqlDeb.getConneccion();
                st = cnx.createStatement();
                rs = st.executeQuery("DELETE FROM CONDUCTOR ");
                LimpiarTabla(tblConductor);
                JOptionPane.showMessageDialog(null, "TODOS LOS REGISTROS DE LA TABLA CONDUCTOR SE HAN ELIMINADO SATISFACTORIAMENTE");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
