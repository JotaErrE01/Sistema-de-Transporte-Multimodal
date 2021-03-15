package control;

import BaseDeDatos.ConexionSqlDeb;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JTextField;
import model.CiudadOrigen;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jonathan Ruiz R. <sguergachi at gmail.com>
 */
public class AdmCiudadOrigen {

    Connection cnx = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ArrayList<CiudadOrigen> origenes = null;
    CiudadOrigen c_origen = null;
    private static AdmCiudadOrigen admOrigen = null;

    private AdmCiudadOrigen() {
        origenes = new ArrayList<CiudadOrigen>();
    }

    public ArrayList<CiudadOrigen> getOrigenes() {
        return origenes;
    }

    public void setOrigenes(ArrayList<CiudadOrigen> origenes) {
        this.origenes = origenes;
    }

    public static AdmCiudadOrigen getAdmOrigen() {
        if (admOrigen == null) {
            admOrigen = new AdmCiudadOrigen();
        }
        return admOrigen;
    }

    //GUARDAR DATOS EN LA BASE DE DATOS
    public void guardar(JTextField txtNombreCiudadO, JTextField txtIdCiudadO, LocalDate dtcSalida, TimePicker TPSalida) {

        //String f = dtcSalida + "";
        Date fechaS = Date.valueOf(dtcSalida);

        c_origen = new CiudadOrigen();
        c_origen.setIdCiudadOrigen(txtIdCiudadO.getText());
        c_origen.setCiudadOrigen(txtNombreCiudadO.getText().toUpperCase());
        c_origen.setFechaSalida(fechaS);
        c_origen.setHoraSalida(TPSalida.getText());
        //conductores.add(conductor);
        //VerDataBase();
        //GuardarDataBase(conductor);
        try {
            cnx = ConexionSqlDeb.getConneccion();
            pst = cnx.prepareStatement("INSERT INTO CIUDAD_ORIGEN("
                    + " ID_CIUDAD_ORIGEN,NOMBRE_CIUDAD_ORIGEN,FECHA_SALIDA,HORA_SALIDA) "
                    + "VALUES(?,?,?,?)");
            pst.setString(1, c_origen.getIdCiudadOrigen());
            pst.setString(2, c_origen.getCiudadOrigen());
            pst.setDate(3, c_origen.getFechaSalida());
            pst.setString(4, c_origen.getHoraSalida());
            pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AdmConductor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error en insert");
        }

    }

    //LIMPIAR TABLA
    public void LimpiarTabla(JTable tblOrigen) {
        DefaultTableModel tb = (DefaultTableModel) tblOrigen.getModel();
        for (int i = tb.getRowCount() - 1; i >= 0; i--) {
            tb.removeRow(i);
        }
    }

    //CREANDO EL ARRAY PARA LA LISTARLOS EN LA TABLA
    public void VerDataBase(JTable tblOrigen) {

        LimpiarTabla(tblOrigen);

        origenes = new ArrayList<>();

        try {

            cnx = ConexionSqlDeb.getConneccion();
            st = cnx.createStatement();
            rs = st.executeQuery("SELECT *"
                    + "     FROM CIUDAD_ORIGEN ");

            while (rs.next()) {

                c_origen = new CiudadOrigen();
                c_origen.setIdCiudadOrigen(rs.getString(1));
                c_origen.setCiudadOrigen(rs.getString(2));
                c_origen.setFechaSalida(rs.getDate(3));
                c_origen.setHoraSalida(rs.getString(4));
                origenes.add(c_origen);

            }

            DefaultTableModel tb = (DefaultTableModel) tblOrigen.getModel();
            origenes.forEach(o -> {
                tb.addRow(new Object[]{o.getIdCiudadOrigen(), o.getCiudadOrigen(), o.getFechaSalida(), o.getHoraSalida()});
            });

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //ELIMINAR FILA
    public void DeleteRow(JTable tblOrigen) {

        DefaultTableModel modelo = (DefaultTableModel) tblOrigen.getModel();
        int row = tblOrigen.getSelectedRow();

        if (row >= 0) {

            int msj = JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO QUE DESEA ELIMINAR LA FILA SELECCIONADA");
            if (msj == JOptionPane.YES_OPTION) {

                //ELIMINAR DE LA BASE DE DATOS
                try {

                    cnx = ConexionSqlDeb.getConneccion();
                    pst = cnx.prepareStatement("DELETE FROM CIUDAD_ORIGEN "
                            + " WHERE ID_CIUDAD_ORIGEN=?");
                    pst.setString(1, origenes.get(row).getIdCiudadOrigen());
                    pst.executeQuery();

                    //ELIMINAR DEL ARRAY
                    origenes.remove(row);
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

    public void Buscar(JTable tblOrigen, JTextField txtId, JTextField txtNombreCiudad, JDateChooser dtcPartida, TimePicker TPpartida) {
        Date fechaS = Date.valueOf("1999-10-05");
        if (dtcPartida.getDate() != null) {
            LocalDate FS = dtcPartida.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            fechaS = Date.valueOf(FS);
        }
        CiudadOrigen C_O = null;
        origenes.clear();
        try {
            cnx = ConexionSqlDeb.getConneccion();
            st = cnx.createStatement();
            rs = st.executeQuery("SELECT *"
                    + " FROM CIUDAD_ORIGEN "
                    + " WHERE ID_CIUDAD_ORIGEN='" + txtId.getText() + "' OR NOMBRE_CIUDAD_ORIGEN='" + txtNombreCiudad.getText().toUpperCase()
                    + "' OR FECHA_SALIDA= TO_DATE ('" + fechaS + "', ' YYYY-MM-DD" + "') OR HORA_SALIDA= '" + TPpartida.getText() + "'  ");
            while (rs.next()) {
                C_O = new CiudadOrigen();
                C_O.setIdCiudadOrigen(rs.getString(1));
                C_O.setCiudadOrigen(rs.getString(2));
                C_O.setFechaSalida(rs.getDate(3));
                C_O.setHoraSalida(rs.getString(4));
                origenes.add(C_O);
            }
            LimpiarTabla(tblOrigen);
            DefaultTableModel tb = (DefaultTableModel) tblOrigen.getModel();
            origenes.forEach(CO -> {
                tb.addRow(new Object[]{CO.getIdCiudadOrigen(), CO.getCiudadOrigen(), CO.getFechaSalida(), CO.getHoraSalida()});
            });
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void Actualizar(JTable tblOrigen, JTextField txtNombreCiudad, JDateChooser dtcPartida, TimePicker TPpartida) {
        Date fechaS = Date.valueOf("1999-05-10");
        String date = "FECHA";
        String nombre_ciudad = txtNombreCiudad.getText().toUpperCase();
        String hora_partida = TPpartida.getText();
        String pattern = "dd-MM-YYYY";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            int row = tblOrigen.getSelectedRow();
            cnx = ConexionSqlDeb.getConneccion();
            int msj = JOptionPane.showConfirmDialog(null, "ESTÁ SEGURO QUE DESEA ACTUALIZAR LA FILA SELECCIONADA");

            if (txtNombreCiudad.getText().isEmpty()) {
                nombre_ciudad = origenes.get(row).getCiudadOrigen();
            }
            if (dtcPartida.getDate() != null) {
                LocalDate FS = dtcPartida.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                fechaS = Date.valueOf(FS);
                date = simpleDateFormat.format(fechaS);

            } else {
                fechaS = origenes.get(row).getFechaSalida();
                date = simpleDateFormat.format(fechaS);
            }
            if (TPpartida.getText().isEmpty()) {
                hora_partida = origenes.get(row).getHoraSalida();
            }

            if (msj == JOptionPane.YES_OPTION) {
                pst = cnx.prepareStatement("UPDATE CIUDAD_ORIGEN SET "
                        + "NOMBRE_CIUDAD_ORIGEN=?, FECHA_SALIDA= TO_DATE(?,  'dd-MM-YYYY'), HORA_SALIDA=?"
                        + " WHERE ID_CIUDAD_ORIGEN=?");
                pst.setString(1, nombre_ciudad);
                pst.setString(2, date);
                pst.setString(3, hora_partida);
                pst.setString(4, origenes.get(row).getIdCiudadOrigen());
                pst.executeQuery();
                VerDataBase(tblOrigen);
            } else {
                JOptionPane.showMessageDialog(null, "LA TABLA NO SE HA ACTUALIZADO");
            }
        } catch (Exception ex) {
            //System.out.println(ex.getCause());
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR, TRATE DE SELECCIONAR UNA FILA PRIMERO");
        }

    }

    public void DeleteAll(JTable tblOrigen) {
        int msj = JOptionPane.showConfirmDialog(null, "ESTÁ SEGURO QUE DESEA ELIMINAR TODOS LOS REGISTROS\nNO PODRÁ RECUPERARLOS DESPUÉS");
        if (msj == JOptionPane.YES_OPTION) {
            origenes.clear();

            try {
                cnx = ConexionSqlDeb.getConneccion();
                st = cnx.createStatement();
                rs = st.executeQuery("DELETE FROM CIUDAD_ORIGEN ");
                LimpiarTabla(tblOrigen);
                JOptionPane.showMessageDialog(null, "TODOS LOS REGISTROS DE LA TABLA CONDUCTOR SE HAN ELIMINADO SATISFACTORIAMENTE");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
