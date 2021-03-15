package model;

public class Conductor {

    private String nombre_Conductor;
    private String TipoLicencia;
    private String Id_conductor;
    private String TiempoA;
    private String IDCiudadO;
    private String IDCiudadD;
    private String Tipo_transporte;

    public Conductor() {

    }

    public String getNombre_Conductor() {
        return nombre_Conductor;
    }

    public void setNombre_Conductor(String nombre_Conductor) {
        this.nombre_Conductor = nombre_Conductor;
    }

    public String getId_conductor() {
        return Id_conductor;
    }

    public void setId_conductor(String Id_conductor) {
        this.Id_conductor = Id_conductor;
    }

    public String getTipoLicencia() {
        return TipoLicencia;
    }

    public void setTipoLicencia(String TipoLicencia) {
        this.TipoLicencia = TipoLicencia;
    }

    public String getTiempoA() {
        return TiempoA;
    }

    public void setTiempoA(String TiempoA) {
        this.TiempoA = TiempoA;
    }

    public String getIDCiudadO() {
        return IDCiudadO;
    }

    public void setIDCiudadO(String IDCiudadO) {
        this.IDCiudadO = IDCiudadO;
    }

    public String getIDCiudadD() {
        return IDCiudadD;
    }

    public void setIDCiudadD(String IDCiudadD) {
        this.IDCiudadD = IDCiudadD;
    }

    public String getTipo_transporte() {
        return Tipo_transporte;
    }

    public void setTipo_transporte(String Tipo_transporte) {
        this.Tipo_transporte = Tipo_transporte;
    }

}
