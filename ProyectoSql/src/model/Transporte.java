package model;

public class Transporte {

    /*Variables*/
    private String id_Transporte;
    private String Tipo_Transporte;
    private String Id_Producto;

    public Transporte() {
    }

    public String getId_Transporte() {
        return id_Transporte;
    }

    public void setId_Transporte(String id_Transporte) {
        this.id_Transporte = id_Transporte;
    }

    public String getTipo_Transporte() {
        return Tipo_Transporte;
    }

    public void setTipo_Transporte(String Tipo_Transporte) {
        this.Tipo_Transporte = Tipo_Transporte;
    }

    public String getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(String Id_Producto) {
        this.Id_Producto = Id_Producto;
    }

}
