package control;

//LIBRERIAS
import BaseDeDatos.ConexionSqlDeb;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JTextField;
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
        c_Destino.setCiudadDestino(txtNombreCiudadS.getText().toUpperCase());
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
            rs = st.executeQuery("SELECT *   FROM CIUDAD_DESTINO ");
            //System.out.println(rs.getString(1));
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

    //BUSCAR EN LA DATABASE
    public void Buscar(JTable tblDestinos, JTextField txtId, JTextField txtNombre_c, JDateChooser dtcFecha, TimePicker TPLlegada) {
        Date fechaL = Date.valueOf("1999-10-05");
        if (dtcFecha.getDate() != null) {
            LocalDate FS = dtcFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            fechaL = Date.valueOf(FS);
        }
        CiudadDestino C_D = null;
        destinos.clear();
        try {
            cnx = ConexionSqlDeb.getConneccion();
            st = cnx.createStatement();
            rs = st.executeQuery("SELECT *"
                    + " FROM CIUDAD_DESTINO "
                    + " WHERE ID_CIUDAD_DESTINO='" + txtId.getText() + "' OR NOMBRE_CIUDAD_DESTINO='" + txtNombre_c.getText().toUpperCase()
                    + "' OR FECHA_LLEGADA= TO_DATE ('" + fechaL + "', ' YYYY-MM-DD" + "') OR HORA_LLEGADA= '" + TPLlegada.getText() + "'  ");
            while (rs.next()) {
                C_D = new CiudadDestino();
                C_D.setIdCiudadDestino(rs.getString(1));
                C_D.setCiudadDestino(rs.getString(2));
                C_D.setFechaLlegada(rs.getDate(3));
                C_D.setHoraLlegada(rs.getString(4));
                destinos.add(C_D);
            }
            LimpiarTabla(tblDestinos);
            DefaultTableModel tb = (DefaultTableModel) tblDestinos.getModel();
            destinos.forEach(CD -> {
                tb.addRow(new Object[]{CD.getIdCiudadDestino(), CD.getCiudadDestino(), CD.getFechaLlegada(), CD.getHoraLlegada()});
            });
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //ACTUALIZAR LA TABLA Y LA BASE DE DATOS
    public void Actualizar(JTable tblDestinos, JTextField txtNombre_c, JDateChooser dtcFecha, TimePicker TPLlegada) {
        Date fechaP = Date.valueOf("1999-05-10");
        String date = "FECHA";
        String nombre_ciudad = txtNombre_c.getText().toUpperCase();
        String hora_partida = TPLlegada.getText();
        String pattern = "dd-MM-YYYY";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            int row = tblDestinos.getSelectedRow();
            cnx = ConexionSqlDeb.getConneccion();
            int msj = JOptionPane.showConfirmDialog(null, "ESTÁ SEGURO QUE DESEA ACTUALIZAR LA FILA SELECCIONADA");
            if (txtNombre_c.getText().isEmpty()) {
                nombre_ciudad = destinos.get(row).getCiudadDestino();
            }
            if (dtcFecha.getDate() != null) {
                LocalDate FS = dtcFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                fechaP = Date.valueOf(FS);
                date = simpleDateFormat.format(fechaP);

            } else {
                fechaP = destinos.get(row).getFechaLlegada();
                date = simpleDateFormat.format(fechaP);
            }
            if (TPLlegada.getText().isEmpty()) {
                hora_partida = destinos.get(row).getHoraLlegada();
            }

            if (msj == JOptionPane.YES_OPTION) {
                pst = cnx.prepareStatement("UPDATE CIUDAD_DESTINO SET "
                        + "NOMBRE_CIUDAD_DESTINO=?, FECHA_LLEGADA= TO_DATE(?,  'dd-MM-YYYY'), HORA_LLEGADA=?"
                        + " WHERE ID_CIUDAD_DESTINO=?");
                pst.setString(1, nombre_ciudad);
                pst.setString(2, date);
                pst.setString(3, hora_partida);
                pst.setString(4, destinos.get(row).getIdCiudadDestino());
                pst.executeQuery();
                VerDataBase(tblDestinos);
            } else {
                JOptionPane.showMessageDialog(null, "LA TABLA NO SE HA ACTUALIZADO");
            }
        } catch (Exception ex) {
            //System.out.println(ex.getCause());
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR, TRATE DE SELECCIONAR UNA FILA PRIMERO");
        }
    }

    //ELIMINAR FILA SELECCIONADA DE LA BASE DE DATOS
    public void DeleteRow(JTable tblDestinos) {

        DefaultTableModel modelo = (DefaultTableModel) tblDestinos.getModel();
        int row = tblDestinos.getSelectedRow();

        if (row >= 0) {

            int msj = JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO QUE DESEA ELIMINAR LA FILA SELECCIONADA");
            if (msj == JOptionPane.YES_OPTION) {

                //ELIMINAR DE LA BASE DE DATOS
                try {

                    cnx = ConexionSqlDeb.getConneccion();
                    pst = cnx.prepareStatement("DELETE FROM CIUDAD_DESTINO "
                            + " WHERE ID_CIUDAD_DESTINO=?");
                    pst.setString(1, destinos.get(row).getIdCiudadDestino());
                    pst.executeQuery();

                    //ELIMINAR DEL ARRAY
                    destinos.remove(row);
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

    //ELIMINAR TODOS LOS REGISTROS
    public void DeleteAll(JTable tblDestinos) {

        int msj = JOptionPane.showConfirmDialog(null, "ESTÁ SEGURO QUE DESEA ELIMINAR TODOS LOS REGISTROS\nNO PODRÁ RECUPERARLOS DESPUÉS");
        if (msj == JOptionPane.YES_OPTION) {
            destinos.clear();

            try {
                cnx = ConexionSqlDeb.getConneccion();
                st = cnx.createStatement();
                rs = st.executeQuery("DELETE FROM CIUDAD_DESTINO ");
                LimpiarTabla(tblDestinos);
                JOptionPane.showMessageDialog(null, "TODOS LOS REGISTROS DE LA TABLA CONDUCTOR SE HAN ELIMINADO SATISFACTORIAMENTE");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

}
