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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Producto;

public class AdmProducto {

    Connection cnx = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ArrayList<Producto> productos = null;
    Producto producto = null;
    private static AdmProducto admproducto = null;

    private AdmProducto() {
        productos = new ArrayList<Producto>();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public static AdmProducto getamdproducto() {
        if (admproducto == null) {
            admproducto = new AdmProducto();
        }

        return admproducto;
    }

    public static void setAdmproducto(AdmProducto admproducto) {
        AdmProducto.admproducto = admproducto;
    }

    //GUARDAR DATOS
    public void guardar(JTextField txtIdProducto, JTextField txtNombreProducto, JTextField txtCantidadProducto) {

        producto = new Producto();
        producto.setId_Producto(txtIdProducto.getText());
        producto.setNombre_Producto(txtNombreProducto.getText());
        producto.setCantidad_Producto(Integer.parseInt(txtCantidadProducto.getText()));
        //conductores.add(conductor);
        //VerDataBase();
        //GuardarDataBase(conductor);
        try {
            cnx = ConexionSqlDeb.getConneccion();
            pst = cnx.prepareStatement("INSERT INTO PRODUCTO("
                    + " ID_PRODUCTO,NOMBRE_PRODUCTO,CANTIDAD_PRODUCTO) "
                    + "VALUES(?,?,?)");
            pst.setString(1, producto.getId_Producto());
            pst.setString(2, producto.getNombre_Producto());
            pst.setInt(3, producto.getCantidad_Producto());
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
    public void VerDataBase(JTable tblProducto) {

        LimpiarTabla(tblProducto);

        productos = new ArrayList<Producto>();

        try {

            cnx = ConexionSqlDeb.getConneccion();
            st = cnx.createStatement();
            rs = st.executeQuery("SELECT *"
                    + "     FROM PRODUCTO ");

            while (rs.next()) {

                producto = new Producto();
                producto.setId_Producto(rs.getString(1));
                producto.setNombre_Producto(rs.getString(2));
                producto.setCantidad_Producto(rs.getInt(3));
                productos.add(producto);

            }

            DefaultTableModel tb = (DefaultTableModel) tblProducto.getModel();
            for (Producto p : productos) {

                tb.addRow(new Object[]{p.getId_Producto(), p.getNombre_Producto(), p.getCantidad_Producto()});

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
