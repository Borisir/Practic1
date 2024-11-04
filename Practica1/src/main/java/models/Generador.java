package models;

public class Generador {
    private Integer id;
    private String modelo;
    private float costo;
    private float consumoPorHora;
    private float podruccionEnergia;
    private String uso;   
    private String numeroSerie;

    public double getCosto() {
        return costo;
    }
    public void setCosto(float costo) {
        this.costo = costo;
    }
    public double getConsumoPorHora() {
        return consumoPorHora;
    }
    public void setConsumoPorHora(float consumoPorHora) {
        this.consumoPorHora = consumoPorHora;
    }
    public double getPodruccionEnergia() {
        return podruccionEnergia;
    }
    public void setPodruccionEnergia(float podruccionEnergia) {
        this.podruccionEnergia = podruccionEnergia;
    }
    public String getUso() {
        return uso;
    }
    public void setUso(String uso) {
        this.uso = uso;
    }
    public String getNumeroSerie() {
        return numeroSerie;
    }
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public Generador(){
        this.id = 0;
        this.modelo = "";
        this.costo = 0;
        this.consumoPorHora = 0;
        this.podruccionEnergia = 0;
        this.uso = "";
        this.numeroSerie = "";
    }
    public Generador(Integer id, String modelo, float costo, float consumoPorHora, float podruccionEnergia, String uso, String numeroSerie){
        this.id = id;
        this.modelo = modelo;
        this.costo = costo;
        this.consumoPorHora = consumoPorHora;
        this.podruccionEnergia = podruccionEnergia;
        this.uso = uso;
        this.numeroSerie = numeroSerie;
    }
    
}
