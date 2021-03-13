package control;

import BaseDeDatos.ConexionSqlDeb;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JTextField;
import model.CiudadOrigen;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        c_origen.setCiudadOrigen(txtNombreCiudadO.getText());
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

}
