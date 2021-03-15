package control;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;
import java.awt.HeadlessException;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jonathan Ruiz R.  <sguergachi at gmail.com>
 */
public class AdmValidacion {

    AdmConductor adm_Conductor = AdmConductor.getAdmconductor();
    AdmProducto adm_Producto = AdmProducto.getamdproducto();
    AdmCiudadOrigen adm_origen = AdmCiudadOrigen.getAdmOrigen();
    AdmCiudadDestino adm_Destino = AdmCiudadDestino.getAdmDestino();
    AdmTransporte admTransporte = AdmTransporte.getAdmTransporte();

    public void validar(JTextField txtIdConductor, JTextField txtNombreConductor, JTextField txtTipoLicencia, JTextField txtTiempoA,
            JTextField txtIdProducto, JTextField txtNombreProducto, JTextField txtCantidadProductos, JTextField txtNombreCiudadO,
            JTextField txtIdCiudadO, JDateChooser dtcSalida, TimePicker TPSalida, JTextField txtNombreCiudadD, JTextField txtIdCiudadD,
            JDateChooser dtcLlegada, TimePicker TPLlegada, JComboBox<String> cmbTipoTransporte, JTextField txtIDTransporte) {
        try {
            LocalDate FechaSalida = dtcSalida.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate FechaLlegada = dtcLlegada.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (txtIdConductor.getText().isEmpty() || txtNombreConductor.getText().isEmpty() || txtTipoLicencia.getText().isEmpty()
                    || txtTiempoA.getText().isEmpty() || txtIdProducto.getText().isEmpty() || txtNombreProducto.getText().isEmpty()
                    || txtCantidadProductos.getText().isEmpty() || txtNombreCiudadO.getText().isEmpty() || txtIdCiudadO.getText().isEmpty()
                    || dtcSalida.getDateFormatString().isEmpty() || TPSalida.getText().isEmpty() || txtNombreCiudadD.getText().isEmpty()
                    || txtIdCiudadD.getText().isEmpty() || dtcLlegada.getDateFormatString().isEmpty() || TPLlegada.getText().isEmpty()
                    || txtIDTransporte.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "FALTAN CAMPOS DE INGRESAR", "ERROR", JOptionPane.WARNING_MESSAGE);

            } else if (FechaSalida.isAfter(FechaLlegada)) {

                JOptionPane.showMessageDialog(null, "LA FECHA DE SALIDA NO PUEDE SER MAYOR A LA DE LLEGADA", "ERROR", JOptionPane.WARNING_MESSAGE);

            } else if (FechaSalida.equals(FechaLlegada) && TPSalida.getTime().equals(TPLlegada.getTime())) {

                JOptionPane.showMessageDialog(null, "LAS HORAS  NO PUEDEN SER LAS MISMAS CUANDO LAS FECHAS SON IGUALES", "ERROR", JOptionPane.WARNING_MESSAGE);

            } else if (FechaSalida.equals(FechaLlegada) && TPSalida.getTime().isAfter(TPLlegada.getTime())) {

                JOptionPane.showMessageDialog(null, "LA HORA DE SALIDA ES MAYOR A LA DE LLEGADA Y LAS FECHAS SON IGUALES", "ERROR", JOptionPane.WARNING_MESSAGE);

            } else {
                try {

                    adm_origen.guardar(txtNombreCiudadO, txtIdCiudadO, FechaSalida, TPSalida);
                    adm_Destino.guardar(txtNombreCiudadD, txtIdCiudadD, FechaLlegada, TPLlegada);
                    adm_Producto.guardar(txtIdProducto, txtNombreProducto, txtCantidadProductos, txtIdCiudadO, txtIdCiudadD);
                    admTransporte.guardar(txtIDTransporte, cmbTipoTransporte, txtIdProducto);
                    adm_Conductor.guardar(txtIdConductor, txtNombreConductor, txtTipoLicencia, txtTiempoA, txtIDTransporte, txtIdCiudadO, txtIdCiudadD);

                    //JOptionPane.showMessageDialog(null, "DATOS GUARDADOS SATISFACTORIAMENTE");
                } catch (Exception e) {
                    System.out.println(e.getCause());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "FALTAN CAMPOS DE INGRESAR", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

}
