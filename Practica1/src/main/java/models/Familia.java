package models;

public class Familia {
    private Integer id;
    private String nombre;
    private Integer numeroMiembros;
    private String generadorasociado;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getNumeroMiembros() {
        return numeroMiembros;
    }
    public void setNumeroMiembros(Integer numeroMiembros) {
        this.numeroMiembros = numeroMiembros;
    }
    public String getgeneradorasociado() {
        return generadorasociado;
    }
    public void setgeneradorasociado(String generadorasociado) {
        this.generadorasociado = generadorasociado;
    }
    public Familia(){
        this.id = 0;
        this.nombre = "";
        this.numeroMiembros = 0;
        this.generadorasociado = "";
    }
    public Familia(Integer id, String nombre, Integer numeroMiembros, String generadorasociado){
        this.id = id;
        this.nombre = nombre;
        this.numeroMiembros = numeroMiembros;
        this.generadorasociado = generadorasociado;
    }
    
    
}
