package models;

public class Transaccion {
    private int id;
    private String tipo;
    private String fecha;
    private String familiaId;
    private String generadorId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(String familiaId) {
        this.familiaId = familiaId;
    }

    public String getGeneradorId() {
        return generadorId;
    }

    public void setGeneradorId(String generadorId) {
        this.generadorId = generadorId;
    }

    public Transaccion(){
        this.id = 0;
        this.tipo = "";
        this.fecha = "";
        this.familiaId = "";
        this.generadorId = "";
    }
    public Transaccion(int id, String tipo, String fecha, String familiaId, String generadorId){
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.familiaId = familiaId;
        this.generadorId = generadorId;
    }

    
}
