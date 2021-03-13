package model;

import java.sql.Date;

public class CiudadDestino {

    private String CiudadDestino;
    private String IdCiudadDestino;
    private Date FechaLlegada;
    private String HoraLlegada;

    public CiudadDestino() {

    }

    public String getCiudadDestino() {
        return CiudadDestino;
    }

    public void setCiudadDestino(String CiudadDestino) {
        this.CiudadDestino = CiudadDestino;
    }

    public String getIdCiudadDestino() {
        return IdCiudadDestino;
    }

    public void setIdCiudadDestino(String IdCiudadDestino) {
        this.IdCiudadDestino = IdCiudadDestino;
    }

    public Date getFechaLlegada() {
        return FechaLlegada;
    }

    public void setFechaLlegada(Date FechaLlegada) {
        this.FechaLlegada = FechaLlegada;
    }

    public String getHoraLlegada() {
        return HoraLlegada;
    }

    public void setHoraLlegada(String HoraLlegada) {
        this.HoraLlegada = HoraLlegada;
    }

}
