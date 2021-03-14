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
import javax.swing.JOptionPane;
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
        productos = new ArrayList<>();
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
            productos.forEach(p -> {
                tb.addRow(new Object[]{p.getId_Producto(), p.getNombre_Producto(), p.getCantidad_Producto()});
            });

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //BUSCAR PRODUCTO
    public void Buscar(JTable tblProdcuto, JTextField txtID, JTextField txtNombreP, JTextField txtCantidadP) {

        Producto prod = null;
        productos.clear();
        try {
            cnx = ConexionSqlDeb.getConneccion();
            st = cnx.createStatement();
            rs = st.executeQuery("SELECT *"
                    + " FROM PRODUCTO "
                    + " WHERE ID_PRODUCTO='" + txtID.getText() + "' OR NOMBRE_PRODUCTO='" + txtNombreP.getText() + "' OR CANTIDAD_PRODUCTO= '" + txtCantidadP.getText() + "'  ");
            while (rs.next()) {
                prod = new Producto();
                prod.setId_Producto(rs.getString(1));
                prod.setNombre_Producto(rs.getString(2));
                prod.setCantidad_Producto(rs.getInt(3));
                productos.add(prod);
            }
            LimpiarTabla(tblProdcuto);
            DefaultTableModel tb = (DefaultTableModel) tblProdcuto.getModel();
            productos.forEach(p -> {
                tb.addRow(new Object[]{p.getId_Producto(), p.getNombre_Producto(), p.getCantidad_Producto()});
            });
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    //ACTUALIZAR DATOS DE LA TABLA PRODUCTOS
    public void Actualizar(JTable tblProdcuto, JTextField txtID, JTextField txtNombreP, JTextField txtCantidadP) {
        String id = txtID.getText();
        String nombre = txtNombreP.getText();
        int cantidad = Integer.parseInt(txtCantidadP.getText());
        try {
            int row = tblProdcuto.getSelectedRow();
            cnx = ConexionSqlDeb.getConneccion();
            int msj = JOptionPane.showConfirmDialog(null, "ESTÁ SEGURO QUE DESEA ACTUALIZAR LA FILA SELECCIONADA");

            if (txtID.getText().isEmpty()) {
                id = productos.get(row).getId_Producto();
            }
            if (txtNombreP.getText().isEmpty()) {
                nombre = productos.get(row).getNombre_Producto();
            }
            if (txtCantidadP.getText().isEmpty()) {
                cantidad = productos.get(row).getCantidad_Producto();
            }

            if (msj == JOptionPane.YES_OPTION) {
                pst = cnx.prepareStatement("UPDATE PRODUCTO SET "
                        + "ID_CONDUCTOR=?, NOMBRE=?,APELLIDO=?"
                        + " WHERE ID_PRODUCTO=? OR NOMBRE_PRODUCTO=? OR CANTIDAD_PRODUCTO=?");
                pst.setString(1, id);
                pst.setString(2, nombre);
                pst.setInt(3, cantidad);
                pst.setString(4, productos.get(row).getId_Producto());
                pst.setString(5, productos.get(row).getNombre_Producto());
                pst.setInt(6, productos.get(row).getCantidad_Producto());
                pst.executeQuery();
                VerDataBase(tblProdcuto);
            } else {
                JOptionPane.showMessageDialog(null, "LA TABLA NO SE HA ACTUALIZADO");
            }
        } catch (Exception ex) {
            System.out.println(ex.getCause());
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR, TRATE DE SELECCIONAR UNA FILA PRIMERO");
        }
    }

    public void DeleteRow(JTable tblProdcuto) {

        DefaultTableModel modelo = (DefaultTableModel) tblProdcuto.getModel();
        int row = tblProdcuto.getSelectedRow();

        if (row >= 0) {

            int msj = JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO QUE DESEA ELIMINAR LA FILA SELECCIONADA");
            if (msj == JOptionPane.YES_OPTION) {

                //ELIMINAR DE LA BASE DE DATOS
                try {

                    cnx = ConexionSqlDeb.getConneccion();
                    pst = cnx.prepareStatement("DELETE FROM PRODUCTO "
                            + " WHERE ID_PRODUCTO=?");
                    pst.setString(1, productos.get(row).getId_Producto());
                    pst.executeQuery();

                    //ELIMINAR DEL ARRAY
                    productos.remove(row);
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

    public void DeleteAll(JTable tblProdcuto) {

        int msj = JOptionPane.showConfirmDialog(null, "ESTÁ SEGURO QUE DESEA ELIMINAR TODOS LOS REGISTROS\nNO PODRÁ RECUPERARLOS DESPUÉS");
        if (msj == JOptionPane.YES_OPTION) {
            productos.clear();

            try {
                cnx = ConexionSqlDeb.getConneccion();
                st = cnx.createStatement();
                rs = st.executeQuery("DELETE FROM PRODUCTO ");
                LimpiarTabla(tblProdcuto);
                JOptionPane.showMessageDialog(null, "TODOS LOS REGISTROS DE LA TABLA PRODUCTOS SE HAN ELIMINADO SATISFACTORIAMENTE");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

}
