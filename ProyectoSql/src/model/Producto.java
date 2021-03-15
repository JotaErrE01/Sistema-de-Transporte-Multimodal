package model;

public class Producto {

    private String Id_Producto;
    private int Cantidad_Producto;
    private String Nombre_Producto;
    private String ID_ciudadOrigen;
    private String Id_ciudadDestino;

    public Producto() {
    }

    public String getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(String Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

    public int getCantidad_Producto() {
        return Cantidad_Producto;
    }

    public void setCantidad_Producto(int Cantidad_Producto) {
        this.Cantidad_Producto = Cantidad_Producto;
    }

    public String getNombre_Producto() {
        return Nombre_Producto;
    }

    public void setNombre_Producto(String Nombre_Producto) {
        this.Nombre_Producto = Nombre_Producto;
    }

    public String getID_ciudadOrigen() {
        return ID_ciudadOrigen;
    }

    public void setID_ciudadOrigen(String ID_ciudadOrigen) {
        this.ID_ciudadOrigen = ID_ciudadOrigen;
    }

    public String getId_ciudadDestino() {
        return Id_ciudadDestino;
    }

    public void setId_ciudadDestino(String Id_ciudadDestino) {
        this.Id_ciudadDestino = Id_ciudadDestino;
    }

}
