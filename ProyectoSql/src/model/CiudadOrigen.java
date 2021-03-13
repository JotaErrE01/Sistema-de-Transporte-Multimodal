package model;

import java.sql.Date;

public class CiudadOrigen {

    private String CiudadOrigen;
    private String IdCiudadOrigen;
    private Date FechaSalida;
    private String HoraSalida;

    public CiudadOrigen() {
    }

    public String getCiudadOrigen() {
        return CiudadOrigen;
    }

    public void setCiudadOrigen(String CiudadOrigen) {
        this.CiudadOrigen = CiudadOrigen;
    }

    public String getIdCiudadOrigen() {
        return IdCiudadOrigen;
    }

    public void setIdCiudadOrigen(String IdCiudadOrigen) {
        this.IdCiudadOrigen = IdCiudadOrigen;
    }

    public Date getFechaSalida() {
        return FechaSalida;
    }

    public void setFechaSalida(Date FechaSalida) {
        this.FechaSalida = FechaSalida;
    }

    public String getHoraSalida() {
        return HoraSalida;
    }

    public void setHoraSalida(String HoraSalida) {
        this.HoraSalida = HoraSalida;
    }

}
