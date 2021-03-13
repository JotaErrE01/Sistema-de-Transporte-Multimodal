package model;

public class Conductor {

    private String nombre_Conductor;
    private String apellido_Conductor;
    private String Id_conductor;

    /*
    public Conductor(String nombre_Conductor, String apellido_Conductor, String Id_conductor) {
        this.nombre_Conductor = nombre_Conductor;
        this.apellido_Conductor = apellido_Conductor;
        this.Id_conductor = Id_conductor;
    }*/

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

    public String getApellido_Conductor() {
        return apellido_Conductor;
    }

    public void setApellido_Conductor(String apellido_Conductor) {
        this.apellido_Conductor = apellido_Conductor;
    }

}
