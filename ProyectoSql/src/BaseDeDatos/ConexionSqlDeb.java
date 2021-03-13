package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ConexionSqlDeb {

    public static Connection getConneccion() {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");  //DRIVER DE SQL DEVELOPER
            String myDB = "jdbc:oracle:thin:@localhost:1521:XE";//URL DE SQL DEVELOPER
            String usuario = "system";
            String password = "jotaerre01";
            Connection cnx = DriverManager.getConnection(myDB, usuario, password);
            return cnx;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionSqlDeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
