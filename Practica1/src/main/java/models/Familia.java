package models;

public class Familia {
    private int id;
    private String nombre;
    private int numeroMiembros;
    private String generadorId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getNumeroMiembros() {
        return numeroMiembros;
    }
    public void setNumeroMiembros(int numeroMiembros) {
        this.numeroMiembros = numeroMiembros;
    }
    public String getGeneradorId() {
        return generadorId;
    }
    public void setGeneradorId(String generadorId) {
        this.generadorId = generadorId;
    }
    public Familia(){
        this.id = 0;
        this.nombre = "";
        this.numeroMiembros = 0;
        this.generadorId = "";
    }
    public Familia(int id, String nombre, int numeroMiembros, String generadorId){
        this.id = id;
        this.nombre = nombre;
        this.numeroMiembros = numeroMiembros;
        this.generadorId = generadorId;
    }
    
    
}
