package control;

//LIBRERIAS
import BaseDeDatos.ConexionSqlDeb;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JTextField;
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
import model.CiudadDestino;

/**
 *
 * @author Jonathan Ruiz R. Todos los derechos Reservados
 * xd<sguergachi at gmail.com>
 */
public class AdmCiudadDestino {

    //VARIABLES GLOBALES
    Connection cnx = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ArrayList<CiudadDestino> destinos = null;
    CiudadDestino c_Destino = null;
    private static AdmCiudadDestino admDestino = null;

    //CONSTRUCTOR
    public AdmCiudadDestino() {
        destinos = new ArrayList<>();
    }

    //SETTERES AND GETTERS
    public ArrayList<CiudadDestino> getDestinos() {
        return destinos;
    }

    public void setDestinos(ArrayList<CiudadDestino> destinos) {
        this.destinos = destinos;
    }

    public static AdmCiudadDestino getAdmDestino() {
        if (admDestino == null) {
            admDestino = new AdmCiudadDestino();
        }
        return admDestino;
    }

    public static void setAdmDestino(AdmCiudadDestino admDestino) {
        AdmCiudadDestino.admDestino = admDestino;
    }

    //GUARDAR DATOS EN LA BASE DE DATOS
    public void guardar(JTextField txtNombreCiudadS, JTextField txtIdCiudadS, LocalDate dtcLLegada, TimePicker TPSalida) {

        //String f = dtcSalida + "";
        Date fechaLLegada = Date.valueOf(dtcLLegada);

        c_Destino = new CiudadDestino();
        c_Destino.setIdCiudadDestino(txtIdCiudadS.getText());
        c_Destino.setCiudadDestino(txtNombreCiudadS.getText());
        c_Destino.setFechaLlegada(fechaLLegada);
        c_Destino.setHoraLlegada(TPSalida.getText());
        //conductores.add(conductor);
        //VerDataBase();
        //GuardarDataBase(conductor);
        try {
            cnx = ConexionSqlDeb.getConneccion();
            pst = cnx.prepareStatement("INSERT INTO CIUDAD_DESTINO("
                    + " ID_CIUDAD_DESTINO,NOMBRE_CIUDAD_DESTINO,FECHA_LLEGADA,HORA_LLEGADA) "
                    + "VALUES(?,?,?,?)");
            pst.setString(1, c_Destino.getIdCiudadDestino());
            pst.setString(2, c_Destino.getCiudadDestino());
            pst.setDate(3, c_Destino.getFechaLlegada());
            pst.setString(4, c_Destino.getHoraLlegada());
            pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AdmConductor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error en insert");
        }

    }

    //LIMPIAR TABLA
    public void LimpiarTabla(JTable tblDestino) {
        DefaultTableModel tb = (DefaultTableModel) tblDestino.getModel();
        for (int i = tb.getRowCount() - 1; i >= 0; i--) {
            tb.removeRow(i);
        }
    }

    //CREANDO EL ARRAY PARA LA LISTARLOS EN LA TABLA
    public void VerDataBase(JTable tblDestino) {

        LimpiarTabla(tblDestino);

        destinos = new ArrayList<>();

        try {

            cnx = ConexionSqlDeb.getConneccion();
            st = cnx.createStatement();
            rs = st.executeQuery("SELECT *"
                    + "     FROM CIUDAD_DESTINO ");

            while (rs.next()) {

                c_Destino = new CiudadDestino();
                c_Destino.setIdCiudadDestino(rs.getString(1));
                c_Destino.setCiudadDestino(rs.getString(2));
                c_Destino.setFechaLlegada(rs.getDate(3));
                c_Destino.setHoraLlegada(rs.getString(4));
                destinos.add(c_Destino);

            }

            DefaultTableModel tb = (DefaultTableModel) tblDestino.getModel();
            destinos.forEach(o -> {
                tb.addRow(new Object[]{o.getIdCiudadDestino(), o.getCiudadDestino(), o.getFechaLlegada(), o.getHoraLlegada()});
            });

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
